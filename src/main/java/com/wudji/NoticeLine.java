package com.wudji;

import java.awt.*;

public class NoticeLine {
    String messageInfo = "Innovation in China 中国智造，慧及全球 0123456789";
    String textStyle = "default";

    int colorR = 0;
    int colorG = 0;
    int colorB = 0;

    int index;

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
