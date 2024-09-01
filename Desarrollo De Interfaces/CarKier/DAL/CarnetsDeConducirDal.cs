using CarKier.Modelo;
using CarKier.PLL;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace CarKier.DAL
{
    public class CarnetsDeConducirDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier/";

        public CarnetsDeConducirDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<carnets_de_conducir>> findAllByUsuario(int id)
        {
            string cadena = apiUrl+"mostrarCarnetsUsuario/" + id.ToString();
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
                List<carnets_de_conducir> carnets = JsonConvert.DeserializeObject<List<carnets_de_conducir>>(responseData);

                return carnets;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }

        }

        public async Task<carnets_de_conducir> findAllByID(int id)
        {
            string cadena = apiUrl + "CarnetsId/" + id.ToString();
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
                carnets_de_conducir carnet = JsonConvert.DeserializeObject<carnets_de_conducir>(responseData);

                return carnet;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }

        }

        public async Task<bool> CrearCarnet(carnets_de_conducir carnet)
        {
            string cadena = apiUrl + "registrarCarnets";

            
            try
            {
                // Serializar el objeto usuario a JSON
                var json = JsonConvert.SerializeObject(carnet);
                var data = new StringContent(json, Encoding.UTF8, "application/json");

                // Realizar la solicitud HTTP POST
                HttpResponseMessage response = await _httpClient.PostAsync(cadena, data);
                response.EnsureSuccessStatusCode(); // Verificar si la solicitud fue exitosa

                // Leer la respuesta (opcional)
                string responseData = await response.Content.ReadAsStringAsync();

                // Puedes realizar validaciones adicionales aquí si es necesario
                return true;
            }
            catch (Exception e)
            {
                // Manejo de errores generales
                Console.WriteLine($"Excepción en la solicitud: {e.Message}");
                return false;
            }
        }

        public async Task<bool> deleteCarnetsid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return false;
            string urlConParametros = apiUrl + "deleteCarnets/" + id.ToString();

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

        public async Task<bool> UpdateCarnetId(carnets_de_conducir carnet)
        {
            string cadena = apiUrl + "updateCarnets";
            try
            {
                var json = JsonConvert.SerializeObject(carnet);
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


    }
}