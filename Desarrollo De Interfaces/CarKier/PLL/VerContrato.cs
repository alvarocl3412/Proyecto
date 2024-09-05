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
        private static ContratosDal contratoDal = new ContratosDal();
        private static UsuariosDal usuDal = new UsuariosDal();
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static SegurosDal seguroDal = new SegurosDal();
        private static EstadoContratoDal estadoContratoDal = new EstadoContratoDal();

        public VerContrato()
        {
            InitializeComponent();
            _contrato = new contratos();
            btnGuardar.Text = "Crear";
            lblPrecioDia.Visible = false;
            lblPreciototal.Visible = false;
            txtPrecioDia.Visible = false;
            txtPrecioTotal.Visible = false;
        }

        public VerContrato(contratos contrato)
        {
            InitializeComponent();
            _contrato = contrato;
            ComponentesInicializar();
            txtIdVehiculo.Enabled = false;
            txtIdCliente.Enabled = false;
        }

        #region METODOS INTERFAZ

        private void VerContrato_Load(object sender, EventArgs e)
        {
            CargarComboBox();
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

                    CrearContrato();
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

      

        public async void CargarComboBox()
        {
         


            List<tipos_seguros> seguros = await seguroDal.SegurosfindAll(); // lista seguros
            List<estado_contrato> estadoContrato = await estadoContratoDal.EstadoContratosfindAll();


            if (seguros != null && seguros.Count > 0)
            {
                cbSeguro.DataSource = seguros;

                // Mostrar solo los nombres de los seguros
                cbSeguro.DisplayMember = "Nombre";

                // (Opcional) Establecer el valor seleccionado con el ID del seguro
                cbSeguro.ValueMember = "Id";
            }
            else
            {
                MessageBox.Show("No se pudieron cargar los seguros.");
            }

            if (estadoContrato != null && estadoContrato.Count > 0)
            {
                cbEstadoContrato.DataSource = estadoContrato;

                // Mostrar solo los nombres de los seguros
                cbEstadoContrato.DisplayMember = "estado";

                // (Opcional) Establecer el valor seleccionado con el ID del seguro
                cbEstadoContrato.ValueMember = "Id";
            }
            else
            {
                MessageBox.Show("No se pudieron cargar los estados del contrato.");
            }

        }
      

        public async void ComponentesInicializar()
        {
            cbSeguro.SelectedValue = _contrato.idSeguro;
            cbEstadoContrato.SelectedValue = _contrato.idEstado;

            vehiculos vehiculo = await vehiculoDal.findVehiculoId(_contrato.idvehiculo);
            txtIdVehiculo.Text = vehiculo.matricula;

            usuarios usu = await usuDal.findUsuarioId(_contrato.idCliente);
            txtIdCliente.Text = usu.nombre + " " + usu.apellidos;

            if (_contrato.pagado)
            {
                cbPagado.SelectedItem = "Si";
            } else
            {
                cbPagado.SelectedItem = "No";
            }

            txtFechaInicio.Text = _contrato.fechaInicio.ToString("dd/MM/yyyy");
            txtFechaFinal.Text = _contrato.fechaFin.ToString("dd/MM/yyyy");
            txtPrecioDia.Text = _contrato.precioDia.ToString();
            txtPrecioTotal.Text = _contrato.precioTotal.ToString();
        }

        public async void CrearContrato()
        {
            vehiculos vehiculo = await vehiculoDal.findVehiculoMatricula(txtIdVehiculo.Text);
            if(vehiculo != null)
            {
                _contrato.idvehiculo = vehiculo.id;
            }
            else
            {
                MessageBox.Show("No existe el vehiculo con esa matricula "); ;
               
            }

            int? idCliente = await usuDal.findUsuarioDni(txtIdCliente.Text);
            if (idCliente != null)
            {
                _contrato.idvehiculo = vehiculo.id;
            }
            else
            {
                MessageBox.Show("No existe el cliente con ese dni "); ;
            }

            int idEstado = (int)cbEstadoContrato.SelectedValue;
            _contrato.idEstado = idEstado;

            int idSeguro = (int)cbSeguro.SelectedValue;
            _contrato.idSeguro = idSeguro;

            _contrato.pagado = false;
            if (cbPagado.Text.Contains("Si"))
            {
                _contrato.pagado = true;
            }

            _contrato.fechaInicio = DateTime.Parse(txtFechaInicio.Text);
            _contrato.fechaFin = DateTime.Parse(txtFechaFinal.Text);

            bool creado = await contratoDal.CrearContrato(_contrato);
            if (creado)
            {
                MessageBox.Show("Se ha creado el contrato");
            }
            else
            {
                MessageBox.Show("No se ha podido crear el  contrato");
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

            _contrato.fechaInicio = DateTime.Parse(txtFechaInicio.Text); 
            _contrato.fechaFin = DateTime.Parse(txtFechaFinal.Text);

            bool modificado = await contratoDal.UpdateContrato(_contrato);
            if (modificado)
            {
                MessageBox.Show("Se ha modificado correctamente el contrato");
            }
            else
            {
                MessageBox.Show("No se ha podido modificar el contrato");
            }

        }

    }
}
