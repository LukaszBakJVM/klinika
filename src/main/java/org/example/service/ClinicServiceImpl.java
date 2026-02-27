package org.example.service;

import org.example.config.DbUtils;
import org.example.dao.PacjentDAO;
import org.example.dao.WizytaDAO;
import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.exception.DuplicatePesel;
import org.example.exception.SqlConnectionException;
import org.example.mapper.Mapper;
import org.example.model.Pacjent;
import org.example.model.Wizyta;
import org.example.utils.validation.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClinicServiceImpl implements ClinicService {
    private final PacjentDAO pacjentDAO = new PacjentDAO();
    private final WizytaDAO wizytaDAO = new WizytaDAO();
    private final ValidatorService validatorService = new ValidatorService();
    private final Mapper mapper = new Mapper();
    Logger logger = LoggerFactory.getLogger(ClinicServiceImpl.class);


    public ClinicServiceImpl() {
    }

    @Override
    public void zapiszPacjentaZWizytama(PacjentDto pacjentDto, Wizyta wizyta) {

        validatorService.validation(pacjentDto);


        try (Connection conn = DbUtils.getConnection()) {

            try {
                conn.setAutoCommit(false);


                Pacjent pesel = pacjentDAO.znajdzPoPeselu(conn, pacjentDto.getPesel());

                Long pacjentId;

                if (pesel != null) {

                    pacjentId = pesel.getId();
                } else {
                    Pacjent save = mapper.save(pacjentDto);
                    pacjentId = pacjentDAO.zapiszPacjentaIWIzyte(conn, save);
                }


                Wizyta toSave = Wizyta.builder().pacjentId(pacjentId).dataWizyty(wizyta.getDataWizyty()).rozpoznanie(wizyta.getRozpoznanie())
                        .kwota(wizyta.getKwota()).build();
                wizytaDAO.save(conn, toSave);

                conn.commit();

            } catch (Exception e) {

                try {
                    conn.rollback();
                } catch (SQLException ex) {
                   logger.error(ex.getMessage());
                }

                throw new SqlConnectionException(String.format("Błąd transakcji %s ", e));
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(String.format("Błąd transakcji %s ", e));
        }
    }

    public void zapiszPacjenta(PacjentDto pacjentDto) {
          validatorService.validation(pacjentDto);

        try (Connection conn = DbUtils.getConnection()) {

            try {
                conn.setAutoCommit(false);


                Pacjent pesel = pacjentDAO.znajdzPoPeselu(conn, pacjentDto.getPesel());

                if (pesel != null) {

                    throw new DuplicatePesel(String.format("Pacjent o podanym peselu %s posiada juz konto", pesel.getPesel()));
                } else {
                    Pacjent save = mapper.save(pacjentDto);
                    pacjentDAO.zapiszPacjenta(conn, save);
                }


                conn.commit();

            } catch (Exception e) {

                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                }

                throw new SqlConnectionException(String.format("Błąd transakcji %s ", e));
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(String.format("Błąd transakcji %s ", e));
        }
    }
  @Override
  public   PacjentZWizytamiDto pacjentZWizytami(String pesel) {
        try (Connection conn = DbUtils.getConnection()) {
            Pacjent pacjent = pacjentDAO.znajdzPoPeselu(conn, pesel);
            List<Wizyta> byPacjent = wizytaDAO.findByPacjentId(conn, pacjent.getId());
            return mapper.toDto(pacjent,byPacjent);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
  }
}


