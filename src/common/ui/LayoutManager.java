package common.ui;

import javax.swing.*;
import java.awt.*;

public class LayoutManager {

    private static JFrame targetUI = null;

    private static LayoutManager manager = null;

    private LayoutManager(JFrame module) {
        targetUI = module;
    }

    public static LayoutManager getInstance(JFrame module) {
        if(manager == null) {
            manager = new LayoutManager(module);
        }
        return manager;
    }

    public void setGridLayout() {
        targetUI.setLayout(new GridLayout(2,0));
    }


}
