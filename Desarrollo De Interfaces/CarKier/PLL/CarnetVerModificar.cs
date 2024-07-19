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
    public partial class CarnetVerModificar : Form
    {
        public int numero { get; set; }
        public CarnetVerModificar()
        {
            InitializeComponent();
        }

        public CarnetVerModificar(int numero)
        {
            InitializeComponent();
            this.numero = numero;
            if (numero == 1)
            {
                InicializarComponenterAñadir();
            }

            if (numero == 2)
            {
                InicializarComponentesVer();
            }
        }

        #region Métodos para la inicializacion
        private void InicializarComponenterAñadir()
        {
            btnGuardar.Text = "Agregar";
        }

        private void InicializarComponentesVer()
        {
            //Le pasamos argumentos
            txtIdCarnet.Text = "1";
            txtIdUsuario.Text = "1";
            txtTipo.Text = "1";
            txtFechaExpedicion.Text = "1";
            txtFechaCaducidad.Text = "1";
        }
        #endregion


        #region funcionalidad botones
        private void btnGuardar_Click(object sender, EventArgs e)
        {
            if (numero == 1)
            {
                DialogResult result = MessageBox.Show("¿Quieres agregar el nuevo carnet?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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

            if (numero == 2)
            {
                DialogResult result = MessageBox.Show("¿Quieres guardar los cambios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                }
                else if (result == DialogResult.No)
                {
                    // El usuario hizo clic en "No"
                }
            }
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Seguro que quieres salir sin guardar los cambios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (result == DialogResult.Yes)
            {
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"

                    //Metodo para guardar


                    //MeTtodo para cerrar
                    this.Close();
                } else
                {
                    //EL usuario hizo clic en "No"
                    //Cerramos sin guardar
                    this.Close();
                }

            }
        }
        #endregion


    }
}
