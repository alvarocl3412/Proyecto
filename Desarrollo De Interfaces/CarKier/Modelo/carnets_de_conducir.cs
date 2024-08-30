using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class carnets_de_conducir
    {
        public int id { get; set; }
        public int idusuario { get; set; }
        public int idTipocarnet { get; set; }
        public DateTime fechaExpedicion { get; set; }
        public DateTime fechaCaducidad { get; set; }


        // Constructor por defecto
        public carnets_de_conducir()
        {
        }

        // Constructor que inicializa todas las propiedades
        public carnets_de_conducir(int idCarnet, int idUsuario, int idTipoCarnet, DateTime fechaExpedicion, DateTime fechaCaducidad)
        {
            id = idCarnet;
            idusuario = idUsuario;
            idTipocarnet = idTipoCarnet;
            this.fechaExpedicion = fechaExpedicion;
            this.fechaCaducidad = fechaCaducidad;
        }

    }
}
