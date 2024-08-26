using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.DAL
{
    internal class estado_contrato
    {
        public int idestado { get; set; }

        public string estado { get; set; }

        public estado_contrato() { }

        public estado_contrato(int idestado,string estado)
        {
            this.idestado = idestado;
            this.estado = estado;
        }
    }
}
