package org.example.dto;

import lombok.Builder;

@Builder
public class ZapiszPacjentaZWizytami {
   private final PacjentDto pacjentDto;
   private final WizytaDto wizytaDto;

    public ZapiszPacjentaZWizytami(PacjentDto pacjentDto, WizytaDto wizytaDto) {
        this.pacjentDto = pacjentDto;
        this.wizytaDto = wizytaDto;
    }

    public PacjentDto getPacjentDto() {
        return pacjentDto;
    }

    public WizytaDto getWizytaDto() {
        return wizytaDto;
    }

    @Override
    public String toString() {
        return "ZapiszPacjentaZWizytami{" +
                "pacjentDto=" + pacjentDto +
                ", wizytaDto=" + wizytaDto +
                '}';
    }
}
