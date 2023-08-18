package com.wudji.lessonlist.Screens;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.wudji.lessonlist.Utils.FileControl;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PoemDetailScreen extends JFrame {
    private JLabel contentLabel;

    public PoemDetailScreen(String json, int fontSize) {

        setTitle("诗歌详细信息");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        contentLabel = new JLabel();
        contentLabel.setFont(FileControl.getFont(Font.PLAIN, fontSize));
        contentLabel.setVerticalAlignment(SwingConstants.TOP);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(contentLabel), constraints);

        displayJsonContent(json);

        contentLabel.setFont(FileControl.getFont(Font.PLAIN, fontSize));

        pack();
    }

    private void displayJsonContent(String json) {
        JSONObject full = JSON.parseObject(json);

        if(Objects.equals(full.getString("status"), "success")){
            JSONObject origin = full.getJSONObject("data").getJSONObject("origin");

            String title = "《" + origin.getString("title") + "》 ——" + origin.getString("dynasty") + " " +origin.getString("author");

            // content
            JSONArray contentArray = origin.getJSONArray("content");
            StringBuilder contentBuilder = new StringBuilder();
            for (Object contentObj : contentArray) {
                contentBuilder.append(contentObj.toString()).append("<br>");
            }
            String content = contentBuilder.toString();
            String translation = "<p style=\"background-color:rgb(255,255,0)\">\n暂无翻译或获取翻译失败！</p>";
            JSONArray translationArray = origin.getJSONArray("translate");
            try{
                StringBuilder translationBuilder = new StringBuilder();
                for (Object translationObj : translationArray) {
                    translationBuilder.append(translationObj.toString()).append("<br>");
                    translation = translationBuilder.toString();
                }
            }catch(Exception e){
                // translation = "暂无翻译或获取翻译失败！";
            }


            // suggestReason
            JSONArray suggestReasonArray = full.getJSONObject("data").getJSONArray("matchTags");
            StringBuilder suggestReasonBuilder = new StringBuilder();
            for (Object suggestObj : suggestReasonArray) {
                suggestReasonBuilder.append(suggestObj.toString()).append(" | ");
            }
            String suggestReason = suggestReasonBuilder.toString();

            String displayText = "<html><b>"+ title +"</b><br>" + content + "<br><b>翻译:</b><br>" + translation + "</b><br><br><b>推荐理由(基于设备地理信息及天气、节气信息):</b><br>" + suggestReason + "</b><br><br><b>Token:</b><br>"+ full.getString("token") + "</html>";
            contentLabel.setText(displayText);

        }else if(Objects.equals(full.getString("status"), "error")){
            String displayText = "<html><p style=\"background-color:rgb(255,255,0)\">\n获取诗词信息失败！</p><br><br><b>errMessage:</b><br>"+ full.getString("errMessage") + "</html>";
            contentLabel.setText(displayText);
        }


    }

}
