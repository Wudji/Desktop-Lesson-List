package com.wudji.lessonlist.screens;

import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.utils.FileControl;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LicenseInfoScreen extends JDialog {
    private JTextArea textArea;
    public LicenseInfoScreen() {
        super((Frame) null, true);
        setTitle("协议页面");
        //setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(FileControl.getFont(Font.BOLD, (int)(MainActivity.globalConfig.getLessonFontSize() * 0.7)));

        // 按钮
        JButton agreeButton = new JButton("同意");
        JButton disagreeButton = new JButton("不同意");

        agreeButton.addActionListener(e -> {
            FileControl.writeString("config/license.txt","User agreed to ToS at (Unix timestamp) " + System.currentTimeMillis());
            MainActivity.isAgreedTOS = true;
            dispose();
        });

        disagreeButton.addActionListener(e -> {
            System.exit(0);
        });


        // 布局
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        readLicense();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agreeButton);
        buttonPanel.add(disagreeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800,800);
    }
    
    public void displayLicense(){
        if(!verifyLicense()){
            this.setVisible(true);
        }

    }
    public static boolean verifyLicense(){
        File f = new File("config/license.txt");
        return f.exists();
    }
    private void readLicense() {
        try {
            textArea.setText(FileControl.getFormattedStr(new File("resources/license.txt")));
        } catch (Exception e) {
            textArea.setText("Failed to get License File: resources/license.txt");
        }
    }
}
