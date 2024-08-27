using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class contratos
    {
        // Propiedades
        public int idcontrato { get; set; }
        public int idvehiculo { get; set; }
        public int idcliente { get; set; }
        public int idestado { get; set; }
        public int id_seguro { get; set; }
        public DateTime fecha_inicio { get; set; }
        public DateTime fecha_fin { get; set; }
        public double precio_dia { get; set; }
        public double precio_total { get; set; }
        public bool pagado { get; set; }

        // Constructor por defecto
        public contratos()
        {
        }

        // Constructor que inicializa todas las propiedades
        public contratos(int idcontrato, int idvehiculo, int idcliente, int idestado, int id_seguro, DateTime fecha_inicio, DateTime fecha_fin, double precio_dia, double precio_total, bool pagado)
        {
            this.idcontrato = idcontrato;
            this.idvehiculo = idvehiculo;
            this.idcliente = idcliente;
            this.idestado = idestado;
            this.id_seguro = id_seguro;
            this.fecha_inicio = fecha_inicio;
            this.fecha_fin = fecha_fin;
            this.precio_dia = precio_dia;
            this.precio_total = precio_total;
            this.pagado = pagado;
        }


    }
}
