package clientSide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import packets.KeyPressPacket;

class Main extends JFrame {
    JPanel p1, p2;
    Dimension d;
    Client client;
    public Main() {
        client = new Client();
        new Thread(client).start();
        createAndShowGUI();
    }
    
    private void createAndShowGUI() {
        setTitle("Killing Frenzy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setLayout(new FlowLayout());
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(500, 500));
        p1.setBackground(Color.GRAY);
        p1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));
        
        JButton okButton = new JButton("JOIN THE MADNESS");
        p1.add(okButton);
        add(p1);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	client.sendPacket(new KeyPressPacket());
                new GameClientWindow();
            }
        });
        
        setSize(500, 500);
        setVisible(true);
        pack();
    }
    
    public static void main(String args[]) {
        new Main();
    }
}