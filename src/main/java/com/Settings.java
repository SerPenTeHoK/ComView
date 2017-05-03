package com;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * GUI for Settings
 */

class Settings extends JDialog {
    static JDialog settings = new JDialog(GUICom.frame,"Настройки", true);

    static void showSetting(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        JLabel speedL = new JLabel("Скорость (бод/с):");
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

        JLabel dataL = new JLabel("Размер кадра (бит):");
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

        JLabel stopBitsL = new JLabel("Стоп биты (бит):");
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

        JLabel parityL = new JLabel("Четность:");
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

        JLabel langL = new JLabel("Язык интерфейса:");
        JComboBox<String> lang = new JComboBox<>(new String[]{"Русский", "English"});
        lang.setEnabled(false);
        panel.add(langL);
        panel.add(lang, "wrap, pushX, growX");
        lang.addItemListener(e -> {        });

        JButton buttonSave = new JButton("Сохранить");
        buttonSave.setMaximumSize(new Dimension(120,22));
        buttonSave.setMinimumSize(new Dimension(120,22));
        panel.add(buttonSave, "pushY, bottom");
        buttonSave.addActionListener(e -> settings.setVisible(false));

        JButton buttonAbout = new JButton("О программе");
        buttonAbout.setMaximumSize(new Dimension(120,22));
        buttonAbout.setMinimumSize(new Dimension(120,22));
        panel.add(buttonAbout, "pushY, bottom, right");
        buttonAbout.addActionListener(e -> About.showAbout());

        settings.getContentPane().add(panel);
        settings.setPreferredSize(new Dimension(320,240));
        settings.setBounds(100,300,100,100);
        settings.setResizable(false);
        settings.setLocationRelativeTo(GUICom.frame);
        settings.pack();
        settings.setVisible(true);
    }
}
