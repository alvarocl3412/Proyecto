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
    public class EstadoContratoDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://localhost:8089/CarKier/";

        public EstadoContratoDal()
        {
            _httpClient = new HttpClient();
        }

        public async Task<List<estado_contrato>> EstadoContratosfindAll()
        {
            string cadena = apiUrl + "EstadoContratofindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Deserializar el string JSON a una lista de objetos Empresa
                List<estado_contrato> estadoContrato = JsonConvert.DeserializeObject<List<estado_contrato>>(responseData);


                return estadoContrato;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }


        public async Task<string> findEstadoContratoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "Sin Estado";
            string urlConParametros = apiUrl + "EstadoContratoId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                estado_contrato estadoContrato = JsonConvert.DeserializeObject<estado_contrato>(responseData);
                return estadoContrato?.estado ?? "Sin Estado";
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return "Error"; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<int> findEstadoContrato(string estado)
        {
            // Verificar si el id es null
            if (estado == null)
                return 0;
            string urlConParametros = apiUrl + "EstadoContratoEstado/" + estado;

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                estado_contrato estadoContrato = JsonConvert.DeserializeObject<estado_contrato>(responseData);
                return estadoContrato.id;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return 0; // Retorna "Error" si hay una excepción
            }
        }

    }
}
