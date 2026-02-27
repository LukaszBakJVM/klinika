package org.example.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;

@Builder
public final class PacjentDto {
    @NotBlank(message = "Imię nie może być puste")
    private final String imie;
    @NotBlank(message = "Nazwisko nie może być puste")
    private final String nazwisko;
    @NotBlank(message = "Pesel nie może być pusty")
    @PESEL(message = "Niepoprawny  Pesel")
    private final String pesel;

    public PacjentDto(String imie, String nazwisko, String pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
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

    @Override
    public String toString() {
        return "Dane pacjenta" + "imie= " + imie + '\'' + " nazwisko= " + nazwisko + '\'' + ", pesel= " + pesel;
    }
}
