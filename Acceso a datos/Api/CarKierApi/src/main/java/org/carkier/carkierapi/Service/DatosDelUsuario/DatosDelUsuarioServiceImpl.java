package org.carkier.carkierapi.Service.DatosDelUsuario;

import org.carkier.carkierapi.Repositorio.DatosDelUsuarioRepository;
import org.carkier.carkierapi.modelos.DatosDelUsuario.DatosDelUsuario;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DatosDelUsuarioServiceImpl  implements DatosDelUsuarioService {
    private final DatosDelUsuarioRepository repositorio;

    public DatosDelUsuarioServiceImpl(DatosDelUsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }
    @Override
    public List<DatosDelUsuario> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<DatosDelUsuario> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Optional<DatosDelUsuario> updateDatos(DatosDelUsuario datos) {
        Optional<DatosDelUsuario> datosUsu = repositorio.findById(datos.getId());
        if (datosUsu.isPresent()){
            DatosDelUsuario datosActualizados = datosUsu.get();
            datosActualizados.setPuntos(datos.getPuntos());
            datosActualizados.setAdministrador(datos.isAdministrador());
            datosActualizados.setFechaBanInicio(datos.getFechaBanInicio());
            datosActualizados.setFechaBanFinal(datos.getFechaBanFinal());
            datosActualizados.setCantidadBan(datos.getCantidadBan());
            datosActualizados.setMarcaEliminar(datos.isMarcaEliminar());
            repositorio.save(datosActualizados);
            return Optional.of(datosActualizados);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public DatosDelUsuario banearUsuario(Integer id) {
        DatosDelUsuario datosUsu = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Datos del usuario no encontrado con el id: " + id));
        datosUsu.setFechaBanInicio(LocalDate.now());
        switch (datosUsu.getCantidadBan()) {
            case 0:
                datosUsu.setFechaBanFinal(datosUsu.getFechaBanInicio().plusDays(2));
                break;
            case 1:
                datosUsu.setFechaBanFinal(datosUsu.getFechaBanInicio().plusDays(4));
                break;
            case 2:
                datosUsu.setFechaBanFinal(datosUsu.getFechaBanInicio().plusDays(5));
                break;
            case 3:
                datosUsu.setFechaBanFinal(datosUsu.getFechaBanInicio().plusDays(6));
                break;
            case 4:
                datosUsu.setFechaBanFinal(datosUsu.getFechaBanInicio().plusDays(7));
                break;
            case 5:
                marcarEliminarUsuario(id);
                break;
            default:
                // Manejar otros casos si es necesario
                break;
        }
        datosUsu.setCantidadBan(datosUsu.getCantidadBan()+1);
        return repositorio.save(datosUsu);
    }

    @Override
    public DatosDelUsuario marcarEliminarUsuario(Integer id) {
        DatosDelUsuario datosUsu = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Datos del usuario no encontrado con el id: " + id));
        datosUsu.setMarcaEliminar(true);
        return repositorio.save(datosUsu);
    }
}
