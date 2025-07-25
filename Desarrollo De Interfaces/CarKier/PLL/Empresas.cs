﻿using CarKier.DAL;
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
        private string txtFiltro = "Introduce el nombre";

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
            txtFiltrar.TextChanged += txtFiltrar_TextChanged;
        }

        private void lvEmpresas_SelectedIndexChanged(object sender, EventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvEmpresas.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            tsmiVer.Enabled = hasSelectedItem;
            tsmiEliminar.Enabled = hasSelectedItem;
        }


        private void tsmiNuevo_Click(object sender, EventArgs e)
        {
            PLL.VerEmpresa infoEmpresa = new PLL.VerEmpresa(this);
            infoEmpresa.Show();
        }

        private void tsmiVer_Click(object sender, EventArgs e)
        {
            verEmpresa();
        }

        private void lvEmpresas_DoubleClick(object sender, EventArgs e)
        {
            verEmpresa();
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
            if (txt.Text == "Introduce el nombre")
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
                txt.Text = "Introduce el nombre";
                txt.ForeColor = Color.Gray;
            }

        }

        #endregion


        #region METODOS COMPLEMENTARIOS

        public async Task CargarTabla()
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

        private async void verEmpresa()
        {

            var selectedItem = lvEmpresas.SelectedItems[0];
            int id = int.Parse(selectedItem.Tag.ToString());

            empresas empresa = await emprDal.findEmpresId(id);

            if (empresa != null)
            {
                PLL.VerEmpresa infoEmpresa = new PLL.VerEmpresa(empresa, this);
                infoEmpresa.Show();

            }
            else
            {
                MessageBox.Show("No se ha encontrado el usuario");
            }

        }


        #endregion

        private async void txtFiltrar_TextChanged(object sender, EventArgs e)
        {
            string texto = txtFiltrar.Text.Trim();

            if (string.IsNullOrEmpty(texto) || texto == txtFiltro || txtFiltrar.ForeColor == Color.Gray)
            {
                CargarTabla();
                return;
            }

            empresas resultados = await emprDal.findEmpresasPorNombre(texto);

            lvEmpresas.Items.Clear();

            if (resultados != null)
            {
                ListViewItem item = new ListViewItem(resultados.nombre);
                item.SubItems.Add(resultados.descripcion);
                item.SubItems.Add(resultados.direccion);
                item.SubItems.Add(resultados.telefono);
                item.SubItems.Add(resultados.correoElectronico);
                item.SubItems.Add(resultados.ofreceCoches.ToString());
                item.Tag = resultados.id.ToString();
                lvEmpresas.Items.Add(item);
            }
            else
            {
                CargarTabla();
            }
        }

    }
}
