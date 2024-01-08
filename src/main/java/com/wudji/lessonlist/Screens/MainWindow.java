package com.wudji.lessonlist.screens;


import com.wudji.lessonlist.utils.ClockButtonManager;
import com.wudji.lessonlist.utils.ExceptionManager;
import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.utils.FileControl;
import com.wudji.lessonlist.obj.Lesson;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Calendar;

public class MainWindow extends JDialog {

    Calendar c = Calendar.getInstance();
    int week = c.get(Calendar.DAY_OF_WEEK);
    Lesson[] lessonList = FileControl.getLessonListFromJSON(week - 1);
    // clock
    WPanel clockPanel = new WPanel(3,28);

    // lessonPanel
    WPanel lessonPanel = new WPanel(lessonList.length * 2,28);

    WPanel countDownPanel = new WPanel(2,28);

    Font lessonFont = FileControl.getFont(Font.BOLD, MainActivity.globalConfig.getLessonFontSize());
    public MainWindow(int x,int y,int w,int h) throws HeadlessException {

        this.setUndecorated(true);
        this.setVisible(true);
        this.setName("电子课表主界面");
        this.setTitle("电子课表主界面");
        this.setBackground(new Color(0,0,0,0.0f));
        this.lessonPanel.setBackground(new Color(0,0,0,0.0f));

        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setBounds(x,y,w,h);

        // this.setBounds(150,150,150,150);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.add(clockPanel);
        if (MainActivity.globalConfig.isEnableCountDown()) {
            this.add(countDownPanel);
        }
        this.add(lessonPanel);

    }
    public void update(){
        this.updateLessonStatus();
        this.updateClockStatus();
        if (MainActivity.globalConfig.isEnableCountDown()) {
            this.updateCountdownStatus();
        }
        this.validate();
        this.repaint();

    }

    private void updateLessonStatus(){

        lessonPanel.removeAll();
        try {
            for (Lesson lesson : lessonList) {

                long sysMillis = System.currentTimeMillis();

                JButton lessonButt = new JButton(lesson.getIndex() + "." + lesson.getName());

                lessonButt.setFont(lessonFont);


                if (lesson.getMillisTimeEnd() < sysMillis) {
                    /*new Color(148, 248, 125)*/
                    lessonButt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 13, 1, 0,
                            MainActivity.globalConfig.getPastLessonColor()),
                            BorderFactory.createEmptyBorder(5, 13, 5, 17)));
                } else if (lesson.getMillisTimeStart() > sysMillis) {
                    /*new Color(255, 88, 79)*/
                    lessonButt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 13, 1, 0,
                            MainActivity.globalConfig.getNextLessonColor()),
                            BorderFactory.createEmptyBorder(5, 13, 5, 17)));
                } else if (lesson.getMillisTimeStart() < sysMillis && lesson.getMillisTimeEnd() > sysMillis) {
                    /*new Color(234, 170, 99)*/
                    lessonButt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 13, 1, 0,
                            MainActivity.globalConfig.getPresentLessonColor()),
                            BorderFactory.createEmptyBorder(5, 13, 5, 17)));
                }

                lessonButt.setBackground(MainActivity.globalConfig.getNoAlphaWindowBgColor());

                lessonPanel.add(lessonButt);
                if(MainActivity.globalConfig.isEnableSepLine()) {
                    lessonPanel.add(this.getSeparateLine());
                }
            }
        }catch(ParseException e){
            ExceptionManager.showErrorDialog(e);
        }
    }

    private void updateClockStatus(){
        clockPanel.removeAll();
        clockPanel.add(ClockButtonManager.getTimeButton());
        clockPanel.add(ClockButtonManager.getWeekButton(week));
        clockPanel.add(ClockButtonManager.getConfigButton());
    }

    private void updateCountdownStatus(){
        countDownPanel.removeAll();
        countDownPanel.add(ClockButtonManager.getCountDownInfoButton());
        countDownPanel.add(ClockButtonManager.getCountdownButton());
    }

    private JButton getSeparateLine(){
        JButton j = new JButton("============");
        j.setFont(lessonFont);
        j.setBackground(MainActivity.globalConfig.getNoAlphaWindowBgColor());
        j.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 13, 1, 0,
                new Color(242, 247, 253)),
                BorderFactory.createEmptyBorder(5, 13, 5, 17)));
        return j;
    }
}