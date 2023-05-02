package com.wudji.Screens;

import com.wudji.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class AboutScreen extends JDialog {
    JPanel infoPanel = new JPanel();
    public AboutScreen() {
        super((Frame) null, "", true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ImageIcon icon = new ImageIcon("about.png");
        JLabel label = new JLabel(icon);

        this.setSize(icon.getIconWidth(), icon.getIconHeight() + 200);

        infoPanel.add(label, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // 我在写什么¿
        JLabel label1 = new JLabel("电子课表" + MainActivity.version + "by Wudji(吾爱破解@YooooooDaLi)");
        label1.setFont(new Font(null,0,25));


        JLabel label2 = new JLabel("本软件由Wudji@tjyz利用课余时间开发，感谢使用~");
        label2.setFont(new Font(null,0,25));

        JLabel linkLabelGithub = new JLabel("开源地址：https://github.com/Wudji/Desktop-Lesson-List/，欢迎提issue(和star)");
        linkLabelGithub.setForeground(new Color(40, 112, 215));
        linkLabelGithub.setFont(new Font(null,0,25));
        linkLabelGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Wudji/Desktop-Lesson-List/"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel linkLable52pojie = new JLabel("吾爱破解：https://www.52pojie.cn/thread-1780607-1-1.html");
        linkLable52pojie.setForeground(new Color(40, 112, 215));
        linkLable52pojie.setFont(new Font(null,0,25));
        linkLable52pojie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.52pojie.cn/thread-1780607-1-1.html"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        infoPanel.add(label1);
        infoPanel.add(label2);
        infoPanel.add(linkLabelGithub);
        infoPanel.add(linkLable52pojie);

        this.add(infoPanel);
        this.setTitle("电子课表" + MainActivity.version + "by Wudji(吾爱破解@YooooooDaLi)");

    }
}
