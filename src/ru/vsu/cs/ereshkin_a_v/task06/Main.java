package ru.vsu.cs.ereshkin_a_v.task06;

import ru.vsu.cs.ereshkin_a_v.task06.gui.FrameMain;
import ru.vsu.cs.util.SwingUtils;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        //SwingUtils.setLookAndFeelByName("GTK+");
        //SwingUtils.setDefaultFont("Ubuntu", 18);
        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));

    }
}