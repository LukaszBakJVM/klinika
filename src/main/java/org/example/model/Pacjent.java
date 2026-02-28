package org.example.model;


import lombok.Builder;

@Builder
public class Pacjent {

    private Long id;

    private String imie;

    private String nazwisko;

    private String pesel;

    public Pacjent(Long id, String imie, String nazwisko, String pesel) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}




