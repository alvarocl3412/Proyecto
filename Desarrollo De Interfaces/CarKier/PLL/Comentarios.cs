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
    public partial class Comentarios : Form
    {
        public Comentarios()
        {
            InitializeComponent();
        }
        private void Comentarios_Load(object sender, EventArgs e)
        {
            CargarTabla();
        }

        #region Metodos para la tabla

        private void tsmiNuevo_Click(object sender, EventArgs e)
        {

        }

        private void tsmiVer_Click(object sender, EventArgs e)
        {
            PLL.VerComentario infoComentario = new PLL.VerComentario();
            infoComentario.Show();
        }

        private void tsmiEliminar_Click(object sender, EventArgs e)
        {

        }

        #endregion


        private async Task CargarTabla()
        {
            ComentariosDal vdal = new ComentariosDal();

            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<comentarios> listaComentarios = await vdal.ComentariosfindAll();

            // Limpiar elementos existentes
            lvComentarios.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var comentarios in listaComentarios)
            {

                string nom = await vdal.findUsuarioid(comentarios.idUsuario);
                ListViewItem item = new ListViewItem(nom);


                string vehiculo = await vdal.findVehiculoid(comentarios.idVehiculo);
                item.SubItems.Add(vehiculo);

                string cadena = "no responde";
                if(comentarios.idComentarioRespuesta != null)
                {
                    comentarios comen = await vdal.findComentarioid(comentarios.idComentarioRespuesta);
                    cadena = await vdal.findUsuarioid(comen.idUsuario);
                }
                
                //   string usurespon = await vdal.findUsuarioid(comen.idUsuario);
                item.SubItems.Add(cadena);

                item.SubItems.Add(comentarios.comentario);

                item.SubItems.Add(comentarios.fecha.ToString("dd/MM/yyyy"));
                lvComentarios.Items.Add(item);
            }
        }

    }
}
