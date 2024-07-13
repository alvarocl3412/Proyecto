package org.carkier.carkierapi.Service.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.carkier.carkierapi.Repositorio.DatosDelUsuarioRepository;
import org.carkier.carkierapi.Repositorio.UsuarioRepository;
import org.carkier.carkierapi.modelos.DatosDelUsuario.DatosDelUsuario;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl  implements  UsuarioService{
    private final UsuarioRepository repositorio;
    private DatosDelUsuarioRepository datosDelUsuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository repositorio, DatosDelUsuarioRepository datosDelUsuarioRepository) {
        this.repositorio = repositorio;
        this.datosDelUsuarioRepository = datosDelUsuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return repositorio.findAll();
    }

    private List<Usuario> findAllUsuariosConDatos() {
        List<Usuario> usuarios = repositorio.findAll();

        // Para cada usuario, obtener sus datos del usuario
        usuarios.forEach(usuario -> {
            DatosDelUsuario datos = datosDelUsuarioRepository.findById(usuario.getId()).orElse(null);
      //      usuario.setDatosDelUsuario(datos);
        });
         return usuarios;
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Optional<Usuario> findByDni(String dni) {
        return repositorio.findByDni(dni);
    }
    @Override
    public Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena) {
        Optional<Usuario> optionalUsuario = repositorio.findByCorreo(correo);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Argon2 argon2 = Argon2Factory.create();
            if (argon2.verify(usuario.getContrasena(), contrasena)) {
                //Como existe el usuario miramos ahora si esta baneado o no
                DatosDelUsuario datUsu = datosDelUsuarioRepository.findById(usuario.getId()).get();
                LocalDate hoy = LocalDate.now();
                if (datUsu.getFechaBanInicio() != null && datUsu.getFechaBanFinal() != null
                        && (hoy.isAfter(datUsu.getFechaBanInicio()) || hoy.isEqual(datUsu.getFechaBanInicio()))
                        && (hoy.isBefore(datUsu.getFechaBanFinal()) || hoy.isEqual(datUsu.getFechaBanFinal()))) {
                    // El usuario está baneado, retornar Optional.empty()
                    return Optional.empty();
                }
                // El usuario no está baneado, retornar el usuario
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> updateUsuario(Usuario usuario) {
        Optional<Usuario> usur = repositorio.findById(usuario.getId());
        if (usur.isPresent()){
            Usuario usuarioActualizado = usur.get();
            usuarioActualizado.setDni(usuario.getDni());
            usuarioActualizado.setNombre(usuario.getNombre());
            usuarioActualizado.setApellidos(usuario.getApellidos());
            usuarioActualizado.setTelefono(usuario.getTelefono());
            usuarioActualizado.setCorreo(usuario.getCorreo());
            usuarioActualizado.setContrasena(usuario.getContrasena());
            repositorio.save(usuario);
            return Optional.of(usuarioActualizado);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Usuario save(Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create();
        usuario.setContrasena(argon2.hash(2, 65536, 1, usuario.getContrasena()));  // Codificar en una sola línea
        return repositorio.save(usuario);
    }

}