package com.wudji.lessonlist;

import com.wudji.lessonlist.obj.WindowConfig;
import com.wudji.lessonlist.screens.LicenseInfoScreen;
import com.wudji.lessonlist.screens.MainWindow;
import com.wudji.lessonlist.screens.NoticeScreen;
import com.wudji.lessonlist.screens.PoemScreen;
import com.wudji.lessonlist.screens.WelcomeScreen;
import com.wudji.lessonlist.utils.ExceptionManager;
import com.wudji.lessonlist.utils.FileControl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity {

    public static WindowConfig globalConfig = FileControl.getWindowConfig();
    public static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static String base_version = "v1.1.2";
    public static String product_version = "v1.2.4-poem-suggestion-tjyz";
    public static PoemScreen poemScreen;
    public static MainWindow window;
    public static WelcomeScreen welcomeScreen;
    public static NoticeScreen noticeScreen;
    public static boolean isAgreedTOS = LicenseInfoScreen.verifyLicense();

    public static void main(String[] args){
        Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());

        while(!isAgreedTOS){
            LicenseInfoScreen ls = new LicenseInfoScreen();
            ls.displayLicense();
        }

        window = new MainWindow((int)d.getWidth() - globalConfig.getPosx(),globalConfig.getPosy(),globalConfig.getWeight(),(int)d.getHeight() - globalConfig.getHeight());
        welcomeScreen = new WelcomeScreen();
        noticeScreen = new NoticeScreen(window.getLocation());

        Timer timer = new Timer();

        if (globalConfig.isEnablePoemSuggestion()){
            Thread thread = new Thread(() -> {
                Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());

                poemScreen = new PoemScreen(window.getLocation());
                noticeScreen.updatePosition(poemScreen.getHeight());

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        refreshPoemInfo(poemScreen);
                    }

                    private void refreshPoemInfo(PoemScreen poemScreen){

                        poemScreen.updatePoemInfo();
                    }
                }, 3600000,3600000);
            });
            thread.start();
        }

        // 欢迎界面
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showDelayScreen();
            }

            private void showDelayScreen(){

                // 隐藏欢迎页面
                welcomeScreen.setVisible(false);
                poemScreen.setVisible(true);
                if(MainActivity.globalConfig.isEnableNotice()){
                // 显示公告框
                    noticeScreen.setVisible(true);
                }
            }
        }, 3000);

        welcomeScreen.setVisible(true);

        // 主循环
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateA();
            }

            private void updateA() {
                window.update();
            }
        },1,1000);
    }
}
class CustomExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        ExceptionManager.showErrorDialog(e);
    }
}