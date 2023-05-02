package com.wudji.Utils;

import com.wudji.Lesson;

import java.io.*;
import com.alibaba.fastjson2.*;
import com.wudji.NoticeLine;
import com.wudji.WindowConfig;

public class FileControl {
    private static String getStr(File jsonFile){
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Lesson[] getLessonListFromJSON(int week){
        String lessonListStr = getStr(new File(week + ".json"));
        return JSON.parseArray(lessonListStr,Lesson.class).toArray(new Lesson[0]);
    }

    public static WindowConfig getWindowConfig(){
        String configStr = getStr(new File("windowConfig.json"));

        return JSON.parseArray(configStr,WindowConfig.class).toArray(new WindowConfig[0])[0];
    }

    public static NoticeLine[] getNoticeLinesFromJSON(int week){
        String noticeLinesStr = getStr(new File("notice/" + week + ".json"));
        return JSON.parseArray(noticeLinesStr,NoticeLine.class).toArray(new NoticeLine[0]);
    }

    public static void writeLessonListToJson(Lesson[] lessons, int targetDay){
        // todo
    }

    public static void writeWindowConfigToJson(WindowConfig config){
        // todo
    }
}
