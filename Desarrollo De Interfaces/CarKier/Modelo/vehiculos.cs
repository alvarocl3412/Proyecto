using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class vehiculos
    {

        // Propiedades
        public int idvehiculo { get; set; }
        public int idempresa { get; set; }
        public int idusuariospropietario { get; set; }
        public int idestado { get; set; }
        public string matricula { get; set; }
        public string marca { get; set; }
        public string modelo { get; set; }
        public int anio { get; set; }
        public int km { get; set; }
        public double precioventa { get; set; }
        public double preciodia { get; set; }

        // Constructor por defecto
        public vehiculos()
        {
        }

        // Constructor que inicializa todas las propiedades
        public vehiculos(int idvehiculo, int idempresa, int idusuariospropietario, int idestado, string matricula, string marca, string modelo, int anio, int km, double precioventa, double preciodia)
        {
            this.idvehiculo = idvehiculo;
            this.idempresa = idempresa;
            this.idusuariospropietario = idusuariospropietario;
            this.idestado = idestado;
            this.matricula = matricula;
            this.marca = marca;
            this.modelo = modelo;
            this.anio = anio;
            this.km = km;
            this.precioventa = precioventa;
            this.preciodia = preciodia;
        }
    }
}
