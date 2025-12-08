/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.frminicio;

/**
 *
 * @author 0scar Areiza
 */
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FrmInicio extends JFrame {

    private JPanel panelPrincipal;   // Panel que se va a actualizar dinámicamente

    public FrmInicio() {
        setTitle("Rummy - Inicio");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Color azulFondo = new Color(33, 150, 190);
        getContentPane().setBackground(azulFondo);

        mostrarPantallaInicio();
    }

    // ============================================================
    //  PANTALLA INICIAL
    // ============================================================
    private void mostrarPantallaInicio() {

        if (panelPrincipal != null) {
            remove(panelPrincipal);
        }

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBounds(0, 0, 650, 420);
        add(panelPrincipal);

        Color amarilloPanel = new Color(255, 237, 153);
        Color azulBoton = new Color(30, 136, 229);
        Color rojoBoton = new Color(239, 83, 80);

        // LOGO
        ImageIcon logo = new ImageIcon("RUTA/DEL/LOGO.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(20, 15, 150, 60);
        panelPrincipal.add(logoLabel);

        // PANEL AMARILLO CENTRAL
        JPanel panel = new JPanel();
        panel.setBackground(amarilloPanel);
        panel.setBounds(60, 100, 520, 200);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(amarilloPanel, 20));
        panelPrincipal.add(panel);

        // BOTÓN CREAR PARTIDA
        JButton btnCrear = new JButton("Crear partida");
        btnCrear.setBounds(140, 40, 240, 40);
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setBackground(azulBoton);
        btnCrear.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCrear.setFocusPainted(false);
        btnCrear.setBorder(BorderFactory.createEmptyBorder());
        panel.add(btnCrear);

        // BOTÓN UNIRSE A PARTIDA
        JButton btnUnirse = new JButton("Unirse a partida");
        btnUnirse.setBounds(140, 110, 240, 40);
        btnUnirse.setForeground(Color.WHITE);
        btnUnirse.setBackground(rojoBoton);
        btnUnirse.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnUnirse.setFocusPainted(false);
        btnUnirse.setBorder(BorderFactory.createEmptyBorder());
        btnUnirse.addActionListener(e -> mostrarPantallaIP());
        panel.add(btnUnirse);

        revalidate();
        repaint();
    }

    // ============================================================
    //  PANTALLA DE INGRESO DE IP
    // ============================================================
    private void mostrarPantallaIP() {

        remove(panelPrincipal);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBounds(0, 0, 650, 420);
        add(panelPrincipal);

        Color amarilloPanel = new Color(255, 237, 153);
        Color azulBoton = new Color(30, 136, 229);
        Color rojoBoton = new Color(239, 83, 80);

        // LOGO
        ImageIcon logo = new ImageIcon("RUTA/DEL/LOGO.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(20, 15, 150, 60);
        panelPrincipal.add(logoLabel);

        // PANEL AMARILLO
        JPanel panel = new JPanel();
        panel.setBackground(amarilloPanel);
        panel.setBounds(60, 100, 520, 220);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(amarilloPanel, 20));
        panelPrincipal.add(panel);

        // TEXTO SUPERIOR
        JLabel lblTexto = new JLabel("Ingrese la dirección IP de partida", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTexto.setBounds(40, 10, 440, 40);
        panel.add(lblTexto);

        JLabel lblTexto2 = new JLabel("en red local (LAN):", SwingConstants.CENTER);
        lblTexto2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTexto2.setBounds(40, 40, 440, 40);
        panel.add(lblTexto2);

        // CAMPO DE IP
        JTextField txtIP = new JTextField("192.168.1.100");
        txtIP.setHorizontalAlignment(JTextField.CENTER);
        txtIP.setFont(new Font("Segoe UI", Font.BOLD, 20));
        txtIP.setBounds(130, 90, 260, 40);
        panel.add(txtIP);

        // BOTÓN CANCELAR
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(90, 150, 150, 40);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(rojoBoton);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder());
        btnCancelar.addActionListener(e -> mostrarPantallaInicio());
        panel.add(btnCancelar);

        // BOTÓN CONTINUAR
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(280, 150, 150, 40);
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBackground(azulBoton);
        btnContinuar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnContinuar.setFocusPainted(false);
        btnContinuar.setBorder(BorderFactory.createEmptyBorder());
        btnContinuar.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Esperando a ser aceptado...",
                    "Conectando",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // ========================================================
            // AQUÍ IRÁ LA LÓGICA DE COMUNICACIÓN CON EL SERVIDOR
            // Ejemplo:
            // Cliente.enviarSolicitudUnion(txtIP.getText());
            // ========================================================
        });
        panel.add(btnContinuar);

        revalidate();
        repaint();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmInicio().setVisible(true);
        });
    }
}


