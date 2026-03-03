package org.example.ui;

import org.example.dto.PacjentZWizytamiDto;
import org.example.dto.WizytaDto;
import org.example.service.ClinicService;
import org.example.service.ClinicServiceImpl;

import javax.swing.*;
import java.awt.*;

public class SearchPatientPanel extends JPanel {
    private final JTextField peselField = new JTextField(15);

    private final JLabel imieLabel = new JLabel();
    private final JLabel nazwiskoLabel = new JLabel();

    private final JTextArea wizytyArea = new JTextArea(10, 40);

    private final ClinicService clinicService;

    public SearchPatientPanel() {
        this.clinicService = new ClinicServiceImpl();

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("PESEL:"));
        topPanel.add(peselField);
        JButton searchButton = new JButton("Szukaj");
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(imieLabel);
        centerPanel.add(nazwiskoLabel);

        add(centerPanel, BorderLayout.CENTER);

        wizytyArea.setEditable(false);
        add(new JScrollPane(wizytyArea), BorderLayout.SOUTH);

        searchButton.addActionListener(e -> wyszukajPacjenta());
    }
    private void wyszukajPacjenta() {
        try {
            String pesel = peselField.getText();

            PacjentZWizytamiDto dto = clinicService.pacjentZWizytami(pesel);

            imieLabel.setText("Imię: " + dto.getImie());
            nazwiskoLabel.setText("Nazwisko: " + dto.getNazwisko());

            wizytyArea.setText("");

            for (WizytaDto wizyta : dto.getWizyty()) {
                wizytyArea.append(
                        "Data: " + wizyta.getDataWizyty() +
                                ", Kwota: " + wizyta.getKwota() +
                                ", Rozpoznanie: " + wizyta.getRozpoznanie() +
                                "\n"
                );
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}

