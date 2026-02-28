package org.example.dao;

import lombok.SneakyThrows;
import org.example.model.Wizyta;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WizytaDAOImpl implements WizytaDAO {
    @SneakyThrows
    @Override
    public void save(Connection conn, Wizyta wizyta) {

        String sql = "INSERT INTO WIZYTA(PACJENT_ID, DATA_WIZYTY, ROZPOZNANIE, KWOTA) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, wizyta.getPacjentId());
            ps.setDate(2, Date.valueOf(wizyta.getDataWizyty()));
            ps.setString(3, wizyta.getRozpoznanie());
            ps.setBigDecimal(4, wizyta.getKwota());

            ps.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public List<Wizyta> findByPacjentId(Connection conn, long pacjentId)  {

        List<Wizyta> result = new ArrayList<>();


      //  String sql = "SELECT * FROM WIZYTA w INNER JOIN PACJENT p ON w.PACJENT_ID = p.ID WHERE p.ID = ?";
        String sql= "SELECT * FROM WIZYTA  WHERE PACJENT_ID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, pacjentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Wizyta w = Wizyta.builder().id(rs.getLong("ID")).dataWizyty(rs.getDate("DATA_WIZYTY").toLocalDate()).rozpoznanie(rs.getString("ROZPOZNANIE")).kwota(rs.getBigDecimal("KWOTA")).pacjentId(rs.getLong("PACJENT_ID")).build();


                result.add(w);
            }
        }

        return result;
    }


    @SneakyThrows
    @Override
    public BigDecimal sumByDateRange(Connection conn, LocalDate from, LocalDate to)  {

        String sql = "SELECT SUM(KWOTA) FROM WIZYTA WHERE  DATA_WIZYTY BETWEEN ? AND ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setDate(1, java.sql.Date.valueOf(from));
            ps.setDate(2, java.sql.Date.valueOf(to));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }

        return BigDecimal.ZERO;
    }
}

