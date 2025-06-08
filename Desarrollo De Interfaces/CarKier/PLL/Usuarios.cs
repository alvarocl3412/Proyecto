using CarKier.DAL;
using CarKier.Modelo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CarKier.PLL
{
    public partial class Usuarios : Form
    {
        private static UsuariosDal usuDal = new UsuariosDal();
        private static DatosDelUsuarioDal datosDal = new DatosDelUsuarioDal();
        private string txtFiltro = "Introduce el dni";

        public Usuarios()
        {
            InitializeComponent();
            configuracion();
            txtFiltrarDni.Text = txtFiltro;
            txtFiltrarDni.ForeColor = Color.Gray;

            txtFiltrarDni.Enter += RemovePlaceholder;
            txtFiltrarDni.Leave += SetPlaceholder;
            CargarTabla();

        }

        #region  METODOS INTERFAZ
        private async void Usuarios_Load(object sender, EventArgs e)
        {
            await CargarTabla();
            txtFiltrarDni.TextChanged += TxtFiltroMarca_TextChanged;

        }

        private void lvUsuarios_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvUsuarios.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            verToolStripMenuItem.Enabled = hasSelectedItem;
            eliminarToolStripMenuItem.Enabled = hasSelectedItem;
        }

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

        private void nuevoToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            crearUsuario();
        }

        private void verToolStripMenuItem_Click(object sender, EventArgs e)
        {
            verUsuario();
        }

        private void lvUsuarios_DoubleClick(object sender, EventArgs e)
        {
            verUsuario();
        }

        private void RemovePlaceholder(object sender, EventArgs e)
        {
            if (txtFiltrarDni.Text == txtFiltro)
            {
                txtFiltrarDni.Text = "";
                txtFiltrarDni.ForeColor = Color.Black;
            }
        }

        private void SetPlaceholder(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtFiltrarDni.Text))
            {
                txtFiltrarDni.Text = txtFiltro;
                txtFiltrarDni.ForeColor = Color.Gray;
                CargarTabla();
            }
        }
        private async void eliminarToolStripMenuItem_Click(object sender, EventArgs e)
        {
            var selectedItem = lvUsuarios.SelectedItems[0];
            int id = int.Parse(selectedItem.Tag.ToString());
            usuarios us = await usuDal.findUsuarioId(id);
            bool eliminado = await datosDal.DeleteUsuarioId(us.id);

            if (eliminado )
            {
                MessageBox.Show("El usuario se eliminara en unos minutos");
            }
            else
            {
                MessageBox.Show("El usuario  no se eliminara");

            }

        }

        private async void TxtFiltroMarca_TextChanged(object sender, EventArgs e)
        {
            string texto = txtFiltrarDni.Text.Trim();

            if (texto.Equals("Introduce el dni"))
            {
                CargarTabla();
            }

            // Evitar filtrar si está mostrando el placeholder (texto gris)
            if (string.IsNullOrEmpty(texto) || texto == txtFiltro || txtFiltrarDni.ForeColor == Color.Gray)
            {
                return; // No hacer nada
            }

            usuarios resultado = await usuDal.findUsuarioDni2(texto);

            lvUsuarios.Items.Clear();

            if (resultado != null)
            {
                ListViewItem item = new ListViewItem(resultado.dni);
                item.SubItems.Add(resultado.nombre);
                item.SubItems.Add(resultado.apellidos);
                item.SubItems.Add(resultado.telefono);
                item.SubItems.Add(resultado.correo);
                item.SubItems.Add(resultado.fechaNacimiento.ToString("dd/MM/yyyy"));
                item.Tag = resultado.id.ToString();
                lvUsuarios.Items.Add(item);
            }
            else
            {
                CargarTabla();
            }
        }



        #endregion


        #region METODOS COMPLEMENTARIOS
        private void configuracion()
        {
            verToolStripMenuItem.Enabled = false;
            eliminarToolStripMenuItem.Enabled = false;
            txtFiltrarDni.Text = "Introduce Dni para filtrar";
            txtFiltrarDni.ForeColor = Color.Gray;
            txtFiltrarDni.Enter += txtFiltrarDni_Enter;
            txtFiltrarDni.Leave += txtFiltrarDni_Leave;
        }

        private void crearUsuario()
        {
            VerUsuario verUsuarioForm = new VerUsuario(this);
            verUsuarioForm.ShowDialog();
        }

        private async void verUsuario()
        {
                var selectedItem = lvUsuarios.SelectedItems[0];
                int id = int.Parse(selectedItem.Tag.ToString());

                usuarios usuario = await usuDal.findUsuarioId(id);

                if (usuario != null)
                {
                    VerUsuario verUsuarioForm = new VerUsuario(usuario,this);
                    verUsuarioForm.ShowDialog();
                    
                }
                else
                {
                    MessageBox.Show("No se ha encontrado el usuario");
                }

        }

        public async Task CargarTabla()
        {
            List<usuarios> listaUsuarios = await usuDal.UsuariosfindAll();
            if (listaUsuarios == null)
            {
                MessageBox.Show("No se pudo cargar la lista de usuarios.");
                return;
            }

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
                item.Tag = usuarios.id.ToString();
                lvUsuarios.Items.Add(item);
            }
        }


        #endregion


    }
}
