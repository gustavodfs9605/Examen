package com.example.examen_1;
 public class ExpReg {
    static public boolean validarString(String string) {
        return string.matches("[A-za-a _0-9]{1,15}");
    }

    static public boolean validarFloat(String string) {
        return string.matches("^\\d{1,3}(\\.?\\d)*(,\\d{1,2})?$");
    }
}
