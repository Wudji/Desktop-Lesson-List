package com.wudji.lessonlist.Utils;

import com.wudji.lessonlist.MainActivity;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

public class ExceptionManager {
    public static void showErrorDialog(Throwable throwable) {
        if(MainActivity.globalConfig.isTemp_feature_notice()) {
            throwable.printStackTrace();
        }else{
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            String stackTrace = sw.toString();

            String errorMessage = throwable.getMessage();
            String dialogMessage = errorMessage + "\n\n" + stackTrace + "\n\n" + "===========" + "\n\n" + "Software information:" + "\n"
                    + "base_version: " + MainActivity.base_version + "\n" + "product_version: " + MainActivity.product_version + "\n\n"
                    + "Env information:" + "\nos: " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + "\n";

            // System.out.println(stackTrace);

            JOptionPane.showMessageDialog(null, dialogMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void areAllPropertiesNotNull(Object obj) {
        if (obj == null) {
            showErrorDialog(new IllegalStateException("Target object is null!"));
        }
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);

                if (value == null) {
                    showErrorDialog(new IllegalStateException("Object" + obj + "'s attribute is null or is not set properly."));
                }
            } catch (IllegalAccessException e) {
                showErrorDialog(e);
            }
        }
    }
}
