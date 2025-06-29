﻿using CarKier.Modelo;
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
        string apiUrl = "http://localhost:8089/CarKier/";

        public VehiculosDal()
        {
            _httpClient = new HttpClient();
        }


        public async Task<List<vehiculos>> VehiculosfindAll()
        {
            string cadena = apiUrl +"VehiculosfindAll";
            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);

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

        public async Task<vehiculos> findVehiculoId(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return null;
            string urlConParametros = apiUrl + "VehiuculosId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                vehiculos vehiculo = JsonConvert.DeserializeObject<vehiculos>(responseData);
                return vehiculo;
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<string> findVehiculoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return "No Pertenece";
            string urlConParametros = apiUrl+"VehiuculosId/" + id.ToString();

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(urlConParametros);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                vehiculos vehiculo = JsonConvert.DeserializeObject<vehiculos>(responseData);
                return vehiculo?.matricula ?? "No Pertenece";
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return "Error"; // Retorna "Error" si hay una excepción
            }
        }

        public async Task<vehiculos> findVehiculoMatricula(string matricula)
        {
            if (string.IsNullOrWhiteSpace(matricula))
            {
                throw new ArgumentException("La matrícula no puede estar vacía", nameof(matricula));
            }

            string cadena = apiUrl + "VehiuculosMatricula/" + Uri.EscapeDataString(matricula.ToUpper());

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                // Debug: Verifica la respuesta obtenida
                Console.WriteLine($"Respuesta de la API para findVehiculoMatricula: {responseData}");

                vehiculos vehiculo = JsonConvert.DeserializeObject<vehiculos>(responseData);

                if (vehiculo != null)
                {
                    return vehiculo;
                }
                else
                {
                    Console.WriteLine("No se pudo deserializar el vehículo.");
                    return null; // Valor predeterminado si no se encuentra el vehículo
                }
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna null en caso de error
            }
            catch (JsonException e)
            {
                Console.WriteLine($"Error al deserializar el JSON: {e.Message}");
                return null; // Retorna null si hay un error al deserializar
            }
        }

        public async Task<List<vehiculos>> findVehiculoMarca(string marca)
        {
          

            string cadena = apiUrl + "VehiculosMarca/" + Uri.EscapeDataString(marca);

            try
            {
                HttpResponseMessage response = await _httpClient.GetAsync(cadena);
                response.EnsureSuccessStatusCode();

                string responseData = await response.Content.ReadAsStringAsync();

                // Debug: Verifica la respuesta obtenida
                Console.WriteLine($"Respuesta de la API para findVehiculoMarca: {responseData}");

                List<vehiculos> vehiculo = JsonConvert.DeserializeObject<List<vehiculos>>(responseData);

                if (vehiculo != null)
                {
                    return vehiculo;
                }
                else
                {
                    Console.WriteLine("No se pudo deserializar el vehículo.");
                    return null; // Valor predeterminado si no se encuentra el vehículo
                }
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Error en la solicitud: {e.Message}");
                return null; // Retorna null en caso de error
            }
            catch (JsonException e)
            {
                Console.WriteLine($"Error al deserializar el JSON: {e.Message}");
                return null; // Retorna null si hay un error al deserializar
            }
        }

        public async Task<bool> CrearVehiculo(vehiculos vehiculo)
        {
            string cadena = apiUrl + "CrearVehiculo"; // Cambia esta URL a la de tu API

            try
            {
                // Serializar el objeto usuario a JSON
                var json = JsonConvert.SerializeObject(vehiculo);
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

        public async Task<bool> UpdateVehiculo(vehiculos vehiculo)
        {
            string cadena = apiUrl + "updateVehiculo";
            try
            {
                var json = JsonConvert.SerializeObject(vehiculo);
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

        public async Task<bool> deleteVehiculoid(int? id)
        {
            // Verificar si el id es null
            if (id == null)
                return false;
            string urlConParametros = apiUrl + "deleteVehiculo/" + id.ToString();

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
