using CarKier.Modelo;
using CarKier.PLL;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.DAL
{
     public class DatosDelUsuarioDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://localhost:8089/CarKier/";

        public DatosDelUsuarioDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<datos_del_usuario> DatosUsuariofindId(int id)
        {
            string cadena = apiUrl + "DatosUsuariosId/" + id.ToString();
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Deserializar el string JSON a una lista de objetos Empresa
                datos_del_usuario datos = JsonConvert.DeserializeObject<datos_del_usuario>(responseData);


                return datos;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }

        public async Task<bool> UpdateDatosId(datos_del_usuario datos)
        {
            string cadena = apiUrl + "updateDatosUsuarios"; 
            try
            {
                var json = JsonConvert.SerializeObject(datos);
                var data = new StringContent(json, Encoding.UTF8, "application/json");

                HttpResponseMessage response = await _httpClient.PutAsync(cadena, data);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                // Puedes realizar validaciones aquí si lo necesitas, dependiendo de la respuesta
                return true;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return false;
            }
        }

        public async Task<bool> DeleteUsuarioId(int id)
        {
            string cadena = apiUrl+ "DatosDelUsuarioMarcarEliminar/" + id.ToString(); // Construir la URL con el ID del usuario

            try
            {
                // Realizar la solicitud HTTP PUT sin cuerpo
                HttpResponseMessage response = await _httpClient.PutAsync(cadena, null);
                response.EnsureSuccessStatusCode(); // Verificar si la solicitud fue exitosa

                // Leer la respuesta (opcional)
                string responseData = await response.Content.ReadAsStringAsync();

                return true;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return false;
            }
        }


    }
}
