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
        public Empresas()
        {
            InitializeComponent();  
        }

        private async void Empresas_Load(object sender, EventArgs e)
        {
            await CargarDatos();
        }
        private async Task<List<empresas>> ObtenerEmpresasAsync()
        {
            EmpresasDal empr = new EmpresasDal();
            return await empr.findAll();
        }

        private async Task CargarDatos()
        {
            // Obtener la lista de empresas de manera asíncrona
            List<empresas> listaEmpresas = await ObtenerEmpresasAsync();

            // Cargar la lista en el ListView
            CargarTabla(listaEmpresas);
        }
        private void CargarTabla(List<empresas> listaEmpresas)
        {
            // Limpiar elementos existentes
            lvEmpresas.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var empresa in listaEmpresas)
            {
                ListViewItem item = new ListViewItem(empresa.nombre.ToString());
                item.SubItems.Add(empresa.descripcion);
                item.SubItems.Add(empresa.direccion);
                item.SubItems.Add(empresa.telefono);
                item.SubItems.Add(empresa.correoElectronico);
                item.SubItems.Add(empresa.ofreceCoches.ToString());
                lvEmpresas.Items.Add(item);
            }
        }


        #region Funcionalidad hint para el texto Filtrar

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

        #region Funcionalidad metodos tabla NUEVO, VER, ELIMINAR
        private void tsmiNuevo_Click(object sender, EventArgs e)
        {

        }
        private void tsmiVer_Click(object sender, EventArgs e)
        {
            PLL.VerEmpresa infoEmpresa = new PLL.VerEmpresa();
            infoEmpresa.Show();
        }

        private void tsmiEliminar_Click(object sender, EventArgs e)
        {

        }
        #endregion  
    }
}
