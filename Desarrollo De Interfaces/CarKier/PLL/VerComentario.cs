using CarKier.DAL;
using CarKier.Modelo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CarKier.PLL
{
    public partial class VerComentario : Form
    {
        private static comentarios _comentarios;
        private static usuarios usuAdmin;
        private static ComentariosDal comenDal = new ComentariosDal();
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static UsuariosDal usuDal = new UsuariosDal();
        private Comentarios _actualizarComentarios;

        public VerComentario(Comentarios ventanaPrincipal)
        {
            InitializeComponent();
            _comentarios = new comentarios();
            btnGuardar.Text = "Crear";
            lblIdUsuario.Text = "DNI";

            lblIdRespuesta.Visible = false;
            txtRespuesta.Visible = false;
            txtFecha.Visible = false;
            lblFecha.Visible = false;
            _actualizarComentarios = ventanaPrincipal;
        }
        public VerComentario(usuarios us, Comentarios ventanaPrincipal)
        {
            InitializeComponent();
            usuAdmin = us;
            _comentarios = new comentarios();
            btnGuardar.Text = "Crear";
            txtIdUsuario.Text = usuAdmin.nombre + " - " + usuAdmin.apellidos;
            txtRespuesta.Visible = false;
            lblIdRespuesta.Visible = false;
            txtIdUsuario.Enabled = false;
            lblFecha.Visible = false;
            txtFecha.Visible = false;
            _actualizarComentarios = ventanaPrincipal;

        }

        public VerComentario(comentarios comen, usuarios us, Comentarios ventanaPrincipal)
        {
            InitializeComponent();
            _comentarios = comen;
            usuAdmin = us;
            MostrarComentario();
            txtRespuesta.Enabled = false;
            txtIdUsuario.Enabled = false;
            _actualizarComentarios = ventanaPrincipal;
        }

        #region METODOS INTERFAZ

        private async void btnGuardar_Click(object sender, EventArgs e)
        {

            var matricula = txtMatriculaComentario.Text;

            if (btnGuardar.Text.Contains("Crear"))
            {
                DialogResult result = MessageBox.Show("¿Quieres crear el nuevo comentarios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo carnet
                    CrearComentario(matricula);

                    //Cerramos la ventana
                    this.Close();
                }
                else if (result == DialogResult.No)
                {
                    // El usuario hizo clic en "No"
                    this.Close();
                }
            }
            else
            {
                DialogResult result = MessageBox.Show("¿Quieres guardar los datos de los comentarios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo carnet
                    ModificarComentario();

                    //Cerramos la ventana
                    this.Close();
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
            DialogResult result = MessageBox.Show("¿Quieres salir sin guardar los datos de los comentario?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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

        private async void MostrarComentario()
        {
            usuarios usu = await usuDal.findUsuarioId(_comentarios.idUsuario);
            txtIdUsuario.Text = usu.nombre + " - " + usu.apellidos;

            string cadena = "nadie";
            if (_comentarios.idComentarioRespuesta != null)
            {
                comentarios comen = await comenDal.findComentarioId(_comentarios.idComentarioRespuesta);
                cadena = await usuDal.findUsuarioid(comen.idUsuario);
            }
            txtRespuesta.Text = cadena;

            vehiculos vehi = await vehiculoDal.findVehiculoId(_comentarios.idVehiculo);
            txtMatriculaComentario.Text = vehi.matricula;

            txtComentario.Text = _comentarios.comentario;
            txtFecha.Text = _comentarios.fecha;
        }

        private async void ModificarComentario()
        {
            //Actualizamos los datos los usuarios no se podra modifcar ni el id de respuesta 
            _comentarios.fecha = txtFecha.Text;
            _comentarios.comentario = txtComentario.Text;

            vehiculos vehi = await vehiculoDal.findVehiculoMatricula(txtMatriculaComentario.Text);
            if (vehi != null)
            {
                _comentarios.idVehiculo = vehi.id;
            }
            else
            {
                MessageBox.Show("se cerrara la ventana no se guarda");
                return;
            }


            bool modificado = await comenDal.UpdateComentario(_comentarios);
            if (modificado)
            {
                MessageBox.Show("Se ha modificado correctamente");
                _actualizarComentarios.CargarTabla();
            }
            else
            {
                MessageBox.Show("No se ha podido modificar correctamente");
            }
        }


        private async void CrearComentario(string matricula)
        {

            var matriculaComent = matricula;
            _comentarios.comentario = txtComentario.Text;

            int? ID = await usuDal.findUsuarioDni(txtIdUsuario.Text);
            if (ID != null)
            {
                _comentarios.idUsuario = ID.Value;
            }

            int? IDCOMEN = await usuDal.findUsuarioDni(txtRespuesta.Text);
            _comentarios.idComentarioRespuesta = null;
            if (IDCOMEN != null)
            {
                _comentarios.idComentarioRespuesta = IDCOMEN;
            }

            //var matricula = txtMatriculaComentario.Text;
            //Console.WriteLine("MAtricula: " + matricula);

            // Verifica si la matrícula está vacía o contiene solo espacios en blanco
            if (string.IsNullOrEmpty(matricula))
            {
                MessageBox.Show("La matrícula del vehículo no puede estar vacía.");
                return;
            }

            // Depuración: verificar el valor que se obtiene del campo de matrícula
            Console.WriteLine($"Matrícula del vehículo ingresada: '{txtMatriculaComentario.Text}'");

            // Buscar el vehículo por matrícula
            vehiculos vehi = await vehiculoDal.findVehiculoMatricula(matricula);

            if (vehi != null)
            {
                _comentarios.idVehiculo = vehi.id;
            }
            else
            {
                MessageBox.Show("No se encontró el vehículo con la matrícula proporcionada. Se cerrará la ventana y no se guardará el comentario.");
                return;
            }

            // Crear el comentario en la base de datos
            bool creado = await comenDal.CrearComentario(_comentarios);

            if (creado)
            {
                MessageBox.Show("Se ha creado el comentario correctamente.");
                _actualizarComentarios.CargarTabla();
            }
            else
            {
                MessageBox.Show("No se ha podido crear el comentario.");
            }
            
        }


        #endregion
    }
}
