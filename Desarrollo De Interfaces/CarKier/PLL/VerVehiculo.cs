using CarKier.DAL;
using CarKier.Modelo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.TextBox;

namespace CarKier.PLL
{
    public partial class VerVehiculo : Form
    {
        private static vehiculos _vehiculo;
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static UsuariosDal usuDal = new UsuariosDal();
        private static EmpresasDal emprDal = new EmpresasDal();
        private static EstadoVehiculoDal estadoVehiDal = new EstadoVehiculoDal();
        private static EstadoContratoDal estadoContratoDal = new EstadoContratoDal();
        public VerVehiculo()
        {
            InitializeComponent();
            cargarCombo();
            _vehiculo = new vehiculos();
            btnGuardar.Text = " Crear";
            lblUsuario.Text = "Usuario Pertenece DNI:";
        }

        public VerVehiculo(vehiculos vehiculo)
        {
            InitializeComponent();
            cargarCombo();
            _vehiculo = vehiculo;
            mostrarDatos();
            txtUsuPertenece.Enabled = false;
            txtEmpresa.Enabled = false;
        }


        #region METODOS INTERFAZ

        private void VerVehiculo_Load(object sender, EventArgs e)
        {

        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            if (btnGuardar.Text.Contains(" Crear"))
            {
                DialogResult result = MessageBox.Show("¿Quieres crear el vehiculo ?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo carnet
                    crearVehiculo();

                    //Cerramos la ventana
                    this.Close();
                }
                else if (result == DialogResult.No)
                {
                    // El usuario hizo clic en "No"
                    this.Close();
                }
            }
            else
            {
                DialogResult result = MessageBox.Show("¿Quieres guardar el vehiculo ?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo carnet
                    ModificarVehiculo();

                    //Cerramos la ventana
                    this.Close();
                }
                else if (result == DialogResult.No)
                {
                    // El usuario hizo clic en "No"
                    this.Close();
                }
            }

        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres salir sin guardar el  vehiculo?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (result == DialogResult.Yes)
            {
                // El usuario hizo clic en "Yes"
                //Se agrega el nuevo carnet


                //Cerramos la ventana
                this.Close();
            }
            else if (result == DialogResult.No)
            {
                // El usuario hizo clic en "No"
                this.Close();
            }
        }

        #endregion


        #region METODOS COMPLEMENTARIOS

        public async void cargarCombo()
        {
            List<estado_vehiculo> estadovehiculo = await estadoVehiDal.findEstadoVehiculoAll();

            estadovehiculo.Add(new estado_vehiculo { estado = "OTRO", id = -1 });

            cbEstado.DataSource = estadovehiculo;
            cbEstado.DisplayMember = "estado";  // Debe coincidir con la propiedad del objeto
            cbEstado.ValueMember = "id";        // Debe coincidir con la propiedad del objeto
        }

        public async void mostrarDatos()
        {
            txtEmpresa.Text = await emprDal.findEmpresid(_vehiculo.idEmpresa);

            usuarios us = await usuDal.findUsuarioId(_vehiculo.idUsuariosPropietario);
            if (us != null)
            {
                txtUsuPertenece.Text = us.nombre + " " + us.apellidos;
            }
            else
            {
                txtUsuPertenece.Text = "No Pertenece";
            }

            txtUsuPertenece.Enabled = false;
            txtEmpresa.Enabled = false;

            // Aquí seleccionamos el item por el valor de "ValueMember"
            cbEstado.SelectedValue = _vehiculo.idEstado;

            txtMatricula.Text = _vehiculo.matricula;
            txtMarca.Text = _vehiculo.marca;
            txtModelo.Text = _vehiculo.modelo;
            txtAnio.Text = _vehiculo.anio.ToString();
            txtKilometro.Text = _vehiculo.km.ToString();
            txtPrecioVenta.Text = _vehiculo.precioventa.ToString();
            txtPrecioDia.Text = _vehiculo.preciodia.ToString();
        }

        public async void ModificarVehiculo()
        {
            if (cbEstado.SelectedValue != null)
            {
                _vehiculo.idEstado = (int)cbEstado.SelectedValue;
            }
            else
            {
                MessageBox.Show("Por favor selecciona un estado válido.");
                return; // Salir del método si no hay un valor seleccionado
            }

            _vehiculo.matricula = txtMatricula.Text;
            _vehiculo.marca = txtMarca.Text;
            _vehiculo.modelo = txtModelo.Text;

            // Manejo seguro de conversiones de texto a números

            _vehiculo.anio = Int32.Parse(txtAnio.Text);

            if (!int.TryParse(txtKilometro.Text, out int kilometro))
            {
                MessageBox.Show("Por favor ingresa un kilometraje válido.");
                return;
            }
            _vehiculo.km = kilometro;

            if (!double.TryParse(txtPrecioVenta.Text, out double precioVenta))
            {
                MessageBox.Show("Por favor ingresa un precio de venta válido.");
                return;
            }
            _vehiculo.precioventa = precioVenta;

            if (!double.TryParse(txtPrecioDia.Text, out double precioDia))
            {
                MessageBox.Show("Por favor ingresa un precio por día válido.");
                return;
            }
            _vehiculo.preciodia = precioDia;

            // Simulación de la modificación del vehículo
            bool modificado = await vehiculoDal.UpdateVehiculo(_vehiculo);

            if (modificado)
            {
                MessageBox.Show("Vehiculo Modificado");
            }
            else
            {
                MessageBox.Show("No se ha podido modificar");
            }
        }

        public async void crearVehiculo()
        {
            //id emprsa
            if (!string.IsNullOrEmpty(txtEmpresa.Text))
            {
                // Intentamos obtener el ID de la empresa
                int idEmpresa = await emprDal.findEmpresNombre(txtEmpresa.Text);

                // Si obtenemos un ID válido, lo asignamos al vehículo
                if (idEmpresa > 0)
                {
                    _vehiculo.idEmpresa = idEmpresa;
                }
                else
                {
                    MessageBox.Show("No se encontró una empresa con ese nombre. Verifica la información.", "Empresa no encontrada", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
            }

            //id usu
            // Buscamos el usuario por DNI
            if (!string.IsNullOrEmpty(txtUsuPertenece.Text))
            {
                // Intentamos obtener el ID del usuario
                int? idUsuario = await usuDal.findUsuarioDni(txtUsuPertenece.Text);

                // Si obtenemos un ID válido, lo asignamos al vehículo
                if (idUsuario != null)
                {
                    _vehiculo.idUsuariosPropietario = idUsuario;
                }
                else
                {
                    MessageBox.Show("No se encontró un usuario con ese DNI. Verifica la información.", "Usuario no encontrado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
            }

            //Estado
            if (cbEstado.SelectedValue != null)
            {
                _vehiculo.idEstado = (int)cbEstado.SelectedValue;
            }
            else
            {
                MessageBox.Show("Por favor selecciona un estado válido.");
            }

            _vehiculo.matricula = txtMatricula.Text;
            _vehiculo.marca = txtMarca.Text;
            _vehiculo.modelo = txtModelo.Text;

            // Manejo seguro de conversiones de texto a números

            _vehiculo.anio = int.Parse(txtAnio.Text);

            if (!int.TryParse(txtKilometro.Text, out int kilometro))
            {
                MessageBox.Show("Por favor ingresa un kilometraje válido.");
                return;
            }
            _vehiculo.km = kilometro;

            if (!double.TryParse(txtPrecioVenta.Text, out double precioVenta))
            {
                MessageBox.Show("Por favor ingresa un precio de venta válido.");
                return;
            }
            _vehiculo.precioventa = precioVenta;

            if (!double.TryParse(txtPrecioDia.Text, out double precioDia))
            {
                MessageBox.Show("Por favor ingresa un precio por día válido.");
                return;
            }
            _vehiculo.preciodia = precioDia;

            bool creado = await vehiculoDal.CrearVehiculo(_vehiculo);
            if (creado)
            {
                MessageBox.Show("Creado correctamente");
            }
            else
            {
                MessageBox.Show("No se ha podido crear");
            }

        }

        #endregion

    }
}
