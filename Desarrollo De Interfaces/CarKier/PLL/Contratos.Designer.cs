namespace CarKier.PLL
{
    partial class Contratos
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Contratos));
            this.lvContratos = new System.Windows.Forms.ListView();
            this.chIdVehiculo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chIdCliente = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chEstado = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chSeguro = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chFechaInicio = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chFechaFinal = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chPrecioDia = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chPrecioFinal = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chPagado = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.cmdMenuContrato = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmVer = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmEliminar = new System.Windows.Forms.ToolStripMenuItem();
            this.txtFiltrarContratos = new System.Windows.Forms.TextBox();
            this.btnFiltrar = new System.Windows.Forms.Button();
            this.pbImgContrato = new System.Windows.Forms.PictureBox();
            this.cmdMenuContrato.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbImgContrato)).BeginInit();
            this.SuspendLayout();
            // 
            // lvContratos
            // 
            this.lvContratos.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chIdVehiculo,
            this.chIdCliente,
            this.chEstado,
            this.chSeguro,
            this.chFechaInicio,
            this.chFechaFinal,
            this.chPrecioDia,
            this.chPrecioFinal,
            this.chPagado});
            this.lvContratos.ContextMenuStrip = this.cmdMenuContrato;
            this.lvContratos.Cursor = System.Windows.Forms.Cursors.Default;
            this.lvContratos.FullRowSelect = true;
            this.lvContratos.GridLines = true;
            this.lvContratos.HideSelection = false;
            this.lvContratos.Location = new System.Drawing.Point(12, 132);
            this.lvContratos.MultiSelect = false;
            this.lvContratos.Name = "lvContratos";
            this.lvContratos.Size = new System.Drawing.Size(776, 306);
            this.lvContratos.TabIndex = 4;
            this.lvContratos.UseCompatibleStateImageBehavior = false;
            this.lvContratos.View = System.Windows.Forms.View.Details;
            // 
            // chIdVehiculo
            // 
            this.chIdVehiculo.Text = "IdVehiculo";
            this.chIdVehiculo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chIdVehiculo.Width = 65;
            // 
            // chIdCliente
            // 
            this.chIdCliente.Text = "IdCliente";
            this.chIdCliente.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chIdCliente.Width = 66;
            // 
            // chEstado
            // 
            this.chEstado.Text = "Estado";
            this.chEstado.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chEstado.Width = 80;
            // 
            // chSeguro
            // 
            this.chSeguro.Text = "Seguro";
            this.chSeguro.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chSeguro.Width = 80;
            // 
            // chFechaInicio
            // 
            this.chFechaInicio.Text = "fecha Inicio";
            this.chFechaInicio.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chFechaInicio.Width = 100;
            // 
            // chFechaFinal
            // 
            this.chFechaFinal.Text = "Fecha Final";
            this.chFechaFinal.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chFechaFinal.Width = 100;
            // 
            // chPrecioDia
            // 
            this.chPrecioDia.Text = "Precio Dia";
            this.chPrecioDia.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chPrecioDia.Width = 80;
            // 
            // chPrecioFinal
            // 
            this.chPrecioFinal.Text = "Precio Final";
            this.chPrecioFinal.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.chPrecioFinal.Width = 80;
            // 
            // chPagado
            // 
            this.chPagado.Text = "Pagado";
            this.chPagado.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // cmdMenuContrato
            // 
            this.cmdMenuContrato.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.tsmNuevo,
            this.toolStripSeparator2,
            this.tsmVer,
            this.toolStripSeparator3,
            this.tsmEliminar});
            this.cmdMenuContrato.Name = "cmdMenuContrato";
            this.cmdMenuContrato.Size = new System.Drawing.Size(118, 88);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(114, 6);
            // 
            // tsmNuevo
            // 
            this.tsmNuevo.Image = global::CarKier.Properties.Resources.action_add_16xMD;
            this.tsmNuevo.Name = "tsmNuevo";
            this.tsmNuevo.Size = new System.Drawing.Size(117, 22);
            this.tsmNuevo.Text = "Nuevo";
            this.tsmNuevo.Click += new System.EventHandler(this.tsmNuevo_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(114, 6);
            // 
            // tsmVer
            // 
            this.tsmVer.Image = global::CarKier.Properties.Resources.ojo1;
            this.tsmVer.Name = "tsmVer";
            this.tsmVer.Size = new System.Drawing.Size(117, 22);
            this.tsmVer.Text = "Ver";
            this.tsmVer.Click += new System.EventHandler(this.tsmVer_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(114, 6);
            // 
            // tsmEliminar
            // 
            this.tsmEliminar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.tsmEliminar.Name = "tsmEliminar";
            this.tsmEliminar.Size = new System.Drawing.Size(117, 22);
            this.tsmEliminar.Text = "Eliminar";
            this.tsmEliminar.Click += new System.EventHandler(this.tsmEliminar_Click);
            // 
            // txtFiltrarContratos
            // 
            this.txtFiltrarContratos.Location = new System.Drawing.Point(259, 59);
            this.txtFiltrarContratos.Name = "txtFiltrarContratos";
            this.txtFiltrarContratos.Size = new System.Drawing.Size(184, 20);
            this.txtFiltrarContratos.TabIndex = 8;
            this.txtFiltrarContratos.Enter += new System.EventHandler(this.txtFiltrarContratos_Enter);
            this.txtFiltrarContratos.Leave += new System.EventHandler(this.txtFiltrarContratos_Leave);
            // 
            // btnFiltrar
            // 
            this.btnFiltrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFiltrar.Location = new System.Drawing.Point(478, 48);
            this.btnFiltrar.Name = "btnFiltrar";
            this.btnFiltrar.Size = new System.Drawing.Size(86, 38);
            this.btnFiltrar.TabIndex = 7;
            this.btnFiltrar.Text = "Filtrar";
            this.btnFiltrar.UseVisualStyleBackColor = true;
            // 
            // pbImgContrato
            // 
            this.pbImgContrato.Location = new System.Drawing.Point(22, 13);
            this.pbImgContrato.Name = "pbImgContrato";
            this.pbImgContrato.Size = new System.Drawing.Size(177, 113);
            this.pbImgContrato.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbImgContrato.TabIndex = 10;
            this.pbImgContrato.TabStop = false;
            // 
            // Contratos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.pbImgContrato);
            this.Controls.Add(this.txtFiltrarContratos);
            this.Controls.Add(this.btnFiltrar);
            this.Controls.Add(this.lvContratos);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Contratos";
            this.Text = "Contratos";
            this.Load += new System.EventHandler(this.Contratos_Load);
            this.cmdMenuContrato.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pbImgContrato)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView lvContratos;
        private System.Windows.Forms.ColumnHeader chIdVehiculo;
        private System.Windows.Forms.ColumnHeader chIdCliente;
        private System.Windows.Forms.ColumnHeader chEstado;
        private System.Windows.Forms.ColumnHeader chSeguro;
        private System.Windows.Forms.ColumnHeader chFechaInicio;
        private System.Windows.Forms.ColumnHeader chFechaFinal;
        private System.Windows.Forms.ColumnHeader chPrecioDia;
        private System.Windows.Forms.ColumnHeader chPrecioFinal;
        private System.Windows.Forms.ColumnHeader chPagado;
        private System.Windows.Forms.TextBox txtFiltrarContratos;
        private System.Windows.Forms.Button btnFiltrar;
        private System.Windows.Forms.ContextMenuStrip cmdMenuContrato;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmNuevo;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripMenuItem tsmVer;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripMenuItem tsmEliminar;
        private System.Windows.Forms.PictureBox pbImgContrato;
    }
}