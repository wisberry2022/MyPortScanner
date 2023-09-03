package ui;

import common.ui.LayoutManager;
import common.ui.PanelManager;
import common.ui.TableManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainUI extends JFrame {

    private LayoutManager lm = LayoutManager.getInstance(this);
    private PanelManager pm = PanelManager.getInstance(this);
    private TableManager tm = TableManager.getInstance(this);

    private JScrollPane scrollTable = null;
    private JTable table = null;

    private static final int SCREEN_WIDTH = 300;
    private static final int SCREEN_HEIGHT = 600;
    private static int[] LOCATION_ARR = {0,0};
    private int axisY = 0;
    private int axisX = 0;

    public void setDisplay(boolean setCenter) {
        setTitle("MyPortScanner");
        setSize(300,600);
        if(setCenter) {
            setLocationRelativeTo(null);
        }

        setLayout(null);

        add(getTitle("MyScanner", false));
        add(getInputBox(false));
        setDisplayTable(new String[]{"port", "protocol", "state", "service"}, true);
//        addData();
        add(scrollTable);
        setResizable(false);
        setVisible(true);
    }

    private JPanel getTitle(String title, boolean debug) {
        axisY = 30;
        JPanel titlePanel = pm.getPanel(SCREEN_WIDTH, axisY, debug);
        titlePanel.setLocation(0, LOCATION_ARR[1]);
        JLabel label = new JLabel(title);
        titlePanel.add(label);
        setNextAxisY(axisY);
        return titlePanel;
    }

    private JPanel getInputBox(boolean debug) {
        axisY = 40;
        JPanel inputPanel = pm.getInputPanel(SCREEN_WIDTH, axisY, debug);
        inputPanel.setLocation(0, LOCATION_ARR[1]);
        setNextAxisY(axisY);
        return inputPanel;
    }

    private void setDisplayTable(String[] headers, boolean debug) {
        Object[] result = tm.getTableFrame(headers, debug);
        scrollTable = (JScrollPane)result[0];
        table = (JTable)result[1];
        scrollTable.setLocation(0, LOCATION_ARR[1]);
        setNextAxisY(axisY);
    }

    private void addData() {
//        데이터 추가는 아래와 같이
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        String[] record = new String[4];
//        record[0] = "80";
//        record[1] = "tcp";
//        record[2] = "ok";
//        record[3] = "intelliJ";
//        model.addRow(record);
    }

    private void setNextAxisY(int axisY) {
        LOCATION_ARR[1] += axisY;
    }

    private void setNextAxisX(int axisX) {
        LOCATION_ARR[0] += axisX;
    }

    public static void main(String[] args) {
        MainUI main = new MainUI();
        main.setDisplay(true);
    }

}
