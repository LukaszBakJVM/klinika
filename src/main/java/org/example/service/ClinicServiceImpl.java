package org.example.service;

import org.example.config.DbUtils;
import org.example.dao.PacjentDAO;
import org.example.dao.PacjentDAOImpl;
import org.example.dao.WizytaDAO;
import org.example.dao.WizytaDAOImpl;
import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.dto.ZapiszPacjentaZWizytami;
import org.example.exception.DuplicatePesel;
import org.example.exception.PacjentNotFoundException;
import org.example.exception.SqlConnectionException;
import org.example.mapper.Mapper;
import org.example.model.Pacjent;
import org.example.model.Wizyta;
import org.example.utils.validation.ValidatorService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ClinicServiceImpl implements ClinicService {
    private final PacjentDAO pacjentDAO;
    private final WizytaDAO wizytaDAO;
    private final ValidatorService validatorService;
    private final Mapper mapper;
    private final DbUtils dbUtils;

    public ClinicServiceImpl(
            PacjentDAO pacjentDAO,

            WizytaDAO wizytaDAO,
            ValidatorService validatorService,
            Mapper mapper,
            DbUtils dbUtils) {

        this.pacjentDAO = pacjentDAO;
        this.wizytaDAO = wizytaDAO;
        this.validatorService = validatorService;
        this.mapper = mapper;
        this.dbUtils = dbUtils;
    }


    public ClinicServiceImpl() {
        this.dbUtils = new DbUtils();
        this.pacjentDAO = new PacjentDAOImpl();
        this.wizytaDAO = new WizytaDAOImpl();
        this.validatorService = new ValidatorService();
        this.mapper = new Mapper();
    }


    @Override
    public void zapiszPacjentaZWizytama(ZapiszPacjentaZWizytami zapisz) {

        validatorService.validation(zapisz.getPacjentDto());
        validatorService.validation(zapisz.getWizytaDto());


        try (Connection conn = dbUtils.getConnection()) {

            try {
                conn.setAutoCommit(false);


                Pacjent pesel = pacjentDAO.znajdzPoPeselu(conn, zapisz.getPacjentDto().getPesel());

                Long pacjentId;

                if (pesel != null) {
                    pacjentId = pesel.getId();
                    Wizyta entity = mapper.toEntity(zapisz.getWizytaDto(), pacjentId);
                    wizytaDAO.save(conn, entity);

                } else {
                    Pacjent save = mapper.save(zapisz.getPacjentDto());
                    pacjentId = pacjentDAO.zapiszPacjentaIWIzyte(conn, save);


                    Wizyta entity = mapper.toEntity(zapisz.getWizytaDto(), pacjentId);
                    wizytaDAO.save(conn, entity);

                }
                conn.commit();

            } catch (Exception e) {

                try {
                    conn.rollback();
                } catch (SQLException ex) {


                    throw new SqlConnectionException(String.format("Błąd transakcji %s ", e));
                }
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(String.format("Błąd transakcji %s ", e));
        }
    }

    @Override
    public void zapiszPacjenta(PacjentDto pacjentDto) {
        validatorService.validation(pacjentDto);

        try (Connection conn = dbUtils.getConnection()) {

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

            } catch (DuplicatePesel e) {
                conn.rollback();
                throw e;
            } catch (Exception e) {
                conn.rollback();
                throw new SqlConnectionException("Błąd transakcji");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PacjentZWizytamiDto pacjentZWizytami(String pesel) {
        try (Connection conn = dbUtils.getConnection()) {
            Pacjent pacjent = pacjentDAO.znajdzPoPeselu(conn, pesel);
            if (pacjent == null) {
                throw new PacjentNotFoundException(String.format("Pacjenta o peselu %s nie znaleziono u nas w bazie  wybierz opcje 1 lub 2 z menu", pesel));
            }
            List<Wizyta> byPacjent = wizytaDAO.findByPacjentId(conn, pacjent.getId());
            return mapper.toDto(pacjent, byPacjent);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal kwota(LocalDate poczatek, LocalDate koniec) {

        try (Connection conn = dbUtils.getConnection()) {
            return wizytaDAO.sumaNaPodstawieDaty(conn, poczatek, koniec);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


