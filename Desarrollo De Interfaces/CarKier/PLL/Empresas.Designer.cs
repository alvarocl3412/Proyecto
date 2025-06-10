namespace CarKier.PLL
{
    partial class Empresas
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Empresas));
            this.lvEmpresas = new System.Windows.Forms.ListView();
            this.chNombre = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chDescripcion = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chDireccion = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chTelefono = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chCorreo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chOfreceCoches = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.xmsMenuTablaEmpresa = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiVer = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiEliminar = new System.Windows.Forms.ToolStripMenuItem();
            this.txtFiltrar = new System.Windows.Forms.TextBox();
            this.pbImgEmpresa = new System.Windows.Forms.PictureBox();
            this.xmsMenuTablaEmpresa.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbImgEmpresa)).BeginInit();
            this.SuspendLayout();
            // 
            // lvEmpresas
            // 
            this.lvEmpresas.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chNombre,
            this.chDescripcion,
            this.chDireccion,
            this.chTelefono,
            this.chCorreo,
            this.chOfreceCoches});
            this.lvEmpresas.ContextMenuStrip = this.xmsMenuTablaEmpresa;
            this.lvEmpresas.Cursor = System.Windows.Forms.Cursors.Default;
            this.lvEmpresas.FullRowSelect = true;
            this.lvEmpresas.GridLines = true;
            this.lvEmpresas.HideSelection = false;
            this.lvEmpresas.Location = new System.Drawing.Point(16, 162);
            this.lvEmpresas.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.lvEmpresas.MultiSelect = false;
            this.lvEmpresas.Name = "lvEmpresas";
            this.lvEmpresas.Size = new System.Drawing.Size(1023, 376);
            this.lvEmpresas.TabIndex = 3;
            this.lvEmpresas.UseCompatibleStateImageBehavior = false;
            this.lvEmpresas.View = System.Windows.Forms.View.Details;
            this.lvEmpresas.SelectedIndexChanged += new System.EventHandler(this.lvEmpresas_SelectedIndexChanged);
            this.lvEmpresas.DoubleClick += new System.EventHandler(this.lvEmpresas_DoubleClick);
            // 
            // chNombre
            // 
            this.chNombre.Text = "Nombre";
            this.chNombre.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chNombre.Width = 120;
            // 
            // chDescripcion
            // 
            this.chDescripcion.Text = "Descipcion";
            this.chDescripcion.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chDescripcion.Width = 132;
            // 
            // chDireccion
            // 
            this.chDireccion.Text = "Direccion";
            this.chDireccion.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chDireccion.Width = 130;
            // 
            // chTelefono
            // 
            this.chTelefono.Text = "Telefono";
            this.chTelefono.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chTelefono.Width = 100;
            // 
            // chCorreo
            // 
            this.chCorreo.Text = "correo_electronico";
            this.chCorreo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chCorreo.Width = 130;
            // 
            // chOfreceCoches
            // 
            this.chOfreceCoches.Text = "Ofrece Coches";
            this.chOfreceCoches.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chOfreceCoches.Width = 100;
            // 
            // xmsMenuTablaEmpresa
            // 
            this.xmsMenuTablaEmpresa.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.xmsMenuTablaEmpresa.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.tsmiNuevo,
            this.toolStripSeparator2,
            this.tsmiVer,
            this.toolStripSeparator3,
            this.tsmiEliminar});
            this.xmsMenuTablaEmpresa.Name = "xmsMenuTablaEmpresa";
            this.xmsMenuTablaEmpresa.Size = new System.Drawing.Size(137, 100);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(133, 6);
            // 
            // tsmiNuevo
            // 
            this.tsmiNuevo.Image = global::CarKier.Properties.Resources.action_add_16xMD;
            this.tsmiNuevo.Name = "tsmiNuevo";
            this.tsmiNuevo.Size = new System.Drawing.Size(136, 26);
            this.tsmiNuevo.Text = "Nuevo";
            this.tsmiNuevo.Click += new System.EventHandler(this.tsmiNuevo_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(133, 6);
            // 
            // tsmiVer
            // 
            this.tsmiVer.Image = global::CarKier.Properties.Resources.ojo1;
            this.tsmiVer.Name = "tsmiVer";
            this.tsmiVer.Size = new System.Drawing.Size(136, 26);
            this.tsmiVer.Text = "Ver";
            this.tsmiVer.Click += new System.EventHandler(this.tsmiVer_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(133, 6);
            // 
            // tsmiEliminar
            // 
            this.tsmiEliminar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.tsmiEliminar.Name = "tsmiEliminar";
            this.tsmiEliminar.Size = new System.Drawing.Size(136, 26);
            this.tsmiEliminar.Text = "Eliminar";
            this.tsmiEliminar.Click += new System.EventHandler(this.tsmiEliminar_Click);
            // 
            // txtFiltrar
            // 
            this.txtFiltrar.Location = new System.Drawing.Point(352, 68);
            this.txtFiltrar.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.txtFiltrar.Name = "txtFiltrar";
            this.txtFiltrar.Size = new System.Drawing.Size(244, 22);
            this.txtFiltrar.TabIndex = 6;
            this.txtFiltrar.TextChanged += new System.EventHandler(this.txtFiltrar_TextChanged);
            this.txtFiltrar.Enter += new System.EventHandler(this.txtFiltrar_Enter);
            this.txtFiltrar.Leave += new System.EventHandler(this.txtFiltrar_Leave);
            // 
            // pbImgEmpresa
            // 
            this.pbImgEmpresa.Image = global::CarKier.Properties.Resources.empresa;
            this.pbImgEmpresa.Location = new System.Drawing.Point(16, 15);
            this.pbImgEmpresa.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.pbImgEmpresa.Name = "pbImgEmpresa";
            this.pbImgEmpresa.Size = new System.Drawing.Size(219, 140);
            this.pbImgEmpresa.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbImgEmpresa.TabIndex = 8;
            this.pbImgEmpresa.TabStop = false;
            // 
            // Empresas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1067, 554);
            this.Controls.Add(this.pbImgEmpresa);
            this.Controls.Add(this.txtFiltrar);
            this.Controls.Add(this.lvEmpresas);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "Empresas";
            this.Text = "Empresas";
            this.Load += new System.EventHandler(this.Empresas_Load);
            this.xmsMenuTablaEmpresa.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pbImgEmpresa)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView lvEmpresas;
        private System.Windows.Forms.ColumnHeader chNombre;
        private System.Windows.Forms.ColumnHeader chDescripcion;
        private System.Windows.Forms.ColumnHeader chDireccion;
        private System.Windows.Forms.ColumnHeader chTelefono;
        private System.Windows.Forms.ColumnHeader chCorreo;
        private System.Windows.Forms.ColumnHeader chOfreceCoches;
        private System.Windows.Forms.TextBox txtFiltrar;
        private System.Windows.Forms.ContextMenuStrip xmsMenuTablaEmpresa;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmiNuevo;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripMenuItem tsmiVer;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripMenuItem tsmiEliminar;
        private System.Windows.Forms.PictureBox pbImgEmpresa;
    }
}