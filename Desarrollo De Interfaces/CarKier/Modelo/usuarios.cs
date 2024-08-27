using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.Modelo
{
    public class usuarios
    {
        // Propiedades
        public int idusuarios { get; set; }
        public string dni { get; set; }
        public string nombre { get; set; }
        public string apellidos { get; set; }
        public string telefono { get; set; }
        public DateTime fecha_nacimiento { get; set; }
        public string correo { get; set; }
        public string contrasena { get; set; }

        // Constructor por defecto
        public usuarios()
        {
        }

        // Constructor que inicializa todas las propiedades
        public usuarios(int idusuarios, string dni, string nombre, string apellidos, string telefono, DateTime fecha_nacimiento, string correo, string contrasena)
        {
            this.idusuarios = idusuarios;
            this.dni = dni;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.telefono = telefono;
            this.fecha_nacimiento = fecha_nacimiento;
            this.correo = correo;
            this.contrasena = contrasena;
        }
    }
}
