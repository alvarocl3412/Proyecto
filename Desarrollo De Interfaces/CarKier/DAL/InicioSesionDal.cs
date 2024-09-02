using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using CarKier.Modelo;
using CarKier.PLL;
using Newtonsoft.Json;


namespace CarKier.DAL
{
    public class InicioSesionDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier/UsuarioInicioSesionAdmin";

        public InicioSesionDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<usuarios> Login(string correo, string contrasena)
        {
            // Construir la URL con los parámetros
            string cadena = $"{apiUrl}?correo={Uri.EscapeDataString(correo)}&contrasena={Uri.EscapeDataString(contrasena)}";

            try
            {
                // Realizar la solicitud POST (sin cuerpo, ya que los parámetros están en la URL)
                HttpResponseMessage response = await _httpClient.PostAsync(cadena, null);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Deserializar el string JSON a un objeto usuarios
                usuarios usuario = JsonConvert.DeserializeObject<usuarios>(responseData);

                // Devolver el objeto usuarios
                return usuario;
            }
            catch (HttpRequestException e)
            {
                // Manejar cualquier error de solicitud
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }

    }
}
