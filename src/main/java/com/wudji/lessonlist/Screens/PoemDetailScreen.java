package com.wudji.lessonlist.screens;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.utils.FileControl;
import com.wudji.lessonlist.network.PoemNetworkRequest;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PoemDetailScreen extends JDialog {
    private JLabel contentLabel;

    private String deviceInfo = PoemNetworkRequest.getDeviceInfo();

    static JButton refreshButton = new JButton("🔄 换句新的诗词");

    public PoemDetailScreen(String json, int fontSize) {
        super((Frame) null, "诗歌详细信息", true);

        // setTitle("诗歌详细信息");
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        // setLocationRelativeTo(null);
        Font font = FileControl.getFont(Font.PLAIN, fontSize);

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        contentLabel = new JLabel();
        contentLabel.setFont(font);
        contentLabel.setVerticalAlignment(SwingConstants.TOP);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(contentLabel), constraints);

        displayJsonContent(json);

        contentLabel.setFont(FileControl.getFont(Font.PLAIN, fontSize));

        constraints.gridx = 0;
        constraints.gridy = 1;

        refreshButton.setFont(new Font(null,Font.BOLD,16));
        refreshButton.setBackground(new Color(242,242,242));
        add(refreshButton,constraints);
        DelayedButtonEnabler enabler = new DelayedButtonEnabler(refreshButton, 120000);
        refreshButton.addActionListener(enabler);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JButton infoButton = new JButton("⚙ 获取实时客户端信息");
        infoButton.setFont(new Font(null,Font.BOLD,16));
        infoButton.setBackground(new Color(242,242,242));
        infoButton.addActionListener(e ->{
            Thread t = new Thread(()->{
                PoemRawScreen pr = new PoemRawScreen(PoemNetworkRequest.getDeviceInfo());
                pr.setVisible(true);
            });
            t.start();
        });
        add(infoButton,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        JButton returnButton = new JButton("🔎 显示源返回信息");
        returnButton.setFont(new Font(null,Font.BOLD,16));
        returnButton.setBackground(new Color(242,242,242));
        add(returnButton,constraints);
        returnButton.addActionListener(e ->{
            PoemRawScreen pr = new PoemRawScreen(json);
            pr.setVisible(true);
        });

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
            JSONArray suggestReasonArray = JSONObject.parseObject(deviceInfo).getJSONObject("data").getJSONArray("tags");
            StringBuilder suggestReasonBuilder = new StringBuilder();
            for (Object suggestObj : suggestReasonArray) {
                suggestReasonBuilder.append(suggestObj.toString()).append(" | ");
            }
            String suggestReason = suggestReasonBuilder.toString();

            String displayText = "<html><b>"+ title +"</b><br>" + content + "<br><b>翻译:</b><br>" + translation + "</b><br><br><b>设备标签(基于设备地理信息及天气、节气信息):</b><br>" + suggestReason + "</b><br><br><b>Token:</b><br>"+ full.getString("token") + "</html>";
            contentLabel.setText(displayText);

        }else if(Objects.equals(full.getString("status"), "error")){
            String displayText = "<html><p style=\"background-color:rgb(255,255,0)\">\n获取诗词信息失败！</p><br><br><b>errMessage:</b><br>"+ full.getString("errMessage") + "</html>";
            contentLabel.setText(displayText);
        }


    }

}

class DelayedButtonEnabler implements ActionListener {
    private JButton button;
    private Timer timer;

    public DelayedButtonEnabler(JButton button, int delayMilliseconds) {
        this.button = button;

        timer = new Timer(delayMilliseconds, e -> enableButton());
        timer.setRepeats(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        disableButton();
        MainActivity.poemScreen.updatePoemInfo();
        ((JButton) e.getSource()).setText("已刷新，操作冷却中(120s)...");
        timer.start();
    }

    private void disableButton() {
        button.setEnabled(false);
    }

    private void enableButton() {
        button.setEnabled(true);
        button.setText("换句新的诗词");
    }
}