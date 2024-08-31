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
    public partial class Contratos : Form
    {
        private static ContratosDal contratoDal = new ContratosDal();
        private static VehiculosDal vehiculoDal = new VehiculosDal();
        private static UsuariosDal usuDal = new UsuariosDal();
        private static EstadoContratoDal estadoContDal = new EstadoContratoDal();
        private static SegurosDal seguroDal = new SegurosDal();
        public Contratos()
        {
            InitializeComponent();
        }


        #region METODOS INTERFAZ
        private void Contratos_Load(object sender, EventArgs e)
        {
            CargarTabla();
            tsmVer.Enabled = false;
            tsmEliminar.Enabled = false;
        }

        private void lvContratos_SelectedIndexChanged(object sender, EventArgs e)
        {
            // para saber si hay algo seleccionado
            bool hasSelectedItem = lvContratos.SelectedItems.Count > 0;

            // para habilitar o sehabilitar las funciones de ver y eliminar
            tsmVer.Enabled = hasSelectedItem;
            tsmEliminar.Enabled = hasSelectedItem;
        }

        private void lvContratos_DoubleClick(object sender, EventArgs e)
        {

        }

        private void tsmNuevo_Click(object sender, EventArgs e)
        {

        }

        private void tsmVer_Click(object sender, EventArgs e)
        {
            PLL.VerContrato infoContrato = new PLL.VerContrato();
            infoContrato.Show();
        }

        private async void tsmEliminar_Click(object sender, EventArgs e)
        {
            // Obtener el elemento seleccionado
            var selectedItem = lvContratos.SelectedItems[0];

            // Suponiendo que el ID de la empresa está almacenado en el Tag del ListViewItem
            int contrato = int.Parse(selectedItem.Tag.ToString());
            DialogResult result = MessageBox.Show("¿Estás seguro de que quieres eliminar el contrato seleccionado?",
          "Confirmación de eliminación", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result == DialogResult.Yes)
            {
                bool eliminado = await contratoDal.deleteContratoId(contrato);
                CargarTabla();
            }
        }

        private void txtFiltrarContratos_Enter(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (txt.Text == "Introduce el id del cliente para filtrar")
            {
                txt.Text = "";
                txt.ForeColor = Color.Black;
            }
        }

        private void txtFiltrarContratos_Leave(object sender, EventArgs e)
        {
            TextBox txt = sender as TextBox;
            if (string.IsNullOrWhiteSpace(txt.Text))
            {
                txt.Text = "Introduce el id del cliente para filtrar";
                txt.ForeColor = Color.Gray;
            }
        }

        #endregion

        #region METODOS COMPLEMENTARIOS


        private async Task CargarTabla()
        {
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<contratos> listaContratos = await contratoDal.ContratosfindAll();

            // Limpiar elementos existentes
            lvContratos.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var contra in listaContratos)
            {

                string matricula = await vehiculoDal.findVehiculoid(contra.idvehiculo);
                ListViewItem item = new ListViewItem(matricula);


                string nom = await usuDal.findUsuarioid(contra.idcliente);
                item.SubItems.Add(nom);

                string estado = await estadoContDal.findEstadoContratoid(contra.idEstado);
                item.SubItems.Add(estado);

                string seguro = await seguroDal.findSegurosid(contra.idSeguro);
                item.SubItems.Add(seguro);

                item.SubItems.Add(contra.fechaInicio.ToString("dd/MM/yyyy"));
                item.SubItems.Add(contra.fechaFin.ToString("dd/MM/yyyy"));
                item.SubItems.Add(contra.precioDia.ToString());
                item.SubItems.Add(contra.precioTotal.ToString());
                item.SubItems.Add(contra.pagado ? "SI" : "NO");
                item.Tag = contra.id.ToString();
                lvContratos.Items.Add(item);
            }
        }




        #endregion


    }
}
