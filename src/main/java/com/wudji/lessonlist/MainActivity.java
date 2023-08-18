package com.wudji.lessonlist;

import java.awt.*;
import java.text.ParseException;
import java.util.*;
import java.util.Timer;

import com.wudji.lessonlist.Screens.*;
import com.wudji.lessonlist.Utils.FileControl;
import com.wudji.lessonlist.network.PoemNetworkRequest;
import com.wudji.lessonlist.obj.WindowConfig;


public class MainActivity {

    public static WindowConfig globalConfig = FileControl.getWindowConfig();
    public static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static String base_version = "v1.1.2";
    public static String product_version = "v1.2.0_poem_suggestion_tjyz";

    public static void main(String[] args){
        MainWindow window = new MainWindow((int)d.getWidth() - globalConfig.getPosx(),globalConfig.getPosy(),globalConfig.getWeight(),(int)d.getHeight() - globalConfig.getHeight());
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        NoticeScreen noticeScreen = new NoticeScreen(window.getLocation());

        Timer timer = new Timer();

        if (globalConfig.isEnablePoemSuggestion()){
            Thread thread = new Thread(() -> {
                String data = PoemNetworkRequest.getPoemData();
                System.out.println(data);
                PoemScreen poemScreen = new PoemScreen(window.getLocation(),data);
                noticeScreen.updatePosition(poemScreen.getHeight());
                poemScreen.setVisible(true);
            });
            thread.start();
        }

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
        },(int) (Math.random()*2000));

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