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
        private VerUsuario _ventanaPrincipal;
        public static TipoCarnetDal tcDal = new TipoCarnetDal();
        public static UsuariosDal usuDal = new UsuariosDal();
        public static usuarios usuNuevo = new usuarios();
        public static carnets_de_conducir _carnet;
        public CarnetVerModificar(VerUsuario ventanaPrincipal)
        {
            InitializeComponent();
            _carnet = new carnets_de_conducir();
            _ventanaPrincipal = ventanaPrincipal;
        }

        public CarnetVerModificar(carnets_de_conducir carnet, VerUsuario ventanaPrincipal)
        {
            InitializeComponent();
            _carnet = carnet;
            txtIdUsuario.Enabled = false;
            _ventanaPrincipal = ventanaPrincipal;
            InicializarComponentesVer();


        }

        public CarnetVerModificar(int id, VerUsuario ventanaPrincipal)
        {
            InitializeComponent();
            _carnet = new carnets_de_conducir();
            btnGuardar.Text = " CREAR";
            usuNuevo.id = id;
            _ventanaPrincipal = ventanaPrincipal;
            InicializarComponentesCrear(id);
        }

        #region METODOS INTERFAZ

        private void CarnetVerModificar_Load(object sender, EventArgs e)
        {
            CargarTiposDeCarnet();
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            if (btnGuardar.Text.Contains("CREAR"))
            {
                DialogResult result = MessageBox.Show("¿Quieres crear el nuevo carnet de conducir?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

                if (result == DialogResult.Yes)
                {
                    crearCarnet();
                    this.Close();
                }
                else if (result == DialogResult.No)
                {
                    
                }
            }
            else
            {
                DialogResult result = MessageBox.Show("¿Quieres guardar los cambios?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

                if (result == DialogResult.Yes)
                {
                    guardarModificacion();
                    this.Close();
                }
                else if (result == DialogResult.No)
                {
                    this.Close();
                }
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


        private void cbTipo_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cbTipo.SelectedItem != null && ((tipo_carnet)cbTipo.SelectedItem).nombre == "Otro")
            {
                MostrarDialogoCrearNuevoTipo();
            }
        }

        private async void eliminarToolStripMenuItem_Click(object sender, EventArgs e)
        {
            int texto = (int)cbTipo.SelectedValue;
            bool eliminado = await tcDal.deleteTipoCanrteid(texto);
            CargarTiposDeCarnet();

        }



        #endregion


        #region METODOS COMPLEMENTARIOS

        private async void InicializarComponentesVer()
        {
            usuarios usur = await usuDal.findUsuarioId(_carnet.idusuario);
            //Le pasamos argumentos
            txtIdUsuario.Text = usur.nombre + " " + usur.apellidos;
            cbTipo.SelectedItem = _carnet.idTipocarnet;
            txtFechaExpedicion.Text = _carnet.fechaExpedicion.ToString("dd-MM-yyyy");
            txtFechaCaducidad.Text = _carnet.fechaCaducidad.ToString("dd-MM-yyyy");


        }

        private async void InicializarComponentesCrear(int id)
        {
            usuarios us = await usuDal.findUsuarioId(id);
            //Le pasamos argumentos
            txtIdUsuario.Text = us.nombre + " " + us.apellidos;
            txtIdUsuario.Enabled = false;
            lblFechaCaducidad.Visible = false;
            txtFechaCaducidad.Visible = false;
        }

        private async void CargarTiposDeCarnet()
        {
            List<tipo_carnet> tiposDeCarnet = await tcDal.findTiposDeCarnetAll();
            tiposDeCarnet.Add(new tipo_carnet { nombre = "Otro", id = -1 });


            cbTipo.DataSource = tiposDeCarnet;
            cbTipo.DisplayMember = "Nombre";  // Lo que se muestra en el ComboBox
            cbTipo.ValueMember = "Id";  // El valor asociado (id)
        }

        private void MostrarDialogoCrearNuevoTipo()
        {
            Form dialogo = new Form
            {
                Text = "Crear nuevo tipo de carnet",
                Size = new Size(300, 150)
            };

            Label lblMensaje = new Label { Left = 50, Top = 20, Text = "Introduce el nuevo tipo de carnet:" };
            TextBox txtNombreNuevoTipo = new TextBox { Left = 50, Top = 50, Width = 200 };
            Button btnSi = new Button { Text = "Sí", Left = 50, Width = 100, Top = 80 };
            Button btnNo = new Button { Text = "No", Left = 150, Width = 100, Top = 80 };

            btnSi.Click += async (sender, e) =>
            {
                string nuevoTipoNombre = txtNombreNuevoTipo.Text.Trim();
                if (!string.IsNullOrEmpty(nuevoTipoNombre))
                {
                    bool resultado = await tcDal.CrearTipoCarnet(nuevoTipoNombre);

                    if (resultado)
                    {
                        MessageBox.Show("Nuevo tipo creado con éxito.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);

                        // Recargar el ComboBox con el nuevo tipo
                        CargarTiposDeCarnet();
                    }
                    else
                    {
                        MessageBox.Show("Error al crear el nuevo tipo.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
                dialogo.Close();
            };

            btnNo.Click += (sender, e) => dialogo.Close();

            dialogo.Controls.Add(lblMensaje);
            dialogo.Controls.Add(txtNombreNuevoTipo);
            dialogo.Controls.Add(btnSi);
            dialogo.Controls.Add(btnNo);

            dialogo.ShowDialog();
        }

        private async void crearCarnet()
        {
            _carnet.idusuario = usuNuevo.id;
            _carnet.idTipocarnet = (int) cbTipo.SelectedValue;
            _carnet.fechaExpedicion = DateTime.Parse(txtFechaExpedicion.Text);
            bool creado = await cdcDal.CrearCarnet(_carnet);
            if (creado)
            {
                MessageBox.Show("Se ha creado correctamente");
               await _ventanaPrincipal.CargarTabla();
            } else
            {
                MessageBox.Show("No se ha creado correctamente");

            }

        }

        private async void guardarModificacion()
        {
            _carnet.fechaExpedicion = DateTime.Parse(txtFechaExpedicion.Text);
            _carnet.idTipocarnet = (int)cbTipo.SelectedValue;
            bool creado = await cdcDal.CrearCarnet(_carnet);
            if (creado)
            {
                MessageBox.Show("Se ha modificado correctamente");
                await _ventanaPrincipal.CargarTabla();
            }
            else
            {
                MessageBox.Show("No se ha podido modificadar correctamente");

            }
        }



        #endregion


    }
}
