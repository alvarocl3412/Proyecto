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
        public int id { get; set; }
        public int? puntos { get; set; }
        public bool? administrador { get; set; }
        public DateTime? fechaBanInicio { get; set; }
        public DateTime? fechaBanFinal { get; set; }
        public int? cantidadBan { get; set; }
        public bool? marcaEliminar { get; set; }

        // Constructor por defecto
        public datos_del_usuario()
        {
        }

        // Constructor que inicializa todas las propiedades
        public datos_del_usuario(int id, int puntos, bool administrador, DateTime? fechaBanInicio, DateTime? fechaBanFinal, int cantidadBan, bool marcaEliminar)
        {
            this.id = id;
            this.puntos = puntos;
            this.administrador = administrador;
            this.fechaBanInicio = fechaBanInicio;
            this.fechaBanFinal = fechaBanFinal;
            this.cantidadBan = cantidadBan;
            this.marcaEliminar = marcaEliminar;
        }
    }
}
