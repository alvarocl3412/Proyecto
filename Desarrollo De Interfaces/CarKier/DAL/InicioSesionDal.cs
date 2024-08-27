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
        string apiUrl = "https://10.0.2.2:8080/CarKier/UsuarioInicioSesion";

        public InicioSesionDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<usuarios> Login(string correo, string contrasena)
        {

            // Crear un diccionario con los parámetros
            var parametros = new { correo = correo, contrasena = contrasena };

            // Convertir el diccionario a JSON
            string jsonData = JsonConvert.SerializeObject(parametros);

            // Crear el contenido de la solicitud, especificando el tipo de contenido (application/json)
            HttpContent content = new StringContent(jsonData, Encoding.UTF8, "application/json");

            try
            {
                // Realizar la solicitud POST
                HttpResponseMessage response = await _httpClient.PostAsync(apiUrl, content);

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
