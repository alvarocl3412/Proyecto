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
        string apiUrl = "http://10.0.2.2:8089/CarKier/";

        public EstadoContratoDal()
        {
            _httpClient = new HttpClient();
        }

        public async Task<string> findEstadoContratoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "Sin Estado";
            string urlConParametros = apiUrl+"EstadoContratoId/" + id.ToString();

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

    }
}
