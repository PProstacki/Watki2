package wątki2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener {

    JButton bCalculate, bStopThreads;
    JTextField tfFactorialNumber;
    JLabel lOutcome, lIterationNanoseconds, lRecursionNanoseconds;
    RecursionThread rt;
    IterationThread it;
    Thread iteration, recursion;

    Window() {
        setTitle("Silnia na 2 sposoby");
        setLocation(500, 500);
        setSize(370, 150);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        tfFactorialNumber = new JTextField("Podaj liczbę:");
        tfFactorialNumber.setLocation(10, 10);
        tfFactorialNumber.setSize(220, 25);
        tfFactorialNumber.addActionListener(this);
        add(tfFactorialNumber);

        bCalculate = new JButton("Licz");
        bCalculate.setLocation(240, 10);
        bCalculate.setSize(115, 40);
        bCalculate.addActionListener(this);
        add(bCalculate);

        bStopThreads = new JButton("Przerwij");
        bStopThreads.setLocation(240, 60);
        bStopThreads.setSize(115, 40);
        bStopThreads.addActionListener(this);
        add(bStopThreads);

        lOutcome = new JLabel("Wynik:");
        lOutcome.setLocation(10, 45);
        lOutcome.setSize(220, 25);
        add(lOutcome);

        lIterationNanoseconds = new JLabel("Czas iteracji: ");
        lIterationNanoseconds.setLocation(10, 70);
        lIterationNanoseconds.setSize(220, 25);
        add(lIterationNanoseconds);

        lRecursionNanoseconds = new JLabel("Czas rekurencji: ");
        lRecursionNanoseconds.setLocation(10, 95);
        lRecursionNanoseconds.setSize(220, 25);
        add(lRecursionNanoseconds);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == bCalculate || source == tfFactorialNumber) {
            it = new IterationThread(Integer.parseInt(tfFactorialNumber.getText()));
            iteration = new Thread(it);
            rt = new RecursionThread(Integer.parseInt(tfFactorialNumber.getText()));
            recursion = new Thread(rt);

            iteration.start();
            recursion.start();

            while (iteration.isAlive() && recursion.isAlive()) {}

            if (!recursion.isAlive() && !recursion.isInterrupted()) {
                lRecursionNanoseconds.setText("Liczyło: " + rt.getNanoseconds() + " ns");
                lOutcome.setText("Wynik: " + rt.getOutcome());
            }
            
            if (!iteration.isAlive() && !iteration.isInterrupted()) {
                lIterationNanoseconds.setText("Liczyło: " + it.getNanoseconds() + " ns");
                lOutcome.setText("Wynik: " + it.getOutcome());
            }

            while (iteration.isAlive() || recursion.isAlive()) {}

            if (!recursion.isAlive() && !recursion.isInterrupted()) {
                lRecursionNanoseconds.setText("Liczyło: " + rt.getNanoseconds() + " ns");
            }
            
            if (!iteration.isAlive() && !iteration.isInterrupted()) {
                lIterationNanoseconds.setText("Liczyło: " + it.getNanoseconds() + " ns");
            }

        } else if (source == bStopThreads) {
            iteration.interrupt();
            recursion.interrupt();
            lOutcome.setText("Wynik: Przerwano");
        }
    }

}
