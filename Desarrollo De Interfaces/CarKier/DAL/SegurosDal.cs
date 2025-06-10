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
    public class SegurosDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://localhost:8089/CarKier/";

        public SegurosDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<tipos_seguros>> SegurosfindAll()
        {
            string cadena = apiUrl + "TipoSegurofindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Deserializar el string JSON a una lista de objetos Empresa
                List<tipos_seguros> listaSeguros = JsonConvert.DeserializeObject<List<tipos_seguros>>(responseData);

                return listaSeguros;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }

        public async Task<tipos_seguros> findSegurosId(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return null;
            string urlConParametros = apiUrl + "TipoSeguroId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                tipos_seguros tipos = JsonConvert.DeserializeObject<tipos_seguros>(responseData);
                return tipos;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<string> findSegurosid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "Sin Seguro";
            string urlConParametros = apiUrl+"TipoSeguroId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                tipos_seguros tipos = JsonConvert.DeserializeObject<tipos_seguros>(responseData);
                return tipos?.nombre ?? "Sin Seguro";
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return "Error"; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<bool> CrearSeguro(tipos_seguros seguro)
        {
            string cadena = apiUrl + "crearSeguro"; // Cambia esta URL a la de tu API

            try
            {
                // Serializar el objeto usuario a JSON
                var json = JsonConvert.SerializeObject(seguro);
                var data = new StringContent(json, Encoding.UTF8, "application/json");

                // Realizar la solicitud HTTP POST
                HttpResponseMessage response = await _httpClient.PostAsync(cadena, data);
                response.EnsureSuccessStatusCode(); // Verificar si la solicitud fue exitosa

                // Leer la respuesta (opcional)
                string responseData = await response.Content.ReadAsStringAsync();

                // Puedes realizar validaciones adicionales aquí si es necesario
                return true;
            }
            catch (HttpRequestException e)
            {
                // Manejo de errores
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return false;
            }
        }

        public async Task<bool> UpdateSeguro(tipos_seguros seguro)
        {
            string cadena = apiUrl + "modificarSeguro";
            try
            {
                var json = JsonConvert.SerializeObject(seguro);
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

        public async Task<bool> deleteSegurosid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return false;
            string urlConParametros = apiUrl + "deleteTipoSeguro/" + id.ToString();

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