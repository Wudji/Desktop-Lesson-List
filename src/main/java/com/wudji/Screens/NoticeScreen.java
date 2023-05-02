package com.wudji.Screens;

import com.wudji.MainActivity;
import com.wudji.NoticeLine;
import com.wudji.Utils.FileControl;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class NoticeScreen extends JDialog {
    Calendar c = Calendar.getInstance();
    int week = c.get(7);

    JPanel panel = new JPanel();
    public NoticeScreen(Point mainWindowLocation) {

        this.setTitle("Notice Screen");

        this.setUndecorated(true);

        this.setSize(MainActivity.globalConfig.getNoticeWeight(), MainActivity.globalConfig.getNoticeHeight());

        this.setLocationRelativeTo(null);

        this.setLocation(mainWindowLocation.x - this.getWidth(), mainWindowLocation.y);

        this.setUndecorated(true);

        this.setVisible(false);

        this.updateNoticeList(FileControl.getNoticeLinesFromJSON(week - 1));
    }

    private void updateNoticeList(NoticeLine[] lines){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (NoticeLine line : lines) {
            JLabel label = new JLabel(line.getMessageInfo());
            switch (line.getTextStyle()){
                case "bold":
                    label.setFont(new Font(null, Font.BOLD, MainActivity.globalConfig.getNoticeFontSize()));
                    break;
                case "italic":
                    label.setFont(new Font(null, Font.ITALIC, MainActivity.globalConfig.getNoticeFontSize()));
                    break;
                case "default":
                default:
                    label.setFont(new Font(null, Font.PLAIN, MainActivity.globalConfig.getNoticeFontSize()));
            }
            label.setForeground(line.getFontColor());
            panel.add(label);
        }
        this.add(panel);
    }
}
