namespace CarKier.PLL
{
    partial class Seguros
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Seguros));
            this.lvSeguros = new System.Windows.Forms.ListView();
            this.chNombre = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chDescripcion = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chCoste = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.cmsTablaSeguro = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiVer = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiEliminar = new System.Windows.Forms.ToolStripMenuItem();
            this.txtFiltrarSeguros = new System.Windows.Forms.TextBox();
            this.btnFiltrar = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.cmsTablaSeguro.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // lvSeguros
            // 
            this.lvSeguros.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chNombre,
            this.chDescripcion,
            this.chCoste});
            this.lvSeguros.ContextMenuStrip = this.cmsTablaSeguro;
            this.lvSeguros.Cursor = System.Windows.Forms.Cursors.Default;
            this.lvSeguros.FullRowSelect = true;
            this.lvSeguros.GridLines = true;
            this.lvSeguros.HideSelection = false;
            this.lvSeguros.Location = new System.Drawing.Point(12, 132);
            this.lvSeguros.MultiSelect = false;
            this.lvSeguros.Name = "lvSeguros";
            this.lvSeguros.Size = new System.Drawing.Size(776, 306);
            this.lvSeguros.TabIndex = 5;
            this.lvSeguros.UseCompatibleStateImageBehavior = false;
            this.lvSeguros.View = System.Windows.Forms.View.Details;
            // 
            // chNombre
            // 
            this.chNombre.Text = "Nombre";
            this.chNombre.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chNombre.Width = 200;
            // 
            // chDescripcion
            // 
            this.chDescripcion.Text = "Descripcion";
            this.chDescripcion.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chDescripcion.Width = 260;
            // 
            // chCoste
            // 
            this.chCoste.Text = "Coste";
            this.chCoste.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chCoste.Width = 190;
            // 
            // cmsTablaSeguro
            // 
            this.cmsTablaSeguro.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator3,
            this.tsmiNuevo,
            this.toolStripSeparator2,
            this.tsmiVer,
            this.toolStripSeparator1,
            this.tsmiEliminar});
            this.cmsTablaSeguro.Name = "contextMenuStrip1";
            this.cmsTablaSeguro.Size = new System.Drawing.Size(118, 88);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(114, 6);
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
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(114, 6);
            // 
            // tsmiEliminar
            // 
            this.tsmiEliminar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.tsmiEliminar.Name = "tsmiEliminar";
            this.tsmiEliminar.Size = new System.Drawing.Size(117, 22);
            this.tsmiEliminar.Text = "Eliminar";
            this.tsmiEliminar.Click += new System.EventHandler(this.tsmiEliminar_Click);
            // 
            // txtFiltrarSeguros
            // 
            this.txtFiltrarSeguros.Location = new System.Drawing.Point(253, 69);
            this.txtFiltrarSeguros.Name = "txtFiltrarSeguros";
            this.txtFiltrarSeguros.Size = new System.Drawing.Size(184, 20);
            this.txtFiltrarSeguros.TabIndex = 10;
            // 
            // btnFiltrar
            // 
            this.btnFiltrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFiltrar.Location = new System.Drawing.Point(472, 58);
            this.btnFiltrar.Name = "btnFiltrar";
            this.btnFiltrar.Size = new System.Drawing.Size(86, 38);
            this.btnFiltrar.TabIndex = 9;
            this.btnFiltrar.Text = "Filtrar";
            this.btnFiltrar.UseVisualStyleBackColor = true;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::CarKier.Properties.Resources.seguro_coche;
            this.pictureBox1.Location = new System.Drawing.Point(12, 12);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(140, 114);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 11;
            this.pictureBox1.TabStop = false;
            // 
            // Seguros
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.txtFiltrarSeguros);
            this.Controls.Add(this.btnFiltrar);
            this.Controls.Add(this.lvSeguros);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Seguros";
            this.Text = "Seguros";
            this.Load += new System.EventHandler(this.Seguros_Load);
            this.cmsTablaSeguro.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView lvSeguros;
        private System.Windows.Forms.ColumnHeader chNombre;
        private System.Windows.Forms.ColumnHeader chDescripcion;
        private System.Windows.Forms.ColumnHeader chCoste;
        private System.Windows.Forms.TextBox txtFiltrarSeguros;
        private System.Windows.Forms.Button btnFiltrar;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.ContextMenuStrip cmsTablaSeguro;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripMenuItem tsmiNuevo;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripMenuItem tsmiVer;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmiEliminar;
    }
}