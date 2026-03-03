package org.example.ui;

import com.github.lgooddatepicker.components.DatePicker;
import org.example.dto.PacjentDto;
import org.example.dto.WizytaDto;
import org.example.dto.ZapiszPacjentaZWizytami;
import org.example.service.ClinicService;
import org.example.service.ClinicServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;


public class AddPatientWithVisitPanel extends JPanel {
    private final ClinicService service = new ClinicServiceImpl();

    private final JTextField imie = new JTextField(10);
    private final JTextField nazwisko = new JTextField(6);
    private final JTextField pesel = new JTextField(3);

    private final JTextField rozpoznanie = new JTextField(3);
    private final JTextField cena = new JTextField(3);
    private final DatePicker datePicker = new DatePicker();


    public AddPatientWithVisitPanel() {


        setLayout(new GridLayout(0, 1, 10, 10));

        add(new JLabel("Imię:"));
        add(imie);

        add(new JLabel("Nazwisko:"));
        add(nazwisko);

        add(new JLabel("Pesel:"));
        add(pesel);


        add(new JLabel("data:"));
        add(datePicker);

        add(new JLabel("rozpoznanie:"));
        add(rozpoznanie);

        add(new JLabel("kwota:"));
        add(cena);

        JButton save = new JButton("Zapisz");
        add(save);

        save.addActionListener(e -> zapiszZWizytami());
    }


    private void zapiszZWizytami() {
        try {
            PacjentDto dto = PacjentDto.builder().imie(imie.getText()).nazwisko(nazwisko.getText()).pesel(pesel.getText()).build();
            WizytaDto wizytaDto = WizytaDto.builder().kwota(new BigDecimal(cena.getText())).dataWizyty(datePicker.getDate()).rozpoznanie(rozpoznanie.getText()).build();

            service.zapiszPacjentaZWizytama(ZapiszPacjentaZWizytami.builder().pacjentDto(dto).wizytaDto(wizytaDto).build());

            JOptionPane.showMessageDialog(this, "Zapisano!");
            wyczyscPola();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void wyczyscPola() {
        imie.setText("");
        nazwisko.setText("");
        pesel.setText("");
        cena.setText("");
        rozpoznanie.setText("");

        datePicker.setDate(null);
    }
}

