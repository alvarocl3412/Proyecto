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
    public partial class VerComentario : Form
    {
        private static comentarios _comentarios;
        private static usuarios usuAdmin;
        private static ComentariosDal comenDal = new ComentariosDal();
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static UsuariosDal usuDal = new UsuariosDal();


        public VerComentario()
        {
            InitializeComponent();
        }
        public VerComentario(usuarios us)
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

        }

        public VerComentario(comentarios comen, usuarios us)
        {
            InitializeComponent();
            _comentarios = comen;
            usuAdmin = us;
            MostrarComentario();
            txtRespuesta.Enabled = false;
            txtIdUsuario.Enabled = false;
        }

        #region METODOS INTERFAZ

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            if (btnGuardar.Text.Contains("Crear"))
            {
                DialogResult result = MessageBox.Show("¿Quieres crear el nuevo comentarios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"
                    //Se agrega el nuevo carnet
                    CrearComentario();

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
            txtIdVehiculo.Text = vehi.matricula;

            txtComentario.Text = _comentarios.comentario;
            txtFecha.Text = _comentarios.fecha.ToString("dd/MM/yyyy");
        }

        private async void ModificarComentario()
        {
            //Actualizamos los datos los usuarios no se podra modifcar ni el id de respuesta 
            _comentarios.fecha = DateTime.Parse(txtFecha.Text);
            _comentarios.comentario = txtComentario.Text;

            vehiculos vehi = await vehiculoDal.findVehiculoMatricula(txtIdVehiculo.Text);
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
            }
            else
            {
                MessageBox.Show("No se ha podido modificar correctamente");
            }
        }


        private async void CrearComentario()
        {
            _comentarios.comentario = txtComentario.Text;
            _comentarios.idUsuario = usuAdmin.id;
            _comentarios.idComentarioRespuesta = null;
           

            if (string.IsNullOrWhiteSpace(txtIdVehiculo.Text))
            {
                MessageBox.Show("La matrícula del vehículo no puede estar vacía.");
                return;
            }

            vehiculos vehi = await vehiculoDal.findVehiculoMatricula(txtIdVehiculo.Text);

            if (vehi != null)
            {
                _comentarios.idVehiculo = vehi.id;
            }
            else
            {
                MessageBox.Show("No se encontró el vehículo con la matrícula proporcionada. Se cerrará la ventana y no se guardará el comentario.");
                return;
            }



            bool creado = await comenDal.CrearComentario(_comentarios);

            if (creado)
            {
                MessageBox.Show("Se ha creado el comentario correctamente.");
            }
            else
            {
                MessageBox.Show("No se ha podido crear el comentario.");
            }
        }

        #endregion

    }
}
