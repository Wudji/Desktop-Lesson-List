package com.wudji.lessonlist.screens;

import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.utils.FileControl;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LicenseInfoScreen extends JDialog {
    private final JTextArea textArea;
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

        disagreeButton.addActionListener(e -> System.exit(0));


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
        textArea.setText(FileControl.getFormattedStr(new File("resources/license.txt")));
        // it works anyway
        if(textArea.getText().length() < 10) textArea.setText("获取程序许可文件失败: resources/license.txt。" +
                "\n请打开最新版本电子课表程序安装包resources/license.txt文件查看许可.");
    }
}
