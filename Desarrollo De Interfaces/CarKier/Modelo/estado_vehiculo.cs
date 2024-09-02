using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class estado_vehiculo
    {
        public int id { get; set; }

        public string estado { get; set; }

        public estado_vehiculo() { }

        public estado_vehiculo(int idestado, string estado)
        {
            this.id = idestado;
            this.estado = estado;
        }
    }
}
