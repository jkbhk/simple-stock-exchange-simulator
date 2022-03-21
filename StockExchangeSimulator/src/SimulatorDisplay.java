
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javafx.event.ActionEvent;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class SimulatorDisplay extends JFrame {

    private int price = 10;
    private JLabel testPriceIndicator = new JLabel("Current bid price for FB : $" + price);
    private JButton btn = new JButton("Market Buy");
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

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                commandField.setText("");
                // log.setText(log.getText() + "\nMarket Buy for FB at $" + price);
                log.append("\nMarket Buy for FB at $" + price);
                // log.setCaretPosition(log.getText().length());
                price++;
                testPriceIndicator.setText("Current bid price for FB : $" + price);

            }
        });
    }

}
