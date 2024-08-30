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
    public class CarnetsDeConducirDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier";

        public CarnetsDeConducirDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<carnets_de_conducir>> findAllByUsuario(int id)
        {
            apiUrl += "/mostrarCarnetsUsuario/findAll"+id.ToString();
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(apiUrl);

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
    }
}