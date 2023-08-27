package common.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.LayoutManager;

public class PanelManager {

    private static JFrame targetUI = null;
    private static PanelManager manager = null;

    private PanelManager(JFrame module) {
        targetUI = module;
    }

    public static PanelManager getInstance(JFrame targetUI) {
        if(manager == null) {
            manager = new PanelManager(targetUI);
        }
        return manager;
    }

    public Panel getGridPanel(int row, int column) {
       Panel panel = new Panel();
       panel.setLayout(new GridLayout(row, column));
       return panel;
    }

    public JPanel getPanel(int width, int height, boolean debug) {
        JPanel panel = new JPanel();
        if(debug) {
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        panel.setSize(width, height);
        return panel;
    }

    public JPanel getInputPanel(int width, int height, boolean debug) {
        JPanel panel = getPanel(width, height, debug);
        panel.setAlignmentY(10.0f);
        JTextField tf = new JTextField();
        tf.setColumns(12);
        JButton jbtn = new JButton("확인");
        jbtn.setSize(0, 25);
        panel.add(tf);
        panel.add(jbtn);
        return panel;
    }
}
