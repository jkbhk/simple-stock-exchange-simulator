
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javafx.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulatorDisplay extends JFrame implements IDisplayable {

    private int price = 10;
    private JLabel testPriceIndicator = new JLabel("$" + price);
    private JButton btn = new JButton("run command");
    private JTextArea log = new JTextArea();
    private JTextField commandField = new JTextField();

    public SimulatorDisplay() {

        setSize(400, 400);
        setTitle("simulator");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5, 1));
        getContentPane().add(testPriceIndicator);

        getContentPane().add(log);
        log.setEditable(false);
        JScrollPane scrollpane = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollpane);

        getContentPane().add(commandField);
        getContentPane().add(btn);

        //pack();

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                //log.append("\nMarket Buy for FB at $" + price);
                //price++;
                //testPriceIndicator.setText("Current bid price for FB : $" + price);

                //log.append("\n" + commandField.getText());
                InputManager.instance.handleInput(commandField.getText());
                commandField.setText("");

            }
        });
    }


    @Override
    public void displayMessage(String message) {
        log.append("\n" + message);
        
    }

    @Override
    public void updatePrice(double amt) {
        
        if(amt > 0)
            testPriceIndicator.setForeground(Color.GREEN); 
        else
            testPriceIndicator.setForeground(Color.RED);

        testPriceIndicator.setText("$"+CounterManager.instance.getCounters().get("FB").getPrice());

    }
}
