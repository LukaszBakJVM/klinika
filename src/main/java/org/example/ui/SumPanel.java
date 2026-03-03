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

    private final JLabel wynikLabel = new JLabel("Suma: 0.00 zł");
    private final JButton liczButton = new JButton("Oblicz");

    private final ClinicService clinicService;

    public SumPanel() {
        this.clinicService = new ClinicServiceImpl();

        initUI();
        initListeners();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createRow("Data od:", poczatek));
        add(Box.createVerticalStrut(10));
        add(createRow("Data do:", koniec));
        add(Box.createVerticalStrut(15));
        add(liczButton);
        add(Box.createVerticalStrut(15));
        add(wynikLabel);

        wynikLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        liczButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private JPanel createRow(String labelText, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        panel.add(component);
        return panel;
    }

    private void initListeners() {
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
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}


