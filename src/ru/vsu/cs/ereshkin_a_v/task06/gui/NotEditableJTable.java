package ru.vsu.cs.ereshkin_a_v.task06.gui;

import javax.swing.*;
import java.io.Serial;

public class NotEditableJTable extends JTable {
    @Serial
    private static final long serialVersionUID = 1L;

    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
