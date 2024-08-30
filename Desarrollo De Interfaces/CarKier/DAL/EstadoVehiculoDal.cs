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
    public class EstadoVehiculoDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier";

        public EstadoVehiculoDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<string> findEstadoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "Ningun estado";
            string urlConParametros = "http://10.0.2.2:8089/CarKier/EstadoVehiculoId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                estado_vehiculo estadovehiculo = JsonConvert.DeserializeObject<estado_vehiculo>(responseData);
                return estadovehiculo?.estado ?? "Ningun estado";
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return "Error"; // Retorna "Error" si hay una excepción
            }
        }

    }


}
