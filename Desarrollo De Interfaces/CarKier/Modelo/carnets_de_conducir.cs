using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class carnets_de_conducir
    {
        public int IdCarnet { get; set; }
        public int IdUsuario { get; set; }
        public int IdTipoCarnet { get; set; }
        public DateTime FechaExpedicion { get; set; }
        public DateTime FechaCaducidad { get; set; }


        // Constructor por defecto
        public carnets_de_conducir()
        {
        }

        // Constructor que inicializa todas las propiedades
        public carnets_de_conducir(int idCarnet, int idUsuario, int idTipoCarnet, DateTime fechaExpedicion, DateTime fechaCaducidad)
        {
            IdCarnet = idCarnet;
            IdUsuario = idUsuario;
            IdTipoCarnet = idTipoCarnet;
            FechaExpedicion = fechaExpedicion;
            FechaCaducidad = fechaCaducidad;
        }

    }
}
