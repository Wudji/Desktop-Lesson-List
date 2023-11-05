package com.wudji.lessonlist.screens;

import com.wudji.lessonlist.utils.FileControl;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class MainConfigScreen extends JDialog {
    Font fontS = FileControl.getFont(Font.BOLD,20);
    Font fontL = FileControl.getFont(Font.BOLD,26);

    String targetDay = "Monday";
    public MainConfigScreen(){
        super((Frame) null, "主配置界面", true);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6,6,6,6);

        JLabel titleLable1 = new JLabel("主配置页面");
        titleLable1.setFont(fontL);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        panel.add(titleLable1,constraints);

        JLabel titleLable2 = new JLabel("你可在此配置电子课表程序.");
        titleLable2.setFont(FileControl.getFont(Font.PLAIN,20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        panel.add(titleLable2,constraints);

        constraints.gridwidth = 1;
        JButton windowConfigButton = new JButton("通用配置");
        windowConfigButton.addActionListener(e -> {
            GeneralConfigScreen generalConfigScreen = new GeneralConfigScreen();
            generalConfigScreen.setVisible(true);
        });
        windowConfigButton.setFont(fontS);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(windowConfigButton,constraints);

        JComboBox<String> box = new JComboBox<>(new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});
        box.setFont(fontS);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(box,constraints);

        JButton lessonConfigButton = new JButton("配置课表");
        lessonConfigButton.addActionListener(e -> {
            targetDay = (String) box.getSelectedItem();
            LessonConfigScreen lessonConfigScreen = new LessonConfigScreen(FileControl.getLessonListFromJSON(LessonConfigScreen.getDateNumFromString(targetDay)), targetDay);
            lessonConfigScreen.setVisible(true);
        });
        lessonConfigButton.setFont(fontS);
        constraints.gridx = 2;
        constraints.gridy = 3;
        panel.add(lessonConfigButton,constraints);

        JButton aboutButton = new JButton("关于页面");
        aboutButton.addActionListener(e -> {
            AboutScreen aboutScreen = new AboutScreen();
            aboutScreen.setVisible(true);
        });
        aboutButton.setFont(fontS);
        constraints.gridx = 4;
        constraints.gridy = 3;
        panel.add(aboutButton,constraints);

        JButton quitButton = new JButton("退出电子课表程序");
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setFont(fontS);
        constraints.gridx = 5;
        constraints.gridy = 3;
        panel.add(quitButton,constraints);

        add(panel);
        pack();
        setLocationRelativeTo(null);

    }
}
