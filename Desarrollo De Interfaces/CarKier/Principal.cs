using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CarKier
{
    public partial class Principal : Form
    {
        public Principal()
        {
            InitializeComponent();
        }

        private void usuariosToolStripMenuItem_Click(object sender, EventArgs e)
        {
            foreach (Form form in Application.OpenForms)
            {
                if (typeof(PLL.Usuarios) == form.GetType())
                {
                    form.Activate(); //Nos muestra por formulario 
                    return;
                }
            }

            //Si no esta activo, lo instanciamos y mostramos
            PLL.Usuarios infoUsuarios = new PLL.Usuarios();
            infoUsuarios.MdiParent = this;
            infoUsuarios.Show();
        }

        private void vehiculosToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            foreach (Form form in Application.OpenForms)
            {
                if (typeof(PLL.Vehiculos) == form.GetType())
                {
                    form.Activate(); //Nos muestra por formulario 
                    return;
                }
            }

            //Si no esta activo, lo instanciamos y mostramos
            PLL.Vehiculos vehiculos = new PLL.Vehiculos();
            vehiculos.MdiParent = this;
            vehiculos.Show();
        
        }

        private void vehiculosToolStripMenuItem_Click(object sender, EventArgs e)
        {
            foreach (Form form in Application.OpenForms)
            {
                if (typeof(PLL.Empresas) == form.GetType())
                {
                    form.Activate(); //Nos muestra por formulario 
                    return;
                }
            }

            //Si no esta activo, lo instanciamos y mostramos
            PLL.Empresas empresas = new PLL.Empresas();
            empresas.MdiParent = this;
            empresas.Show();
        }

        private void seguToolStripMenuItem_Click(object sender, EventArgs e)
        {
            foreach (Form form in Application.OpenForms)
            {
                if (typeof(PLL.Contratos) == form.GetType())
                {
                    form.Activate(); //Nos muestra por formulario 
                    return;
                }
            }

            //Si no esta activo, lo instanciamos y mostramos
            PLL.Contratos contratos = new PLL.Contratos();
            contratos.MdiParent = this;
            contratos.Show();
        }
    }
}
