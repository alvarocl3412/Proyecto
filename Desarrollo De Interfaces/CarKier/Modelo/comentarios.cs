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
        public int idcomentarios { get; set; }
        public int idusuarios { get; set; }
        public int idvehiculo { get; set; }
        public int idcomentario { get; set; }
        public DateTime fecha { get; set; }

        // Constructor por defecto
        public comentarios()
        {
        }

        // Constructor que inicializa todas las propiedades
        public comentarios(int idcomentarios, int idusuarios, int idvehiculo, int idcomentario, DateTime fecha)
        {
            this.idcomentarios = idcomentarios;
            this.idusuarios = idusuarios;
            this.idvehiculo = idvehiculo;
            this.idcomentario = idcomentario;
            this.fecha = fecha;
        }
    }
}
