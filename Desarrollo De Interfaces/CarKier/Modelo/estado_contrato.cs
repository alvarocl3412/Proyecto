using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class estado_contrato
    {
        public int id { get; set; }

        public string estado { get; set; }

        public estado_contrato() { }

        public estado_contrato(int idestado, string estado)
        {
            this.id = idestado;
            this.estado = estado;
        }
    }
}
