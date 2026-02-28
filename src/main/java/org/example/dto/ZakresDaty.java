package org.example.dto;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public class ZakresDaty {
    private final LocalDate dataPoczatkowa;
    private final LocalDate dataKoncowa;

    public ZakresDaty(LocalDate dataPoczatkowa, LocalDate dataKoncowa) {
        this.dataPoczatkowa = dataPoczatkowa;
        this.dataKoncowa = dataKoncowa;
    }

    public LocalDate getDataPoczatkowa() {
        return dataPoczatkowa;
    }

    public LocalDate getDataKoncowa() {
        return dataKoncowa;
    }
}
