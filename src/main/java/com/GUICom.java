package com;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Графический интерфейс
 */
public class GUICom {
    private GetCom getCom = new GetCom();
    private String[] comL = getCom.getCom();
    private Connect com;
    private String selectedCom;
    private JFrame frame = new JFrame("Wise Control");
    private JPanel panel = new JPanel();
    private JComboBox comList = new JComboBox(comL);
    private JTextField textField = new JTextField(15);
    private JButton buttonSend = new JButton("Отправить");
    private JButton buttonConnect = new JButton("Соединить");
    private JCheckBox checkBox = new JCheckBox("+\\r");

    private GUICom() {
        //******************************* Общее для фрейма **********************************************
        JFrame.setDefaultLookAndFeelDecorated(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            URL resource = frame.getClass().getResource("/images/bt3.png");
            BufferedImage image = ImageIO.read(resource);
            frame.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.setLayout(new MigLayout());
        panel.setBorder(new TitledBorder("Список доступных COM портов")); // рамка панели с текстом


        //******************************* Выпадающее меню ***********************************************
        comList.setToolTipText("Выберите COM");
        comList.setFont(new Font("Arial", Font.BOLD, 12));
        //comList.setMinimumSize(new Dimension(150,22));
        comList.setSelectedIndex(-1); // индекс элемента JComboBox по умолчанию при запуске программы
        comList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    selectedCom = (String) e.getItem();
                }
            }
        });

        //******************************* Блок кнопок ****************************************************
        buttonSend.setMaximumSize(new Dimension(120,22));
        buttonSend.setMinimumSize(new Dimension(120,22));
        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("Введите команду")){
                    textField.setText("Введите команду");
                }
                else if (checkBox.isSelected()){
                    com.sendCom(textField.getText() + "\r");
                    textField.setText("Введите команду");}
                else {
                    com.sendCom(textField.getText());
                    textField.setText("Введите команду");}

            }
        });
        buttonConnect.setMaximumSize(new Dimension(120,22));
        buttonConnect.setMinimumSize(new Dimension(120,22));
        buttonConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedCom.equals("")){buttonConnect.setText("Соединить");}
                else if (buttonConnect.getText().equals("Соединить")){
                    com = new Connect(selectedCom);
                    com.openConnect();
                    buttonConnect.setText("Разъединить");}
                else {
                    com.closeConnect();
                    buttonConnect.setText("Соединить");
                }
            }
        });

        //******************************* Текстовые поля **************************************************
        textField.setText("Введите команду");
        textField.setFont(new Font("Arial", Font.ITALIC, 12));
        textField.setMinimumSize(new Dimension(150,24));
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }
            public void focusLost(FocusEvent e) {}
        });

        //******************************* Размещение на панели ********************************************
        panel.add(comList, "span 1, growX");                         // добавление JComboBox на панель
        panel.add(buttonConnect, "wrap, pushX");
        panel.add(textField, "pushX, growX");
        panel.add(buttonSend, "wrap, pushX");
        panel.add(checkBox);

        //******************************* Компоновка фрейма ************************************************
        frame.getContentPane().add(panel);                            // добавление панели "panel" на фрейм
        frame.setPreferredSize(new Dimension(320, 150)); // размер окна при запуске программы
        frame.setMinimumSize(new Dimension(320, 150));   // минимальный размер окна
        frame.pack();                                                 // автоматическая подстройка всех компонентов окна
        frame.setLocationRelativeTo(null);                            // размещение окна по центру при запуске программы
        frame.setVisible(true);                                       // сделать окно видимым
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICom();
            }
        });
    }
}