namespace CarKier
{
    partial class InicioSesion
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(InicioSesion));
            this.btnInicioSesion = new System.Windows.Forms.Button();
            this.lblCorreo = new System.Windows.Forms.Label();
            this.txtCorreoElectronico = new System.Windows.Forms.TextBox();
            this.lblContraseña = new System.Windows.Forms.Label();
            this.txtContrasena = new System.Windows.Forms.TextBox();
            this.pbOjo = new System.Windows.Forms.PictureBox();
            this.pbLogo = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pbOjo)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbLogo)).BeginInit();
            this.SuspendLayout();
            // 
            // btnInicioSesion
            // 
            this.btnInicioSesion.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.btnInicioSesion.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnInicioSesion.Location = new System.Drawing.Point(312, 335);
            this.btnInicioSesion.Margin = new System.Windows.Forms.Padding(13);
            this.btnInicioSesion.Name = "btnInicioSesion";
            this.btnInicioSesion.Size = new System.Drawing.Size(195, 40);
            this.btnInicioSesion.TabIndex = 0;
            this.btnInicioSesion.Text = "Iniciar Sesion";
            this.btnInicioSesion.UseVisualStyleBackColor = true;
            this.btnInicioSesion.Click += new System.EventHandler(this.btnInicioSesion_Click);
            // 
            // lblCorreo
            // 
            this.lblCorreo.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblCorreo.AutoSize = true;
            this.lblCorreo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12.25F);
            this.lblCorreo.Location = new System.Drawing.Point(341, 223);
            this.lblCorreo.Margin = new System.Windows.Forms.Padding(13);
            this.lblCorreo.Name = "lblCorreo";
            this.lblCorreo.Size = new System.Drawing.Size(140, 20);
            this.lblCorreo.TabIndex = 2;
            this.lblCorreo.Text = "Correo Elctronico";
            // 
            // txtCorreoElectronico
            // 
            this.txtCorreoElectronico.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txtCorreoElectronico.BackColor = System.Drawing.Color.Snow;
            this.txtCorreoElectronico.Location = new System.Drawing.Point(312, 243);
            this.txtCorreoElectronico.Margin = new System.Windows.Forms.Padding(13);
            this.txtCorreoElectronico.Name = "txtCorreoElectronico";
            this.txtCorreoElectronico.Size = new System.Drawing.Size(195, 20);
            this.txtCorreoElectronico.TabIndex = 3;
            // 
            // lblContraseña
            // 
            this.lblContraseña.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblContraseña.AutoSize = true;
            this.lblContraseña.Font = new System.Drawing.Font("Microsoft Sans Serif", 12.25F);
            this.lblContraseña.Location = new System.Drawing.Point(362, 279);
            this.lblContraseña.Margin = new System.Windows.Forms.Padding(13);
            this.lblContraseña.Name = "lblContraseña";
            this.lblContraseña.Size = new System.Drawing.Size(95, 20);
            this.lblContraseña.TabIndex = 4;
            this.lblContraseña.Text = "Contraseña";
            // 
            // txtContrasena
            // 
            this.txtContrasena.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txtContrasena.Location = new System.Drawing.Point(312, 297);
            this.txtContrasena.Margin = new System.Windows.Forms.Padding(13);
            this.txtContrasena.Name = "txtContrasena";
            this.txtContrasena.PasswordChar = '*';
            this.txtContrasena.Size = new System.Drawing.Size(195, 20);
            this.txtContrasena.TabIndex = 5;
            // 
            // pbOjo
            // 
            this.pbOjo.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.pbOjo.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbOjo.Image = global::CarKier.Properties.Resources.ojo1;
            this.pbOjo.Location = new System.Drawing.Point(503, 297);
            this.pbOjo.Margin = new System.Windows.Forms.Padding(13);
            this.pbOjo.Name = "pbOjo";
            this.pbOjo.Size = new System.Drawing.Size(26, 20);
            this.pbOjo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbOjo.TabIndex = 6;
            this.pbOjo.TabStop = false;
            this.pbOjo.Click += new System.EventHandler(this.pbOjo_Click);
            // 
            // pbLogo
            // 
            this.pbLogo.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.pbLogo.Image = global::CarKier.Properties.Resources.logo;
            this.pbLogo.Location = new System.Drawing.Point(312, 46);
            this.pbLogo.Margin = new System.Windows.Forms.Padding(13);
            this.pbLogo.Name = "pbLogo";
            this.pbLogo.Size = new System.Drawing.Size(195, 164);
            this.pbLogo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbLogo.TabIndex = 1;
            this.pbLogo.TabStop = false;
            // 
            // InicioSesion
            // 
            this.AcceptButton = this.btnInicioSesion;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.pbOjo);
            this.Controls.Add(this.txtContrasena);
            this.Controls.Add(this.lblContraseña);
            this.Controls.Add(this.txtCorreoElectronico);
            this.Controls.Add(this.lblCorreo);
            this.Controls.Add(this.pbLogo);
            this.Controls.Add(this.btnInicioSesion);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "InicioSesion";
            this.Text = "Inicio Sesion";
            this.SizeChanged += new System.EventHandler(this.InicioSesion_SizeChanged);
            ((System.ComponentModel.ISupportInitialize)(this.pbOjo)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbLogo)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnInicioSesion;
        private System.Windows.Forms.PictureBox pbLogo;
        private System.Windows.Forms.Label lblCorreo;
        private System.Windows.Forms.TextBox txtCorreoElectronico;
        private System.Windows.Forms.Label lblContraseña;
        private System.Windows.Forms.TextBox txtContrasena;
        private System.Windows.Forms.PictureBox pbOjo;
    }
}

