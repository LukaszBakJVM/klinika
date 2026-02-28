package org.example.AppControl;

import org.example.exception.NoSuchOptionException;

public enum Option {
    EXIT(0, "Wyjście z programu"), ADD_PATIENT(1, "Dodanie Pacjenta"), ADD_PATIENT_AND_VISIT(2, "Dodanie pacjenta z wizytą"), PRINT_PATIENT_VISIT(3, "Wyświetlenie wizyt pacjenta"),
    PRINT_COST_VISIT_IN_DATE_RANGE(4, "Wyświetlenie sumy kwot wizyt w podanym zakresie dat");
    private final int value;
    private final String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    static Option createFromInt(int option) {
        try {
            return Option.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id " + option);
        }
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }
}

