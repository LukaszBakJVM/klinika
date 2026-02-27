package org.example.service;

import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.model.Wizyta;

public interface ClinicService {
    public void zapiszPacjentaZWizytama(PacjentDto pacjentDto, Wizyta wizyta);

    void zapiszPacjenta(PacjentDto pacjentDto);

    PacjentZWizytamiDto pacjentZWizytami(String pesel);
}
