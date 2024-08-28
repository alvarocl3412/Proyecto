using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class empresas
    {

        // Propiedades
        public int idempresa { get; set; }
        public string nombre { get; set; }
        public string descripcion { get; set; }
        public string direccion { get; set; }
        public string telefono { get; set; }
        public string correo_electronico { get; set; }
        public int ofrece_coches { get; set; }

        // Constructor por defecto
        public empresas()
        {
        }

        // Constructor que inicializa todas las propiedades
        public empresas(int idempresa, string nombre, string descripcion, string direccion, string telefono, string correo_electronico, int ofrece_coches)
        {
            this.idempresa = idempresa;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.direccion = direccion;
            this.telefono = telefono;
            this.correo_electronico = correo_electronico;
            this.ofrece_coches = ofrece_coches;
        }
    }
}
