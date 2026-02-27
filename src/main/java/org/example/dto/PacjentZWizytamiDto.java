package org.example.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class PacjentZWizytamiDto {
    private final String imie;

    private final String nazwisko;

    private final String pesel;

    private final List<WizytaDto> wizyty;

    public PacjentZWizytamiDto(String imie, String nazwisko, String pesel, List<WizytaDto> wizyty) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.wizyty = wizyty;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public List<WizytaDto> getWizyty() {
        return wizyty;
    }

    @Override
    public String toString() {
        return "PacjentZWizytamiDto{" + "imie='" + imie + '\'' + ", nazwisko='" + nazwisko + '\'' + ", pesel='" + pesel + '\'' + ", wizyty=" + wizyty + '}';
    }
}

