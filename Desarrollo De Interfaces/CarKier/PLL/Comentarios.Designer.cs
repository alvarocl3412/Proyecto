namespace CarKier.PLL
{
    partial class Comentarios
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Comentarios));
            this.lvComentarios = new System.Windows.Forms.ListView();
            this.chIdUsu = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chIdVehiculo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chIdComenRespuesta = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chComentario = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chFecha = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.cmsTablaComentario = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiVer = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiEliminar = new System.Windows.Forms.ToolStripMenuItem();
            this.txtFiltrarComentario = new System.Windows.Forms.TextBox();
            this.btnFiltrar = new System.Windows.Forms.Button();
            this.pcImgComentario = new System.Windows.Forms.PictureBox();
            this.cmsTablaComentario.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pcImgComentario)).BeginInit();
            this.SuspendLayout();
            // 
            // lvComentarios
            // 
            this.lvComentarios.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chIdUsu,
            this.chIdVehiculo,
            this.chIdComenRespuesta,
            this.chComentario,
            this.chFecha});
            this.lvComentarios.ContextMenuStrip = this.cmsTablaComentario;
            this.lvComentarios.Cursor = System.Windows.Forms.Cursors.Default;
            this.lvComentarios.FullRowSelect = true;
            this.lvComentarios.GridLines = true;
            this.lvComentarios.HideSelection = false;
            this.lvComentarios.Location = new System.Drawing.Point(12, 112);
            this.lvComentarios.MultiSelect = false;
            this.lvComentarios.Name = "lvComentarios";
            this.lvComentarios.Size = new System.Drawing.Size(776, 306);
            this.lvComentarios.TabIndex = 6;
            this.lvComentarios.UseCompatibleStateImageBehavior = false;
            this.lvComentarios.View = System.Windows.Forms.View.Details;
            // 
            // chIdUsu
            // 
            this.chIdUsu.Text = "Usuario";
            this.chIdUsu.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chIdUsu.Width = 90;
            // 
            // chIdVehiculo
            // 
            this.chIdVehiculo.Text = "Vehiculo";
            this.chIdVehiculo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chIdVehiculo.Width = 90;
            // 
            // chIdComenRespuesta
            // 
            this.chIdComenRespuesta.Text = "Comentario Respuesta";
            this.chIdComenRespuesta.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chIdComenRespuesta.Width = 140;
            // 
            // chComentario
            // 
            this.chComentario.Text = "Comentario";
            this.chComentario.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chComentario.Width = 260;
            // 
            // chFecha
            // 
            this.chFecha.Text = "Fecha";
            this.chFecha.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chFecha.Width = 140;
            // 
            // cmsTablaComentario
            // 
            this.cmsTablaComentario.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.tsmiNuevo,
            this.toolStripSeparator2,
            this.tsmiVer,
            this.tsmiEliminar});
            this.cmsTablaComentario.Name = "cmsTablaComentario";
            this.cmsTablaComentario.Size = new System.Drawing.Size(118, 82);
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
            // tsmiEliminar
            // 
            this.tsmiEliminar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.tsmiEliminar.Name = "tsmiEliminar";
            this.tsmiEliminar.Size = new System.Drawing.Size(117, 22);
            this.tsmiEliminar.Text = "Eliminar";
            this.tsmiEliminar.Click += new System.EventHandler(this.tsmiEliminar_Click);
            // 
            // txtFiltrarComentario
            // 
            this.txtFiltrarComentario.Location = new System.Drawing.Point(211, 52);
            this.txtFiltrarComentario.Name = "txtFiltrarComentario";
            this.txtFiltrarComentario.Size = new System.Drawing.Size(184, 20);
            this.txtFiltrarComentario.TabIndex = 12;
            // 
            // btnFiltrar
            // 
            this.btnFiltrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFiltrar.Location = new System.Drawing.Point(430, 41);
            this.btnFiltrar.Name = "btnFiltrar";
            this.btnFiltrar.Size = new System.Drawing.Size(86, 38);
            this.btnFiltrar.TabIndex = 11;
            this.btnFiltrar.Text = "Filtrar";
            this.btnFiltrar.UseVisualStyleBackColor = true;
            // 
            // pcImgComentario
            // 
            this.pcImgComentario.Image = global::CarKier.Properties.Resources.comentario;
            this.pcImgComentario.Location = new System.Drawing.Point(28, 12);
            this.pcImgComentario.Name = "pcImgComentario";
            this.pcImgComentario.Size = new System.Drawing.Size(118, 94);
            this.pcImgComentario.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pcImgComentario.TabIndex = 13;
            this.pcImgComentario.TabStop = false;
            // 
            // Comentarios
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.pcImgComentario);
            this.Controls.Add(this.txtFiltrarComentario);
            this.Controls.Add(this.btnFiltrar);
            this.Controls.Add(this.lvComentarios);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Comentarios";
            this.Text = "Comentarios";
            this.Load += new System.EventHandler(this.Comentarios_Load);
            this.cmsTablaComentario.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pcImgComentario)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView lvComentarios;
        private System.Windows.Forms.ColumnHeader chIdUsu;
        private System.Windows.Forms.ColumnHeader chIdVehiculo;
        private System.Windows.Forms.ColumnHeader chIdComenRespuesta;
        private System.Windows.Forms.ColumnHeader chComentario;
        private System.Windows.Forms.ColumnHeader chFecha;
        private System.Windows.Forms.ContextMenuStrip cmsTablaComentario;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmiNuevo;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripMenuItem tsmiVer;
        private System.Windows.Forms.ToolStripMenuItem tsmiEliminar;
        private System.Windows.Forms.TextBox txtFiltrarComentario;
        private System.Windows.Forms.Button btnFiltrar;
        private System.Windows.Forms.PictureBox pcImgComentario;
    }
}