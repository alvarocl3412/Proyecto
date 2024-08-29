using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class tipos_seguros
    {
        public int id { get; set; }

        public string nombre { get; set; }

        public string descripcion { get; set; }

        public double coste { get; set; }

        public tipos_seguros() { }

        public tipos_seguros(int idseguro, string nombre, string descripcion, double coste)
        {
            this.id = idseguro;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.coste = coste;
        }
    }
}
