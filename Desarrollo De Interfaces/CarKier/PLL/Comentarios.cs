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
    public partial class Comentarios : Form
    {
        public Comentarios()
        {
            InitializeComponent();
        }

        #region Metodos para la tabla

        private void tsmiNuevo_Click(object sender, EventArgs e)
        {

        }

        private void tsmiVer_Click(object sender, EventArgs e)
        {
            PLL.VerComentario infoComentario = new PLL.VerComentario();
            infoComentario.Show();
        }

        private void tsmiEliminar_Click(object sender, EventArgs e)
        {

        }

        #endregion
    }
}
