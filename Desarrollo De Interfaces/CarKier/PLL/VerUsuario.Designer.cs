namespace CarKier.PLL
{
    partial class VerUsuario
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(VerUsuario));
            this.lblNombre = new System.Windows.Forms.Label();
            this.txtNombre = new System.Windows.Forms.TextBox();
            this.txtDni = new System.Windows.Forms.TextBox();
            this.txtApellidos = new System.Windows.Forms.TextBox();
            this.lblDni = new System.Windows.Forms.Label();
            this.lblApellidos = new System.Windows.Forms.Label();
            this.lblTelefono = new System.Windows.Forms.Label();
            this.txtTelefono = new System.Windows.Forms.TextBox();
            this.txtCorreo = new System.Windows.Forms.TextBox();
            this.lblCorreo = new System.Windows.Forms.Label();
            this.lvMostrarCarnets = new System.Windows.Forms.ListView();
            this.chID = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chTipo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chFecha = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chFechaCadu = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.cmsCarnet = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.ntsmNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.mtsmVer = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.mtsmEliminar = new System.Windows.Forms.ToolStripMenuItem();
            this.label2 = new System.Windows.Forms.Label();
            this.pcFotoImaggen = new System.Windows.Forms.PictureBox();
            this.lblFechaNac = new System.Windows.Forms.Label();
            this.txtFechaNac = new System.Windows.Forms.TextBox();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.btnDatos = new System.Windows.Forms.Button();
            this.btnContratos = new System.Windows.Forms.Button();
            this.cmsCarnet.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pcFotoImaggen)).BeginInit();
            this.SuspendLayout();
            // 
            // lblNombre
            // 
            this.lblNombre.AutoSize = true;
            this.lblNombre.Font = new System.Drawing.Font("Microsoft Sans Serif", 12.25F, System.Drawing.FontStyle.Italic);
            this.lblNombre.Location = new System.Drawing.Point(483, 41);
            this.lblNombre.Name = "lblNombre";
            this.lblNombre.Size = new System.Drawing.Size(73, 20);
            this.lblNombre.TabIndex = 5;
            this.lblNombre.Text = "Nombre:";
            // 
            // txtNombre
            // 
            this.txtNombre.Location = new System.Drawing.Point(562, 41);
            this.txtNombre.Name = "txtNombre";
            this.txtNombre.Size = new System.Drawing.Size(193, 20);
            this.txtNombre.TabIndex = 6;
            // 
            // txtDni
            // 
            this.txtDni.Location = new System.Drawing.Point(326, 42);
            this.txtDni.Name = "txtDni";
            this.txtDni.Size = new System.Drawing.Size(130, 20);
            this.txtDni.TabIndex = 9;
            // 
            // txtApellidos
            // 
            this.txtApellidos.Location = new System.Drawing.Point(326, 85);
            this.txtApellidos.Name = "txtApellidos";
            this.txtApellidos.Size = new System.Drawing.Size(130, 20);
            this.txtApellidos.TabIndex = 10;
            // 
            // lblDni
            // 
            this.lblDni.AutoSize = true;
            this.lblDni.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblDni.Location = new System.Drawing.Point(229, 42);
            this.lblDni.Name = "lblDni";
            this.lblDni.Size = new System.Drawing.Size(41, 20);
            this.lblDni.TabIndex = 11;
            this.lblDni.Text = "DNI:";
            // 
            // lblApellidos
            // 
            this.lblApellidos.AutoSize = true;
            this.lblApellidos.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblApellidos.Location = new System.Drawing.Point(213, 83);
            this.lblApellidos.Name = "lblApellidos";
            this.lblApellidos.Size = new System.Drawing.Size(74, 20);
            this.lblApellidos.TabIndex = 12;
            this.lblApellidos.Text = "Apelidos:";
            // 
            // lblTelefono
            // 
            this.lblTelefono.AutoSize = true;
            this.lblTelefono.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTelefono.Location = new System.Drawing.Point(483, 83);
            this.lblTelefono.Name = "lblTelefono";
            this.lblTelefono.Size = new System.Drawing.Size(75, 20);
            this.lblTelefono.TabIndex = 13;
            this.lblTelefono.Text = "Telefono:";
            // 
            // txtTelefono
            // 
            this.txtTelefono.Location = new System.Drawing.Point(562, 83);
            this.txtTelefono.Name = "txtTelefono";
            this.txtTelefono.Size = new System.Drawing.Size(193, 20);
            this.txtTelefono.TabIndex = 14;
            // 
            // txtCorreo
            // 
            this.txtCorreo.Location = new System.Drawing.Point(562, 124);
            this.txtCorreo.Name = "txtCorreo";
            this.txtCorreo.Size = new System.Drawing.Size(193, 20);
            this.txtCorreo.TabIndex = 15;
            // 
            // lblCorreo
            // 
            this.lblCorreo.AutoSize = true;
            this.lblCorreo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCorreo.Location = new System.Drawing.Point(495, 124);
            this.lblCorreo.Name = "lblCorreo";
            this.lblCorreo.Size = new System.Drawing.Size(61, 20);
            this.lblCorreo.TabIndex = 16;
            this.lblCorreo.Text = "Correo:";
            // 
            // lvMostrarCarnets
            // 
            this.lvMostrarCarnets.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chID,
            this.chTipo,
            this.chFecha,
            this.chFechaCadu});
            this.lvMostrarCarnets.ContextMenuStrip = this.cmsCarnet;
            this.lvMostrarCarnets.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lvMostrarCarnets.FullRowSelect = true;
            this.lvMostrarCarnets.GridLines = true;
            this.lvMostrarCarnets.HideSelection = false;
            this.lvMostrarCarnets.Location = new System.Drawing.Point(114, 258);
            this.lvMostrarCarnets.MultiSelect = false;
            this.lvMostrarCarnets.Name = "lvMostrarCarnets";
            this.lvMostrarCarnets.Size = new System.Drawing.Size(609, 124);
            this.lvMostrarCarnets.TabIndex = 21;
            this.lvMostrarCarnets.UseCompatibleStateImageBehavior = false;
            this.lvMostrarCarnets.View = System.Windows.Forms.View.Details;
            // 
            // chID
            // 
            this.chID.Text = "idCarnet";
            this.chID.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chID.Width = 117;
            // 
            // chTipo
            // 
            this.chTipo.Text = "Tipo";
            this.chTipo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chTipo.Width = 116;
            // 
            // chFecha
            // 
            this.chFecha.Text = "Fecha Expedicion";
            this.chFecha.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chFecha.Width = 184;
            // 
            // chFechaCadu
            // 
            this.chFechaCadu.Text = "Fecha Caducidad";
            this.chFechaCadu.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chFechaCadu.Width = 179;
            // 
            // cmsCarnet
            // 
            this.cmsCarnet.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.ntsmNuevo,
            this.toolStripSeparator2,
            this.mtsmVer,
            this.toolStripSeparator3,
            this.mtsmEliminar});
            this.cmsCarnet.Name = "cmsCarnet";
            this.cmsCarnet.Size = new System.Drawing.Size(118, 88);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(114, 6);
            // 
            // ntsmNuevo
            // 
            this.ntsmNuevo.Image = global::CarKier.Properties.Resources.action_add_16xMD;
            this.ntsmNuevo.Name = "ntsmNuevo";
            this.ntsmNuevo.Size = new System.Drawing.Size(117, 22);
            this.ntsmNuevo.Text = "Nuevo";
            this.ntsmNuevo.Click += new System.EventHandler(this.ntsmNuevo_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(114, 6);
            // 
            // mtsmVer
            // 
            this.mtsmVer.Image = global::CarKier.Properties.Resources.ojo1;
            this.mtsmVer.Name = "mtsmVer";
            this.mtsmVer.Size = new System.Drawing.Size(117, 22);
            this.mtsmVer.Text = "Ver";
            this.mtsmVer.Click += new System.EventHandler(this.mtsmVer_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(114, 6);
            // 
            // mtsmEliminar
            // 
            this.mtsmEliminar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.mtsmEliminar.Name = "mtsmEliminar";
            this.mtsmEliminar.Size = new System.Drawing.Size(117, 22);
            this.mtsmEliminar.Text = "Eliminar";
            this.mtsmEliminar.Click += new System.EventHandler(this.mtsmEliminar_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(358, 232);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(170, 20);
            this.label2.TabIndex = 22;
            this.label2.Text = "Carnets de conducir";
            // 
            // pcFotoImaggen
            // 
            this.pcFotoImaggen.Image = global::CarKier.Properties.Resources.Usuario;
            this.pcFotoImaggen.Location = new System.Drawing.Point(24, 32);
            this.pcFotoImaggen.Name = "pcFotoImaggen";
            this.pcFotoImaggen.Size = new System.Drawing.Size(183, 154);
            this.pcFotoImaggen.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pcFotoImaggen.TabIndex = 4;
            this.pcFotoImaggen.TabStop = false;
            // 
            // lblFechaNac
            // 
            this.lblFechaNac.AutoSize = true;
            this.lblFechaNac.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic);
            this.lblFechaNac.Location = new System.Drawing.Point(205, 125);
            this.lblFechaNac.Name = "lblFechaNac";
            this.lblFechaNac.Size = new System.Drawing.Size(141, 20);
            this.lblFechaNac.TabIndex = 24;
            this.lblFechaNac.Text = "Fecha Nacimiento:";
            // 
            // txtFechaNac
            // 
            this.txtFechaNac.Location = new System.Drawing.Point(348, 125);
            this.txtFechaNac.Name = "txtFechaNac";
            this.txtFechaNac.Size = new System.Drawing.Size(139, 20);
            this.txtFechaNac.TabIndex = 25;
            // 
            // btnGuardar
            // 
            this.btnGuardar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnGuardar.Image = global::CarKier.Properties.Resources.Save_6530;
            this.btnGuardar.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnGuardar.Location = new System.Drawing.Point(243, 404);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(110, 34);
            this.btnGuardar.TabIndex = 27;
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.UseVisualStyleBackColor = true;
            this.btnGuardar.Click += new System.EventHandler(this.btnGuardar_Click);
            // 
            // btnCancelar
            // 
            this.btnCancelar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.btnCancelar.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCancelar.Location = new System.Drawing.Point(455, 404);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(112, 34);
            this.btnCancelar.TabIndex = 28;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = true;
            this.btnCancelar.Click += new System.EventHandler(this.btnCancelar_Click);
            // 
            // btnDatos
            // 
            this.btnDatos.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnDatos.Location = new System.Drawing.Point(243, 170);
            this.btnDatos.Name = "btnDatos";
            this.btnDatos.Size = new System.Drawing.Size(149, 32);
            this.btnDatos.TabIndex = 29;
            this.btnDatos.Text = "Especificaciones";
            this.btnDatos.UseVisualStyleBackColor = true;
            this.btnDatos.Click += new System.EventHandler(this.btnDatos_Click);
            // 
            // btnContratos
            // 
            this.btnContratos.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnContratos.Location = new System.Drawing.Point(548, 170);
            this.btnContratos.Name = "btnContratos";
            this.btnContratos.Size = new System.Drawing.Size(103, 32);
            this.btnContratos.TabIndex = 30;
            this.btnContratos.Text = "Contratos";
            this.btnContratos.UseVisualStyleBackColor = true;
            // 
            // VerUsuario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.btnContratos);
            this.Controls.Add(this.btnDatos);
            this.Controls.Add(this.btnCancelar);
            this.Controls.Add(this.btnGuardar);
            this.Controls.Add(this.txtFechaNac);
            this.Controls.Add(this.lblFechaNac);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.lvMostrarCarnets);
            this.Controls.Add(this.lblCorreo);
            this.Controls.Add(this.txtCorreo);
            this.Controls.Add(this.txtTelefono);
            this.Controls.Add(this.lblTelefono);
            this.Controls.Add(this.lblApellidos);
            this.Controls.Add(this.lblDni);
            this.Controls.Add(this.txtApellidos);
            this.Controls.Add(this.txtDni);
            this.Controls.Add(this.txtNombre);
            this.Controls.Add(this.lblNombre);
            this.Controls.Add(this.pcFotoImaggen);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "VerUsuario";
            this.Text = "Usuario";
            this.cmsCarnet.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pcFotoImaggen)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pcFotoImaggen;
        private System.Windows.Forms.Label lblNombre;
        private System.Windows.Forms.TextBox txtNombre;
        private System.Windows.Forms.TextBox txtDni;
        private System.Windows.Forms.TextBox txtApellidos;
        private System.Windows.Forms.Label lblDni;
        private System.Windows.Forms.Label lblApellidos;
        private System.Windows.Forms.Label lblTelefono;
        private System.Windows.Forms.TextBox txtTelefono;
        private System.Windows.Forms.TextBox txtCorreo;
        private System.Windows.Forms.Label lblCorreo;
        private System.Windows.Forms.ListView lvMostrarCarnets;
        private System.Windows.Forms.ColumnHeader chID;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ColumnHeader chTipo;
        private System.Windows.Forms.ColumnHeader chFecha;
        private System.Windows.Forms.ColumnHeader chFechaCadu;
        private System.Windows.Forms.ContextMenuStrip cmsCarnet;
        private System.Windows.Forms.ToolStripMenuItem ntsmNuevo;
        private System.Windows.Forms.ToolStripMenuItem mtsmVer;
        private System.Windows.Forms.ToolStripMenuItem mtsmEliminar;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.Label lblFechaNac;
        private System.Windows.Forms.TextBox txtFechaNac;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Button btnCancelar;
        private System.Windows.Forms.Button btnDatos;
        private System.Windows.Forms.Button btnContratos;
    }
}