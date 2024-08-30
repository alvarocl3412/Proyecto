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
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static UsuariosDal usuDal = new UsuariosDal();
        private static EmpresasDal emprDal = new EmpresasDal();
        private static EstadoVehiculoDal estadoVehiDal = new EstadoVehiculoDal();
        public Vehiculos()
        {
            InitializeComponent();
            CargarTabla();
        }

        private void verToolStripMenuItem_Click(object sender, EventArgs e)
        {
            PLL.VerVehiculo infoVehiculo= new PLL.VerVehiculo();
            infoVehiculo.Show();
        }

        private async void Vehiculos_Load(object sender, EventArgs e)
        {
           // CargarTabla();
        }
      
        private async Task CargarTabla()
        {
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<vehiculos> listaVehiculo = await vehiculoDal.VehiculosfindAll();

            // Limpiar elementos existentes
            lvVehiculos.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var vehiculo in listaVehiculo)
            {
               string idEm = await emprDal.findEmpresId(vehiculo.idEmpresa);
               ListViewItem item = new ListViewItem(idEm);

                string idusu = await usuDal.findUsuarioid(vehiculo.idUsuariosPropietario);
                item.SubItems.Add(idusu);

                string idest = await estadoVehiDal.findEstadoid(vehiculo.idEstado);
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
