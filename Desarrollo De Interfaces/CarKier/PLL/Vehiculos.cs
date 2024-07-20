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
    public partial class Vehiculos : Form
    {
        public Vehiculos()
        {
            InitializeComponent();
        }

        private void verToolStripMenuItem_Click(object sender, EventArgs e)
        {
            PLL.VerVehiculo infoVehiculo= new PLL.VerVehiculo();
            infoVehiculo.Show();
        }
    }
}
