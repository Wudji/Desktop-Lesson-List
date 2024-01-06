package com.wudji.lessonlist.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.wudji.lessonlist.obj.Lesson;
import com.wudji.lessonlist.obj.NoticeLine;
import com.wudji.lessonlist.obj.WindowConfig;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileControl {
    public static String getFormattedStr(File jsonFile){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(jsonFile.toPath()), StandardCharsets.UTF_8))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }

            // 输出读取到的字符串
            return contentBuilder.toString();
        } catch (Exception e) {

        }
        return "";
    }

    public static String getRoughStr(String filePath){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }

            return contentBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void writeString(String filePath,String content){
        try{
            (new File(filePath)).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Lesson[] getLessonListFromJSON(int week){
        String lessonListStr = getFormattedStr(new File("lessons/" + week + ".json"));
        Lesson[] lessons = JSON.parseArray(lessonListStr,Lesson.class).toArray(new Lesson[0]);
        if (lessons.length > 15){
            ExceptionManager.showErrorDialog(new IllegalStateException("Lesson number > 15 is not supported by default!"));
        }
        return lessons;
    }

    public static WindowConfig getWindowConfig(){
        try {

            String jsonString = new String(Files.readAllBytes(Paths.get("config/windowConfig.json")));

            return JSON.parseObject(jsonString, WindowConfig.class);

        } catch (IOException e) {

            ExceptionManager.showErrorDialog(e);
            return new WindowConfig();

        }
    }

    public static NoticeLine[] getNoticeLinesFromJSON(int week){
        try {
            String noticeLinesStr = getFormattedStr(new File("notice/" + week + ".json"));
            NoticeLine[] lines = JSON.parseArray(noticeLinesStr, NoticeLine.class).toArray(new NoticeLine[0]);
            return lines;
        }catch (Exception e){
            ExceptionManager.showErrorDialog(e);
        }
        return new NoticeLine[0];
    }

    public static void writeLessonListToJson(Lesson[] lessons, int targetDay){
        String jsonString = JSON.toJSONString(lessons, JSONWriter.Feature.PrettyFormat);
        try (OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(new File("lessons/" + targetDay + ".json").toPath()), StandardCharsets.UTF_8)){
            writer.write(jsonString);
        } catch (IOException e) {
            ExceptionManager.showErrorDialog(e);
        }
    }

    public static void writeWindowConfigToJson(WindowConfig config){
        String jsonString = JSON.toJSONString(config, JSONWriter.Feature.PrettyFormat);

        try (OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(new File("config/windowConfig.json").toPath()), StandardCharsets.UTF_8)){
            writer.write(jsonString);
        } catch (IOException e) {
            ExceptionManager.showErrorDialog(e);
        }
    }

    public static Font getFont(int fontStyle,int fontSize){
        try {
            File ttfFile = new File("resources/font.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, ttfFile);

            return font.deriveFont(fontStyle, fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        // 获取失败
        return new Font(null,fontStyle,fontSize);
    }
}
