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
            this.btnFiltrar = new System.Windows.Forms.Button();
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
            this.lvEmpresas.Location = new System.Drawing.Point(12, 132);
            this.lvEmpresas.MultiSelect = false;
            this.lvEmpresas.Name = "lvEmpresas";
            this.lvEmpresas.Size = new System.Drawing.Size(776, 306);
            this.lvEmpresas.TabIndex = 3;
            this.lvEmpresas.UseCompatibleStateImageBehavior = false;
            this.lvEmpresas.View = System.Windows.Forms.View.Details;
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
            this.chDescripcion.Width = 122;
            // 
            // chDireccion
            // 
            this.chDireccion.Text = "Direccion";
            this.chDireccion.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chDireccion.Width = 100;
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
            this.xmsMenuTablaEmpresa.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.tsmiNuevo,
            this.toolStripSeparator2,
            this.tsmiVer,
            this.toolStripSeparator3,
            this.tsmiEliminar});
            this.xmsMenuTablaEmpresa.Name = "xmsMenuTablaEmpresa";
            this.xmsMenuTablaEmpresa.Size = new System.Drawing.Size(118, 88);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(114, 6);
            // 
            // tsmiNuevo
            // 
            this.tsmiNuevo.Image = global::CarKier.Properties.Resources.action_add_16xMD;
            this.tsmiNuevo.Name = "tsmiNuevo";
            this.tsmiNuevo.Size = new System.Drawing.Size(117, 22);
            this.tsmiNuevo.Text = "Nuevo";
            this.tsmiNuevo.Click += new System.EventHandler(this.tsmiNuevo_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(114, 6);
            // 
            // tsmiVer
            // 
            this.tsmiVer.Image = global::CarKier.Properties.Resources.ojo1;
            this.tsmiVer.Name = "tsmiVer";
            this.tsmiVer.Size = new System.Drawing.Size(117, 22);
            this.tsmiVer.Text = "Ver";
            this.tsmiVer.Click += new System.EventHandler(this.tsmiVer_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(114, 6);
            // 
            // tsmiEliminar
            // 
            this.tsmiEliminar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.tsmiEliminar.Name = "tsmiEliminar";
            this.tsmiEliminar.Size = new System.Drawing.Size(117, 22);
            this.tsmiEliminar.Text = "Eliminar";
            this.tsmiEliminar.Click += new System.EventHandler(this.tsmiEliminar_Click);
            // 
            // btnFiltrar
            // 
            this.btnFiltrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFiltrar.Location = new System.Drawing.Point(483, 44);
            this.btnFiltrar.Name = "btnFiltrar";
            this.btnFiltrar.Size = new System.Drawing.Size(86, 38);
            this.btnFiltrar.TabIndex = 5;
            this.btnFiltrar.Text = "Filtrar";
            this.btnFiltrar.UseVisualStyleBackColor = true;
            // 
            // txtFiltrar
            // 
            this.txtFiltrar.Location = new System.Drawing.Point(264, 55);
            this.txtFiltrar.Name = "txtFiltrar";
            this.txtFiltrar.Size = new System.Drawing.Size(184, 20);
            this.txtFiltrar.TabIndex = 6;
            this.txtFiltrar.Enter += new System.EventHandler(this.txtFiltrar_Enter);
            this.txtFiltrar.Leave += new System.EventHandler(this.txtFiltrar_Leave);
            // 
            // pbImgEmpresa
            // 
            this.pbImgEmpresa.Image = global::CarKier.Properties.Resources.empresa;
            this.pbImgEmpresa.Location = new System.Drawing.Point(12, 12);
            this.pbImgEmpresa.Name = "pbImgEmpresa";
            this.pbImgEmpresa.Size = new System.Drawing.Size(164, 114);
            this.pbImgEmpresa.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbImgEmpresa.TabIndex = 8;
            this.pbImgEmpresa.TabStop = false;
            // 
            // Empresas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.pbImgEmpresa);
            this.Controls.Add(this.txtFiltrar);
            this.Controls.Add(this.btnFiltrar);
            this.Controls.Add(this.lvEmpresas);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
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
        private System.Windows.Forms.Button btnFiltrar;
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