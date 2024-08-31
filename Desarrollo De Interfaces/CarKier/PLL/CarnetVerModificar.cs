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
    public partial class CarnetVerModificar : Form
    {
        public static CarnetsDeConducirDal cdcDal = new CarnetsDeConducirDal();
        public static TipoCarnetDal tcDal = new TipoCarnetDal();
        public static UsuariosDal usuDal = new UsuariosDal();
        public static carnets_de_conducir _carnet;
        public CarnetVerModificar()
        {
            InitializeComponent();
        }

        public CarnetVerModificar(carnets_de_conducir carnet)
        {
            InitializeComponent();
            _carnet = carnet;
            InicializarComponentesVer();


        }

        #region METODOS INTERFAZ

        private void CarnetVerModificar_Load(object sender, EventArgs e)
        {

        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Quieres guardar los cambios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

            if (result == DialogResult.Yes)
            {
                // El usuario hizo clic en "Yes"
            }
            else if (result == DialogResult.No)
            {
                // El usuario hizo clic en "No"
            }

        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Seguro que quieres salir sin guardar los cambios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (result == DialogResult.Yes)
            {
                if (result == DialogResult.Yes)
                {
                    // El usuario hizo clic en "Yes"

                    //Metodo para guardar


                    //MeTtodo para cerrar
                    this.Close();
                }
                else
                {
                    //EL usuario hizo clic en "No"
                    //Cerramos sin guardar
                    this.Close();
                }

            }
        }
        #endregion


        #region METODOS COMPLEMENTARIOS

        private async void InicializarComponentesVer()
        {
            usuarios usur = await usuDal.findUsuarioId(_carnet.idusuario);
            //Le pasamos argumentos
            txtIdUsuario.Text = usur.nombre + " " + usur.apellidos;
            txtTipo.Text = await tcDal.findipoCarnetById(_carnet.idTipocarnet);
            txtFechaExpedicion.Text = _carnet.fechaExpedicion.ToString("dd-MM-yyyy");
            txtFechaCaducidad.Text = _carnet.fechaCaducidad.ToString("dd-MM-yyyy");
        }




        #endregion
    }
}
