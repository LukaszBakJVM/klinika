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
    private final ClinicService service;


    private final JTextField imie = new JTextField(15);
    private final JTextField nazwisko = new JTextField(15);
    private final JTextField pesel = new JTextField(15);
    private final JTextField rozpoznanie = new JTextField(15);
    private final JTextField cena = new JTextField(15);
    private final DatePicker datePicker = new DatePicker();

    private final JButton saveButton = new JButton("Zapisz");

    public AddPatientWithVisitPanel() {
        this.service = new ClinicServiceImpl();
        initUI();
        initListeners();
    }

    private void initUI() {
        setLayout();
        addFields();
    }

    private void setLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addFields() {
        add(createRow("Imię:", imie));
        add(createRow("Nazwisko:", nazwisko));
        add(createRow("PESEL:", pesel));
        add(createRow("Data wizyty:", datePicker));
        add(createRow("Rozpoznanie:", rozpoznanie));
        add(createRow("Kwota:", cena));

        add(Box.createVerticalStrut(15));
        add(saveButton);
    }

    private JPanel createRow(String labelText, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        panel.add(component);
        return panel;
    }

    private void initListeners() {
        saveButton.addActionListener(e -> zapiszZWizytami());
    }

    private void zapiszZWizytami() {
        try {
            PacjentDto dto = PacjentDto.builder()
                    .imie(imie.getText().trim())
                    .nazwisko(nazwisko.getText().trim())
                    .pesel(pesel.getText().trim())
                    .build();

            WizytaDto wizytaDto = WizytaDto.builder()
                    .kwota(new BigDecimal(cena.getText().trim()))
                    .dataWizyty(datePicker.getDate())
                    .rozpoznanie(rozpoznanie.getText().trim())
                    .build();

            service.zapiszPacjentaZWizytama(
                    ZapiszPacjentaZWizytami.builder()
                            .pacjentDto(dto)
                            .wizytaDto(wizytaDto)
                            .build()
            );

            JOptionPane.showMessageDialog(this, "Zapisano!");
            wyczyscPola();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
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

