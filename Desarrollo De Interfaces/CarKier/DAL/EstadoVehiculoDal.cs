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
        string apiUrl = "http://10.0.2.2:8089/CarKier/";

        public EstadoVehiculoDal()
        {
            _httpClient = new HttpClient();
        }

        public async Task<List<estado_vehiculo>> findEstadoVehiculoAll()
        {
            string cadena = apiUrl + "EstadoVehiculo/findAll";

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

                response.EnsureSuccessStatusCode(); // Verifica si la solicitud fue exitosa

                string responseData = await response.Content.ReadAsStringAsync();

                // Supongamos que la API devuelve un JSON con una lista de strings
                List<estado_vehiculo> estado = JsonConvert.DeserializeObject<List<estado_vehiculo>>(responseData);

                return estado;
            }

            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }

        public async Task<string> findEstadoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "Ningun estado";
            string urlConParametros = apiUrl + "EstadoVehiculoId/" + id.ToString();

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
