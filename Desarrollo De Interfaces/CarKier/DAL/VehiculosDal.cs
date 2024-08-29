using CarKier.Modelo;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using static System.Net.WebRequestMethods;

namespace CarKier.DAL
{
    public class VehiculosDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier";
        //string apiUrlEmpresa = "http://10.0.2.2:8089/CarKier/EmpresasId/";

        public VehiculosDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<vehiculos>> VehiculosfindAll()
        {
            apiUrl += "/VehiculosfindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(apiUrl);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Deserializar el string JSON a una lista de objetos Empresa
                List<vehiculos> listVehiculos = JsonConvert.DeserializeObject<List<vehiculos>>(responseData);


                return listVehiculos;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }
        }


        public async Task<string> findEmpresId(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "No Pertenece";
            string urlConParametros = "http://10.0.2.2:8089/CarKier/EmpresasId/"+id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                empresas empresa = JsonConvert.DeserializeObject<empresas>(responseData);
                return empresa?.nombre ?? "No Pertenece";
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return "Error"; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<string> findUsuarioid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "No Pertenece";
            string urlConParametros = "http://10.0.2.2:8089/CarKier/UsuarioId/"+id.ToString();

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

        public async Task<string> findEstadoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "Ningun estado";
            string urlConParametros = "http://10.0.2.2:8089/CarKier/EstadoVehiculoId/"+id.ToString();

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
