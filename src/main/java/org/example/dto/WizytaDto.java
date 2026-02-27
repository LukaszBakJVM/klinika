package org.example.dto;

import lombok.Builder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public class WizytaDto {
    @NotNull(message = "Data wizyty nie może być pusta")

    private final LocalDate dataWizyty;
    @NotBlank(message = "Rozpoznanie nie może być puste")
    @Size(max = 255, message = "Rozpoznanie za długie")
    private final String rozpoznanie;
    @NotNull(message = "Kwota nie może być pusta")
    @DecimalMin(value = "0.0", message = "Kwota nie może być ujemna")
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
        return "WizytaDto{" + "dataWizyty=" + dataWizyty + ", rozpoznanie='" + rozpoznanie + '\'' + ", kwota=" + kwota + '}';
    }
}
