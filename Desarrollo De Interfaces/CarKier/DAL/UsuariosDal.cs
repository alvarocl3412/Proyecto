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
    public class UsuariosDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier";

        public UsuariosDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<usuarios>> UsuariosfindAll()
        {
            string ruta = apiUrl+"/UsuariofindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(ruta);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Imprimir el JSON para inspección
                Console.WriteLine("JSON recibido: " + responseData);

                // Deserializar el string JSON a una lista de objetos Empresa
                List<usuarios> listaUsuarios = JsonConvert.DeserializeObject<List<usuarios>>(responseData);

                return listaUsuarios;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }

        }

        public async Task<usuarios> findUsuarioDni(string dni)
        {
            // Verificar si el id es null
            if (dni == null)
                return null;
            string urlConParametros = apiUrl+"/UsuarioDni/" + dni;

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                usuarios usuario = JsonConvert.DeserializeObject<usuarios>(responseData);
                return usuario;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<string> findUsuarioid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "No Pertenece";
            string urlConParametros = apiUrl+"/UsuarioId/" + id.ToString();

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



    }
}
