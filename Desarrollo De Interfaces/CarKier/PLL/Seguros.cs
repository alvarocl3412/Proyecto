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
        private static SegurosDal vdal = new SegurosDal();
        public Seguros()
        {
            InitializeComponent();
            tsmiVer.Enabled = false;
            tsmiEliminar.Enabled = false;
        }


        #region METODOS INTERFAZ
        private void Seguros_Load(object sender, EventArgs e)
        {
            CargarTabla();
        }

        private void lvSeguros_SelectedIndexChanged(object sender, EventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvSeguros.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            tsmiVer.Enabled = hasSelectedItem;
            tsmiEliminar.Enabled = hasSelectedItem;
        }

        private void lvSeguros_DoubleClick(object sender, EventArgs e)
        {

        }

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


        #region METODOS COMPLEMENTARIOS
        private async Task CargarTabla()
        {
           
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<tipos_seguros> listaSeguros = await vdal.SegurosfindAll();

            // Limpiar elementos existentes
            lvSeguros.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var seguros in listaSeguros)
            {

                ListViewItem item = new ListViewItem(seguros.nombre);
                item.SubItems.Add(seguros.descripcion);
                item.SubItems.Add(seguros.coste.ToString());
                lvSeguros.Items.Add(item);
            }
        }


        #endregion


    }
}
