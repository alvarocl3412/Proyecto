using CarKier.Modelo;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.DAL
{
    public class TipoCarnetDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier/";

        public TipoCarnetDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<string> findipoCarnetById(int id)
        {
            string cadena = apiUrl + "TipoCarnetId/" + id.ToString();
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Imprimir el JSON para inspección
                Console.WriteLine("JSON recibido: " + responseData);

                // Deserializar el string JSON a una lista de objetos Empresa
                tipo_carnet tipo = JsonConvert.DeserializeObject<tipo_carnet>(responseData);

                return tipo.nombre;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }

        }

        public async Task<List<tipo_carnet>> findTiposDeCarnetAll()
        {
            string cadena = apiUrl+"TipoCarnetfindAll";

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

                response.EnsureSuccessStatusCode(); // Verifica si la solicitud fue exitosa

                string responseData = await response.Content.ReadAsStringAsync();

                // Supongamos que la API devuelve un JSON con una lista de strings
                List<tipo_carnet> tiposDeCarnet = JsonConvert.DeserializeObject<List<tipo_carnet>>(responseData);

                return tiposDeCarnet;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                // Si ocurre un error, retornamos una lista vacía o puedes lanzar la excepción dependiendo de tu lógica
                return new List<tipo_carnet>();
            }
        }


        public async Task<bool> CrearTipoCarnet(string nombre)
        {
            string cadena = apiUrl + "TipoCarnetsave";

            try
            {
                // Construir la solicitud con el nombre como parámetro de consulta
                var queryString = new Dictionary<string, string> { { "nombre", nombre } };
                var content = new FormUrlEncodedContent(queryString);

                // Realizar la solicitud HTTP POST
                HttpResponseMessage response = await _httpClient.PostAsync(cadena, content);

                // Verificar si la solicitud fue exitosa
                if (response.IsSuccessStatusCode)
                {
                    // Leer la respuesta (opcional)
                    string responseData = await response.Content.ReadAsStringAsync();
                    Console.WriteLine($"Respuesta de la API: {responseData}");
                    return true;
                }
                else
                {
                    // Leer el contenido de error si la solicitud no fue exitosa
                    string errorResponse = await response.Content.ReadAsStringAsync();
                    Console.WriteLine($"Error en la solicitud: {response.StatusCode} - {errorResponse}");
                    return false;
                }
            }
            catch (Exception e)
            {
                // Manejo de errores generales
                Console.WriteLine($"Excepción en la solicitud: {e.Message}");
                return false;
            }
        }

        public async Task<bool> deleteTipoCanrteid(int id)
        {
            // Verificar si el id es null
            if (id == null)
                return false;
            string urlConParametros = apiUrl + "TipoCarnetDelete/" + id.ToString();

            try
            {
                // Usar DELETE en lugar de GET para eliminar
                HttpResponseMessage response = await _httpClient.DeleteAsync(urlConParametros);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                return true; // Si no hubo excepción, la operación fue exitosa
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return false; // Retorna "Error" si hay una excepción
            }
        }




    }
}
