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
    public partial class Contratos : Form
    {
        public Contratos()
        {
            InitializeComponent();
        }

        #region Funcionalida Filtrar
        private void txtFiltrarContratos_Enter(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (txt.Text == "Introduce el id del cliente para filtrar")
            {
                txt.Text = "";
                txt.ForeColor = Color.Black;
            }
        }

        private void txtFiltrarContratos_Leave(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (string.IsNullOrWhiteSpace(txt.Text))
            {
                txt.Text = "Introduce el id del cliente para filtrar";
                txt.ForeColor = Color.Gray;
            }
        }


        #endregion

        #region Funcionalida metodos tabla NUEVO,VER,ELIMINAR
        private void tsmNuevo_Click(object sender, EventArgs e)
        {

        }

        private void tsmVer_Click(object sender, EventArgs e)
        {
            PLL.VerContrato infoContrato = new PLL.VerContrato();
            infoContrato.Show();
        }

        private void tsmEliminar_Click(object sender, EventArgs e)
        {

        }

        #endregion


    }
}
