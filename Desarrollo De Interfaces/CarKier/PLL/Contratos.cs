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
        public Contratos()
        {
            InitializeComponent();
        }
        private void Contratos_Load(object sender, EventArgs e)
        {
            CargarTabla();
        }

        #region Funcionalida Filtrar
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

        #region Funcionalida metodos tabla NUEVO,VER,ELIMINAR
        private void tsmNuevo_Click(object sender, EventArgs e)
        {

        }

        private void tsmVer_Click(object sender, EventArgs e)
        {
            PLL.VerContrato infoContrato = new PLL.VerContrato();
            infoContrato.Show();
        }

        private void tsmEliminar_Click(object sender, EventArgs e)
        {

        }


        #endregion


        private async Task CargarTabla()
        {
            ContratosDal vdal = new ContratosDal();
            
            //Creamos la lista y llamamos al metodo para pedir los vehiuclos
            List<contratos> listaContratos = await vdal.ContratosfindAll();

            // Limpiar elementos existentes
            lvContratos.Items.Clear();

            // Cargar los datos en el ListView
            foreach (var contra in listaContratos)
            {
              
                ListViewItem item = new ListViewItem(contra.idvehiculo.ToString());

                item.SubItems.Add(contra.idcliente.ToString());

                item.SubItems.Add(contra.idEstado.ToString());

                string seguro = await vdal.findSegurosid(contra.idSeguro);
                item.SubItems.Add(seguro);

                item.SubItems.Add(contra.fechaInicio.ToString("dd/MM/yyyy"));
                item.SubItems.Add(contra.fechaFin.ToString("dd/MM/yyyy"));
                item.SubItems.Add(contra.precioDia.ToString());
                item.SubItems.Add(contra.precioTotal.ToString());
                item.SubItems.Add(contra.pagado ? "SI" : "NO");
                lvContratos.Items.Add(item);
            }
        }


    }
}
