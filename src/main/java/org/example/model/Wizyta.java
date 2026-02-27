package org.example.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
@Builder
public class Wizyta {
    private Long id;
    private final Long pacjentId;
    private final LocalDate dataWizyty;
    private final String rozpoznanie;
    private final BigDecimal kwota;

    public Wizyta(Long id, Long pacjentId, LocalDate dataWizyty, String rozpoznanie, BigDecimal kwota) {
        this.id = id;
        this.pacjentId = pacjentId;
        this.dataWizyty = dataWizyty;
        this.rozpoznanie = rozpoznanie;
        this.kwota = kwota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacjentId() {
        return pacjentId;
    }

    public LocalDate getDataWizyty() {
        return dataWizyty;
    }

    public String getRozpoznanie() {
        return rozpoznanie;
    }

    public BigDecimal getKwota() {
        return kwota;
    }
}

