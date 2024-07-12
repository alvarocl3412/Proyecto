package org.carkier.carkierapi.Service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.carkier.carkierapi.Repositorio.DatosDelUsuarioRepository;
import org.carkier.carkierapi.Repositorio.UsuarioRepository;
import org.carkier.carkierapi.modelos.DatosDelUsuario;
import org.carkier.carkierapi.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioServiceImpl  implements  UsuarioService{
    private final UsuarioRepository repositorio;
    @Autowired
    private DatosDelUsuarioRepository datosDelUsuarioRepository;
    public UsuarioServiceImpl(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
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
            usuario.setDatosDelUsuario(datos);
        });
         return usuarios;
    }
    @Override
    public List<Usuario> findAllUsuariosBaneados() {
        return findAllUsuariosConDatos().stream()
                .filter(usuario -> usuario.getDatosDelUsuario() != null && usuario.getDatosDelUsuario().getFechaBanInicio() != null)
                .collect(Collectors.toList());
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
                // Cargar los datos del usuario
                Optional<DatosDelUsuario> optionalDatos = datosDelUsuarioRepository.findById(usuario.getId());
                optionalDatos.ifPresent(usuario::setDatosDelUsuario);
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Usuario save(Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create();
        usuario.setContrasena(argon2.hash(2, 65536, 1, usuario.getContrasena()));  // Codificar en una sola línea
        return repositorio.save(usuario);
    }

}