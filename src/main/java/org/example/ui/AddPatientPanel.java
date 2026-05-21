package org.example.ui;

import org.example.dto.PacjentDto;
import org.example.service.ClinicService;
import org.example.service.ClinicServiceImpl;

import javax.swing.*;
import java.awt.*;

public class AddPatientPanel extends JPanel {
    private final ClinicService service;

    private final JTextField imie = new JTextField(15);
    private final JTextField nazwisko = new JTextField(15);
    private final JTextField pesel = new JTextField(15);

    private final JButton saveButton = new JButton("Zapisz");

    public AddPatientPanel() {
        this.service = new ClinicServiceImpl();
        initUI();
        initListeners();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createRow("Imię:", imie));
        add(createRow("Nazwisko:", nazwisko));
        add(createRow("PESEL:", pesel));

        add(Box.createVerticalStrut(15));
        add(saveButton);
    }

    private JPanel createRow(String labelText, JComponent component) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(labelText));
        row.add(component);
        return row;
    }

    private void initListeners() {
        saveButton.addActionListener(e -> savePatient());


    }

    private void savePatient() {
        try {
            PacjentDto dto = PacjentDto.builder().imie(imie.getText().trim()).nazwisko(nazwisko.getText().trim()).pesel(pesel.getText().trim()).build();

            service.zapiszPacjenta(dto);

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
    }
}
