package com.wudji.lessonlist.obj;

import java.awt.*;

public class NoticeLine {
    String messageInfo = "Innovation in China 中国智造，慧及全球 0123456789";
    String textStyle = "default";

    int colorR = 0;
    int colorG = 0;
    int colorB = 0;

    int index;

    public NoticeLine() {
    }

    public NoticeLine(String messageInfo, String textStyle, int colorR, int colorG, int colorB, int index) {
        this.messageInfo = messageInfo;
        this.textStyle = textStyle;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.index = index;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    public Color getFontColor(){
        return new Color(colorR,colorG,colorB);
    }
}
