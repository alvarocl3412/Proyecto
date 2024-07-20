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
    public partial class Usuarios : Form
    {
        public Usuarios()
        {
            InitializeComponent();
            configuracion();
        }


        //Metodos funcionalidades
        private void txtFiltrarDni_Enter(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (txt.Text == "Introduce el Dni para filtrar")
            {
                txt.Text = "";
                txt.ForeColor = Color.Black;
            }
        }

        private void txtFiltrarDni_Leave(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (string.IsNullOrWhiteSpace(txt.Text))
            {
                txt.Text = "Introduce el Dni para filtrar";
                txt.ForeColor = Color.Gray;
            }
        }

        private void verToolStripMenuItem_Click(object sender, EventArgs e)
        {
            PLL.VerUsuario infoUsuarios = new PLL.VerUsuario();
            infoUsuarios.Show();
        }


        //Metodos complementarios

        private void configuracion()
            {
            txtFiltrarDni.Text = "Introduce Dni para filtrar";
            txtFiltrarDni.ForeColor = Color.Gray;
            txtFiltrarDni.Enter += txtFiltrarDni_Enter;
            txtFiltrarDni.Leave += txtFiltrarDni_Leave;
        }

      
    }
}
