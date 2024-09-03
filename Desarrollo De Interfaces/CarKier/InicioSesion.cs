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

namespace CarKier
{
    public partial class InicioSesion : Form
    {
        private bool mostrarContraseña = false;
        string apiUrl = "https://CarKier/crear";
        public InicioSesion()
        {
            InitializeComponent();
            this.Resize += InicioSesion_SizeChanged;
        }

        //Metodos funcionalidades

        //Boton de inicio sesio comprobar que no estan los campos en blancos, comprobar que existe el
        //Usuario y comprobar que es administrador y si es iniciar sesion correctamente si no mensaje de error
        private async void btnInicioSesion_Click(object sender, EventArgs e)
        {
              if (validarDatos())
              {
                string correo = txtCorreoElectronico.Text;
                string contrasena = txtContrasena.Text;

                InicioSesionDal inicioSesion = new InicioSesionDal();

                // Llamar al método LoginUsuarioAsync y obtener el objeto Usuario
                usuarios usuario = await inicioSesion.Login(correo, contrasena);

                    if (usuario != null)
                    {
                    MessageBox.Show($"Bienvenido, {usuario.nombre}");
                    this.Hide();

                    // Asignar un valor al entero guardado
                    guardarUsuario.numeroGuardado = usuario.id;
                   

                    Principal infoDesarrollador = new Principal();
                    infoDesarrollador.Show();

                    // Cerrar la ventana de inicio de sesión
                   
                }
                    else
                    {
                    // Lógica para un inicio de sesión fallido
                    MessageBox.Show("Correo o contraseña incorrectos.");
                    }
            }
        }

        private void btnSalir_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        //Para mostrar la contraseña
        private void pbOjo_Click(object sender, EventArgs e)
        {
            // Alternar entre mostrar y ocultar la contraseña
            mostrarContraseña = !mostrarContraseña;

            if (mostrarContraseña)
            {
                // Mostrar contraseña
                txtContrasena.PasswordChar = '\0';
            }
            else
            {
                // Ocultar contraseña
                txtContrasena.PasswordChar = '*';
            }
        }


        private void InicioSesion_SizeChanged(object sender, EventArgs e)
        {
            int espacioEntreControles = 0;
            //Calcular los Labels
            lblCorreo.Left = (ClientSize.Width - lblCorreo.Width) / 2;
            lblContraseña.Left = (ClientSize.Width - lblContraseña.Width) / 2;


            // Calcular Los TextBox y la imagen del ojo para la contraseña
            txtCorreoElectronico.Left = (ClientSize.Width - txtCorreoElectronico.Width) / 2;
            int nuevoLeftTxtContraseña = (ClientSize.Width - txtContrasena.Width) / 2;
            txtContrasena.Left = nuevoLeftTxtContraseña;
            pbOjo.Left = nuevoLeftTxtContraseña + txtContrasena.Width + espacioEntreControles;

            //Calcular los botones
            btnInicioSesion.Left = (ClientSize.Width - btnInicioSesion.Width) / 2;
            btnSalir.Left = (ClientSize.Width - btnSalir.Width) / 2;

        }

  

        //Metodos extras
        public bool validarDatos()
        {
            if (string.IsNullOrEmpty(txtCorreoElectronico.Text))
            {
                MessageBox.Show("Debes introducir el correo electronico", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtCorreoElectronico.Focus();
                return false;
            }

            if (string.IsNullOrEmpty(txtContrasena.Text))
            {
                MessageBox.Show("Debes introducir la contraseña", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtContrasena.Focus();
                return false;
            }

            return true;

        }


    }
}
