package com.wudji.lessonlist.utils;

import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.screens.MainConfigScreen;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ClockButtonManager {
    static String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    static SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");

    static Font font1 = FileControl.getFont(Font.BOLD, MainActivity.globalConfig.getClockFontsize() - 1);
    static Font font2 = FileControl.getFont(Font.BOLD, MainActivity.globalConfig.getClockFontsize() - 1);

    public static JButton getTimeButton(){
        JButton j = new JButton(" 时间:" + formatter.format(new Date(System.currentTimeMillis())) +" ");
        j.setFont(font1);
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        j.addActionListener(e -> {
            MainConfigScreen mainConfigScreen = new MainConfigScreen();
            mainConfigScreen.setVisible(true);
        });
        return j;
    }
    public static JButton getWeekButton(int week){
        JButton j = new JButton(weeks[week - 1]);
        j.setFont(font2);
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        return j;

    }

    public static JButton getCountdownButton(){
        Date d = Calendar.getInstance().getTime();
        // fix #1: 当日期形如10月31日时，dateEnd计算出错。
        LocalDateTime dateStart = LocalDateTime.of(MainActivity.globalConfig.getCountDownYear(), MainActivity.globalConfig.getCountDownMonth() , MainActivity.globalConfig.getCountDownDate(),8,1);
        LocalDateTime dateEnd = LocalDateTime.of(d.getYear() + 1900, d.getMonth() + 1, d.getDate(), 0, 1);

        Duration duration = Duration.between(dateStart, dateEnd);

        int day = Math.abs((int)duration.toDays());
        JButton j = new JButton(day + " 天");
        j.setFont(font2);
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        return j;
    }

    public static JButton getCountDownInfoButton(){
        JButton j = new JButton(MainActivity.globalConfig.getCountDownInfo());
        j.setFont(font2);
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        return j;
    }

    public static JButton getConfigButton() {
        JButton config = new JButton("△ 配置程序");
        config.setFont(font2);
        config.setBackground(MainActivity.globalConfig.getWindowBgColor());
        config.addActionListener(e -> {
            MainConfigScreen mainConfigScreen = new MainConfigScreen();
            mainConfigScreen.setVisible(true);
        });
        return config;
    }
}
