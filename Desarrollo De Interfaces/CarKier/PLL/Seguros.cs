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
    public partial class Seguros : Form
    {
        public Seguros()
        {
            InitializeComponent();
        }


        #region Metodos para las tabla
        private void tsmiNuevo_Click(object sender, EventArgs e)
        {

        }

        private void tsmiVer_Click(object sender, EventArgs e)
        {
            PLL.VerSeguro infoSeguro = new PLL.VerSeguro();
            infoSeguro.Show();
        }

        private void tsmiEliminar_Click(object sender, EventArgs e)
        {

        }

        #endregion
    }
}
