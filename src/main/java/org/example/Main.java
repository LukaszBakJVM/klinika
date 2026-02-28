package org.example;

import org.example.AppControl.KlinikaControl;

import java.util.logging.LogManager;

public class Main {
    private static final String APP_NAME = "Klinika v2.4";

    public static void main(String[] args)  {


        LogManager.getLogManager().reset();
        System.out.println(APP_NAME);
        KlinikaControl klinikaControl = new KlinikaControl();
        klinikaControl.controlLoop();
    }
}

