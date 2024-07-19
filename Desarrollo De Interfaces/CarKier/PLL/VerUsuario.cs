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

        #region Métodos para la tabla añadir ver 
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
        #endregion


        #region Funcionalidad botones
        private void btnDatos_Click(object sender, EventArgs e)
        {
            PLL.DatosDelUsuario DatosUsu = new PLL.DatosDelUsuario();
            DatosUsu.Show();
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {

        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {

        }
        #endregion


    }
}