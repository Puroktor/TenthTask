package ru.vsu.cs.skofenko.jTableVersion;

import java.util.Locale;

public class JTableMain {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }
}