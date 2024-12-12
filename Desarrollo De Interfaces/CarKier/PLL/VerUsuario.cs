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
        private static usuarios _usuario;
        private Usuarios _ventanaPrincipal;
        private static CarnetsDeConducirDal cdcdal = new CarnetsDeConducirDal();
        private static TipoCarnetDal tcDal = new TipoCarnetDal();
        private static UsuariosDal usudal = new UsuariosDal();

        public VerUsuario(Usuarios ventanaPrincipal)
        {
            InitializeComponent();
            _usuario = new usuarios();
            btnGuardar.Text = "Crear";
            txtContrasenia.Visible = true;
            lblContrasenia.Visible = true;
            _ventanaPrincipal = ventanaPrincipal;
        }

        public VerUsuario(usuarios usu,Usuarios ventanaPrincipal)
        {
            InitializeComponent();
            btnGuardar.Text = "Guardar";
            _usuario = usu;

            txtDni.Text = _usuario.dni;
            txtNombre.Text = _usuario.nombre;
            txtApellidos.Text = _usuario.apellidos;
            txtTelefono.Text = _usuario.telefono;
            txtFechaNac.Text = _usuario.fechaNacimiento.ToString("dd-MM-yyyy");
            txtCorreo.Text = _usuario.correo;
            txtContrasenia.Visible = false;
            lblContrasenia.Visible = false;
            _ventanaPrincipal = ventanaPrincipal;


        }

        #region METODOS INTERFAZ
        private void VerUsuario_Load(object sender, EventArgs e)
        {
            CargarTabla();
           
            mtsmVer.Enabled = false;
            mtsmEliminar.Enabled = false;
          
        }

        private void lvMostrarCarnets_SelectedIndexChanged(object sender, EventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvMostrarCarnets.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            mtsmVer.Enabled = hasSelectedItem;
            mtsmEliminar.Enabled = hasSelectedItem;
        }

        private void ntsmNuevo_Click(object sender, EventArgs e)
        {
            int id = _usuario.id;
            PLL.CarnetVerModificar CarnetNUevo = new PLL.CarnetVerModificar(id,this);
            CarnetNUevo.Show();
        }

        private void mtsmVer_Click(object sender, EventArgs e)
        {
            verCarnets();

        }

        private void lvMostrarCarnets_DoubleClick(object sender, EventArgs e)
        {
            verCarnets();
        }

        private async void mtsmEliminar_Click(object sender, EventArgs e)
        {  // Obtener el elemento seleccionado
            var selectedItem = lvMostrarCarnets.SelectedItems[0];

            // Suponiendo que el ID de la empresa está almacenado en el Tag del ListViewItem
            int carnet = int.Parse(selectedItem.Tag.ToString());
            DialogResult result = MessageBox.Show("¿Estás seguro de que quieres eliminar el carnet de conducir  seleccionado?",
          "Confirmación de eliminación", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result == DialogResult.Yes)
            {
                bool eliminado = await cdcdal.deleteCarnetsid(carnet);
                CargarTabla();
            }

        }

        private void btnDatos_Click(object sender, EventArgs e)
        {

            PLL.DatosDelUsuario DatosUsu = new PLL.DatosDelUsuario(_usuario);
            DatosUsu.Show();
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {

            if (btnGuardar.Text.Contains("Crear"))
            {
                DialogResult result = MessageBox.Show("¿Quieres crear el usuario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {

                    crearUsuario();
                    this.Close();
                }
                else
                {
                    this.Close();
                }
            }
            else if(btnGuardar.Text.Contains("Guardar"))
            {
                DialogResult result = MessageBox.Show("¿Quieres guardar los datos del usuario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {

                    guardarCambios();

                    //Cerramos la ventana
                    this.Close();

                    // Llamar al método de actualización en el formulario principal
                    Usuarios formPrincipal = new Usuarios();
                    formPrincipal.CargarTabla();
                }
                else if (result == DialogResult.No)
                {
                    // El usuario hizo clic en "No"
                    this.Close();
                }
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
        public async Task CargarTabla()
        {

            List<carnets_de_conducir> listaCarntes = await cdcdal.findAllByUsuario(_usuario.id);
            // Limpiar elementos existentes
            lvMostrarCarnets.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var carnet in listaCarntes)
            {
                string tipo = await tcDal.findipoCarnetById(carnet.idTipocarnet);
                ListViewItem item = new ListViewItem(tipo);
                item.SubItems.Add(carnet.fechaExpedicion.ToString("dd/MM/yyyy"));
                item.SubItems.Add(carnet.fechaCaducidad.ToString("dd/MM/yyyy"));
                item.Tag = carnet.id.ToString();
                lvMostrarCarnets.Items.Add(item);
            }
        }

        private async Task guardarCambios()
        {
            _usuario.dni = txtDni.Text;
            _usuario.nombre = txtNombre.Text;
            _usuario.apellidos = txtApellidos.Text;
            _usuario.telefono = txtTelefono.Text;
            _usuario.fechaNacimiento = DateTime.Parse(txtFechaNac.Text);
            _usuario.correo = txtCorreo.Text;
           
           bool modificacion = await usudal.UpdateUsuarioId(_usuario);
           if(modificacion)
            {
                MessageBox.Show("Se ha modificado el usuario correctamente");
                await _ventanaPrincipal.CargarTabla();
            } else
            {
                MessageBox.Show("No se ha podido modificar el usuario");

            }

        }

        private async Task crearUsuario()
        {
            _usuario.dni = txtDni.Text;
            _usuario.nombre = txtNombre.Text;
            _usuario.apellidos = txtApellidos.Text;
            _usuario.telefono = txtTelefono.Text;
            _usuario.fechaNacimiento = DateTime.Parse(txtFechaNac.Text);
            _usuario.correo = txtCorreo.Text;
            _usuario.contrasena = txtContrasenia.Text;

            bool creado = await usudal.CrearUsuario(_usuario);

            if (creado)
            {
                MessageBox.Show("Se ha creado el usuario correctamente");
                await _ventanaPrincipal.CargarTabla();
            }
            else
            {
                MessageBox.Show("No se ha podido crear el usuario");

            }
        }

        private async void verCarnets()
        {
            var selectedItem = lvMostrarCarnets.SelectedItems[0];

            // Suponiendo que el ID de la empresa está almacenado en el Tag del ListViewItem
            int carnetid = int.Parse(selectedItem.Tag.ToString());
            carnets_de_conducir carnet = await cdcdal.findAllByID(carnetid);

            PLL.CarnetVerModificar CarnetVerModificar = new PLL.CarnetVerModificar(carnet, this);
            CarnetVerModificar.Show();
        }


        #endregion


    }
}