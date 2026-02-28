package org.example;

import org.example.AppControl.KlinikaControl;

public class Main {
    public static void main(String[] args) {

        KlinikaControl klinikaControl = new KlinikaControl();
        klinikaControl.controlLoop();
    }
}

