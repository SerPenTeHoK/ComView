package com;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * GUI for Settings
 */
public class Settings extends JDialog {
    protected static JDialog settings = new JDialog(GUICom.frame,"Настройки", true);

    protected static void showSetting(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        final String[] speedList = {"600","1200","4800","9600","14400","19200","38400","57600","115200","128000","256000"};
        JLabel speedL = new JLabel("Скорость (бод/с):");
        JComboBox speed = new JComboBox(speedList);
        speed.setSelectedIndex(3);
        speed.setBackground(Color.WHITE);
        panel.add(speedL);
        panel.add(speed, "wrap, pushX, growX");
        speed.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    Connect.setSpeed((String) e.getItem());
                }
            }
        });

        final String[] dataBitsList = {"5","6","7","8"};
        JLabel dataL = new JLabel("Размер кадра (бит):");
        JComboBox dataBits = new JComboBox(dataBitsList);
        dataBits.setSelectedIndex(3);
        dataBits.setBackground(Color.WHITE);
        panel.add(dataL);
        panel.add(dataBits, "wrap, pushX, growX");
        dataBits.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                     Connect.setDataBits((String) e.getItem());
                }
            }
        });

        final String[] stopBitsList = {"1","1.5","2"};
        JLabel stopBitsL = new JLabel("Стоп биты (бит):");
        JComboBox stopBits = new JComboBox(stopBitsList);
        stopBits.setSelectedIndex(0);
        stopBits.setBackground(Color.WHITE);
        panel.add(stopBitsL);
        panel.add(stopBits, "wrap, pushX, growX");
        stopBits.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    if (e.getItem().equals("1")){Connect.setStopBits("1");}
                    else if (e.getItem().equals("1.5")){Connect.setStopBits("3");}
                    else if (e.getItem().equals("2")){Connect.setStopBits("2");}
                }
            }
        });

        final String[] parityList = {"none","odd","even","mark","space"};
        JLabel parityL = new JLabel("Четность:");
        JComboBox parity = new JComboBox(parityList);
        parity.setSelectedIndex(0);
        parity.setBackground(Color.WHITE);
        panel.add(parityL);
        panel.add(parity, "wrap, pushX, growX");
        parity.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    if (e.getItem().equals("none")){Connect.setParity("0");}
                    else if (e.getItem().equals("odd")){Connect.setParity("1");}
                    else if (e.getItem().equals("even")){Connect.setParity("2");}
                    else if (e.getItem().equals("mark")){Connect.setParity("3");}
                    else if (e.getItem().equals("space")){Connect.setParity("4");}
                }
            }
        });

        final String[] langList = {"Русский", "English"};
        JLabel langL = new JLabel("Язык интерфейса:");
        JComboBox lang = new JComboBox(langList);
        lang.setEnabled(false);
        panel.add(langL);
        panel.add(lang, "wrap, pushX, growX");
        lang.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

            }
        });

        JButton buttonSave = new JButton("Сохранить");
        buttonSave.setMaximumSize(new Dimension(120,22));
        buttonSave.setMinimumSize(new Dimension(120,22));
        panel.add(buttonSave, "pushY, bottom");
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(false);
            }
        });

        JButton buttonAbout = new JButton("О программе");
        buttonAbout.setMaximumSize(new Dimension(120,22));
        buttonAbout.setMinimumSize(new Dimension(120,22));
        panel.add(buttonAbout, "pushY, bottom, right");
        buttonAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                About.showAbout();
            }
        });

        settings.getContentPane().add(panel);
        settings.setPreferredSize(new Dimension(320,240));
        settings.setBounds(100,300,100,100);
        settings.setResizable(false);
        settings.setLocationRelativeTo(GUICom.frame);
        settings.pack();
        settings.setVisible(true);
    }
}
