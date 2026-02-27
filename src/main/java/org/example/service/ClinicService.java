package org.example.service;

import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.dto.WizytaDto;

public interface ClinicService {
    void zapiszPacjentaZWizytama(PacjentDto pacjentDto, WizytaDto wizytaDto);

    void zapiszPacjenta(PacjentDto pacjentDto);

    PacjentZWizytamiDto pacjentZWizytami(String pesel);
}
