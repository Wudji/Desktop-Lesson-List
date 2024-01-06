package com.wudji.lessonlist.screens;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import java.awt.*;

public class WelcomeScreen extends JDialog {
    public WelcomeScreen() {
        super((Frame) null, "欢迎使用", true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ImageIcon icon = new ImageIcon("resources/welcome.png");
        JLabel label = new JLabel(icon);

        this.setSize(icon.getIconWidth(), icon.getIconHeight());

        this.getContentPane().add(label, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

    }
}
