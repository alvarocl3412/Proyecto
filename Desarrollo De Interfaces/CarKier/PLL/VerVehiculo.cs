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
        public VerVehiculo()
        {
            InitializeComponent();
            cargarCombo();
            _vehiculo = new vehiculos();
        }

        public VerVehiculo(vehiculos vehiculo)
        {
            InitializeComponent();
            cargarCombo();
            _vehiculo = vehiculo;
            mostrarDatos();
        }


        #region METODOS INTERFAZ

        private void VerVehiculo_Load(object sender, EventArgs e)
        {
            
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres guardar los vehiculos ?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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

        public async  void mostrarDatos()
        {
            txtEmpresa.Text = await emprDal.findEmpresid(_vehiculo.idEmpresa);

            usuarios us = await usuDal.findUsuarioId(_vehiculo.idUsuariosPropietario);
            txtUsuPertenece.Text = us.nombre + " " + us.apellidos;

            txtUsuPertenece.Enabled = false;
            txtEmpresa.Enabled = false;

            // Aquí seleccionamos el item por el valor de "ValueMember"
            cbEstado.SelectedValue = _vehiculo.idEstado;

            txtMatricula.Text = _vehiculo.matricula;
            txtMarca.Text = _vehiculo.marca;
            txtModelo.Text = _vehiculo.modelo;
            txtAnio.Text = _vehiculo.anio.ToString();
            txtKilometro.Text = _vehiculo.km.ToString();
            txtPrecioVenta.Text = _vehiculo.precioventa.ToString() + " €";
            txtPrecioDia.Text = _vehiculo.preciodia.ToString() + " €";
        }

        public async void ModificarVehiculo()
        {

            _vehiculo.idEstado = (int)cbEstado.SelectedValue;
            _vehiculo.matricula = txtMatricula.Text;
            _vehiculo.marca = txtMarca.Text;
            _vehiculo.modelo = txtModelo.Text;
            _vehiculo.anio = int.Parse(txtAnio.Text);
            _vehiculo.km = int.Parse(txtKilometro.Text);
            _vehiculo.precioventa = double.Parse(txtPrecioVenta.Text);
            _vehiculo.preciodia = double.Parse(txtPrecioDia.Text);
            bool modificado = true;

            if(modificado)
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

        }

        #endregion

    }
}
