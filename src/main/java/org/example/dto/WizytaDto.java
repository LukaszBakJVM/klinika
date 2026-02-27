package org.example.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
@Builder
public  class WizytaDto {
    private final LocalDate dataWizyty;
    private final String rozpoznanie;
    private final BigDecimal kwota;

    public WizytaDto(LocalDate dataWizyty, String rozpoznanie, BigDecimal kwota) {
        this.dataWizyty = dataWizyty;
        this.rozpoznanie = rozpoznanie;
        this.kwota = kwota;
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

    @Override
    public String toString() {
        return "WizytaDto{" +
                "dataWizyty=" + dataWizyty +
                ", rozpoznanie='" + rozpoznanie + '\'' +
                ", kwota=" + kwota +
                '}';
    }
}
