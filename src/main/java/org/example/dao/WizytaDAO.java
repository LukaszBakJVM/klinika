package org.example.dao;

import org.example.model.Wizyta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface WizytaDAO {
    void save(Connection conn, Wizyta wizyta) throws SQLException;
    List<Wizyta> findByPacjentId(Connection conn, long pacjentId) ;
    BigDecimal sumByDateRange(Connection conn, LocalDate from, LocalDate to) ;
}
