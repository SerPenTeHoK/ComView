package com;

import jssc.SerialPortList;

    /**
     * Запрос списка COM портов
     */

    class GetCom {
        private String[] portNames = SerialPortList.getPortNames();

        String[] getCom(){
            return this.portNames;
        }
    }

