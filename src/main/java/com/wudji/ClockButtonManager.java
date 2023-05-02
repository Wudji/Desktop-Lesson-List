package com.wudji;

import com.wudji.Screens.AboutScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ClockButtonManager {
    static String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    static SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");

    public static JButton getTimeButton(){
        JButton j = new JButton(" 时间:" + formatter.format(new Date(System.currentTimeMillis())) +" ");
        j.setFont(new Font(null, Font.BOLD,MainActivity.globalConfig.getClockFontsize()));
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        j.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutScreen s = new AboutScreen();
                s.setVisible(true);
            }
        });
        return j;
    }
    public static JButton getWeekButton(int week){
        JButton j = new JButton(weeks[week - 1]);
        j.setFont(new Font(null, Font.BOLD,MainActivity.globalConfig.getClockFontsize()));
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        return j;

    }

    public static JButton getCountdownButton(){
        Date d = Calendar.getInstance().getTime();
        LocalDateTime dateStart = LocalDateTime.of(MainActivity.globalConfig.getCountDownYear(), MainActivity.globalConfig.getCountDownMonth() - 1 , MainActivity.globalConfig.getCountDownDate(),8,1);
        LocalDateTime dateEnd = LocalDateTime.of(d.getYear() + 1900, d.getMonth(), d.getDate(), 8, 1);

        Duration duration = Duration.between(dateStart, dateEnd);

        int day = Math.abs((int)duration.toDays());
        JButton j = new JButton(String.valueOf(day) + " 天");
        j.setFont(new Font(null, Font.BOLD,MainActivity.globalConfig.getClockFontsize() - 1));
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        return j;
    }

    public static JButton getCountDownInfoButton(){
        JButton j = new JButton(MainActivity.globalConfig.getCountDownInfo());
        j.setFont(new Font(null, Font.BOLD,MainActivity.globalConfig.getClockFontsize() - 1));
        j.setBackground(MainActivity.globalConfig.getWindowBgColor());
        return j;
    }
}
