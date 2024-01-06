package com.wudji.lessonlist.screens;

import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.utils.FileControl;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class AboutScreen extends JDialog {
    JPanel infoPanel = new JPanel();
    public AboutScreen() {
        super((Frame) null, "电子课表关于页面", true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setLocationRelativeTo(null);
        infoPanel.setLayout(new GridLayout(10, 1, 20, 10));

        // 我在写什么¿
        JLabel labelTitle = new JLabel("电子课表 by Wudji(吾爱破解@YooooooDaLi)。");
        labelTitle.setFont(FileControl.getFont(Font.BOLD,30));

        JLabel labelBaseVersion = new JLabel("base_version: " + MainActivity.base_version + "");
        labelBaseVersion.setFont(FileControl.getFont(Font.PLAIN,22));

        JLabel labelProductVersion = new JLabel("product_version: " + MainActivity.product_version + "");
        labelProductVersion.setFont(FileControl.getFont(Font.PLAIN,22));

        JLabel labelInfo = new JLabel("程序信息：");
        labelInfo.setFont(FileControl.getFont(Font.BOLD,26));

        JLabel labelLicense = new JLabel("本电子课表程序根据GPL-3.0 license进行许可。");
        labelLicense.setFont(FileControl.getFont(Font.PLAIN,22));

        JLabel linkLabelGithub = new JLabel("开源地址：https://github.com/Wudji/Desktop-Lesson-List/");
        linkLabelGithub.setForeground(new Color(40, 112, 215));
        linkLabelGithub.setFont(FileControl.getFont(Font.PLAIN,22));
        linkLabelGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Wudji/Desktop-Lesson-List/"));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JLabel linkLabel52pojie = new JLabel("吾爱破解：https://www.52pojie.cn/thread-1780607-1-1.html");
        linkLabel52pojie.setForeground(new Color(40, 112, 215));
        linkLabel52pojie.setFont(FileControl.getFont(Font.PLAIN,22));
        linkLabel52pojie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.52pojie.cn/thread-1780607-1-1.html"));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JLabel labelThanks = new JLabel("特别鸣谢：");
        labelThanks.setFont(FileControl.getFont(Font.BOLD,26));

        JLabel linkLabelFastjson = new JLabel("FastJSON by alibaba (Apache-2.0 license)。");
        linkLabelFastjson.setForeground(new Color(40, 112, 215));
        linkLabelFastjson.setFont(FileControl.getFont(Font.PLAIN,22));
        linkLabelFastjson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/alibaba/fastjson2"));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JLabel linkLabelJinrishici = new JLabel("今日诗词开放接口 by xenv@github.com。");
        linkLabelJinrishici.setForeground(new Color(40, 112, 215));
        linkLabelJinrishici.setFont(FileControl.getFont(Font.PLAIN,22));
        linkLabelJinrishici.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.jinrishici.com/"));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        infoPanel.add(labelTitle);

        infoPanel.add(labelInfo);
        infoPanel.add(linkLabelGithub);
        infoPanel.add(linkLabel52pojie);
        infoPanel.add(labelBaseVersion);
        infoPanel.add(labelProductVersion);
        infoPanel.add(labelLicense);

        infoPanel.add(labelThanks);
        infoPanel.add(linkLabelJinrishici);
        infoPanel.add(linkLabelFastjson);

        this.add(infoPanel);
        this.setLocationRelativeTo(null);
        this.setTitle("关于电子课表 " + MainActivity.product_version);
        this.pack();

    }
}
