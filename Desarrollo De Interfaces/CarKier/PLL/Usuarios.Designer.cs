namespace CarKier.PLL
{
    partial class Usuarios
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Usuarios));
            this.lvUsuarios = new System.Windows.Forms.ListView();
            this.chDni = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chNombre = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chApellidos = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chTelefono = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chCorreo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chFechaNac = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.nuevoToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.verToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.eliminarToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.txtFiltrarDni = new System.Windows.Forms.TextBox();
            this.btnFiltrar = new System.Windows.Forms.Button();
            this.pcFotoImaggen = new System.Windows.Forms.PictureBox();
            this.contextMenuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pcFotoImaggen)).BeginInit();
            this.SuspendLayout();
            // 
            // lvUsuarios
            // 
            this.lvUsuarios.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chDni,
            this.chNombre,
            this.chApellidos,
            this.chTelefono,
            this.chCorreo,
            this.chFechaNac});
            this.lvUsuarios.ContextMenuStrip = this.contextMenuStrip1;
            this.lvUsuarios.Cursor = System.Windows.Forms.Cursors.Default;
            this.lvUsuarios.FullRowSelect = true;
            this.lvUsuarios.GridLines = true;
            this.lvUsuarios.HideSelection = false;
            this.lvUsuarios.Location = new System.Drawing.Point(12, 119);
            this.lvUsuarios.MultiSelect = false;
            this.lvUsuarios.Name = "lvUsuarios";
            this.lvUsuarios.Size = new System.Drawing.Size(776, 319);
            this.lvUsuarios.TabIndex = 1;
            this.lvUsuarios.UseCompatibleStateImageBehavior = false;
            this.lvUsuarios.View = System.Windows.Forms.View.Details;
            this.lvUsuarios.ItemSelectionChanged += new System.Windows.Forms.ListViewItemSelectionChangedEventHandler(this.lvUsuarios_ItemSelectionChanged);
            this.lvUsuarios.DoubleClick += new System.EventHandler(this.lvUsuarios_DoubleClick);
            // 
            // chDni
            // 
            this.chDni.Text = "DNI";
            this.chDni.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chDni.Width = 80;
            // 
            // chNombre
            // 
            this.chNombre.Text = "Nombre";
            this.chNombre.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chNombre.Width = 120;
            // 
            // chApellidos
            // 
            this.chApellidos.Text = "Apellidos";
            this.chApellidos.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chApellidos.Width = 197;
            // 
            // chTelefono
            // 
            this.chTelefono.Text = "Telefono";
            this.chTelefono.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chTelefono.Width = 110;
            // 
            // chCorreo
            // 
            this.chCorreo.Text = "Correo";
            this.chCorreo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chCorreo.Width = 153;
            // 
            // chFechaNac
            // 
            this.chFechaNac.Text = "FechaNacimiento";
            this.chFechaNac.Width = 100;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.nuevoToolStripMenuItem1,
            this.toolStripSeparator2,
            this.verToolStripMenuItem,
            this.toolStripSeparator3,
            this.eliminarToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(181, 110);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(177, 6);
            // 
            // nuevoToolStripMenuItem1
            // 
            this.nuevoToolStripMenuItem1.Image = global::CarKier.Properties.Resources.action_add_16xMD;
            this.nuevoToolStripMenuItem1.Name = "nuevoToolStripMenuItem1";
            this.nuevoToolStripMenuItem1.Size = new System.Drawing.Size(180, 22);
            this.nuevoToolStripMenuItem1.Text = "Nuevo";
            this.nuevoToolStripMenuItem1.Click += new System.EventHandler(this.nuevoToolStripMenuItem1_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(177, 6);
            // 
            // verToolStripMenuItem
            // 
            this.verToolStripMenuItem.Image = global::CarKier.Properties.Resources.ojo1;
            this.verToolStripMenuItem.Name = "verToolStripMenuItem";
            this.verToolStripMenuItem.Size = new System.Drawing.Size(180, 22);
            this.verToolStripMenuItem.Text = "Ver";
            this.verToolStripMenuItem.Click += new System.EventHandler(this.verToolStripMenuItem_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(177, 6);
            // 
            // eliminarToolStripMenuItem
            // 
            this.eliminarToolStripMenuItem.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.eliminarToolStripMenuItem.Name = "eliminarToolStripMenuItem";
            this.eliminarToolStripMenuItem.Size = new System.Drawing.Size(180, 22);
            this.eliminarToolStripMenuItem.Text = "Eliminar";
            this.eliminarToolStripMenuItem.Click += new System.EventHandler(this.eliminarToolStripMenuItem_Click);
            // 
            // txtFiltrarDni
            // 
            this.txtFiltrarDni.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtFiltrarDni.Location = new System.Drawing.Point(228, 56);
            this.txtFiltrarDni.Name = "txtFiltrarDni";
            this.txtFiltrarDni.Size = new System.Drawing.Size(176, 22);
            this.txtFiltrarDni.TabIndex = 2;
            this.txtFiltrarDni.Enter += new System.EventHandler(this.txtFiltrarDni_Enter);
            this.txtFiltrarDni.Leave += new System.EventHandler(this.txtFiltrarDni_Leave);
            // 
            // btnFiltrar
            // 
            this.btnFiltrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFiltrar.Location = new System.Drawing.Point(460, 47);
            this.btnFiltrar.Name = "btnFiltrar";
            this.btnFiltrar.Size = new System.Drawing.Size(86, 38);
            this.btnFiltrar.TabIndex = 4;
            this.btnFiltrar.Text = "Filtrar";
            this.btnFiltrar.UseVisualStyleBackColor = true;
            // 
            // pcFotoImaggen
            // 
            this.pcFotoImaggen.Image = global::CarKier.Properties.Resources.Usuario;
            this.pcFotoImaggen.Location = new System.Drawing.Point(30, 17);
            this.pcFotoImaggen.Name = "pcFotoImaggen";
            this.pcFotoImaggen.Size = new System.Drawing.Size(134, 93);
            this.pcFotoImaggen.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pcFotoImaggen.TabIndex = 3;
            this.pcFotoImaggen.TabStop = false;
            // 
            // Usuarios
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.btnFiltrar);
            this.Controls.Add(this.pcFotoImaggen);
            this.Controls.Add(this.txtFiltrarDni);
            this.Controls.Add(this.lvUsuarios);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Usuarios";
            this.Text = "Usuarios";
            this.Load += new System.EventHandler(this.Usuarios_Load);
            this.contextMenuStrip1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pcFotoImaggen)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.ListView lvUsuarios;
        private System.Windows.Forms.ColumnHeader chDni;
        private System.Windows.Forms.ColumnHeader chNombre;
        private System.Windows.Forms.ColumnHeader chApellidos;
        private System.Windows.Forms.ColumnHeader chTelefono;
        private System.Windows.Forms.ColumnHeader chCorreo;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem nuevoToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem verToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem eliminarToolStripMenuItem;
        private System.Windows.Forms.TextBox txtFiltrarDni;
        private System.Windows.Forms.PictureBox pcFotoImaggen;
        private System.Windows.Forms.Button btnFiltrar;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ColumnHeader chFechaNac;
    }
}