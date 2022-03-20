
import javax.swing.*;

import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class SimulatorDisplay extends JFrame {
    
    private int price = 10;
    private JLabel testPriceIndicator = new JLabel("$" + price);
    private JButton btn = new JButton("Market Buy");
    private JTextField commandField = new JTextField();

    public SimulatorDisplay(){
        setSize(400, 400); 
        setTitle("simulator");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5,1));
        getContentPane().add(testPriceIndicator);
        getContentPane().add(commandField);
        getContentPane().add(btn);
        pack();

        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                commandField.setText("");
                price++;
                testPriceIndicator.setText("$"+price);
                
            }
        });
    }


}
