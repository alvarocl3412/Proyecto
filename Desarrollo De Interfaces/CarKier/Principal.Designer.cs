namespace CarKier
{
    partial class Principal
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Principal));
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.vehiculosToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.usuariosToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.vehiculosToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.seguToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.comentariosToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.comentariosToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.vehiculosToolStripMenuItem,
            this.usuariosToolStripMenuItem,
            this.vehiculosToolStripMenuItem1,
            this.seguToolStripMenuItem,
            this.comentariosToolStripMenuItem,
            this.comentariosToolStripMenuItem1});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(800, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // vehiculosToolStripMenuItem
            // 
            this.vehiculosToolStripMenuItem.Name = "vehiculosToolStripMenuItem";
            this.vehiculosToolStripMenuItem.Size = new System.Drawing.Size(69, 20);
            this.vehiculosToolStripMenuItem.Text = "Empresas";
            // 
            // usuariosToolStripMenuItem
            // 
            this.usuariosToolStripMenuItem.Name = "usuariosToolStripMenuItem";
            this.usuariosToolStripMenuItem.Size = new System.Drawing.Size(64, 20);
            this.usuariosToolStripMenuItem.Text = "Usuarios";
            this.usuariosToolStripMenuItem.Click += new System.EventHandler(this.usuariosToolStripMenuItem_Click);
            // 
            // vehiculosToolStripMenuItem1
            // 
            this.vehiculosToolStripMenuItem1.Name = "vehiculosToolStripMenuItem1";
            this.vehiculosToolStripMenuItem1.Size = new System.Drawing.Size(69, 20);
            this.vehiculosToolStripMenuItem1.Text = "Vehiculos";
            this.vehiculosToolStripMenuItem1.Click += new System.EventHandler(this.vehiculosToolStripMenuItem1_Click);
            // 
            // seguToolStripMenuItem
            // 
            this.seguToolStripMenuItem.Name = "seguToolStripMenuItem";
            this.seguToolStripMenuItem.Size = new System.Drawing.Size(71, 20);
            this.seguToolStripMenuItem.Text = "Contratos";
            // 
            // comentariosToolStripMenuItem
            // 
            this.comentariosToolStripMenuItem.Name = "comentariosToolStripMenuItem";
            this.comentariosToolStripMenuItem.Size = new System.Drawing.Size(61, 20);
            this.comentariosToolStripMenuItem.Text = "Seguros";
            // 
            // comentariosToolStripMenuItem1
            // 
            this.comentariosToolStripMenuItem1.Name = "comentariosToolStripMenuItem1";
            this.comentariosToolStripMenuItem1.Size = new System.Drawing.Size(87, 20);
            this.comentariosToolStripMenuItem1.Text = "Comentarios";
            // 
            // Principal
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.menuStrip1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.IsMdiContainer = true;
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Principal";
            this.Text = "Principal";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem vehiculosToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem usuariosToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem vehiculosToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem seguToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem comentariosToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem comentariosToolStripMenuItem1;
    }
}