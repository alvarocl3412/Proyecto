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

    }
}
