package org.example.utils.datareader;

import org.example.dto.PacjentDto;
import org.example.dto.WizytaDto;
import org.example.dto.ZakresDaty;
import org.example.dto.ZapiszPacjentaZWizytami;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.time.LocalDate.parse;

public class DataReader {

    private final Scanner sc = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public PacjentDto zapiszPacjenta() {
        printLine("Imię: ");
        String imie = sc.nextLine();

        printLine("Nazwisko: ");
        String nazwisko = sc.nextLine();

        printLine("Pesel: ");
        String pesel = sc.nextLine();
        return PacjentDto.builder().imie(imie).nazwisko(nazwisko).pesel(pesel).build();

    }


    public ZapiszPacjentaZWizytami zapiszPacjentaZWizytama() {
        printLine("Imię: ");
        String imie = sc.nextLine();

        printLine("Nazwisko: ");
        String nazwisko = sc.nextLine();

        printLine("Pesel: ");
        String pesel = sc.nextLine();


        LocalDate dateWizyty = readDate();

        printLine("Rozpoznanie: ");
        String rozpoznanie = sc.nextLine();


        BigDecimal kwotaWizyty = kwotaWizyty();


        PacjentDto pacjentDto = PacjentDto.builder().imie(imie).nazwisko(nazwisko).pesel(pesel).build();
        WizytaDto wizytaDto = WizytaDto.builder().dataWizyty(dateWizyty).rozpoznanie(rozpoznanie).kwota(kwotaWizyty).build();

        return ZapiszPacjentaZWizytami.builder().pacjentDto(pacjentDto).wizytaDto(wizytaDto).build();


    }

    public String podajPesel() {
        printLine("Podaj Pesel: ");
        return sc.nextLine();


    }

    public ZakresDaty kwotyWZakresieDaty() {
        LocalDate dataPocztkowa = readDate();

        LocalDate dataKoncowa = readDate();

        return ZakresDaty.builder().dataPoczatkowa(dataPocztkowa).dataKoncowa(dataKoncowa).build();


    }

    public void close() {
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    private LocalDate readDate() {
        while (true) {

            try {
                printLine("Podaj datę w formacie dd.MM.yyyy: ");
                String input = sc.nextLine();
                return parse(input, formatter);


            } catch (DateTimeParseException e) {
                printLine("Zła data! Spróbuj ponownie.");
            }
        }
    }

    private BigDecimal kwotaWizyty() {


        while (true) {
            try {
                printLine("Kwota: ");
                String kwota = sc.nextLine();
                return new BigDecimal(kwota).setScale(2, RoundingMode.HALF_UP);

            } catch (Exception e) {
                printLine("Zły format kwoty! Spróbuj ponownie.");
            }
        }
    }

    public <T> void printLine(T value) {
        System.out.println(value);
    }

}
