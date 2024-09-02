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
        public int id { get; set; }
        public int? idEmpresa { get; set; }
        public int? idUsuariosPropietario { get; set; }
        public int? idEstado { get; set; }
        public string matricula { get; set; }
        public string marca { get; set; }
        public string modelo { get; set; }
        public int? anio { get; set; }
        public int? km { get; set; }
        public double? precioventa { get; set; }
        public double? preciodia { get; set; }

        // Constructor por defecto
        public vehiculos()
        {
        }

        // Constructor que inicializa todas las propiedades
        public vehiculos(int idvehiculo, int? idempresa, int? idusuariospropietario, int? idestado, string matricula, string marca, string modelo, int? anio, int? km, double? precioventa, double? preciodia)
        {
            this.id = idvehiculo;
            this.idEmpresa = idempresa;
            this.idUsuariosPropietario = idusuariospropietario;
            this.idEstado = idestado;
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