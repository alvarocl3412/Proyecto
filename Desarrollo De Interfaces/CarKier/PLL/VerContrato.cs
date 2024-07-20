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
        public VerContrato()
        {
            InitializeComponent();
        }

        #region Funcionalidad botones GUARDAR y CANCELAR
        private void btnGuardar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres guardar los datos del contrato?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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



    }
}
