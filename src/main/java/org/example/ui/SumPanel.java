package org.example.ui;

import com.github.lgooddatepicker.components.DatePicker;
import org.example.service.ClinicService;
import org.example.service.ClinicServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SumPanel extends JPanel {
    private final DatePicker poczatek = new DatePicker();
    private final DatePicker koniec = new DatePicker();

    private final JLabel wynikLabel = new JLabel("Suma: ");

    private final ClinicService clinicService;

    public SumPanel() {
        this.clinicService = new ClinicServiceImpl();
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Data od:"));
        add(poczatek);

        add(new JLabel("Data do:"));
        add(koniec);

        JButton liczButton = new JButton("Oblicz");
        add(liczButton);
        add(new JLabel());

        add(wynikLabel);

        liczButton.addActionListener(e -> policzSume());
    }
    private void policzSume() {
        try {
            LocalDate dataOd = poczatek.getDate();
            LocalDate dataDo = koniec.getDate();

            if (dataOd == null || dataDo == null) {
                throw new IllegalArgumentException("Wybierz obie daty");
            }

            if (dataOd.isAfter(dataDo)) {
                throw new IllegalArgumentException("Data początkowa nie może być po końcowej");
            }

            BigDecimal suma = clinicService.kwota(dataOd, dataDo);

            wynikLabel.setText("Suma: " + suma + " zł");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}


