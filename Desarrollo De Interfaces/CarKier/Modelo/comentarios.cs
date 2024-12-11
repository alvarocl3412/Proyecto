using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class comentarios
    {

        // Propiedades
        public int id { get; set; }
        public int idUsuario { get; set; }
        public int idVehiculo { get; set; }
        public int? idComentarioRespuesta { get; set; }
        public string comentario { get; set; }
        public String fecha { get; set; }

        // Constructor por defecto
        public comentarios()
        {
        }

        // Constructor que inicializa todas las propiedades
        public comentarios(int idcomentarios, int idusuarios, int idvehiculo, int? idcomentarioRespuesta,string comentario, String fecha)
        {
            this.id = idcomentarios;
            this.idUsuario = idusuarios;
            this.idVehiculo = idvehiculo;
            this.idComentarioRespuesta = idcomentarioRespuesta;
            this.comentario = comentario;
            this.fecha = fecha;
        }
    }
}
