package org.example.dao;

import org.example.model.Pacjent;

import java.sql.Connection;
import java.util.Optional;

public interface PacjentDAO {
    Long zapiszPacjentaIWIzyte(Connection conn, Pacjent pacjent);

   Optional<Pacjent> znajdzPoPeselu(Connection conn, String pesel);

    void zapiszPacjenta(Connection conn, Pacjent pacjent);
}
