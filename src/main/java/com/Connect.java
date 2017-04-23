package com;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Connect {
    private SerialPort serialPort;

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
}
