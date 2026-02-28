package org.example.dao;

import org.example.model.Pacjent;

import java.sql.Connection;

public interface PacjentDAO {
    Long zapiszPacjentaIWIzyte(Connection conn, Pacjent pacjent);

    Pacjent znajdzPoPeselu(Connection conn, String pesel);

    void zapiszPacjenta(Connection conn, Pacjent pacjent);
}
