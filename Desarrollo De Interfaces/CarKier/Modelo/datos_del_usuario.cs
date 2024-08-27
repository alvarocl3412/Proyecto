using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class datos_del_usuario
    {
        // Propiedades
        public int idusuario { get; set; }
        public int puntos { get; set; }
        public bool administrador { get; set; }
        public DateTime fechaban_inicio { get; set; }
        public DateTime fechaban_final { get; set; }
        public DateTime cantidad_ban { get; set; }
        public bool marcadoeliminar { get; set; }

        // Constructor por defecto
        public datos_del_usuario()
        {
        }

        // Constructor que inicializa todas las propiedades
        public datos_del_usuario(int idusuario, int puntos, bool administrador, DateTime fechaban_inicio, DateTime fechaban_final, DateTime cantidad_ban, bool marcadoeliminar)
        {
            this.idusuario = idusuario;
            this.puntos = puntos;
            this.administrador = administrador;
            this.fechaban_inicio = fechaban_inicio;
            this.fechaban_final = fechaban_final;
            this.cantidad_ban = cantidad_ban;
            this.marcadoeliminar = marcadoeliminar;
        }
    }
}
