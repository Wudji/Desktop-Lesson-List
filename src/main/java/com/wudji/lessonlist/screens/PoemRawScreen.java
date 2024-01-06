package com.wudji.lessonlist.screens;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.utils.FileControl;

import javax.swing.*;
import java.awt.*;

public class PoemRawScreen extends JDialog {
    private JTextArea textArea;
    public PoemRawScreen(String json) {
        super((Frame) null, "Debug Info", true);
        setTitle("Debug Info");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(FileControl.getFont(Font.BOLD, MainActivity.globalConfig.getLessonFontSize()));
        add(scrollPane);

        displayJson(json);

        pack();
    }

    private void displayJson(String json) {
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            String prettyJson = JSON.toJSONString(jsonObject, JSONWriter.Feature.PrettyFormat);
            textArea.setText(prettyJson);
            System.out.println(prettyJson);
        } catch (Exception e) {
            textArea.setText("Invalid JSON format:\n" + json);
        }
    }
}
