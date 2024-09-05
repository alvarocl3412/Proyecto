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
    public class UsuariosDal
    {
        private readonly HttpClient _httpClient;
        string apiUrl = "http://10.0.2.2:8089/CarKier/";

        public UsuariosDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<usuarios>> UsuariosfindAll()
        {
            string ruta = apiUrl+"UsuariofindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(ruta);

                // Verificar si la solicitud fue exitosa
                response.EnsureSuccessStatusCode();

                // Leer el contenido de la respuesta como string
                string responseData = await response.Content.ReadAsStringAsync();

                // Imprimir el JSON para inspección
                Console.WriteLine("JSON recibido: " + responseData);

                // Deserializar el string JSON a una lista de objetos Empresa
                List<usuarios> listaUsuarios = JsonConvert.DeserializeObject<List<usuarios>>(responseData);

                return listaUsuarios;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null;
            }

        }

        public async Task<usuarios> findUsuarioId(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return null;
            string urlConParametros = apiUrl+ "UsuarioId/" + id;

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                usuarios usuario = JsonConvert.DeserializeObject<usuarios>(responseData);
                return usuario;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<string> findUsuarioid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "No Pertenece";
            string urlConParametros = apiUrl+"UsuarioId/" + id.ToString();

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

        public async Task<int?> findUsuarioDni(string dni)
        {
            string urlConParametros = apiUrl + "UsuarioDni/" + dni;

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);

                // Verifica si la respuesta es exitosa y contiene datos
                if (response.IsSuccessStatusCode)
                {
                    string responseData = await response.Content.ReadAsStringAsync();

                    // Verifica si hay contenido antes de intentar deserializar
                    if (!string.IsNullOrEmpty(responseData))
                    {
                        usuarios usuario = JsonConvert.DeserializeObject<usuarios>(responseData);
                        return usuario?.id; // Si no se encuentra el usuario, devuelve null
                    }
                    else
                    {
                        return null; // Devuelve null si no hay contenido en la respuesta
                    }
                }
                else
                {
                    // Maneja otros códigos de estado de error si es necesario
                    return null;
                }
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna null si hay una excepción de solicitud HTTP
            }
            catch (JsonSerializationException e)
            {
                Console.WriteLine($"Error en la deserialización: {e.Message}");
                return null; // Retorna null si hay un error en la deserialización
            }
            catch (Exception e)
            {
                Console.WriteLine($"Error inesperado: {e.Message}");
                return null; // Retorna null para cualquier otro error inesperado
            }
        }

        public async Task<usuarios> findUsuarioNombreApellido(String nombre, String appellidos)
        {
            // Verificar si el id es null
            if (nombre.Contains(null) || appellidos.Contains(null))
                return null;

            string urlConParametros = apiUrl + "UsuarioNombreApellido/" + nombre +"/"+ appellidos;

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                usuarios usuario = JsonConvert.DeserializeObject<usuarios>(responseData);
                return usuario;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna "Error" si hay una excepción
            }
        }
        public async Task<bool> CrearUsuario(usuarios usuario)
        {
            string cadena = apiUrl + "UsuarioRegistrar"; // Cambia esta URL a la de tu API

            try
            {
                // Serializar el objeto usuario a JSON
                var json = JsonConvert.SerializeObject(usuario);
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

        public async Task<bool> UpdateUsuarioId(usuarios usuario)
        {
            string cadena = apiUrl + "updateUsuario";
            try
            {
                var json = JsonConvert.SerializeObject(usuario);
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


        public async Task<bool> deleteUsuariosid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return false;
            string urlConParametros = apiUrl + "deleteUsuarios/" + id.ToString();

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
