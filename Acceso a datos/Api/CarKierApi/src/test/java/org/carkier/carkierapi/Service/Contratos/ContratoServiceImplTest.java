package org.carkier.carkierapi.Service.Contratos;

import org.carkier.carkierapi.Dto.FechasOcupadas;
import org.carkier.carkierapi.Repositorio.Contratos.ContratoRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.Repositorio.Vehiculos.VehiculoRepository;
import org.carkier.carkierapi.modelos.Contratos.Contrato;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContratoServiceImplTest {

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ContratoServiceImpl contratoService;

    private Contrato contrato;
    private Usuario usuario;
    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        // Configurar un contrato de prueba
        contrato = new Contrato();
        contrato.setId(1);
        contrato.setIdvehiculo(1);
        contrato.setIdCliente(1);
        contrato.setIdEstado(1);
        contrato.setIdSeguro(1);
        contrato.setPrecioDia(50.0);
        contrato.setPrecioTotal(150.0);
        contrato.setFechaInicio(LocalDate.now());
        contrato.setFechaFin(LocalDate.now().plusDays(3));
        contrato.setPagado(true);

        // Configurar un usuario de prueba
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Test");
        usuario.setApellidos("User");
        usuario.setCorreo("test@example.com");

        // Configurar un vehículo de prueba
        vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setMatricula("1234ABC");
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
    }

    @Test
    void testSaveContrato() {
        // Configurar comportamiento del mock
        when(contratoRepository.save(any(Contrato.class))).thenReturn(contrato);

        // Ejecutar el método a probar
        Contrato savedContrato = contratoService.save(contrato);

        // Verificar resultados
        assertNotNull(savedContrato);
        assertEquals(contrato.getId(), savedContrato.getId());
        assertEquals(contrato.getIdvehiculo(), savedContrato.getIdvehiculo());
        assertEquals(contrato.getIdCliente(), savedContrato.getIdCliente());
        assertEquals(contrato.getPrecioDia(), savedContrato.getPrecioDia());
        assertEquals(contrato.getPrecioTotal(), savedContrato.getPrecioTotal());
        assertEquals(contrato.getFechaInicio(), savedContrato.getFechaInicio());
        assertEquals(contrato.getFechaFin(), savedContrato.getFechaFin());
        assertTrue(savedContrato.getPagado());

        // Verificar que se llamó al método save del repositorio
        verify(contratoRepository, times(1)).save(contrato);
    }

    @Test
    void testUpdateContrato_Success() {
        // Configurar comportamiento de los mocks
        when(contratoRepository.findById(1)).thenReturn(Optional.of(contrato));
        when(vehiculoRepository.findById(1)).thenReturn(Optional.of(vehiculo));
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(contratoRepository.save(any(Contrato.class))).thenReturn(contrato);

        // Modificar el contrato para la actualización
        Contrato contratoActualizado = new Contrato();
        contratoActualizado.setId(1);
        contratoActualizado.setIdvehiculo(1);
        contratoActualizado.setIdCliente(1);
        contratoActualizado.setIdEstado(2); // Cambio de estado
        contratoActualizado.setIdSeguro(1);
        contratoActualizado.setPrecioDia(60.0); // Cambio de precio
        contratoActualizado.setPrecioTotal(180.0); // Cambio de precio total
        contratoActualizado.setFechaInicio(LocalDate.now());
        contratoActualizado.setFechaFin(LocalDate.now().plusDays(3));
        contratoActualizado.setPagado(true);

        // Ejecutar el método a probar
        Optional<Contrato> result = contratoService.updateContrato(contratoActualizado);

        // Verificar resultados
        assertTrue(result.isPresent());
        assertEquals(contratoActualizado.getIdEstado(), result.get().getIdEstado());
        assertEquals(contratoActualizado.getPrecioDia(), result.get().getPrecioDia());
        assertEquals(contratoActualizado.getPrecioTotal(), result.get().getPrecioTotal());

        // Verificar que se llamaron los métodos esperados
        verify(contratoRepository, times(1)).findById(1);
        verify(vehiculoRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).findById(1);
        verify(contratoRepository, times(1)).save(any(Contrato.class));
    }

    @Test
    void testUpdateContrato_ContratoNotFound() {
        // Configurar comportamiento del mock
        when(contratoRepository.findById(999)).thenReturn(Optional.empty());

        // Crear un contrato con ID inexistente
        Contrato contratoInexistente = new Contrato();
        contratoInexistente.setId(999);

        // Ejecutar el método a probar
        Optional<Contrato> result = contratoService.updateContrato(contratoInexistente);

        // Verificar resultados
        assertFalse(result.isPresent());

        // Verificar que se llamó al método findById pero no al save
        verify(contratoRepository, times(1)).findById(999);
        verify(contratoRepository, never()).save(any(Contrato.class));
    }

    @Test
    void testFindById() {
        // Configurar comportamiento del mock
        when(contratoRepository.findById(1)).thenReturn(Optional.of(contrato));

        // Ejecutar el método a probar
        Optional<Contrato> result = contratoService.findById(1);

        // Verificar resultados
        assertTrue(result.isPresent());
        assertEquals(contrato.getId(), result.get().getId());

        // Verificar que se llamó al método findById del repositorio
        verify(contratoRepository, times(1)).findById(1);
    }

    @Test
    void testObtenerContratosPorEstado() {
        // Crear lista de contratos
        List<Contrato> contratos = new ArrayList<>();
        contratos.add(contrato);

        // Configurar comportamiento del mock
        when(contratoRepository.findByIdEstado(1)).thenReturn(contratos);

        // Ejecutar el método a probar
        List<Contrato> result = contratoService.obtenerContratosPorEstado(1);

        // Verificar resultados
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(contrato.getId(), result.get(0).getId());

        // Verificar que se llamó al método findByIdEstado del repositorio
        verify(contratoRepository, times(1)).findByIdEstado(1);
    }

    @Test
    void testGetFechasOcupadasDesdeHoyPorVehiculo() {
        // Crear lista de contratos
        List<Contrato> contratos = new ArrayList<>();
        contratos.add(contrato);

        // Configurar comportamiento del mock
        when(contratoRepository.findByIdVehiculoAndFechaFinAfter(eq(1), any(LocalDate.class))).thenReturn(contratos);

        // Ejecutar el método a probar
        FechasOcupadas result = contratoService.getFechasOcupadasDesdeHoyPorVehiculo(1);

        // Verificar resultados
        assertNotNull(result);
        assertFalse(result.getFechas().isEmpty());

        // Verificar que se llamó al método findByIdVehiculoAndFechaFinAfter del repositorio
        verify(contratoRepository, times(1)).findByIdVehiculoAndFechaFinAfter(eq(1), any(LocalDate.class));
    }
}
