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
        private static ComentariosDal comenDal = new ComentariosDal();
        private static UsuariosDal usuDal = new UsuariosDal();
        private static VehiculosDal vehiDal = new VehiculosDal();
        public Comentarios()
        {
            InitializeComponent();
        }



        #region METODOS INTERFAZ

        private async void Comentarios_Load(object sender, EventArgs e)
        {
            await CargarTabla();
            tsmiVer.Enabled = false;
            tsmiEliminar.Enabled = false;
        }

        private void lvComentarios_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvComentarios.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            tsmiVer.Enabled = hasSelectedItem;
            tsmiEliminar.Enabled = hasSelectedItem;
        }



        private void tsmiNuevo_Click(object sender, EventArgs e)
        {
            PLL.VerComentario comentario = new PLL.VerComentario();
            comentario.Show();
        }

        private void tsmiVer_Click(object sender, EventArgs e)
        {
            verComentario();
        }

        private void lvComentarios_DoubleClick(object sender, EventArgs e)
        {
            verComentario();
        }

        private async void tsmiEliminar_Click(object sender, EventArgs e)
        {
            // Obtener el elemento seleccionado
            var selectedItem = lvComentarios.SelectedItems[0];

            // Suponiendo que el ID de la empresa está almacenado en el Tag del ListViewItem
            int comentario = int.Parse(selectedItem.Tag.ToString());
            DialogResult result = MessageBox.Show("¿Estás seguro de que quieres eliminar el comentario seleccionado?",
          "Confirmación de eliminación", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result == DialogResult.Yes)
            {
                bool eliminado = await comenDal.deleteComentarioid(comentario);
                CargarTabla();
            }
        }


        #endregion



        #region METODOS COMPLEMENTARIOS

        private async Task CargarTabla()
        { 
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<comentarios> listaComentarios = await comenDal.ComentariosfindAll();

            // Limpiar elementos existentes
            lvComentarios.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var comentarios in listaComentarios)
            {

                string nom = await usuDal.findUsuarioid(comentarios.idUsuario);
                ListViewItem item = new ListViewItem(nom);


                string vehiculo = await vehiDal.findVehiculoid(comentarios.idVehiculo);
                item.SubItems.Add(vehiculo);

                string cadena = "no responde";
                if(comentarios.idComentarioRespuesta != null)
                {
                    comentarios comen = await comenDal.findComentarioId(comentarios.idComentarioRespuesta);
                    cadena = await usuDal.findUsuarioid(comen.idUsuario);
                }
                
                //   string usurespon = await vdal.findUsuarioid(comen.idUsuario);
                item.SubItems.Add(cadena);

                item.SubItems.Add(comentarios.comentario);

                item.SubItems.Add(comentarios.fecha.ToString("dd/MM/yyyy"));
                item.Tag = comentarios.id.ToString();
                lvComentarios.Items.Add(item);
            }
        }

        private async void verComentario()
        {
            var selectedItem = lvComentarios.SelectedItems[0];
            int id = int.Parse(selectedItem.Tag.ToString());

            comentarios comentario = await comenDal.findComentarioId(id);

            if (comentario != null)
            {
                PLL.VerComentario verComentario = new PLL.VerComentario(comentario);
                verComentario.Show();
            }
            else
            {
                MessageBox.Show("No se ha encontrado el usuario");
            }
        }

        #endregion



    }
}