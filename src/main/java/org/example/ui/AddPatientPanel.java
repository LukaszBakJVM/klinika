package org.example.ui;

import org.example.dto.PacjentDto;
import org.example.service.ClinicService;
import org.example.service.ClinicServiceImpl;

import javax.swing.*;
import java.awt.*;

public class AddPatientPanel extends JPanel{
    private final ClinicService service = new ClinicServiceImpl();

    private final JTextField imie = new JTextField(10);
    private final JTextField nazwisko = new JTextField(6);
    private final JTextField pesel = new JTextField(3);

    public AddPatientPanel() {

        setLayout(new GridLayout(0, 1, 10, 10));

        add(new JLabel("Imię:"));
        add(imie);

        add(new JLabel("Nazwisko:"));
        add(nazwisko);

        add(new JLabel("Pesel:"));
        add(pesel);

        JButton save = new JButton("Zapisz");
        add(save);

        save.addActionListener(e -> savePatient());
    }


    private void savePatient() {
        try {
            PacjentDto dto = PacjentDto.builder()
                    .imie(imie.getText())
                    .nazwisko(nazwisko.getText())
                    .pesel(pesel.getText())
                    .build();

            service.zapiszPacjenta(dto);

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
    }
}

