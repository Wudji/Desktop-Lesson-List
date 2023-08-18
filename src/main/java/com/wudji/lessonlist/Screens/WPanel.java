package com.wudji.lessonlist.Screens;

import javax.swing.*;
import java.awt.*;

public class WPanel extends JPanel {
    public WPanel(int lines,int fontSize){
        this.setFont(new Font(null, Font.BOLD,fontSize));
        this.setLayout(new GridLayout(lines,1));
        for(int i = 0;i<lines;i++){
            this.add(new JLabel("Initializing......"));
        }
    }

}
