package clientSide.gui;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import clientSide.Client;

public class MenuWindow extends JFrame {
    private Client client;
    private JPanel mainPanel;

    public MenuWindow(Client client) {
        this.client = client;
        mainPanel = new JPanel();
        createButtons();
        setMenuPanelAttributes();
        setMenuWindowAttributes();
    }

    private void createButtons() {
        JButton joinButton = new JButton("JOIN THE MADNESS");

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGame();
            }
        });

        mainPanel.add(joinButton);
    }

    private void setMenuPanelAttributes() {
        mainPanel.setPreferredSize(new Dimension(200, 50));
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));
    }

    private void setMenuWindowAttributes() {
        setTitle("Killing Frenzy!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setSize(100, 100);
        pack();
    }

    private void createGame() {
        String ipAddr = JOptionPane.showInputDialog("Please enter the IP address of the host:", "localhost");
        if (ipAddr != null && (!ipAddr.equals(""))) {
            client.createGame(ipAddr);
        }
    }
}
