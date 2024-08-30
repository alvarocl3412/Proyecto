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
    public class ComentariosDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier/";

        public ComentariosDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<comentarios>> ComentariosfindAll()
        {
            apiUrl += "ComentariosfindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(apiUrl);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Deserializar el string JSON a una lista de objetos Empresa
                List<comentarios> listaComentarios = JsonConvert.DeserializeObject<List<comentarios>>(responseData);


                return listaComentarios;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }

        public async Task<string> findUsuarioid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "No Pertenece";
            string urlConParametros = "http://10.0.2.2:8089/CarKier/UsuarioId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                usuarios usuario = JsonConvert.DeserializeObject<usuarios>(responseData);
                return usuario?.nombre ?? "No Pertenece";
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return "Error"; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<string> findVehiculoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "No Pertenece";
            string urlConParametros = "http://10.0.2.2:8089/CarKier/VehiuculosId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                vehiculos vehiculo = JsonConvert.DeserializeObject<vehiculos>(responseData);
                return vehiculo?.matricula ?? "No Pertenece";
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return "Error"; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<comentarios> findComentarioid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return null;
            string urlConParametros = "http://10.0.2.2:8089/CarKier/ComentariosId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                comentarios comentario = JsonConvert.DeserializeObject<comentarios>(responseData);
                return comentario;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna "Error" si hay una excepción
            }
        }


    }
}
