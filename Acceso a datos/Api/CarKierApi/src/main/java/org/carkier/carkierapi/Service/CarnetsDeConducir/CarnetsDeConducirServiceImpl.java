package org.carkier.carkierapi.Service.CarnetsDeConducir;

import org.carkier.carkierapi.Repositorio.CarnetDeConducir.CarnetsDeConducirRepository;
import org.carkier.carkierapi.modelos.CarnetsDeConducir.CarnetsDeConducir;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarnetsDeConducirServiceImpl implements CarnetsDeConducirService {
    private final CarnetsDeConducirRepository repositorio;

    public CarnetsDeConducirServiceImpl(CarnetsDeConducirRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CarnetsDeConducir> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<CarnetsDeConducir> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Optional<CarnetsDeConducir> updateCarnet(CarnetsDeConducir carnet) {
        Optional<CarnetsDeConducir> existeVehiuclo = repositorio.findById(carnet.getId());
        if (existeVehiuclo.isPresent()) {
            //Como exite creamos un carnet
            CarnetsDeConducir carnetActualizado = existeVehiuclo.get();
            //Modifcamos todos los datos del dicho carnet
            carnetActualizado.setIdusuario(carnet.getIdusuario());
            carnetActualizado.setIdTipocarnet(carnet.getIdTipocarnet());
            carnetActualizado.setFechaExpedicion(carnet.getFechaExpedicion());
            carnetActualizado.setFechaCaducidad(null);
            repositorio.save(carnetActualizado);
            return Optional.of(carnetActualizado);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public CarnetsDeConducir save(CarnetsDeConducir carnet) {
        return repositorio.save(carnet);
    }

    @Override
    public void deleteById(Integer id) {
        CarnetsDeConducir canet = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El carnet de conducir no se ha encontrado con el ID: " + id));
        repositorio.delete(canet);
    }

}
