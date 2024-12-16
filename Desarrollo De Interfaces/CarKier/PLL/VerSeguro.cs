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
    public partial class VerSeguro : Form
    {
        private static tipos_seguros _seguro;
        private Seguros _ventanPrincipal;
        private static SegurosDal seguroDal = new SegurosDal();
        public VerSeguro(Seguros seguros)
        {
            InitializeComponent();
            _seguro = new tipos_seguros();
            btnGuardar.Text = "Crear";
            _ventanPrincipal = seguros;
        }

        public VerSeguro(tipos_seguros seguro,Seguros seguros)
        {
            InitializeComponent();
            _seguro = seguro;
            _ventanPrincipal = seguros;
            mostrarSeguro();
        }

        #region METODOS INTERFAZ
        private void btnGuardar_Click(object sender, EventArgs e)
        {
            if (btnGuardar.Text.Contains("Crear"))
            {

                DialogResult result = MessageBox.Show("¿Quieres crear el nuevo seguro?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo seguro
                    crearSeguro();

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

                DialogResult result = MessageBox.Show("¿Quieres guardar los datos del seguro?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se modiifca el nuevo seguro
                    modificarSeguro();

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
            DialogResult result = MessageBox.Show("¿Quieres salir sin guardar los datos del seguro?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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

        public void mostrarSeguro()
        {
            txtNombre.Text = _seguro.nombre;
            txtDescripcion.Text = _seguro.descripcion;
            txtCoste.Text = _seguro.coste.ToString();
        }

        private async void modificarSeguro()
        {
            recogerDatos();
            bool modificado = await seguroDal.UpdateSeguro(_seguro);
            if (modificado)
            {
                MessageBox.Show("Se ha modificado el seguro correctamente");
                _ventanPrincipal.CargarTabla();     
            }
            else
            {
                MessageBox.Show("No se ha podido modificar el seguro");
            }
        }

        private async void crearSeguro()
        {
            recogerDatos();
            bool creado = await seguroDal.CrearSeguro(_seguro);
            if (creado)
            {
                MessageBox.Show("Se ha creado el nuevo seguro");
                _ventanPrincipal.CargarTabla();
            }
            else
            {
                MessageBox.Show("No se ha podido crear el seguro");
            }
        }

        private async void recogerDatos()
        {
            _seguro.nombre = txtNombre.Text;
            _seguro.descripcion = txtDescripcion.Text;
            _seguro.coste = int.Parse(txtCoste.Text);
        }


        #endregion
    }
}
