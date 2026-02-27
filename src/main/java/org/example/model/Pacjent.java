package org.example.model;




import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pacjent {

    private Long id;

    private final String imie;

    private final String nazwisko;

    private final String pesel;

    private final List<Wizyta> wizyty;


    private Pacjent(Builder builder) {
        this.id = builder.id;
        this.imie = builder.imie;
        this.nazwisko = builder.nazwisko;
        this.pesel = builder.pesel;
        this.wizyty = Collections.unmodifiableList(
                new ArrayList<>(builder.wizyty));
    }


    public static class Builder {

        private Long id;
        private String imie;
        private String nazwisko;
        private String pesel;
        private List<Wizyta> wizyty = new ArrayList<>();

        public Builder imie(String imie) {
            this.imie = imie;
            return this;
        }

        public Builder nazwisko(String nazwisko) {
            this.nazwisko = nazwisko;
            return this;
        }

        public Builder pesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder addWizyta(Wizyta wizyta) {
            this.wizyty.add(wizyta);
            return this;
        }

        public Pacjent build() {
            return new Pacjent(this);
        }
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

    public List<Wizyta> getWizyty() {
        return Collections.unmodifiableList(wizyty);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pacjent)) return false;
        Pacjent pacjent = (Pacjent) o;
        return Objects.equals(pesel, pacjent.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }
}