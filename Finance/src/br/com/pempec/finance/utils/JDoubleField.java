package br.com.pempec.finance.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class JDoubleField extends JTextField {

    private String value = "000";

    public JDoubleField() {
        super();
        instalar();
        setText(instalarMask(value));
    }

    public JDoubleField(String text) {
        instalar();
        super.setText(instalarMask(text));

    }

    private synchronized void atualizar(char key) {
        if (key >= '0' && key <= '9') {
            value = value + key;
        } else if (key == '\b') {
            if (!value.equals("000") && value.length() > 1) {
                value = value.substring(0, value.length() - 1);
            }
        }
    }

    private synchronized String instalarMask(String value) {
        if (value.charAt(0) == '0') {
            value = value.substring(1, value.length());
        }

        while (value.length() < 3) {
            value = "0" + value;
        }


        this.value = value;
        return value.substring(0, value.length() - 2) + "," + value.substring(value.length() - 2, value.length());
    }

    private void instalar() {
        super.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        super.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                atualizar(e.getKeyChar());
                setText(instalarMask(value));
                e.consume();
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public double getValue() {
        String valor = getText().replace(',', '.');
        return Double.parseDouble(valor);
    }
}
