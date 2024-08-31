using CarKier.DAL;
using CarKier.Modelo;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CarKier.PLL
{
    public partial class Empresas : Form
    {
        private static EmpresasDal emprDal = new EmpresasDal();

        public Empresas()
        {
            InitializeComponent();
        }

        #region METODOS INTERFZ
        private void Empresas_Load(object sender, EventArgs e)
        {
            CargarTabla();
            tsmiVer.Enabled = false;
            tsmiEliminar.Enabled = false;
        }

        private void lvEmpresas_SelectedIndexChanged(object sender, EventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvEmpresas.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            tsmiVer.Enabled = hasSelectedItem;
            tsmiEliminar.Enabled = hasSelectedItem;
        }

        private void lvEmpresas_DoubleClick(object sender, EventArgs e)
        {

        }

        private void tsmiNuevo_Click(object sender, EventArgs e)
        {

        }

        private void tsmiVer_Click(object sender, EventArgs e)
        {
            PLL.VerEmpresa infoEmpresa = new PLL.VerEmpresa();
            infoEmpresa.Show();
        }

        private async void tsmiEliminar_Click(object sender, EventArgs e)
        {
            // Obtener el elemento seleccionado
            var selectedItem = lvEmpresas.SelectedItems[0];

            // Suponiendo que el ID de la empresa está almacenado en el Tag del ListViewItem
            int empresaId = int.Parse(selectedItem.Tag.ToString());
            DialogResult result = MessageBox.Show("¿Estás seguro de que quieres eliminar la empresas seleccionado?",
          "Confirmación de eliminación", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result == DialogResult.Yes)
            {
                bool eliminado = await emprDal.deleteEmpresaId(empresaId);
                CargarTabla();
            }
        }

        //METODOS PARA EL HINT DEL FILTRAR
        private void txtFiltrar_Enter(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (txt.Text == "Introduce el telefono para filtrar")
            {
                txt.Text = "";
                txt.ForeColor = Color.Black;
            }
        }

        private void txtFiltrar_Leave(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (string.IsNullOrWhiteSpace(txt.Text))
            {
                txt.Text = "Introduce el telefono para filtrar";
                txt.ForeColor = Color.Gray;
            }

        }

        #endregion


        #region METODOS COMPLEMENTARIOS
        private async Task CargarTabla()
        {

            List<empresas> listaEmpresas = await emprDal.findEmpresaAll();
            // Limpiar elementos existentes
            lvEmpresas.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var empresa in listaEmpresas)
            {
                ListViewItem item = new ListViewItem(empresa.nombre);
                item.SubItems.Add(empresa.descripcion);
                item.SubItems.Add(empresa.direccion);
                item.SubItems.Add(empresa.telefono);
                item.SubItems.Add(empresa.correoElectronico);
                item.SubItems.Add(empresa.ofreceCoches.ToString());
                item.Tag = empresa.id.ToString();
                lvEmpresas.Items.Add(item);
            }
        }

        #endregion


    }
}
