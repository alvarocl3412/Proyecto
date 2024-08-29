package org.carkier.carkierapi.Service.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.carkier.carkierapi.Repositorio.DatosDelUsuario.DatosDelUsuarioRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.modelos.DatosDelUsuario.DatosDelUsuario;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
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
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByCorreoAndContrasenaAdmin(String correo, String contrasena) {
        Optional<Usuario> optionalUsuario = repositorio.findByCorreo(correo);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Argon2 argon2 = Argon2Factory.create();
            if (argon2.verify(usuario.getContrasena(), contrasena)) {

                Optional<DatosDelUsuario> datosusu = datosDelUsuarioRepository.findById(usuario.getId());
                if (datosusu.isPresent()) {
                    DatosDelUsuario datos = datosusu.get();
                    if (datos.isAdministrador()){
                        return Optional.of(usuario);
                    }
                }
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
        usuario.setContrasena(argon2.hash(2, 65536, 1, usuario.getContrasena()));  // Codificamos la contraseña
        return repositorio.save(usuario);
    }

}