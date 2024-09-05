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
    public class ContratosDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier/";

        public ContratosDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<contratos>> ContratosfindAll()
        {
            string cadena = apiUrl + "ContratofindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Deserializar el string JSON a una lista de objetos Empresa
                List<contratos> listaContrato = JsonConvert.DeserializeObject<List<contratos>>(responseData);


                return listaContrato;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }

        public async Task<contratos> findContratoid(int id)
        {
            // Verificar si el id es null
            string urlConParametros = apiUrl + "ContratosId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                contratos contrato = JsonConvert.DeserializeObject<contratos>(responseData);
                return contrato;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<bool> CrearContrato(contratos contrato)
        {
            string cadena = apiUrl + "CrearContrato";

            try
            {
                // Serializar el objeto usuario a JSON
                var json = JsonConvert.SerializeObject(contrato);
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

        public async Task<bool> UpdateContrato(contratos contrato)
        {
            string cadena = apiUrl + "updateContrato";
            try
            {
                var json = JsonConvert.SerializeObject(contrato);
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


        public async Task<bool> deleteContratoId(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return false;

            string urlConParametros = apiUrl + "deleteContrato/" + id.ToString();

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
                return false; // Retorna false si hay una excepción
            }
        }


    }
}
