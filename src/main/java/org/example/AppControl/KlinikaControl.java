package org.example.AppControl;

import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.dto.ZakresDaty;
import org.example.dto.ZapiszPacjentaZWizytami;
import org.example.exception.NoSuchOptionException;
import org.example.exception.SqlConnectionException;
import org.example.service.ClinicService;
import org.example.service.ClinicServiceImpl;
import org.example.utils.datareader.DataReader;

import java.math.BigDecimal;
import java.util.InputMismatchException;

public class KlinikaControl {

    private final DataReader dataReader = new DataReader();
    ClinicService clinicService = new ClinicServiceImpl();


    public void controlLoop() {
        Option option;

        do {
            try {


                printOptions();
                option = getOption();
                switch (option) {
                    case ADD_PATIENT:
                        zapiszPacjenta();
                        break;
                    case ADD_PATIENT_AND_VISIT:
                        zapiszPacjentaZWizytami();
                        break;
                    case PRINT_PATIENT_VISIT:
                        wyszukajWizytyNaPodstawiePeselu();
                        break;
                    case PRINT_COST_VISIT_IN_DATE_RANGE:
                        sumaKosztowWizyt();

                    break;

                    case EXIT:
                        exit();
                        break;
                    default:
                        dataReader.printLine("Nie ma takiej opcji, wprowadź ponownie: ");
                }
            } catch (Exception e) {
                dataReader.printLine(e.getMessage());
                option = null;

            }

        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                dataReader.printLine(e.getMessage() + ", podaj ponownie:");
            } catch (InputMismatchException ignored) {
                dataReader.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:");
            }
        }

        return option;
    }

    private void zapiszPacjenta() {
        try {

            PacjentDto pacjentDto = dataReader.zapiszPacjenta();
            clinicService.zapiszPacjenta(pacjentDto);
            dataReader.printLine("Pacjent dodany  do bazy");

        } catch (Exception e) {
            throw new SqlConnectionException(e.getMessage());
        }

    }

    private void zapiszPacjentaZWizytami() {
        try {


            ZapiszPacjentaZWizytami zapiszPacjentaZWizytami = dataReader.zapiszPacjentaZWizytama();
            clinicService.zapiszPacjentaZWizytama(zapiszPacjentaZWizytami);
            dataReader.printLine("Wizyta została zapisana");
        } catch (Exception e) {
            throw new SqlConnectionException(e.getMessage());
        }

    }
    private void wyszukajWizytyNaPodstawiePeselu(){
        String pesel = dataReader.podajPesel();
        PacjentZWizytamiDto pacjentZWizytamiDto = clinicService.pacjentZWizytami(pesel);
        dataReader.printLine(pacjentZWizytamiDto);
    }

    private void sumaKosztowWizyt() {
        ZakresDaty zakresDaty = dataReader.kwotyWZakresieDaty();
        BigDecimal kwota = clinicService.kwota(zakresDaty.getDataPoczatkowa(), zakresDaty.getDataKoncowa());
        if (kwota == null) {
            dataReader.printLine(BigDecimal.ZERO);

        } else {
            dataReader.printLine(kwota);
        }
    }

    private void printOptions() {
        dataReader.printLine("Wybierz opcję: ");
        for (Option option : Option.values()) {
            dataReader.printLine(option);
        }
    }

    private void exit() {

        dataReader.close();
        dataReader.printLine("Koniec programu, papa!");
    }
}
