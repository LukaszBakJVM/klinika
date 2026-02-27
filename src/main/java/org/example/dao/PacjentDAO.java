package org.example.dao;

import org.example.exception.SqlConnectionException;
import org.example.model.Pacjent;

import java.sql.*;

public class PacjentDAO {


    public Long zapiszPacjentaIWIzyte(Connection conn, Pacjent pacjent) throws SQLException {

        String zapiszWrazZwizyta = "INSERT INTO PACJENT(IMIE, NAZWISKO, PESEL) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(zapiszWrazZwizyta, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pacjent.getImie());
            ps.setString(2, pacjent.getNazwisko());
            ps.setString(3, pacjent.getPesel());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    pacjent.setId(id);
                    return id;
                }
            }
        }

        throw new SqlConnectionException("Nie udało się zapisać pacjenta");
    }

    @lombok.SneakyThrows
    public Pacjent znajdzPoPeselu(Connection conn, String pesel) {


        String zmajdzNaPodstawiePesel = "SELECT ID, IMIE, NAZWISKO, PESEL FROM PACJENT WHERE PESEL = ?";

        try (PreparedStatement ps = conn.prepareStatement(zmajdzNaPodstawiePesel)) {

            ps.setString(1, pesel);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Pacjent.Builder().id(rs.getLong("ID")).imie(rs.getString("IMIE")).nazwisko(rs.getString("NAZWISKO")).pesel(rs.getString("PESEL")).build();
                }
            }
        }

        throw new SqlConnectionException("Nie udało się zapisać pacjenta");
    }

    public void zapiszPacjenta(Connection conn, Pacjent pacjent) throws SQLException {

        String sql = "INSERT INTO PACJENT(IMIE, NAZWISKO, PESEL) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pacjent.getImie());
            ps.setString(2, pacjent.getNazwisko());
            ps.setString(3, pacjent.getPesel());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {

                    rs.getLong(1);


                }
            }
        }
    }
}
