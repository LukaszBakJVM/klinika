package org.example.service;

import org.example.config.DbUtils;
import org.example.dao.PacjentDAO;
import org.example.dao.WizytaDAO;
import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.dto.WizytaDto;
import org.example.dto.ZapiszPacjentaZWizytami;
import org.example.exception.DuplicatePesel;
import org.example.mapper.Mapper;
import org.example.model.Pacjent;
import org.example.model.Wizyta;
import org.example.utils.validation.ValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    private PacjentDAO pacjentDAO;

    @Mock
    private WizytaDAO wizytaDAO;

    @Mock
    private DbUtils dbUtils;
    @Mock
    private Mapper mapper;
    @Mock
    private ValidatorService validatorService;
    @Mock
    private Connection connection;

    @InjectMocks
    private ClinicServiceImpl service;


    @Test
    void powinienZapisacNowegoPacjentaIZwizyte() throws SQLException {

        //given
        when(dbUtils.getConnection()).thenReturn(connection);

        PacjentDto pacjentDto = PacjentDto.builder().imie("imie").nazwisko("nazwisko").pesel("820811134").build();
        WizytaDto wizytaDto = WizytaDto.builder().dataWizyty(LocalDate.now()).rozpoznanie("wywiad").kwota(new BigDecimal("250")).build();

        doNothing().when(validatorService).validation(any());


        ZapiszPacjentaZWizytami dto = ZapiszPacjentaZWizytami.builder().pacjentDto(pacjentDto).wizytaDto(wizytaDto).build();


        when(pacjentDAO.znajdzPoPeselu(any(), anyString())).thenReturn(null);

        // when
        service.zapiszPacjentaZWizytama(dto);

        // then
        verify(pacjentDAO).zapiszPacjentaIWIzyte(any(), any());

    }

    @Test
    void gdyPacjentIstniejePowinnaZapisacTylkoWizyte() throws SQLException {

        //given

        Pacjent pacjent = Pacjent.builder().id(1L).imie("Jan").nazwisko("Nowak").pesel("123").build();

        PacjentDto pacjentDto = PacjentDto.builder().pesel("123").build();

        WizytaDto wizytaDto = WizytaDto.builder().build();
        doNothing().when(validatorService).validation(any(PacjentDto.class));
        doNothing().when(validatorService).validation(any(WizytaDto.class));

        ZapiszPacjentaZWizytami dto = new ZapiszPacjentaZWizytami(pacjentDto, wizytaDto);

        when(dbUtils.getConnection()).thenReturn(connection);


        when(pacjentDAO.znajdzPoPeselu(any(), eq("123"))).thenReturn(pacjent);

        when(mapper.toEntity(eq(wizytaDto), eq(1L))).thenReturn(Wizyta.builder().build());

        // when
        assertDoesNotThrow(() -> service.zapiszPacjentaZWizytama(dto));

        // then
        verify(wizytaDAO).save(any(), any());
        verify(pacjentDAO, never()).zapiszPacjentaIWIzyte(any(), any());
        verify(connection).commit();
    }

    @Test
    void niePowinienZapisacGdyPeselIstnieje() throws SQLException {
          //given

        when(pacjentDAO.znajdzPoPeselu(any(), eq("00311191370"))).thenReturn(mock(Pacjent.class));
        when(dbUtils.getConnection()).thenReturn(connection);


        PacjentDto dto = PacjentDto.builder().imie("imie").nazwisko("nazwisko").pesel("00311191370").build();

               //when
        assertThrows(DuplicatePesel.class, () -> service.zapiszPacjenta(dto));

        //then
        verify(pacjentDAO, never()).zapiszPacjenta(any(), any());

    }

    @Test
    void powinienZwrocicPacjentaZWizytami() throws SQLException {
        //given

        Pacjent pacjent = Pacjent.builder().id(1L).imie("imie").nazwisko("nazwisko").pesel("00311191370").build();
        when(dbUtils.getConnection()).thenReturn(connection);


        when(pacjentDAO.znajdzPoPeselu(any(), eq("00311191370"))).thenReturn(pacjent);

        when(wizytaDAO.findByPacjentId(any(), eq(1L))).thenReturn(Collections.emptyList());
        when(mapper.toDto(any(), any())).thenReturn(mock(PacjentZWizytamiDto.class));

       //when
        service.pacjentZWizytami("00311191370");
        //then
        verify(pacjentDAO).znajdzPoPeselu(any(),any());







    }

    @Test
    void powinienZwracacSumeKwoty() throws SQLException {
        when(dbUtils.getConnection()).thenReturn(connection);

        when(wizytaDAO.sumByDateRange(any(), any(), any())).thenReturn(BigDecimal.TEN);

        BigDecimal result = service.kwota(LocalDate.now(), LocalDate.now());

        assertEquals(BigDecimal.TEN, result);
    }

}

