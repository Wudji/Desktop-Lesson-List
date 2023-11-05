package com.wudji.lessonlist.screens;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.utils.FileControl;
import com.wudji.lessonlist.network.PoemNetworkRequest;
import com.wudji.lessonlist.obj.NoticeLine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class PoemScreen extends JDialog {

    JPanel panel = new JPanel();
    String poemDataString;
    Point location;
    public PoemScreen(Point mainWindowLocation) {

        this.location = mainWindowLocation;

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

        this.updatePoemInfo();

        pack();

        this.setLocation(mainWindowLocation.x - this.getWidth(), mainWindowLocation.y);

    }

    public void updatePoemInfo(){
        panel.removeAll();
        poemDataString = PoemNetworkRequest.getPoemData();
        System.out.println(poemDataString);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (NoticeLine line : resultResolve()) {
            JLabel label = new JLabel(line.getMessageInfo());
            switch (line.getTextStyle()){
                case "bold":
                    label.setFont((FileControl.getFont(Font.BOLD,(int)(MainActivity.globalConfig.getNoticeFontSize() * 0.9))));
                    break;
                case "italic":
                    label.setFont(FileControl.getFont(Font.ITALIC,(int)(MainActivity.globalConfig.getNoticeFontSize() * 0.7)));
                    break;
                case "default":
                default:
                    label.setFont(FileControl.getFont(Font.PLAIN,(int)(MainActivity.globalConfig.getNoticeFontSize() * 0.7)));
            }
            label.setForeground(line.getFontColor());
            panel.add(label);
        }

        // 标签行解析
        JSONArray suggestReasonArray = JSONObject.parseObject(poemDataString).getJSONObject("data").getJSONArray("matchTags");
        StringBuilder suggestReasonBuilder = new StringBuilder();
        for (Object suggestObj : suggestReasonArray) {
            suggestReasonBuilder.append(suggestObj.toString()).append(" | ");
        }
        JLabel reasonLabel = new JLabel("诗词标签: " + suggestReasonBuilder.toString());
        reasonLabel.setFont(FileControl.getFont(Font.PLAIN,(int)(MainActivity.globalConfig.getNoticeFontSize() * 0.6)));
        panel.add(reasonLabel);

        // 添加信息按钮
        JButton refreshButton = new JButton("🔎 诗词信息");
        refreshButton.setFont(new Font(null, Font.PLAIN, (int)(MainActivity.globalConfig.getNoticeFontSize() * 0.6)));
        refreshButton.setBackground(new Color(238,238,238));
        refreshButton.addActionListener(e -> {
            Thread thread = new Thread(()->{
                PoemDetailScreen poemDetailScreen = new PoemDetailScreen(poemDataString,(int)(MainActivity.globalConfig.getNoticeFontSize() * 0.8));
                poemDetailScreen.setVisible(true);
            });

            thread.start();
        });

        panel.add(refreshButton);
        add(panel);
        this.pack();

        this.setLocation(location.x - this.getWidth(), location.y);

        this.validate();
        this.repaint();
    }

    private NoticeLine[] resultResolve(){
        NoticeLine[] poemInfo = new NoticeLine[2];
        // 诗歌句子解析
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
