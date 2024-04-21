/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.daw.calculadora2;

/**
 *
 * @author aspi_
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora2 extends JFrame implements ActionListener {
    private JTextField pantalla;
    private JButton[] botonesNumeros;
    private JButton[] botonesOperadores;
    private JPanel panelNumeros, panelOperadores;
    private String operador;
    private double numero1, numero2, resultado;

    public Calculadora2() {
        pantalla = new JTextField("0", 20);
        pantalla.setEditable(false);

        botonesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botonesNumeros[i] = new JButton(String.valueOf(i));
            botonesNumeros[i].addActionListener(this);
        }

        botonesOperadores = new JButton[5];
        String[] operadores = {"+", "-", "*", "/", "="};
        for (int i = 0; i < 5; i++) {
            botonesOperadores[i] = new JButton(operadores[i]);
            botonesOperadores[i].addActionListener(this);
        }

        panelNumeros = new JPanel();
        panelNumeros.setLayout(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            panelNumeros.add(botonesNumeros[i]);
        }
        panelNumeros.add(botonesNumeros[0]);

        panelOperadores = new JPanel();
        panelOperadores.setLayout(new GridLayout(5, 1));
        for (int i = 0; i < 5; i++) {
            panelOperadores.add(botonesOperadores[i]);
        }

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(pantalla, BorderLayout.NORTH);
        panelPrincipal.add(panelNumeros, BorderLayout.CENTER);
        panelPrincipal.add(panelOperadores, BorderLayout.EAST);

        add(panelPrincipal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setSize(300, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            operador = comando;
            numero1 = Double.parseDouble(pantalla.getText());
            pantalla.setText("");
        } else if (comando.equals("=")) {
            numero2 = Double.parseDouble(pantalla.getText());
            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0)
                        resultado = numero1 / numero2;
                    else
                        pantalla.setText("Error");
                    break;
            }
            pantalla.setText(String.valueOf(resultado));
        } else {
            String textoPantalla = pantalla.getText();
            if (textoPantalla.equals("0"))
                pantalla.setText(comando);
            else
                pantalla.setText(textoPantalla + comando);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora2();
            }
        });
    }
}

