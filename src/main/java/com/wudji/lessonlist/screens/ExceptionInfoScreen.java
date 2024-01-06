package com.wudji.lessonlist.screens;

import com.wudji.lessonlist.utils.FileControl;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

public class ExceptionInfoScreen extends JDialog {

    public ExceptionInfoScreen(Exception exception) {
        super((Frame) null, true);
        setTitle("Unexpected Exception: " + exception.getMessage());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font(null,Font.BOLD,28));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String stackTrace = sw.toString();

        String crashInfo = "The Desktop Lesson List Program encountered an unexpected exception at " + Calendar.getInstance().getTime()
                + "\n\n" + "Below is the stacktrace of the error: \n\n"
                + stackTrace;

        FileControl.writeString("crash-report-"+ System.currentTimeMillis() + ".txt",crashInfo);

        textArea.setText(crashInfo);

        // 按钮
        JButton aContinue = new JButton("Continue");
        JButton quitProgram = new JButton("Quit Program");

        aContinue.addActionListener(e -> dispose());

        quitProgram.addActionListener(e -> System.exit(0));


        // 布局
        setLayout(new BorderLayout());
        add(scrollPane,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aContinue);
        buttonPanel.add(quitProgram);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(1280,800);
        setVisible(true);
    }

}
