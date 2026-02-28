package org.example.dto;

import lombok.Builder;

@Builder
public class ZapiszPacjentaZWizytami {
   private  PacjentDto pacjentDto;
   private  WizytaDto wizytaDto;

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
