package com.wudji;

import java.awt.*;
import java.text.ParseException;
import java.util.*;
import java.util.Timer;

import com.wudji.Screens.MainWindow;
import com.wudji.Screens.NoticeScreen;
import com.wudji.Screens.WelcomeScreen;
import com.wudji.Utils.FileControl;


public class MainActivity {
    public static WindowConfig globalConfig = FileControl.getWindowConfig();
    public static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static String version = "v1.1.2";

    public static void main(String args[]){
        MainWindow window = new MainWindow((int)d.getWidth() - globalConfig.getPosx(),globalConfig.getPosy(),globalConfig.getWeight(),(int)d.getHeight() - globalConfig.getHeight());
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        NoticeScreen noticeScreen = new NoticeScreen(window.getLocation());
        Timer timer = new Timer();

        // 欢迎界面
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showDelayScreen(welcomeScreen, noticeScreen);
            }

            private void showDelayScreen(WelcomeScreen welcomeScreen,NoticeScreen noticeScreen){
                // 隐藏欢迎页面
                welcomeScreen.setVisible(false);
                if(MainActivity.globalConfig.isEnableNotice()){
                // 显示公告框
                    noticeScreen.setVisible(true);
                }
            }
        },4000);

        welcomeScreen.setVisible(true);

        // 主循环
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    updateA(window);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            private void updateA(MainWindow w) throws ParseException {
                w.update();
            }
        },1,1000);
    }
}