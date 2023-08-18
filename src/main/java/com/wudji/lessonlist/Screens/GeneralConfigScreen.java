package com.wudji.lessonlist.Screens;

import com.wudji.lessonlist.MainActivity;
import com.wudji.lessonlist.Utils.FileControl;
import com.wudji.lessonlist.obj.WindowConfig;

import javax.swing.*;
import java.awt.*;

public class GeneralConfigScreen extends JFrame {
    private WindowConfig config = MainActivity.globalConfig;
    private JTextField clockFontSizeField;
    private JTextField lessonFontSizeField;
    private JTextField noticeFontSizeField;
    private JTextField posxField;
    private JTextField posyField;
    private JTextField weightField;
    private JTextField heightField;
    private JCheckBox enableCountdownCheckbox;

    private JCheckBox enablePoemCheckbox;
    private JTextField countDownYearField;
    private JTextField countDownMonthField;
    private JTextField countDownDateField;
    private JTextField countDownTextField;

    private JCheckBox suppressWarningsCheckbox;
    private JCheckBox enableNoticeCheckbox;
    private JTextField noticeWeightField;
    private JTextField noticeHeightField;

    public GeneralConfigScreen() {
        initialize();
    }

    private void initialize() {
        setTitle("通用配置页面");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6, 6, 6, 6);

        // Add components to the panel
        // 添加标题标签
        JLabel titleLabel = new JLabel("通用配置");
        Font titleFont = FileControl.getFont(Font.BOLD, 26);
        titleLabel.setFont(titleFont);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 6;
        panel.add(titleLabel, constraints);

        JLabel titleLabel2 = new JLabel("部分配置项需要重启程序以生效");
        titleFont = FileControl.getFont(Font.BOLD, 20);
        titleLabel2.setFont(titleFont);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(titleLabel2, constraints);

        Font infoFont = FileControl.getFont(Font.BOLD, 16);
        Font inputFont = FileControl.getFont(Font.PLAIN, 15);
        JLabel clockFontSizeLabel = new JLabel("时钟字体大小:");
        clockFontSizeLabel.setFont(infoFont);
        clockFontSizeField = new JTextField(10);
        clockFontSizeField.setText(String.valueOf(config.getClockFontsize()));
        clockFontSizeField.setFont(inputFont);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(clockFontSizeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(clockFontSizeField, constraints);

        // Lesson Font Size
        JLabel lessonFontSizeLabel = new JLabel("课表字体大小:");
        lessonFontSizeLabel.setFont(infoFont);
        lessonFontSizeField = new JTextField(10);
        lessonFontSizeField.setText(String.valueOf(config.getLessonFontSize()));
        lessonFontSizeField.setFont(inputFont);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lessonFontSizeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(lessonFontSizeField, constraints);

        // Notice Font Size
        JLabel noticeFontSizeLabel = new JLabel("公告板字体大小:");
        noticeFontSizeLabel.setFont(infoFont);
        noticeFontSizeField = new JTextField(10);
        noticeFontSizeField.setText(String.valueOf(config.getNoticeFontSize()));
        noticeFontSizeField.setFont(inputFont);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(noticeFontSizeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(noticeFontSizeField, constraints);

        // Enable Notice
        JLabel enableNoticeLabel = new JLabel("启用公告板:");
        enableNoticeLabel.setFont(infoFont);
        enableNoticeCheckbox = new JCheckBox();
        enableNoticeCheckbox.setSelected(config.isEnableNotice());
        enableNoticeCheckbox.setFont(inputFont);
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(enableNoticeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(enableNoticeCheckbox, constraints);

        // Notice Weight
        JLabel noticeWeightLabel = new JLabel("公告板宽度:");
        noticeWeightLabel.setFont(infoFont);
        noticeWeightField = new JTextField(10);
        noticeWeightField.setText(String.valueOf(config.getNoticeWeight()));
        noticeWeightField.setFont(inputFont);
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(noticeWeightLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(noticeWeightField, constraints);

        // Notice Height
        JLabel noticeHeightLabel = new JLabel("公告板高度:");
        noticeHeightLabel.setFont(infoFont);
        noticeHeightField = new JTextField(10);
        noticeHeightField.setText(String.valueOf(config.getNoticeHeight()));
        noticeHeightField.setFont(inputFont);
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(noticeHeightLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(noticeHeightField, constraints);

        // posx
        JLabel posxLabel = new JLabel("主窗口坐标X:");
        posxLabel.setFont(infoFont);
        posxField = new JTextField(10);
        posxField.setText(String.valueOf(config.getPosx()));
        posxField.setFont(inputFont);
        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(posxLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 2;
        panel.add(posxField, constraints);

        // posy
        JLabel posyLabel = new JLabel("主窗口坐标Y:");
        posyLabel.setFont(infoFont);
        posyField = new JTextField(10);
        posyField.setText(String.valueOf(config.getPosy()));
        posyField.setFont(inputFont);
        constraints.gridx = 2;
        constraints.gridy = 3;
        panel.add(posyLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 3;
        panel.add(posyField, constraints);

        JLabel weightLabel = new JLabel("主窗口宽度:");
        weightLabel.setFont(infoFont);
        weightField = new JTextField(10);
        weightField.setText(String.valueOf(config.getWeight()));
        weightField.setFont(inputFont);
        constraints.gridx = 2;
        constraints.gridy = 4;
        panel.add(weightLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 4;
        panel.add(weightField, constraints);

        JLabel heightLabel = new JLabel("主窗口高度:");
        heightLabel.setFont(infoFont);
        heightField = new JTextField(10);
        heightField.setText(String.valueOf(config.getHeight()));
        heightField.setFont(inputFont);
        constraints.gridx = 2;
        constraints.gridy = 5;
        panel.add(heightLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 5;
        panel.add(heightField, constraints);

        // Enable Notice
        JLabel enablePoemLabel = new JLabel("启用诗词推荐:");
        enablePoemLabel.setFont(infoFont);
        enablePoemCheckbox = new JCheckBox();
        enablePoemCheckbox.setSelected(config.isEnablePoemSuggestion());
        constraints.gridx = 2;
        constraints.gridy = 6;
        panel.add(enablePoemLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 6;
        panel.add(enablePoemCheckbox, constraints);

        JLabel enableCountdownLabel = new JLabel("启用倒计时:");
        enableCountdownLabel.setFont(infoFont);
        enableCountdownCheckbox = new JCheckBox();
        enableCountdownCheckbox.setSelected(config.isEnableCountDown());
        constraints.gridx = 4;
        constraints.gridy = 2;
        panel.add(enableCountdownLabel, constraints);
        constraints.gridx = 5;
        constraints.gridy = 2;
        panel.add(enableCountdownCheckbox, constraints);

        JLabel countDownYearLabel = new JLabel("倒计时年份:");
        countDownYearLabel.setFont(infoFont);
        countDownYearField = new JTextField(10);
        countDownYearField.setText(String.valueOf(config.getCountDownYear()));
        countDownYearField.setFont(inputFont);
        constraints.gridx = 4;
        constraints.gridy = 3;
        panel.add(countDownYearLabel, constraints);
        constraints.gridx = 5;
        constraints.gridy = 3;
        panel.add(countDownYearField, constraints);

        JLabel countDownMonthLabel = new JLabel("倒计时月份:");
        countDownMonthLabel.setFont(infoFont);
        countDownMonthField = new JTextField(10);
        countDownMonthField.setText(String.valueOf(config.getCountDownMonth()));
        countDownMonthField.setFont(inputFont);
        constraints.gridx = 4;
        constraints.gridy = 4;
        panel.add(countDownMonthLabel, constraints);
        constraints.gridx = 5;
        constraints.gridy = 4;
        panel.add(countDownMonthField, constraints);

        JLabel countDownDateLabel = new JLabel("倒计时日期:");
        countDownDateLabel.setFont(infoFont);
        countDownDateField = new JTextField(10);
        countDownDateField.setText(String.valueOf(config.getCountDownDate()));
        countDownDateField.setFont(inputFont);
        constraints.gridx = 4;
        constraints.gridy = 5;
        panel.add(countDownDateLabel, constraints);
        constraints.gridx = 5;
        constraints.gridy = 5;
        panel.add(countDownDateField, constraints);

        JLabel countDownInfoLabel = new JLabel("倒计时信息:");
        countDownInfoLabel.setFont(infoFont);
        countDownTextField = new JTextField(10);
        countDownTextField.setText(String.valueOf(config.getCountDownInfo()));
        countDownTextField.setFont(inputFont);
        constraints.gridx = 4;
        constraints.gridy = 6;
        panel.add(countDownInfoLabel, constraints);
        constraints.gridx = 5;
        constraints.gridy = 6;
        panel.add(countDownTextField, constraints);

        JLabel suppressWarningsLabel = new JLabel("忽略警告:");
        suppressWarningsLabel.setFont(infoFont);
        suppressWarningsCheckbox = new JCheckBox();
        suppressWarningsCheckbox.setSelected(config.isTemp_feature_notice());
        constraints.gridx = 4;
        constraints.gridy = 7;
        panel.add(suppressWarningsLabel, constraints);
        constraints.gridx = 5;
        constraints.gridy = 7;
        panel.add(suppressWarningsCheckbox, constraints);


        titleFont = FileControl.getFont(Font.BOLD,16);
        JButton saveButton = new JButton("不退出程序并保存配置");
        saveButton.setFont(titleFont);
        saveButton.addActionListener(e -> saveConfig(false));
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 3;
        panel.add(saveButton, constraints);

        JButton saveARButton = new JButton("退出程序并保存配置");
        saveARButton.setFont(titleFont);
        saveARButton.addActionListener(e -> saveConfig(true));

        constraints.gridx = 3;
        constraints.gridy = 8;
        constraints.gridwidth = 3;
        panel.add(saveARButton, constraints);

        // updateWindow
        add(panel);
        pack();
        setLocationRelativeTo(null);



    }

    private void saveConfig(boolean isRestart) {
        config.setClockFontsize(Integer.parseInt(clockFontSizeField.getText()));
        config.setLessonFontSize(Integer.parseInt(lessonFontSizeField.getText()));
        config.setNoticeFontSize(Integer.parseInt(noticeFontSizeField.getText()));
        config.setEnableNotice(enableNoticeCheckbox.isSelected());
        config.setNoticeWeight(Integer.parseInt(noticeWeightField.getText()));
        config.setNoticeHeight(Integer.parseInt(noticeHeightField.getText()));
        config.setPosx(Integer.parseInt(posxField.getText()));
        config.setPosy(Integer.parseInt(posyField.getText()));
        config.setWeight(Integer.parseInt(weightField.getText()));
        config.setHeight(Integer.parseInt(heightField.getText()));
        config.setEnablePoemSuggestion(enablePoemCheckbox.isSelected());
        config.setEnableCountDown(enableCountdownCheckbox.isSelected());
        config.setCountDownYear(Integer.parseInt(countDownYearField.getText()));
        config.setCountDownMonth(Integer.parseInt(countDownMonthField.getText()));
        config.setCountDownDate(Integer.parseInt(countDownDateField.getText()));
        config.setCountDownInfo(countDownTextField.getText());
        config.setTemp_feature_notice(suppressWarningsCheckbox.isSelected());

        FileControl.writeWindowConfigToJson(config);

        dispose(); // Close the config window

        if(isRestart){
            System.exit(0);
        }
    }
}
