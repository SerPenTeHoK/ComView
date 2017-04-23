package com;

import jssc.SerialPortList;

    /**
     * Запрос списка COM портов
     */

    public class GetCom {
        private String[] portNames = SerialPortList.getPortNames();

        public String[] getCom(){
            return this.portNames;
        }
    }

