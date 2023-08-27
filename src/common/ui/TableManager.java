package common.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableManager {

    private static JFrame targetUI = null;
    private static TableManager manager = null;

    private TableManager(JFrame module) {
        targetUI = module;
    }

    public static TableManager getInstance(JFrame module) {
        if(manager == null) {
            manager = new TableManager(module);
        }
        return manager;
    }

    private TableModel getTableModel(String[] headers) {
        TableModel tableModel = new DefaultTableModel(headers,0);
        return tableModel;
    }

    public Object[] getTableFrame(String[] headers, boolean debug) {
        JTable table = new JTable(getTableModel(headers));
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrolledTable = new JScrollPane(table);
        scrolledTable.setSize(targetUI.getSize().width, 500);

        scrolledTable.setBackground(Color.WHITE);
        return new Object[]{scrolledTable, table};
    }

}
