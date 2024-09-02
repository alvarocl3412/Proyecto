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
        private static ComentariosDal comenDal = new ComentariosDal();
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static UsuariosDal usuDal = new UsuariosDal();

        public VerComentario()
        {
            InitializeComponent();
            _comentarios = new comentarios();
        }

        public VerComentario(comentarios comentario)
        {
            InitializeComponent();
            _comentarios = comentario;
            MostrarComentario();
        }

        #region METODOS INTERFAZ

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres guardar los datos de los comentarios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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
            txtIdUsuario.Text = usu.nombre + " " + usu.apellidos;

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

        private void ModificarComentario()
        {
            recogerDatos();
        }

        private void recogerDatos()
        {
            _comentarios.fecha = DateTime.Parse(txtFecha.Text);
            _comentarios.comentario = txtComentario.Text;
            //_comentarios.idVehiculo = 
            //_comentarios.idComentarioRespuesta = 
            //_comentarios.idUsuario =            
        }

        private void CrearComentario()
        {

        }

        #endregion
    }
}
