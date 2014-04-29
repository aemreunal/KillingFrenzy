package client;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Main extends JFrame
{
JPanel p1,p2;
Dimension d;

    public Main()
    {
        createAndShowGUI();
    }
    
    private void createAndShowGUI()
    {
        setTitle("Killing Frenzy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
        p1=new JPanel();
        p1.setPreferredSize(new Dimension(500,500));
        p1.setBackground(Color.GRAY);
        p1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,5,true));
        
        JButton okButton = new JButton("JOIN THE MADNESS"); 
        p1.add(okButton);
        add(p1);
        
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }          
         });
        
        setSize(500,500);
        setVisible(true);
        pack();      
    }
    
    public static void main(String args[])
    {
        new Main();
    }
}