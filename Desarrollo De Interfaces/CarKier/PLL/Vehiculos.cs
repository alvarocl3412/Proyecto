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

        }

        #region METODOS INTERFAZ

        private async void Vehiculos_Load(object sender, EventArgs e)
        {
            CargarTabla();
            verToolStripMenuItem.Enabled = false;
            eliminarToolStripMenuItem.Enabled = false;
        }

        private void lvVehiculos_SelectedIndexChanged(object sender, EventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvVehiculos.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            verToolStripMenuItem.Enabled = hasSelectedItem;
            eliminarToolStripMenuItem.Enabled = hasSelectedItem;
        }

        private void nuevoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            PLL.VerVehiculo infoVehiculo = new PLL.VerVehiculo();
            infoVehiculo.Show();
        }

        private void lvVehiculos_DoubleClick(object sender, EventArgs e)
        {
            verVehiculo();
        }

        private void verToolStripMenuItem_Click(object sender, EventArgs e)
        {
            verVehiculo();
        }

        private async void eliminarToolStripMenuItem_Click(object sender, EventArgs e)
        {
            // Obtener el elemento seleccionado
            var selectedItem = lvVehiculos.SelectedItems[0];

            // Suponiendo que el ID de la empresa está almacenado en el Tag del ListViewItem
            int seguro = int.Parse(selectedItem.Tag.ToString());
            DialogResult result = MessageBox.Show("¿Estás seguro de que quieres eliminar el vehiculo seleccionado?",
          "Confirmación de eliminación", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result == DialogResult.Yes)
            {
                bool eliminado = await vehiculoDal.deleteVehiculoid(seguro);
                CargarTabla();
            }
        }

        #endregion

        #region METODOS COMPLEMENTARIOS
        private async Task CargarTabla()
        {
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<vehiculos> listaVehiculo = await vehiculoDal.VehiculosfindAll();

            // Limpiar elementos existentes
            lvVehiculos.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var vehiculo in listaVehiculo)
            {
                string nom = await emprDal.findEmpresid(vehiculo.idEmpresa);
                ListViewItem item = new ListViewItem(nom);

                string idusu = await usuDal.findUsuarioid(vehiculo.idUsuariosPropietario);
                item.SubItems.Add(idusu);

                string idest = await estadoVehiDal.findEstadoid(vehiculo.idEstado);
                item.SubItems.Add(idest);

                item.SubItems.Add(vehiculo.matricula);
                item.SubItems.Add(vehiculo.marca);
                item.SubItems.Add(vehiculo.modelo);
                item.SubItems.Add(vehiculo.anio.ToString());
                item.SubItems.Add(vehiculo.km.ToString());
                item.SubItems.Add(vehiculo.precioventa.ToString() + " €") ;
                item.SubItems.Add(vehiculo.preciodia.ToString() + " €");
                item.Tag = vehiculo.id;
                lvVehiculos.Items.Add(item);
            }
        }

        private async void verVehiculo()
        {
            var selectedItem = lvVehiculos.SelectedItems[0];
            int id = int.Parse(selectedItem.Tag.ToString());

            vehiculos vehiculo = await vehiculoDal.findVehiculoId(id);

            if (vehiculo != null)
            {
                PLL.VerVehiculo infoVehiculo = new PLL.VerVehiculo(vehiculo);
                infoVehiculo.Show();

            }
            else
            {
                MessageBox.Show("No se ha encontrado el usuario");
            }

            
        }




        #endregion


    }
}
