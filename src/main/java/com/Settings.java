package com;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * GUI for Settings
 */

class Settings extends JDialog {

    private About about = new About(null,"О программе", true);
    private JLabel speedL = new JLabel("Скорость (бод/с):");
    private JLabel dataL = new JLabel("Размер кадра (бит):");
    private JLabel stopBitsL = new JLabel("Стоп биты (бит):");
    private JLabel parityL = new JLabel("Четность:");
    private JLabel langL = new JLabel("Язык интерфейса:");
    private JButton buttonSave = new JButton("Сохранить");
    private JButton buttonAbout = new JButton("О программе");

    public void setAbout(String title) {
        about.setTitle(title);
    }

    public void setSpeedL(String title) {
        speedL.setText(title);
    }

    public void setDataL(String title) {
        dataL.setText(title);
    }

    public void setStopBitsL(String title) {
        stopBitsL.setText(title);
    }

    public void setParityL(String title) {
        parityL.setText(title);
    }

    public void setLangL(String title) {
        langL.setText(title);
    }

    public void setButtonSave(String title) {
        buttonSave.setText(title);
    }

    public void setButtonAbout(String title) {
        buttonAbout.setText(title);
    }

    Settings(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        JComboBox <String> speed = new JComboBox<>(new String[]{"600","1200","4800","9600","14400","19200","38400","57600","115200","128000","256000"});
        speed.setSelectedIndex(3);
        speed.setBackground(Color.WHITE);
        panel.add(speedL);
        panel.add(speed, "wrap, pushX, growX");
        speed.addItemListener(e -> {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    Connect.setSpeed((String) e.getItem());
                }
        });

        JComboBox<String> dataBits = new JComboBox<>(new String[]{"5","6","7","8"});
        dataBits.setSelectedIndex(3);
        dataBits.setBackground(Color.WHITE);
        panel.add(dataL);
        panel.add(dataBits, "wrap, pushX, growX");
        dataBits.addItemListener(e -> {
                if (e.getStateChange()==ItemEvent.SELECTED){
                     Connect.setDataBits((String) e.getItem());
                }
        });

        JComboBox<String> stopBits = new JComboBox<>(new String[]{"1","1.5","2"});
        stopBits.setSelectedIndex(0);
        stopBits.setBackground(Color.WHITE);
        panel.add(stopBitsL);
        panel.add(stopBits, "wrap, pushX, growX");
        stopBits.addItemListener(e -> {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    if (e.getItem().equals("1")){Connect.setStopBits("1");}
                    else if (e.getItem().equals("1.5")){Connect.setStopBits("3");}
                    else if (e.getItem().equals("2")){Connect.setStopBits("2");}
                }
        });

        JComboBox<String> parity = new JComboBox<>(new String[]{"none","odd","even","mark","space"});
        parity.setSelectedIndex(0);
        parity.setBackground(Color.WHITE);
        panel.add(parityL);
        panel.add(parity, "wrap, pushX, growX");
        parity.addItemListener(e -> {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    if (e.getItem().equals("none")){Connect.setParity("0");}
                    else if (e.getItem().equals("odd")){Connect.setParity("1");}
                    else if (e.getItem().equals("even")){Connect.setParity("2");}
                    else if (e.getItem().equals("mark")){Connect.setParity("3");}
                    else if (e.getItem().equals("space")){Connect.setParity("4");}
                }
        });

        JComboBox<String> lang = new JComboBox<>(new String[]{"Русский", "English"});
        lang.setEnabled(false);
        panel.add(langL);
        panel.add(lang, "wrap, pushX, growX");
        lang.addItemListener(e -> {        });

        buttonSave.setMaximumSize(new Dimension(120,22));
        buttonSave.setMinimumSize(new Dimension(120,22));
        panel.add(buttonSave, "pushY, bottom");
        buttonSave.addActionListener(e -> super.setVisible(false));

        buttonAbout.setMaximumSize(new Dimension(120,22));
        buttonAbout.setMinimumSize(new Dimension(120,22));
        panel.add(buttonAbout, "pushY, bottom, right");
        buttonAbout.addActionListener(e -> about.showAbout());

        super.getContentPane().add(panel);
        super.setPreferredSize(new Dimension(320,240));
        super.setBounds(100,300,100,100);
        super.setResizable(false);
        super.setLocationRelativeTo(GUICom.frame);
        super.pack();
    }

    void showSetting(){super.setVisible(true);}
}
