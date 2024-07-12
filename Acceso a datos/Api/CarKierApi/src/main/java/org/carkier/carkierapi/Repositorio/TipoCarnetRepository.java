package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.TipoCarnet.TipoCarnet;
import org.springframework.data.jpa.repository.JpaRepository;


//Hay que poner bien los nombres del metodo poner igual que los atrbutos de la clase
public interface TipoCarnetRepository extends JpaRepository<TipoCarnet, Integer> {
    TipoCarnet findByNombre(String nombre);
}
