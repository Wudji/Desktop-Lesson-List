package com.wudji.lessonlist.Screens;

import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.obj.NoticeLine;
import com.wudji.lessonlist.Utils.FileControl;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Calendar;

public class NoticeScreen extends JDialog {
    Calendar c = Calendar.getInstance();
    int week = c.get(Calendar.DAY_OF_WEEK);

    Point location;

    JPanel panel = new JPanel();
    public NoticeScreen(Point mainWindowLocation) {

        this.location = mainWindowLocation;

        this.setTitle("公告页面");

        this.setUndecorated(true);

        this.setSize(MainActivity.globalConfig.getNoticeWeight(), MainActivity.globalConfig.getNoticeHeight());

        this.setLocationRelativeTo(null);

        this.setLocation(location.x - this.getWidth(), location.y);

        Border customBorder = new LineBorder(new Color(217, 231, 203), 8);

        panel.setBorder(customBorder);

        this.setUndecorated(true);

        this.setVisible(false);

        this.updateNoticeList(FileControl.getNoticeLinesFromJSON(week - 1));
    }

    public void updatePosition(int y){
        this.pack();
        this.setLocation(this.location.x - this.getWidth(), this.location.y + y + 30);
    }
    private void updateNoticeList(NoticeLine[] lines){
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
        this.add(panel);
    }
}
