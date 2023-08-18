package com.wudji.lessonlist.Screens;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.Utils.FileControl;
import com.wudji.lessonlist.obj.NoticeLine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

public class PoemScreen extends JDialog {

    JPanel panel = new JPanel();
    String poemDataString;
    public PoemScreen(Point mainWindowLocation, String poemInfoString) {

        this.setTitle("诗词显示窗口");

        this.setUndecorated(true);

        this.setSize(MainActivity.globalConfig.getNoticeWeight(), MainActivity.globalConfig.getNoticeHeight());

        this.setLocationRelativeTo(null);

        this.setUndecorated(true);

        this.setVisible(false);

        this.setBackground(new Color(237, 241, 228));

        // setBorder
        Border customBorder = new LineBorder(new Color(217, 231, 203), 8);

        panel.setBorder(customBorder);

        this.poemDataString = poemInfoString;

        this.updatePoemInfo(resultResolve());

        pack();

        this.setLocation(mainWindowLocation.x - this.getWidth(), mainWindowLocation.y);

    }

    private void updatePoemInfo(NoticeLine[] lines){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (NoticeLine line : lines) {
            JLabel label = new JLabel(line.getMessageInfo());
            switch (line.getTextStyle()){
                case "bold":
                    label.setFont(FileControl.getFont(Font.BOLD,MainActivity.globalConfig.getNoticeFontSize()));
                    break;
                case "italic":
                    label.setFont(FileControl.getFont(Font.ITALIC,MainActivity.globalConfig.getNoticeFontSize()));
                    break;
                case "default":
                default:
                    label.setFont(FileControl.getFont(Font.PLAIN,MainActivity.globalConfig.getNoticeFontSize()));
            }
            label.setForeground(line.getFontColor());
            panel.add(label);
        }

        // add refresh buttons
        JButton refreshButton = new JButton("🔎 诗词信息");
        refreshButton.setFont(new Font(null, Font.PLAIN, 16));

        refreshButton.addActionListener(e -> {
            PoemDetailScreen poemDetailScreen = new PoemDetailScreen(poemDataString,MainActivity.globalConfig.getNoticeFontSize());
            poemDetailScreen.setVisible(true);
        });

        panel.add(refreshButton);
        this.add(panel);
    }

    private NoticeLine[] resultResolve(){
        NoticeLine[] poemInfo = new NoticeLine[2];


        JSONObject fullData = JSONObject.parseObject(poemDataString);
        // System.out.println(fullData.getString("status"));
        if(Objects.equals(fullData.getString("status"), "success")){
            JSONObject poemData = fullData.getJSONObject("data");
            JSONObject originData = poemData.getJSONObject("origin");

            poemInfo[0] = new NoticeLine(poemData.getString("content"),"bold",0,0,0,0);
            poemInfo[1] = new NoticeLine("——" + originData.getString("author") + "《"+ originData.getString("title") +"》","italic",0,0,0,0);

        }else if(Objects.equals(fullData.getString("status"), "error")){
            poemInfo[0] = new NoticeLine("诗词获取失败，何故?","bold",0,0,0,0);
            poemInfo[1] = new NoticeLine("——错误代码：" + fullData.getInteger("errCode").toString() + "；错误信息"+ fullData.getString("errMessage") +"。","italic",0,0,0,0);
        }
        return poemInfo;

    }
}
