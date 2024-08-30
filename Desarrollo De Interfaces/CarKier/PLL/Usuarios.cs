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
        private static UsuariosDal usuDal = new UsuariosDal();
        public Usuarios()
        {
            InitializeComponent();
            configuracion();
            CargarTabla();
        }
        private  void Usuarios_Load(object sender, EventArgs e)
        {
          
        }

        private async void CargarTabla()
        {
            List<usuarios> listaUsuarios = await usuDal.UsuariosfindAll();
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
            verUsuario();
        }
        private void lvUsuarios_DoubleClick(object sender, EventArgs e)
        {
            verUsuario();
        }

        //Metodos complementarios

        private void configuracion()
            {
            txtFiltrarDni.Text = "Introduce Dni para filtrar";
            txtFiltrarDni.ForeColor = Color.Gray;
            txtFiltrarDni.Enter += txtFiltrarDni_Enter;
            txtFiltrarDni.Leave += txtFiltrarDni_Leave;
        }

        private async void verUsuario()
        {
            UsuariosDal usu = new UsuariosDal();
            if (lvUsuarios.SelectedItems.Count > 0)
            {
                var selectedItem = lvUsuarios.SelectedItems[0];
                string dni = selectedItem.SubItems[0].Text;

                var usuario = await usu.findUsuarioDni(dni);

                if (usuario != null)
                {
                    VerUsuario verUsuarioForm = new VerUsuario(usuario);
                    verUsuarioForm.ShowDialog();
                }
                else
                {
                    MessageBox.Show("No se ha encontrado el usuario");
                }
            }
            else
            {
                MessageBox.Show("No se ha seleccionado ningún registro");
            }
        }


    }
}
