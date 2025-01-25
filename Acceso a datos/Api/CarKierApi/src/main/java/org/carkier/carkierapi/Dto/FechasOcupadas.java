package org.carkier.carkierapi.Dto;

import java.time.LocalDate;
import java.util.List;

public class FechasOcupadas {
    private List<String> fechas;

    // Constructor
    public FechasOcupadas(List<String> fechas) {
        this.fechas = fechas;
    }

    // Getter y Setter
    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }
}
