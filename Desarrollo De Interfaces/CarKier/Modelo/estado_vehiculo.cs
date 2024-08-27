using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class estado_vehiculo
    {
        public int idestado { get; set; }

        public string estado { get; set; }

        public estado_vehiculo() { }

        public estado_vehiculo(int idestado, string estado)
        {
            this.idestado = idestado;
            this.estado = estado;
        }
    }
}
