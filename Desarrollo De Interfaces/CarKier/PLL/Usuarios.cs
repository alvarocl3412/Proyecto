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
    public partial class Usuarios : Form
    {
        public Usuarios()
        {
            InitializeComponent();
            configuracion();
        }


        //Metodos funcionalidades
        private void txtFiltrarDni_Enter(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (txt.Text == "Introduce el Dni para filtrar")
            {
                txt.Text = "";
                txt.ForeColor = Color.Black;
            }
        }

        private void txtFiltrarDni_Leave(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (string.IsNullOrWhiteSpace(txt.Text))
            {
                txt.Text = "Introduce el Dni para filtrar";
                txt.ForeColor = Color.Gray;
            }
        }

        private void verToolStripMenuItem_Click(object sender, EventArgs e)
        {
            PLL.VerUsuario infoUsuarios = new PLL.VerUsuario();
            infoUsuarios.Show();
        }


        //Metodos complementarios

        private void configuracion()
            {
            txtFiltrarDni.Text = "Introduce Dni para filtrar";
            txtFiltrarDni.ForeColor = Color.Gray;
            txtFiltrarDni.Enter += txtFiltrarDni_Enter;
            txtFiltrarDni.Leave += txtFiltrarDni_Leave;
        }

        private async void Usuarios_Load(object sender, EventArgs e)
        {
            await CargarDatos();
        }
        private async Task<List<usuarios>> ObtenerUsuariosAsync()
        {
            UsuariosDal usuDal = new UsuariosDal();
            return await usuDal.UsuariosfindAll();
        }

        private async Task CargarDatos()
        {
            // Obtener la lista de empresas de manera asíncrona
            List<usuarios> listaUsuarios = await ObtenerUsuariosAsync();

            // Cargar la lista en el ListView
            CargarTabla(listaUsuarios);
        }
        private void CargarTabla(List<usuarios> listaUsuarios)
        {
            // Limpiar elementos existentes
            lvUsuarios.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var usuarios in listaUsuarios)
            {
                ListViewItem item = new ListViewItem(usuarios.dni);
                item.SubItems.Add(usuarios.nombre);
                item.SubItems.Add(usuarios.apellidos);
                item.SubItems.Add(usuarios.telefono);
                item.SubItems.Add(usuarios.correo);
                item.SubItems.Add(usuarios.fechaNacimiento.ToString("dd/MM/yyyy"));
                lvUsuarios.Items.Add(item);
            }
        }
    }
}
