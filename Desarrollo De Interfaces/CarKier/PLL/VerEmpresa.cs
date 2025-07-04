﻿using CarKier.DAL;
using CarKier.Modelo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CarKier.PLL
{
    public partial class VerEmpresa : Form
    {
        private static empresas _empresa;
        private Empresas _ventanaPrincipal;
        private static EmpresasDal emprDal = new EmpresasDal();
        public VerEmpresa(Empresas ventanaPrincipal)
        {
            InitializeComponent();
            _empresa = new empresas();
            btnGuardar.Text = "Crear";
            _ventanaPrincipal = ventanaPrincipal;
        }

        public VerEmpresa(empresas empr, Empresas ventanaPrincipal)
        {
            InitializeComponent();
            _empresa = empr;
            mostrarDatos();
            _ventanaPrincipal = ventanaPrincipal;

        }

        #region METODOS INTERFAZ
        private void btnGuardar_Click(object sender, EventArgs e)
        {
            if (btnGuardar.Text.Contains("Crear"))
            {
                crear();
                this.Close();
            }
            else
            {
                DialogResult result = MessageBox.Show("¿Quieres guardar  los datos de las empresas?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {

                    //Se agrega la nueva empresa
                    modificarDatos();

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
            DialogResult result = MessageBox.Show("¿Quieres salir sin guardar los datos de las empresas?", "Confirmar Guardado", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
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

        public void mostrarDatos()
        {
            txtNombre.Text = _empresa.nombre;
            txtDireccion.Text = _empresa.direccion;
            txtTelefono.Text = _empresa.telefono;
            txtOfreceCoches.Text = _empresa.ofreceCoches.ToString();
            txtCorreo.Text = _empresa.correoElectronico;
            txtDescripcion.Text = _empresa.descripcion;
        }

        public async void modificarDatos()
        {
            _empresa.nombre = txtNombre.Text;
            _empresa.direccion = txtDescripcion.Text;
            _empresa.telefono = txtTelefono.Text;
            _empresa.ofreceCoches = int.Parse(txtOfreceCoches.Text);
            _empresa.correoElectronico = txtCorreo.Text;
            _empresa.descripcion = txtDescripcion.Text;

            bool modificado = await emprDal.UpdateEmpresa(_empresa);
            if (modificado)
            {
                MessageBox.Show("Se ha modificado correctamente");
                await _ventanaPrincipal.CargarTabla();
            }
            else
            {
                MessageBox.Show("No se ha  podido modificar");

            }
        }

        public async void crear()
        {
            _empresa.nombre = txtNombre.Text;
            _empresa.direccion = txtDireccion.Text;
            _empresa.telefono = txtTelefono.Text;
            if (txtOfreceCoches.Text.Length > 0)
            {
                _empresa.ofreceCoches = int.Parse(txtOfreceCoches.Text);

            }

            _empresa.correoElectronico = txtCorreo.Text;
            _empresa.descripcion = txtDescripcion.Text;

            bool creado = await emprDal.CrearUsuario(_empresa);
            if (creado)
            {
                MessageBox.Show("Se ha modificado correctamente");
                await _ventanaPrincipal.CargarTabla();

            }
            else
            {
                MessageBox.Show("No se ha  podido modificar");

            }

        }

        private void recogerDatos()
        {
            _empresa.nombre = txtNombre.Text;
            _empresa.direccion = txtDescripcion.Text;
            _empresa.telefono = txtTelefono.Text;
            _empresa.ofreceCoches = int.Parse(txtOfreceCoches.Text);
            _empresa.correoElectronico = txtCorreo.Text;
            _empresa.descripcion = txtDescripcion.Text;
        }



        #endregion

    }
}
