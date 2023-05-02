package com.wudji.Screens;


import com.wudji.ClockButtonManager;
import com.wudji.Lesson;
import com.wudji.MainActivity;
import com.wudji.Utils.FileControl;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Calendar;

public class MainWindow extends JDialog {

    Calendar c = Calendar.getInstance();
    int week = c.get(Calendar.DAY_OF_WEEK);

    // clock
    WPanel clockPanel = new WPanel(2,28);

    // lessonPanel
    WPanel lessonPanel = new WPanel(16,28);

    WPanel countDownPanel = new WPanel(2,28);

    Lesson[] lessonList = FileControl.getLessonListFromJSON(week - 1);

    JButton separateLine = new JButton("============");
    public MainWindow(int x,int y,int w,int h) throws HeadlessException {

        this.setUndecorated(true);
        this.setVisible(true);
        this.setName("MainScreen");
        this.setTitle("MainScreen");
        this.setBackground(new Color(0,0,0,0));

        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setBounds(x,y,w,h);

        // this.setBounds(150,150,150,150);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.separateLine.setFont(new Font(null, Font.BOLD, MainActivity.globalConfig.getLessonFontSize()));
        this.separateLine.setBackground(MainActivity.globalConfig.getWindowBgColor());// config

        this.add(clockPanel);
        if (MainActivity.globalConfig.isEnableCountDown()) {this.add(countDownPanel);}
        this.add(lessonPanel);

    }
    public void update() throws ParseException {
        this.updateLessonStatus();
        this.updateClockStatus();
        if (MainActivity.globalConfig.isEnableCountDown()) {this.updateCountdownStatus();}
        this.validate();
        this.repaint();

        // System.out.println("step 5 passed");
    }

    private void updateLessonStatus() throws ParseException {

        lessonPanel.removeAll();

        for(int i = 0;i < lessonList.length;i++){

            long sysMillis = System.currentTimeMillis();

            JButton lessonButt = new JButton(lessonList[i].getIndex() + "." + lessonList[i].getName());

            lessonButt.setFont(new Font(null, Font.BOLD,MainActivity.globalConfig.getLessonFontSize()));
            lessonButt.setBackground(MainActivity.globalConfig.getWindowBgColor());

            if (lessonList[i].getTimeEnd() < sysMillis){
                lessonButt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,17,0,0,MainActivity.globalConfig.getPastLessonColor()/*new Color(148, 248, 125)*/),BorderFactory.createEmptyBorder(5,17,5,17)));
            }else if(lessonList[i].getTimeStart() > sysMillis){
                lessonButt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,17,0,0,MainActivity.globalConfig.getNextLessonColor()/*new Color(255, 88, 79)*/),BorderFactory.createEmptyBorder(5,17,5,17)));
            }else if(lessonList[i].getTimeStart() < sysMillis && lessonList[i].getTimeEnd() > sysMillis){
                lessonButt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,17,0,0,MainActivity.globalConfig.getPresentLessonColor()/*new Color(234, 170, 99)*/),BorderFactory.createEmptyBorder(5,17,5,17)));
            }

            lessonPanel.add(lessonButt);
            lessonPanel.add(this.getSeparateLine());
        }
    }

    private void updateClockStatus(){
        clockPanel.removeAll();
        clockPanel.add(ClockButtonManager.getTimeButton());
        clockPanel.add(ClockButtonManager.getWeekButton(week));
    }

    private void updateCountdownStatus(){
        countDownPanel.removeAll();
        countDownPanel.add(ClockButtonManager.getCountDownInfoButton());
        countDownPanel.add(ClockButtonManager.getCountdownButton());
    }

    private JButton getSeparateLine(){
        JButton j = new JButton("============");
        j.setFont(new Font(null, Font.BOLD, MainActivity.globalConfig.getLessonFontSize()));
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        return j;
    }
}