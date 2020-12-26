package view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerExample extends JFrame {
    private Timer timer;

    public TimerExample() {
        JLabel label = new JLabel("5000");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);

        JButton button1 = new JButton("Start");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("5000");
                timer = new Timer(100, new ActionListener() {
                    private int count = 5000;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count <= 0) {
                            ((Timer) e.getSource()).stop();
                        } else {
                            count -= 100;
                        }
                        label.setText(Integer.toString(count));
                    }

                });
                timer.start();
            }
        });

        JButton button2 = new JButton("Stop");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        JButton button3 = new JButton("Continue");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        add(panel, BorderLayout.LINE_START);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerExample frame = new TimerExample();
            }
        });
    }
}