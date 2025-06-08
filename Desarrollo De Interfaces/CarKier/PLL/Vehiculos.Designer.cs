namespace CarKier.PLL
{
    partial class Vehiculos
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Vehiculos));
            this.lvVehiculos = new System.Windows.Forms.ListView();
            this.chEmpresa = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chIdUsuario = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chEstado = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chMatriula = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chMarca = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chModelo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chAnio = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chKilometro = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chPrecioVenta = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chPrecioDia = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.nuevoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.verToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.eliminarToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.pcCoche = new System.Windows.Forms.PictureBox();
            this.txtFiltroMarca = new System.Windows.Forms.TextBox();
            this.contextMenuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pcCoche)).BeginInit();
            this.SuspendLayout();
            // 
            // lvVehiculos
            // 
            this.lvVehiculos.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chEmpresa,
            this.chIdUsuario,
            this.chEstado,
            this.chMatriula,
            this.chMarca,
            this.chModelo,
            this.chAnio,
            this.chKilometro,
            this.chPrecioVenta,
            this.chPrecioDia});
            this.lvVehiculos.ContextMenuStrip = this.contextMenuStrip1;
            this.lvVehiculos.Cursor = System.Windows.Forms.Cursors.Default;
            this.lvVehiculos.FullRowSelect = true;
            this.lvVehiculos.GridLines = true;
            this.lvVehiculos.HideSelection = false;
            this.lvVehiculos.Location = new System.Drawing.Point(16, 139);
            this.lvVehiculos.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.lvVehiculos.MultiSelect = false;
            this.lvVehiculos.Name = "lvVehiculos";
            this.lvVehiculos.Size = new System.Drawing.Size(1033, 399);
            this.lvVehiculos.TabIndex = 2;
            this.lvVehiculos.UseCompatibleStateImageBehavior = false;
            this.lvVehiculos.View = System.Windows.Forms.View.Details;
            this.lvVehiculos.SelectedIndexChanged += new System.EventHandler(this.lvVehiculos_SelectedIndexChanged);
            this.lvVehiculos.DoubleClick += new System.EventHandler(this.lvVehiculos_DoubleClick);
            // 
            // chEmpresa
            // 
            this.chEmpresa.Text = "Empresa";
            this.chEmpresa.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chEmpresa.Width = 91;
            // 
            // chIdUsuario
            // 
            this.chIdUsuario.Text = "UsuarioPropietario";
            this.chIdUsuario.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chIdUsuario.Width = 98;
            // 
            // chEstado
            // 
            this.chEstado.Text = "Estado";
            this.chEstado.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chEstado.Width = 87;
            // 
            // chMatriula
            // 
            this.chMatriula.Text = "Matricula";
            this.chMatriula.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chMatriula.Width = 75;
            // 
            // chMarca
            // 
            this.chMarca.Text = "Marca";
            this.chMarca.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chMarca.Width = 68;
            // 
            // chModelo
            // 
            this.chModelo.Text = "Modelo";
            this.chModelo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // chAnio
            // 
            this.chAnio.Text = "Año";
            this.chAnio.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // chKilometro
            // 
            this.chKilometro.Text = "KM";
            this.chKilometro.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chKilometro.Width = 55;
            // 
            // chPrecioVenta
            // 
            this.chPrecioVenta.Text = "Precio Venta";
            this.chPrecioVenta.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chPrecioVenta.Width = 73;
            // 
            // chPrecioDia
            // 
            this.chPrecioDia.Text = "Precio Dia";
            this.chPrecioDia.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chPrecioDia.Width = 70;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.nuevoToolStripMenuItem,
            this.toolStripSeparator2,
            this.verToolStripMenuItem,
            this.toolStripSeparator3,
            this.eliminarToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(137, 100);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(133, 6);
            // 
            // nuevoToolStripMenuItem
            // 
            this.nuevoToolStripMenuItem.Image = global::CarKier.Properties.Resources.action_add_16xMD;
            this.nuevoToolStripMenuItem.Name = "nuevoToolStripMenuItem";
            this.nuevoToolStripMenuItem.Size = new System.Drawing.Size(136, 26);
            this.nuevoToolStripMenuItem.Text = "Nuevo";
            this.nuevoToolStripMenuItem.Click += new System.EventHandler(this.nuevoToolStripMenuItem_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(133, 6);
            // 
            // verToolStripMenuItem
            // 
            this.verToolStripMenuItem.Image = global::CarKier.Properties.Resources.ojo1;
            this.verToolStripMenuItem.Name = "verToolStripMenuItem";
            this.verToolStripMenuItem.Size = new System.Drawing.Size(136, 26);
            this.verToolStripMenuItem.Text = "Ver";
            this.verToolStripMenuItem.Click += new System.EventHandler(this.verToolStripMenuItem_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(133, 6);
            // 
            // eliminarToolStripMenuItem
            // 
            this.eliminarToolStripMenuItem.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.eliminarToolStripMenuItem.Name = "eliminarToolStripMenuItem";
            this.eliminarToolStripMenuItem.Size = new System.Drawing.Size(136, 26);
            this.eliminarToolStripMenuItem.Text = "Eliminar";
            this.eliminarToolStripMenuItem.Click += new System.EventHandler(this.eliminarToolStripMenuItem_Click);
            // 
            // pcCoche
            // 
            this.pcCoche.BackColor = System.Drawing.Color.Transparent;
            this.pcCoche.Image = global::CarKier.Properties.Resources.coche;
            this.pcCoche.Location = new System.Drawing.Point(16, 7);
            this.pcCoche.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.pcCoche.Name = "pcCoche";
            this.pcCoche.Size = new System.Drawing.Size(187, 127);
            this.pcCoche.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pcCoche.TabIndex = 3;
            this.pcCoche.TabStop = false;
            // 
            // txtFiltroMarca
            // 
            this.txtFiltroMarca.Location = new System.Drawing.Point(333, 69);
            this.txtFiltroMarca.Name = "txtFiltroMarca";
            this.txtFiltroMarca.Size = new System.Drawing.Size(227, 22);
            this.txtFiltroMarca.TabIndex = 15;
            // 
            // Vehiculos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1067, 554);
            this.Controls.Add(this.txtFiltroMarca);
            this.Controls.Add(this.pcCoche);
            this.Controls.Add(this.lvVehiculos);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "Vehiculos";
            this.Text = "Vehiculos";
            this.Load += new System.EventHandler(this.Vehiculos_Load);
            this.contextMenuStrip1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pcCoche)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView lvVehiculos;
        private System.Windows.Forms.ColumnHeader chEmpresa;
        private System.Windows.Forms.ColumnHeader chIdUsuario;
        private System.Windows.Forms.ColumnHeader chEstado;
        private System.Windows.Forms.ColumnHeader chMatriula;
        private System.Windows.Forms.ColumnHeader chMarca;
        private System.Windows.Forms.ColumnHeader chModelo;
        private System.Windows.Forms.ColumnHeader chAnio;
        private System.Windows.Forms.ColumnHeader chKilometro;
        private System.Windows.Forms.ColumnHeader chPrecioVenta;
        private System.Windows.Forms.ColumnHeader chPrecioDia;
        private System.Windows.Forms.PictureBox pcCoche;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem nuevoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem verToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem eliminarToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.TextBox txtFiltroMarca;
    }
}