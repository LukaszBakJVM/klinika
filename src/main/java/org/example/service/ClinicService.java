package org.example.service;

import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.dto.ZapiszPacjentaZWizytami;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ClinicService {
    void zapiszPacjentaZWizytama(ZapiszPacjentaZWizytami zapisz);

    void zapiszPacjenta(PacjentDto pacjentDto);

    PacjentZWizytamiDto pacjentZWizytami(String pesel);

    BigDecimal kwota(LocalDate poczatek, LocalDate koniec);
}
