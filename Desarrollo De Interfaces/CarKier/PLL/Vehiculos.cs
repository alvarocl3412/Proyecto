using CarKier.DAL;
using CarKier.Modelo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.CompilerServices;
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

        private async void Vehiculos_Load(object sender, EventArgs e)
        {
            CargarTabla();
        }
      
        private async Task CargarTabla()
        {
            VehiculosDal vdal = new VehiculosDal();
            empresas empr = new empresas();
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<vehiculos> listaVehiculo = await vdal.VehiculosfindAll();

            // Limpiar elementos existentes
            lvVehiculos.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var vehiculo in listaVehiculo)
            {
               string idEm = await vdal.findEmpresId(vehiculo.idEmpresa);
               ListViewItem item = new ListViewItem(idEm);

                string idusu = await vdal.findUsuarioid(vehiculo.idUsuariosPropietario);
                item.SubItems.Add(idusu);

                string idest = await vdal.findEstadoid(vehiculo.idEstado);
                item.SubItems.Add(idest);

                item.SubItems.Add(vehiculo.matricula);
                item.SubItems.Add(vehiculo.marca);
                item.SubItems.Add(vehiculo.modelo);
                item.SubItems.Add(vehiculo.anio.ToString());
                item.SubItems.Add(vehiculo.km.ToString());
                item.SubItems.Add(vehiculo.precioventa.ToString());
                item.SubItems.Add(vehiculo.preciodia.ToString());
                lvVehiculos.Items.Add(item);
            }
        }
    }
}
