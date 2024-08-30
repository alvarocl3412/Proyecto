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
            string cadena = apiUrl + "ComentariosfindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

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


        public async Task<comentarios> findComentarioid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return null;
            string urlConParametros = apiUrl+"ComentariosId/" + id.ToString();

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
