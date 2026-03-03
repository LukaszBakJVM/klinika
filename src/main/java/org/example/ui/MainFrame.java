package org.example.ui;

import javax.swing.*;



    public class MainFrame extends JFrame {

        public MainFrame() {
            setTitle("Klinika");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTabbedPane tabs = new JTabbedPane();

            tabs.add("Dodaj pacjenta", new AddPatientPanel());
            tabs.add("Dodaj wizytę", new AddPatientWithVisitPanel());
            tabs.add("Wyszukaj", new SearchPatientPanel());
            tabs.add("Suma wizyt", new SumPanel());

            add(tabs);

            setLocationRelativeTo(null);
        }
    }

