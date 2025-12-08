/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.frmlobby;

/**
 *
 * @Oscar Areiza
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class FrmLobby extends JFrame {

    private JPanel panelLista;  

    public FrmLobby() {
        setTitle("Rummy - Lobby");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

    
        // COLORES SEGÚN MOCKUP
    
        Color azulFondo = new Color(33, 150, 190);
        Color amarilloPanel = new Color(255, 237, 153);
        Color verdeBoton = new Color(67, 160, 71);
        Color rojoBoton = new Color(239, 83, 80);

        getContentPane().setBackground(azulFondo);

        //logo
        ImageIcon logo = new ImageIcon("RUTA/DEL/LOGO.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(20, 10, 150, 60);
        add(logoLabel);

        
        JPanel contenedor = new JPanel();
        contenedor.setBackground(amarilloPanel);
        contenedor.setLayout(null);
        contenedor.setBounds(120, 90, 460, 260);
        contenedor.setBorder(BorderFactory.createLineBorder(amarilloPanel, 18));
        add(contenedor);

     
        // PANEL LISTA DINÁMICA
        panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(amarilloPanel);

        JScrollPane scroll = new JScrollPane(panelLista);
        scroll.setBounds(30, 20, 400, 150);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(amarilloPanel);

        contenedor.add(scroll);

    
        // BOTÓN INICIAR
    
        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(160, 180, 140, 40);
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBackground(verdeBoton);
        btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnIniciar.setFocusPainted(false);
        btnIniciar.setBorder(BorderFactory.createEmptyBorder());
        contenedor.add(btnIniciar);

    
        // TEXTO "Esperando más jugadores..."
    
        JLabel lblEsperando = new JLabel("Esperando más jugadores...");
        lblEsperando.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblEsperando.setBounds(145, 220, 200, 20);
        contenedor.add(lblEsperando);

    
        // BOTÓN SALIR
    
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(560, 350, 90, 40);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(rojoBoton);
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(BorderFactory.createEmptyBorder());
        add(btnSalir);

    
        // AGREGAMOS UN JUGADOR QUEMADO
    
        addPlayer("Creador de partida", "RUTA/DEL/AVATAR.png");
    }


    // MÉTODO PARA AGREGAR OBJETOS A LA LISTA

    public void addPlayer(String nombre, String avatarRuta) {
        JPanel item = new JPanel();
        item.setLayout(new BorderLayout());
        item.setPreferredSize(new Dimension(380, 45));
        item.setMaximumSize(new Dimension(380, 45));

        item.setBackground(Color.WHITE);
        item.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        // Avatar
        JLabel avatar = new JLabel(new ImageIcon(avatarRuta));
        avatar.setPreferredSize(new Dimension(45, 45));
        item.add(avatar, BorderLayout.WEST);

        // Nombre
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        item.add(lblNombre, BorderLayout.CENTER);

        panelLista.add(item);
        panelLista.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmLobby().setVisible(true);
        });
    }
}

