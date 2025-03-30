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

namespace CarKier.PLL
{
    public partial class VerContrato : Form
    {
        private static contratos _contrato;
        private Contratos _ventanaPrincipal;
        private static ContratosDal contratoDal = new ContratosDal();
        private static UsuariosDal usuDal = new UsuariosDal();
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static SegurosDal seguroDal = new SegurosDal();
        private static EstadoContratoDal estadoContratoDal = new EstadoContratoDal();

        public VerContrato(Contratos ventanaPrincipal)
        {
            InitializeComponent();
            _contrato = new contratos();
            btnGuardar.Text = "Crear";
            lblIdCliente.Text = "Dni Cliente:";
            lblPrecioDia.Visible = false;
            lblPreciototal.Visible = false;
            txtPrecioDia.Visible = false;
            txtPrecioTotal.Visible = false;
            _ventanaPrincipal = ventanaPrincipal;

        }

        public VerContrato(contratos contrato, Contratos ventanaPrincipal)
        {
            InitializeComponent();
            _contrato = contrato;
            ComponentesInicializarAsync();
            txtIdVehiculo.Enabled = false;
            txtIdCliente.Enabled = false;
            _ventanaPrincipal = ventanaPrincipal;
        }

        #region METODOS INTERFAZ

        private void VerContrato_Load(object sender, EventArgs e)
        {
            CargarComboBoxAsync();
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            if (btnGuardar.Text.Contains("Crear"))
            {
                DialogResult result = MessageBox.Show("¿Quieres guardar los datos del contrato?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo carnet
                    string dni = txtIdCliente.Text;
                    string matricula = txtIdVehiculo.Text;

                    _contrato.fechaFin = txtFechaFinal.Text;
                    _contrato.fechaInicio = txtFechaInicio.Text;
                    CrearContrato(dni, matricula);
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
                DialogResult result = MessageBox.Show("¿Quieres guardar los datos del contrato?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo carnet
                    ModificarContrato();

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
            DialogResult result = MessageBox.Show("¿Quieres SALIR SIN guardar los datos del contrato?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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



        public async Task CargarComboBoxAsync()
        {
            try
            {
                // Cargar seguros
                List<tipos_seguros> seguros = await seguroDal.SegurosfindAll();
                if (seguros != null && seguros.Count > 0)
                {
                    cbSeguro.DataSource = seguros;
                    cbSeguro.DisplayMember = "Nombre"; // Nombre visible en el ComboBox
                    cbSeguro.ValueMember = "Id";      // Valor interno del ComboBox
                }
                else
                {
                    MessageBox.Show("No se pudieron cargar los seguros.");
                }

                // Cargar estados de contrato
                List<estado_contrato> estadosContrato = await estadoContratoDal.EstadoContratosfindAll();
                if (estadosContrato != null && estadosContrato.Count > 0)
                {
                    // Verificar el contenido de la lista
                    foreach (var estado in estadosContrato)
                    {
                        Console.WriteLine($"Id: {estado.id}, Estado: {estado.estado}");
                    }

                    // Forzar sincronización y asignar DataSource
                    cbEstadoContrato.BindingContext = new BindingContext();
                    cbEstadoContrato.DataSource = estadosContrato;
                    cbEstadoContrato.DisplayMember = "estado";
                    cbEstadoContrato.ValueMember = "Id";
                }
                else
                {
                    MessageBox.Show("No se pudieron cargar los estados del contrato.");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al cargar los datos: {ex.Message}");
            }
        }

        public async Task ComponentesInicializarAsync()
        {
            try
            {
                // Esperar a que se carguen los ComboBox
                await CargarComboBoxAsync();

                // Asignar valores a los ComboBox después de cargar los datos
                cbSeguro.SelectedValue = _contrato.idSeguro;
                cbEstadoContrato.SelectedValue = _contrato.idEstado;


                // Cargar datos del vehículo
                vehiculos vehiculo = await vehiculoDal.findVehiculoId(_contrato.idvehiculo);
                txtIdVehiculo.Text = vehiculo.matricula;

                // Cargar datos del cliente
                usuarios usu = await usuDal.findUsuarioId(_contrato.idCliente);
                txtIdCliente.Text = $"{usu.nombre} {usu.apellidos}";

                // Asignar estado pagado
                cbPagado.SelectedItem = _contrato.pagado ? "Si" : "No";

                // Asignar otros campos del contrato
                txtFechaInicio.Text = _contrato.fechaInicio;
                txtFechaFinal.Text = _contrato.fechaFin;
                txtPrecioDia.Text = _contrato.precioDia.ToString();
                txtPrecioTotal.Text = _contrato.precioTotal.ToString();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al inicializar los componentes: {ex.Message}");
            }
        }

        public async void CrearContrato(string dni, string matricula)
        {
            // Buscar el vehículo por su matrícula
            vehiculos vehiculo = await vehiculoDal.findVehiculoMatricula(matricula);
            if (vehiculo != null)
            {
                _contrato.idvehiculo = vehiculo.id;
            }
            else
            {
                MessageBox.Show("No existe el vehículo con esa matrícula.");
                return; // Salir del método para evitar errores
            }

            // Buscar el cliente por su DNI
            int? idCliente = await usuDal.findUsuarioDni(dni);
            if (idCliente != null)
            {
                _contrato.idCliente = idCliente.Value; // Asignar correctamente idCliente a _contrato.idcliente
            }
            else
            {
                MessageBox.Show("No existe el cliente con ese DNI.");
                return; // Salir del método para evitar errores
            }

            // Configurar detalles del contrato desde los controles de la interfaz
            int idEstado = (int)cbEstadoContrato.SelectedValue;
            _contrato.idEstado = idEstado;

            int idSeguro = (int)cbSeguro.SelectedValue;
            _contrato.idSeguro = idSeguro;

            _contrato.pagado = cbPagado.Text.Contains("Si");

            // Intentar crear el contrato
            bool creado = await contratoDal.CrearContrato(_contrato);
            if (creado)
            {
                MessageBox.Show("Se ha creado el contrato.");
                _ventanaPrincipal.CargarTabla();
            }
            else
            {
                MessageBox.Show("No se ha podido crear el contrato.");
            }
        }


        public async void ModificarContrato()
        {
            int idEstado = (int) cbEstadoContrato.SelectedValue;
            _contrato.idEstado = idEstado;

            int idSeguro = (int)cbSeguro.SelectedValue;
            _contrato.idSeguro = idSeguro;

            _contrato.pagado = false;
            if (cbPagado.Text.Contains("Si"))
            {
                _contrato.pagado = true;
            }

            _contrato.fechaInicio = txtFechaInicio.Text; 
            _contrato.fechaFin = txtFechaFinal.Text;

            bool modificado = await contratoDal.UpdateContrato(_contrato);
            if (modificado)
            {
                MessageBox.Show("Se ha modificado correctamente el contrato");
                _ventanaPrincipal.CargarTabla();

            }
            else
            {
                MessageBox.Show("No se ha podido modificar el contrato");
            }

        }

    }
}
