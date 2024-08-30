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
    public partial class VerUsuario : Form
    {
        private static  usuarios _usuario;
        private static CarnetsDeConducirDal cdcdal = new CarnetsDeConducirDal();
        public VerUsuario()
        {
            InitializeComponent();
           
        }

        public VerUsuario(usuarios usu)
        {
            InitializeComponent();
            _usuario = usu;
            txtDni.Text = _usuario.dni;
            txtNombre.Text= _usuario.nombre;
            txtApellidos.Text = _usuario.apellidos;
            txtTelefono.Text = _usuario.telefono;
            txtFechaNac.Text = _usuario.fechaNacimiento.ToString();
            txtCorreo.Text = _usuario.correo;
            CargarTabla();
        }

        #region METODOS INTERFAZ
        private void VerUsuario_Load(object sender, EventArgs e)
        {
            
        }

        private void ntsmNuevo_Click(object sender, EventArgs e)
        {
            PLL.CarnetVerModificar CarnetVerModificar = new PLL.CarnetVerModificar(1);
            CarnetVerModificar.Show();
        }

        private void mtsmVer_Click(object sender, EventArgs e)
        {
            PLL.CarnetVerModificar CarnetVerModificar = new PLL.CarnetVerModificar(2);
            CarnetVerModificar.Show();
        }

        private void mtsmEliminar_Click(object sender, EventArgs e)
        {

        }

        private void btnDatos_Click(object sender, EventArgs e)
        {
            PLL.DatosDelUsuario DatosUsu = new PLL.DatosDelUsuario();
            DatosUsu.Show();
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres guardar los datos del usuario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (result == DialogResult.Yes)
            {
                // El usuario hizo clic en "Yes"
                //Se agrega el nuevo carnet


                //Cerramos la ventana
                this.Close();
            }
            else if (result == DialogResult.No)
            {
                // El usuario hizo clic en "No"
                this.Close();
            }
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres salir sin guardar los datos del usuario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (result == DialogResult.Yes)
            {
                // El usuario hizo clic en "Yes"
                //Se agrega el nuevo carnet


                //Cerramos la ventana
                this.Close();
            }
            else if (result == DialogResult.No)
            {
                // El usuario hizo clic en "No"
                this.Close();
            }
        }

        #endregion


        #region METODOS COMPLEMENTARIOS
        private async Task CargarTabla()
        {
            
            List<carnets_de_conducir> listaCarntes = await cdcdal.findAllByUsuario(_usuario.id);
            // Limpiar elementos existentes
            lvMostrarCarnets.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var carnet in listaCarntes)
            {
                ListViewItem item = new ListViewItem(carnet.idTipocarnet.ToString());
                item.SubItems.Add(carnet.fechaExpedicion.ToString("dd/MM/yyyy"));
                item.SubItems.Add(carnet.fechaCaducidad.ToString("dd/MM/yyyy"));
                lvMostrarCarnets.Items.Add(item);
            }
        }


        #endregion

    }
}