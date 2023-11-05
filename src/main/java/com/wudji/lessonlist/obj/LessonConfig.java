package com.wudji.lessonlist.obj;

import com.wudji.lessonlist.utils.FileControl;

import javax.swing.*;
import java.awt.*;

public class LessonConfig {
    private JTextField name = new JTextField(10);
    private JTextField index = new JTextField(10);
    private JTextField timeStart = new JTextField(10);
    private JTextField timeEnd = new JTextField(10);
    private JCheckBox isEnabled = new JCheckBox();

    private Font font = FileControl.getFont(Font.PLAIN,14);

    public LessonConfig(Lesson l, boolean i) {
        name.setFont(font);
        index.setFont(font);
        timeStart.setFont(font);
        timeEnd.setFont(font);

        if (i) {
            name.setText(l.getName());
            index.setText(String.valueOf(l.getIndex()));
            timeStart.setText(l.getTimeStart());
            timeEnd.setText(l.getTimeEnd());
        } else {
            name.setText("missingno");
            index.setText("missingno");
            timeStart.setText("missingno");
            timeEnd.setText("missingno");
        }
        isEnabled.setSelected(i);
    }

    public boolean isEnabled(){
        return isEnabled.isSelected();
    }

    public JTextField getName() {
        return name;
    }

    public JTextField getIndex() {
        return index;
    }

    public JTextField getTimeStart() {
        return timeStart;
    }

    public JTextField getTimeEnd() {
        return timeEnd;
    }

    public JCheckBox getIsEnabled() {
        return isEnabled;
    }

}
