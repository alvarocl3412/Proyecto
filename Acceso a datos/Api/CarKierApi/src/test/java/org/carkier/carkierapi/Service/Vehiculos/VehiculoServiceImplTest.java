package org.carkier.carkierapi.Service.Vehiculos;

import org.carkier.carkierapi.Repositorio.Empresa.EmpresaRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.Repositorio.Vehiculos.VehiculoRepository;
import org.carkier.carkierapi.modelos.Empresa.Empresa;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceImplTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private VehiculoServiceImpl vehiculoService;

    private Vehiculo vehiculo;
    private Usuario usuario;
    private Empresa empresa;

    @BeforeEach
    void setUp() {
        // Configurar un vehículo de prueba
        vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setMatricula("1234ABC");
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setAnio(2020);
        vehiculo.setKm(15000);
        vehiculo.setPrecioventa(20000.0);
        vehiculo.setPreciodia(50.0);
        vehiculo.setIdEstado(1);
        vehiculo.setImagen("imagen.jpg");

        // Configurar un usuario de prueba
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Test");
        usuario.setApellidos("User");
        usuario.setCorreo("test@example.com");

        // Configurar una empresa de prueba
        empresa = new Empresa();
        empresa.setId(1);
        empresa.setNombre("Test Company");
        empresa.setCorreoElectronico("empresa@example.com");
    }

    @Test
    void testSaveVehiculoWithUsuario() {
        // Configurar el vehículo con un propietario usuario
        vehiculo.setIdUsuariosPropietario(1);
        vehiculo.setIdEmpresa(null);

        // Configurar comportamiento de los mocks
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        // Ejecutar el método a probar
        Vehiculo savedVehiculo = vehiculoService.save(vehiculo);

        // Verificar resultados
        assertNotNull(savedVehiculo);
        assertEquals(vehiculo.getId(), savedVehiculo.getId());
        assertEquals(vehiculo.getMatricula(), savedVehiculo.getMatricula());
        assertEquals(vehiculo.getMarca(), savedVehiculo.getMarca());
        assertEquals(vehiculo.getModelo(), savedVehiculo.getModelo());
        assertEquals(usuario.getId(), savedVehiculo.getIdUsuariosPropietario());
        assertNull(savedVehiculo.getIdEmpresa());

        // Verificar que se llamaron los métodos esperados
        verify(usuarioRepository, times(1)).findById(1);
        verify(empresaRepository, never()).findById(anyInt());
        verify(vehiculoRepository, times(1)).save(vehiculo);
    }

    @Test
    void testSaveVehiculoWithEmpresa() {
        // Configurar el vehículo con una empresa propietaria
        vehiculo.setIdUsuariosPropietario(null);
        vehiculo.setIdEmpresa(1);

        // Configurar comportamiento de los mocks
        when(empresaRepository.findById(1)).thenReturn(Optional.of(empresa));
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        // Ejecutar el método a probar
        Vehiculo savedVehiculo = vehiculoService.save(vehiculo);

        // Verificar resultados
        assertNotNull(savedVehiculo);
        assertEquals(vehiculo.getId(), savedVehiculo.getId());
        assertEquals(vehiculo.getMatricula(), savedVehiculo.getMatricula());
        assertEquals(vehiculo.getMarca(), savedVehiculo.getMarca());
        assertEquals(vehiculo.getModelo(), savedVehiculo.getModelo());
        assertEquals(empresa.getId(), savedVehiculo.getIdEmpresa());
        assertNull(savedVehiculo.getIdUsuariosPropietario());

        // Verificar que se llamaron los métodos esperados
        verify(empresaRepository, times(1)).findById(1);
        verify(usuarioRepository, never()).findById(anyInt());
        verify(vehiculoRepository, times(1)).save(vehiculo);
    }

    @Test
    void testUpdateVehiculo_Success() {
        // Configurar comportamiento de los mocks
        when(vehiculoRepository.findById(1)).thenReturn(Optional.of(vehiculo));
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        // Modificar el vehículo para la actualización
        Vehiculo vehiculoActualizado = new Vehiculo();
        vehiculoActualizado.setId(1);
        vehiculoActualizado.setMatricula("5678XYZ"); // Cambio de matrícula
        vehiculoActualizado.setMarca("Honda"); // Cambio de marca
        vehiculoActualizado.setModelo("Civic"); // Cambio de modelo
        vehiculoActualizado.setAnio(2021); // Cambio de año
        vehiculoActualizado.setKm(5000); // Cambio de kilómetros
        vehiculoActualizado.setPrecioventa(25000.0); // Cambio de precio de venta
        vehiculoActualizado.setPreciodia(60.0); // Cambio de precio por día
        vehiculoActualizado.setIdEstado(2); // Cambio de estado
        vehiculoActualizado.setImagen("nueva_imagen.jpg"); // Cambio de imagen
        vehiculoActualizado.setIdUsuariosPropietario(1);

        // Ejecutar el método a probar
        Optional<Vehiculo> result = vehiculoService.updateVehiuclo(vehiculoActualizado);

        // Verificar resultados
        assertTrue(result.isPresent());
        assertEquals(vehiculoActualizado.getMatricula(), result.get().getMatricula());
        assertEquals(vehiculoActualizado.getMarca(), result.get().getMarca());
        assertEquals(vehiculoActualizado.getModelo(), result.get().getModelo());
        assertEquals(vehiculoActualizado.getAnio(), result.get().getAnio());
        assertEquals(vehiculoActualizado.getKm(), result.get().getKm());
        assertEquals(vehiculoActualizado.getPrecioventa(), result.get().getPrecioventa());
        assertEquals(vehiculoActualizado.getPreciodia(), result.get().getPreciodia());
        assertEquals(vehiculoActualizado.getIdEstado(), result.get().getIdEstado());
        assertEquals(vehiculoActualizado.getImagen(), result.get().getImagen());

        // Verificar que se llamaron los métodos esperados
        verify(vehiculoRepository, times(1)).findById(1);
        verify(vehiculoRepository, times(1)).save(any(Vehiculo.class));
    }

    @Test
    void testUpdateVehiculo_VehiculoNotFound() {
        // Configurar comportamiento del mock
        when(vehiculoRepository.findById(999)).thenReturn(Optional.empty());

        // Crear un vehículo con ID inexistente
        Vehiculo vehiculoInexistente = new Vehiculo();
        vehiculoInexistente.setId(999);

        // Ejecutar el método a probar
        Optional<Vehiculo> result = vehiculoService.updateVehiuclo(vehiculoInexistente);

        // Verificar resultados
        assertFalse(result.isPresent());

        // Verificar que se llamó al método findById pero no al save
        verify(vehiculoRepository, times(1)).findById(999);
        verify(vehiculoRepository, never()).save(any(Vehiculo.class));
    }

    @Test
    void testFindByMatricula() {
        // Configurar comportamiento del mock
        when(vehiculoRepository.findByMatricula("1234ABC")).thenReturn(Optional.of(vehiculo));

        // Ejecutar el método a probar
        Optional<Vehiculo> result = vehiculoService.findByMatricula("1234ABC");

        // Verificar resultados
        assertTrue(result.isPresent());
        assertEquals(vehiculo.getId(), result.get().getId());
        assertEquals(vehiculo.getMatricula(), result.get().getMatricula());

        // Verificar que se llamó al método findByMatricula del repositorio
        verify(vehiculoRepository, times(1)).findByMatricula("1234ABC");
    }

    @Test
    void testFindByMarca() {
        // Crear lista de vehículos
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculo);

        // Configurar comportamiento del mock
        when(vehiculoRepository.findByMarca("Toyota")).thenReturn(vehiculos);

        // Ejecutar el método a probar
        List<Vehiculo> result = vehiculoService.findByMarca("Toyota");

        // Verificar resultados
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehiculo.getId(), result.get(0).getId());
        assertEquals(vehiculo.getMarca(), result.get(0).getMarca());

        // Verificar que se llamó al método findByMarca del repositorio
        verify(vehiculoRepository, times(1)).findByMarca("Toyota");
    }
}
