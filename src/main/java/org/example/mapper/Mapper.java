package org.example.mapper;

import org.example.dto.PacjentDto;
import org.example.dto.PacjentZWizytamiDto;
import org.example.dto.WizytaDto;
import org.example.model.Pacjent;
import org.example.model.Wizyta;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public Mapper() {
    }

    public Pacjent save(PacjentDto dto) {
        return new Pacjent.Builder().imie(dto.getImie()).nazwisko(dto.getNazwisko()).pesel(dto.getPesel()).build();
    }


    public PacjentZWizytamiDto toDto(Pacjent pacjent, List<Wizyta> wizyty) {

        List<WizytaDto> wizytyDto = wizyty.stream()
                .map(w -> WizytaDto.builder()
                        .dataWizyty(w.getDataWizyty())
                        .rozpoznanie(w.getRozpoznanie())
                        .kwota(w.getKwota())
                        .build()).collect(Collectors.toList());


        return PacjentZWizytamiDto.builder()
                .imie(pacjent.getImie())
                .nazwisko(pacjent.getNazwisko())
                .pesel(pacjent.getPesel())
                .wizyty(wizytyDto)
                .build();
    }
  public   Wizyta toEntity(WizytaDto wizytaDto,Long pacjentId){
        return
               Wizyta.builder().pacjentId(pacjentId).dataWizyty(wizytaDto.getDataWizyty()).rozpoznanie(wizytaDto.getRozpoznanie())
                .kwota(wizytaDto.getKwota()).build();
    }

}