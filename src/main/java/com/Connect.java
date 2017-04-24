package com;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Connect {
    private static SerialPort serialPort;

    public Connect(String selectedCom) {
        serialPort = new SerialPort(selectedCom);
    }

    public void openConnect() {
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
        }
        catch (SerialPortException ex) {
            System.err.println(ex);
        }
    }

    public void closeConnect(){
        try{
            serialPort.closePort();
        }
        catch (SerialPortException ex){
            System.err.println(ex);
        }
    }

    public void sendCom(String data){
        try{
            serialPort.writeBytes(data.getBytes());
        }
        catch (SerialPortException ex){
            System.err.println(ex);
        }
    }

    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue());
                    Terminal.writeTerminal(data);
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
