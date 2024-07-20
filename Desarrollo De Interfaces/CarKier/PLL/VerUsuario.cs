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
    public partial class VerUsuario : Form
    {
        public VerUsuario()
        {
            InitializeComponent();
        }

        #region Métodos para la tabla NUEVO,VER Y ELIMINAR
        private void ntsmNuevo_Click(object sender, EventArgs e)
        {
            PLL.CarnetVerModificar CarnetVerModificar = new PLL.CarnetVerModificar(1);
            CarnetVerModificar.Show();
        }

        private void mtsmVer_Click(object sender, EventArgs e)
        {
            PLL.CarnetVerModificar CarnetVerModificar = new PLL.CarnetVerModificar(2);
            CarnetVerModificar.Show();
        }
        private void mtsmEliminar_Click(object sender, EventArgs e)
        {

        }

        #endregion


        #region Funcionalidad botones ver DATOS,GUARDAR,CANCELAR
        private void btnDatos_Click(object sender, EventArgs e)
        {
            PLL.DatosDelUsuario DatosUsu = new PLL.DatosDelUsuario();
            DatosUsu.Show();
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres guardar los datos del usuario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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


    }
}