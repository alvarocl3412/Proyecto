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
        public int id { get; set; }
        public int idvehiculo { get; set; }
        public int idCliente { get; set; }
        public int idEstado { get; set; }
        public int idSeguro { get; set; }
        public DateTime fechaInicio { get; set; }
        public DateTime fechaFin { get; set; }
        public double precioDia { get; set; }
        public double precioTotal { get; set; }
        public bool pagado { get; set; }

        // Constructor por defecto
        public contratos()
        {

        }

        // Constructor que inicializa todas las propiedades
        public contratos(int idcontrato, int idvehiculo, int idcliente, int idestado, int id_seguro, DateTime fecha_inicio, DateTime fecha_fin, double precio_dia, double precio_total, bool pagado)
        {
            this.id = idcontrato;
            this.idvehiculo = idvehiculo;
            this.idCliente = idcliente;
            this.idEstado = idestado;
            this.idSeguro = id_seguro;
            this.fechaInicio = fecha_inicio;
            this.fechaFin = fecha_fin;
            this.precioDia = precio_dia;
            this.precioTotal = precio_total;
            this.pagado = pagado;
        }


    }
}
