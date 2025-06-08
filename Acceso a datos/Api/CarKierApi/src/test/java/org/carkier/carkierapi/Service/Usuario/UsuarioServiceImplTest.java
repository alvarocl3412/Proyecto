package org.carkier.carkierapi.Service.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.carkier.carkierapi.Repositorio.DatosDelUsuario.DatosDelUsuarioRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.modelos.DatosDelUsuario.DatosDelUsuario;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private DatosDelUsuarioRepository datosDelUsuarioRepository;

    @Mock
    private Argon2 argon2;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;
    private DatosDelUsuario datosDelUsuario;

    @BeforeEach
    void setUp() {
        // Configurar un usuario de prueba
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Test");
        usuario.setApellidos("User");
        usuario.setCorreo("test@example.com");
        usuario.setContrasena("hashedPassword");
        usuario.setDni("12345678A");
        usuario.setTelefono("123456789");

        // Configurar datos del usuario
        datosDelUsuario = new DatosDelUsuario();
        datosDelUsuario.setId(1);
        datosDelUsuario.setAdministrador(false);
    }

    @Test
    void testSaveUsuario() {
        // Configurar comportamiento del mock
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        try (MockedStatic<Argon2Factory> argon2FactoryMockedStatic = Mockito.mockStatic(Argon2Factory.class)) {
            argon2FactoryMockedStatic.when(Argon2Factory::create).thenReturn(argon2);
            when(argon2.hash(anyInt(), anyInt(), anyInt(), anyString())).thenReturn("hashedPassword");

            // Ejecutar el método a probar
            Usuario savedUsuario = usuarioService.save(usuario);

            // Verificar resultados
            assertNotNull(savedUsuario);
            assertEquals(usuario.getId(), savedUsuario.getId());
            assertEquals(usuario.getNombre(), savedUsuario.getNombre());
            assertEquals(usuario.getApellidos(), savedUsuario.getApellidos());
            assertEquals(usuario.getCorreo(), savedUsuario.getCorreo());
            assertEquals("hashedPassword", savedUsuario.getContrasena());

            // Verificar que se llamó al método save del repositorio
            verify(usuarioRepository, times(1)).save(usuario);
            verify(argon2, times(1)).hash(anyInt(), anyInt(), anyInt(), anyString());
        }
    }

    @Test
    void testFindByCorreoAndContrasena_Success() {
        // Configurar comportamiento del mock
        when(usuarioRepository.findByCorreo("test@example.com")).thenReturn(Optional.of(usuario));

        try (MockedStatic<Argon2Factory> argon2FactoryMockedStatic = Mockito.mockStatic(Argon2Factory.class)) {
            argon2FactoryMockedStatic.when(Argon2Factory::create).thenReturn(argon2);
            when(argon2.verify(anyString(), anyString())).thenReturn(true);

            // Ejecutar el método a probar
            Optional<Usuario> result = usuarioService.findByCorreoAndContrasena("test@example.com", "password");

            // Verificar resultados
            assertTrue(result.isPresent());
            assertEquals(usuario.getId(), result.get().getId());
            assertEquals(usuario.getCorreo(), result.get().getCorreo());

            // Verificar que se llamaron los métodos esperados
            verify(usuarioRepository, times(1)).findByCorreo("test@example.com");
            verify(argon2, times(1)).verify(anyString(), anyString());
        }
    }

    @Test
    void testFindByCorreoAndContrasena_WrongPassword() {
        // Configurar comportamiento del mock
        when(usuarioRepository.findByCorreo("test@example.com")).thenReturn(Optional.of(usuario));

        try (MockedStatic<Argon2Factory> argon2FactoryMockedStatic = Mockito.mockStatic(Argon2Factory.class)) {
            argon2FactoryMockedStatic.when(Argon2Factory::create).thenReturn(argon2);
            when(argon2.verify(anyString(), anyString())).thenReturn(false);

            // Ejecutar el método a probar
            Optional<Usuario> result = usuarioService.findByCorreoAndContrasena("test@example.com", "wrongPassword");

            // Verificar resultados
            assertFalse(result.isPresent());

            // Verificar que se llamaron los métodos esperados
            verify(usuarioRepository, times(1)).findByCorreo("test@example.com");
            verify(argon2, times(1)).verify(anyString(), anyString());
        }
    }

    @Test
    void testFindByCorreoAndContrasena_UserNotFound() {
        // Configurar comportamiento del mock
        when(usuarioRepository.findByCorreo("nonexistent@example.com")).thenReturn(Optional.empty());

        // Ejecutar el método a probar
        Optional<Usuario> result = usuarioService.findByCorreoAndContrasena("nonexistent@example.com", "password");

        // Verificar resultados
        assertFalse(result.isPresent());

        // Verificar que se llamaron los métodos esperados
        verify(usuarioRepository, times(1)).findByCorreo("nonexistent@example.com");
        // No se debería verificar la contraseña si el usuario no existe
        verifyNoInteractions(argon2);
    }

    @Test
    void testFindByCorreoAndContrasenaAdmin_Success() {
        // Configurar comportamiento del mock
        when(usuarioRepository.findByCorreo("admin@example.com")).thenReturn(Optional.of(usuario));
        datosDelUsuario.setAdministrador(true);
        when(datosDelUsuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(datosDelUsuario));

        try (MockedStatic<Argon2Factory> argon2FactoryMockedStatic = Mockito.mockStatic(Argon2Factory.class)) {
            argon2FactoryMockedStatic.when(Argon2Factory::create).thenReturn(argon2);
            when(argon2.verify(anyString(), anyString())).thenReturn(true);

            // Ejecutar el método a probar
            Optional<Usuario> result = usuarioService.findByCorreoAndContrasenaAdmin("admin@example.com", "password");

            // Verificar resultados
            assertTrue(result.isPresent());
            assertEquals(usuario.getId(), result.get().getId());
            assertEquals(usuario.getCorreo(), result.get().getCorreo());

            // Verificar que se llamaron los métodos esperados
            verify(usuarioRepository, times(1)).findByCorreo("admin@example.com");
            verify(datosDelUsuarioRepository, times(1)).findById(usuario.getId());
            verify(argon2, times(1)).verify(anyString(), anyString());
        }
    }

    @Test
    void testFindByCorreoAndContrasenaAdmin_NotAdmin() {
        // Configurar comportamiento del mock
        when(usuarioRepository.findByCorreo("user@example.com")).thenReturn(Optional.of(usuario));
        datosDelUsuario.setAdministrador(false);
        when(datosDelUsuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(datosDelUsuario));

        try (MockedStatic<Argon2Factory> argon2FactoryMockedStatic = Mockito.mockStatic(Argon2Factory.class)) {
            argon2FactoryMockedStatic.when(Argon2Factory::create).thenReturn(argon2);
            when(argon2.verify(anyString(), anyString())).thenReturn(true);

            // Ejecutar el método a probar
            Optional<Usuario> result = usuarioService.findByCorreoAndContrasenaAdmin("user@example.com", "password");

            // Verificar resultados
            assertFalse(result.isPresent());

            // Verificar que se llamaron los métodos esperados
            verify(usuarioRepository, times(1)).findByCorreo("user@example.com");
            verify(datosDelUsuarioRepository, times(1)).findById(usuario.getId());
            verify(argon2, times(1)).verify(anyString(), anyString());
        }
    }
}