package com.wudji.lessonlist.utils;

import com.wudji.lessonlist.screens.ExceptionInfoScreen;

public class ExceptionManager {
    public static void showErrorDialog(Throwable throwable) {
        ExceptionInfoScreen es = new ExceptionInfoScreen((Exception) throwable);
            /*StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            String stackTrace = sw.toString();

            String errorMessage = "<html><h2>The Desktop Lesson List Program encountered an unexpected exception:</h2>\n" + throwable.getMessage();
            String dialogMessage = errorMessage + "\n\n" + stackTrace + "\n\n" + "===========" + "\n\n" + "Software information:" + "\n"
                    + "base_version: " + MainActivity.base_version + "\n" + "product_version: " + MainActivity.product_version + "\n\n"
                    + "Env information:" + "\nos: " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + "\n";

            // System.out.println(stackTrace);

            JOptionPane.showMessageDialog(null, dialogMessage, "Error", JOptionPane.ERROR_MESSAGE);*/

    }
}
