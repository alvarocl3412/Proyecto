using CarKier.DAL;
using CarKier.Modelo;
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
        private static SegurosDal seguroDal = new SegurosDal();

        public Seguros()
        {
            InitializeComponent();

        }


        #region METODOS INTERFAZ
        private void Seguros_Load(object sender, EventArgs e)
        {
            CargarTabla();
            tsmiVer.Enabled = false;
            tsmiEliminar.Enabled = false;
        }

        private void lvSeguros_SelectedIndexChanged(object sender, EventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvSeguros.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            tsmiVer.Enabled = hasSelectedItem;
            tsmiEliminar.Enabled = hasSelectedItem;
        }

        private void tsmiNuevo_Click(object sender, EventArgs e)
        {
            PLL.VerSeguro infoSeguro = new PLL.VerSeguro();
            infoSeguro.Show();
        }

        private void lvSeguros_DoubleClick(object sender, EventArgs e)
        {
            verSeguro();
        }
        private void tsmiVer_Click(object sender, EventArgs e)
        {
            verSeguro();
        }

        private async void tsmiEliminar_Click(object sender, EventArgs e)
        {
            // Obtener el elemento seleccionado
            var selectedItem = lvSeguros.SelectedItems[0];

            // Suponiendo que el ID de la empresa está almacenado en el Tag del ListViewItem
            int seguro = int.Parse(selectedItem.Tag.ToString());
            DialogResult result = MessageBox.Show("¿Estás seguro de que quieres eliminar el seguro seleccionado?",
          "Confirmación de eliminación", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result == DialogResult.Yes)
            {
                bool eliminado = await seguroDal.deleteSegurosid(seguro);
                CargarTabla();
            }
        }

        #endregion


        #region METODOS COMPLEMENTARIOS
        private async Task CargarTabla()
        {
           
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<tipos_seguros> listaSeguros = await seguroDal.SegurosfindAll();

            // Limpiar elementos existentes
            lvSeguros.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var seguros in listaSeguros)
            {

                ListViewItem item = new ListViewItem(seguros.nombre);
                item.SubItems.Add(seguros.descripcion);
                item.SubItems.Add(seguros.coste.ToString());
                item.Tag = seguros.id.ToString();
                lvSeguros.Items.Add(item);
            }
        }

        private async void verSeguro()
        {
            var selectedItem = lvSeguros.SelectedItems[0];
            int id = int.Parse(selectedItem.Tag.ToString());

            tipos_seguros seguro = await seguroDal.findSegurosId(id);

            if (seguro != null)
            {
                PLL.VerSeguro infoSeguro = new PLL.VerSeguro(seguro);
                infoSeguro.Show();
            }
            else
            {
                MessageBox.Show("No se ha encontrado el seguro");
            }
        }

        #endregion


    }
}
