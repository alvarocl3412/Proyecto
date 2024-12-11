namespace CarKier.PLL
{
    partial class VerComentario
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
            this.lblIdUsuario = new System.Windows.Forms.Label();
            this.lblIdVehiculo = new System.Windows.Forms.Label();
            this.lblComentario = new System.Windows.Forms.Label();
            this.lblFecha = new System.Windows.Forms.Label();
            this.txtIdUsuario = new System.Windows.Forms.TextBox();
            this.txtFecha = new System.Windows.Forms.TextBox();
            this.txtComentario = new System.Windows.Forms.TextBox();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.pcImgComentario = new System.Windows.Forms.PictureBox();
            this.lblIdRespuesta = new System.Windows.Forms.Label();
            this.txtRespuesta = new System.Windows.Forms.TextBox();
            this.txtMatriculaComentario = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.pcImgComentario)).BeginInit();
            this.SuspendLayout();
            // 
            // lblIdUsuario
            // 
            this.lblIdUsuario.AutoSize = true;
            this.lblIdUsuario.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblIdUsuario.Location = new System.Drawing.Point(254, 30);
            this.lblIdUsuario.Name = "lblIdUsuario";
            this.lblIdUsuario.Size = new System.Drawing.Size(68, 20);
            this.lblIdUsuario.TabIndex = 16;
            this.lblIdUsuario.Text = "Usuario:";
            // 
            // lblIdVehiculo
            // 
            this.lblIdVehiculo.AutoSize = true;
            this.lblIdVehiculo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblIdVehiculo.Location = new System.Drawing.Point(217, 93);
            this.lblIdVehiculo.Name = "lblIdVehiculo";
            this.lblIdVehiculo.Size = new System.Drawing.Size(142, 20);
            this.lblIdVehiculo.TabIndex = 17;
            this.lblIdVehiculo.Text = "Vehiculo Matricula:";
            // 
            // lblComentario
            // 
            this.lblComentario.AutoSize = true;
            this.lblComentario.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblComentario.Location = new System.Drawing.Point(388, 204);
            this.lblComentario.Name = "lblComentario";
            this.lblComentario.Size = new System.Drawing.Size(91, 20);
            this.lblComentario.TabIndex = 18;
            this.lblComentario.Text = "Comentario";
            // 
            // lblFecha
            // 
            this.lblFecha.AutoSize = true;
            this.lblFecha.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblFecha.Location = new System.Drawing.Point(540, 93);
            this.lblFecha.Name = "lblFecha";
            this.lblFecha.Size = new System.Drawing.Size(58, 20);
            this.lblFecha.TabIndex = 19;
            this.lblFecha.Text = "Fecha:";
            // 
            // txtIdUsuario
            // 
            this.txtIdUsuario.Location = new System.Drawing.Point(356, 30);
            this.txtIdUsuario.Name = "txtIdUsuario";
            this.txtIdUsuario.Size = new System.Drawing.Size(143, 20);
            this.txtIdUsuario.TabIndex = 21;
            // 
            // txtFecha
            // 
            this.txtFecha.Location = new System.Drawing.Point(618, 93);
            this.txtFecha.Name = "txtFecha";
            this.txtFecha.Size = new System.Drawing.Size(145, 20);
            this.txtFecha.TabIndex = 22;
            // 
            // txtComentario
            // 
            this.txtComentario.Location = new System.Drawing.Point(90, 241);
            this.txtComentario.Multiline = true;
            this.txtComentario.Name = "txtComentario";
            this.txtComentario.Size = new System.Drawing.Size(658, 104);
            this.txtComentario.TabIndex = 24;
            // 
            // btnCancelar
            // 
            this.btnCancelar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelar.Image = global::CarKier.Properties.Resources.action_Cancel_16xMD;
            this.btnCancelar.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCancelar.Location = new System.Drawing.Point(453, 373);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(128, 34);
            this.btnCancelar.TabIndex = 37;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = true;
            this.btnCancelar.Click += new System.EventHandler(this.btnCancelar_Click);
            // 
            // btnGuardar
            // 
            this.btnGuardar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnGuardar.Image = global::CarKier.Properties.Resources.Save_6530;
            this.btnGuardar.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnGuardar.Location = new System.Drawing.Point(194, 373);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(128, 34);
            this.btnGuardar.TabIndex = 36;
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.UseVisualStyleBackColor = true;
            this.btnGuardar.Click += new System.EventHandler(this.btnGuardar_Click);
            // 
            // pcImgComentario
            // 
            this.pcImgComentario.Image = global::CarKier.Properties.Resources.comentario;
            this.pcImgComentario.Location = new System.Drawing.Point(12, 12);
            this.pcImgComentario.Name = "pcImgComentario";
            this.pcImgComentario.Size = new System.Drawing.Size(203, 190);
            this.pcImgComentario.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pcImgComentario.TabIndex = 14;
            this.pcImgComentario.TabStop = false;
            // 
            // lblIdRespuesta
            // 
            this.lblIdRespuesta.AutoSize = true;
            this.lblIdRespuesta.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblIdRespuesta.Location = new System.Drawing.Point(504, 28);
            this.lblIdRespuesta.Name = "lblIdRespuesta";
            this.lblIdRespuesta.Size = new System.Drawing.Size(104, 20);
            this.lblIdRespuesta.TabIndex = 38;
            this.lblIdRespuesta.Text = "Responde a :";
            // 
            // txtRespuesta
            // 
            this.txtRespuesta.Location = new System.Drawing.Point(627, 30);
            this.txtRespuesta.Name = "txtRespuesta";
            this.txtRespuesta.Size = new System.Drawing.Size(136, 20);
            this.txtRespuesta.TabIndex = 39;
            // 
            // txtMatriculaComentario
            // 
            this.txtMatriculaComentario.Location = new System.Drawing.Point(365, 95);
            this.txtMatriculaComentario.Name = "txtMatriculaComentario";
            this.txtMatriculaComentario.Size = new System.Drawing.Size(143, 20);
            this.txtMatriculaComentario.TabIndex = 40;
            // 
            // VerComentario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.txtMatriculaComentario);
            this.Controls.Add(this.txtRespuesta);
            this.Controls.Add(this.lblIdRespuesta);
            this.Controls.Add(this.btnCancelar);
            this.Controls.Add(this.btnGuardar);
            this.Controls.Add(this.txtComentario);
            this.Controls.Add(this.txtFecha);
            this.Controls.Add(this.txtIdUsuario);
            this.Controls.Add(this.lblFecha);
            this.Controls.Add(this.lblComentario);
            this.Controls.Add(this.lblIdVehiculo);
            this.Controls.Add(this.lblIdUsuario);
            this.Controls.Add(this.pcImgComentario);
            this.Name = "VerComentario";
            this.Text = "VerComentario";
            ((System.ComponentModel.ISupportInitialize)(this.pcImgComentario)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pcImgComentario;
        private System.Windows.Forms.Label lblIdUsuario;
        private System.Windows.Forms.Label lblIdVehiculo;
        private System.Windows.Forms.Label lblComentario;
        private System.Windows.Forms.Label lblFecha;
        private System.Windows.Forms.TextBox txtIdUsuario;
        private System.Windows.Forms.TextBox txtFecha;
        private System.Windows.Forms.TextBox txtComentario;
        private System.Windows.Forms.Button btnCancelar;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Label lblIdRespuesta;
        private System.Windows.Forms.TextBox txtRespuesta;
        private System.Windows.Forms.TextBox txtMatriculaComentario;
    }
}