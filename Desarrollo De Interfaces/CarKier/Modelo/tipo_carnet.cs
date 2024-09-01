using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class tipo_carnet
    {
        public int id { get; set; }

        public string nombre { get; set; }

        public tipo_carnet() { }

        public tipo_carnet(int idtipo, string nombre)
        {
            this.id = idtipo;
            this.nombre = nombre;
        }
    }
}
