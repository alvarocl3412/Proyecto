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
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace CarKier.PLL
{
    public partial class DatosDelUsuario : Form
    {
        private static datos_del_usuario _datos;
        private static usuarios usuario;
        private static DatosDelUsuarioDal datosUsuDal = new DatosDelUsuarioDal();
        
        public DatosDelUsuario()
        {
            InitializeComponent();
            txtUsuario.Enabled = false;
        }
        public DatosDelUsuario(usuarios usu)
        {
            InitializeComponent();
            txtUsuario.Enabled = false;
            usuario = usu;
        }

        #region METODOS INTERFAZ

        private async void DatosDelUsuario_Load(object sender, EventArgs e)
        {
            _datos = await datosUsuDal.DatosUsuariofindId(usuario.id);
            rellenarCampos(_datos);


        }

        private async void btnGuardar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres guardar los datos del usuario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (result == DialogResult.Yes)
            {
                // El usuario hizo clic en "Yes"
                //Se agrega el nuevo carnet
                guardarCambios();
            
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
            DialogResult result = MessageBox.Show("¿Quieres salir sin guardar los datos del usuario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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

        private  void rellenarCampos(datos_del_usuario datos)
        {
            txtUsuario.Text = usuario.nombre + " " + usuario.apellidos;

            cbAdministrador.SelectedItem = datos.administrador.HasValue && datos.administrador.Value ? "SI" : "NO";

            if (datos.puntos != null)
            {
                txtPuntos.Text = datos.puntos.ToString();

            } else
            {
                txtPuntos.Text = "0";

            }
            txtFechaBanInicio.Text = datos.fechaBanInicio.HasValue ? datos.fechaBanInicio.Value.ToString("yyyy-MM-dd") : "Sin bannear";

            txtFechaBanFinal.Text = datos.fechaBanFinal.HasValue ? datos.fechaBanFinal.Value.ToString("yyyy-MM-dd") : "Sin bannear";
            txtCantidadBan.Text = datos.cantidadBan.HasValue ? datos.cantidadBan.Value.ToString() : "0";
            cbEliminar.Checked = datos.marcaEliminar.HasValue && datos.marcaEliminar.Value;

        }

        private async void guardarCambios()
        {

            string cad = cbAdministrador.Text;
            
            _datos.administrador = cad == "SI"? true : false;
            _datos.puntos = int.Parse(txtPuntos.Text);

            // Manejo de fechas 
            if (DateTime.TryParse(txtFechaBanInicio.Text, out DateTime fechaBanInicio))
            {
                _datos.fechaBanInicio = fechaBanInicio;
            }
            else
            {
                _datos.fechaBanInicio = null; // O asigna un valor predeterminado según tu lógica
            }

            if (DateTime.TryParse(txtFechaBanFinal.Text, out DateTime fechaBanFinal))
            {
                _datos.fechaBanFinal = fechaBanFinal;
            }
            else
            {
                _datos.fechaBanFinal = null;
            }

            _datos.cantidadBan = int.Parse(txtCantidadBan.Text);
            _datos.marcaEliminar = cbEliminar.Checked;

            bool modificado = await datosUsuDal.UpdateDatosId(_datos);

            if (modificado)
            {
                MessageBox.Show("Los datos se han guardado correctamente.");
            }
            else
            {
                MessageBox.Show("No se pudo guardar los datos.");
            }
        }

        #endregion

    }
}
