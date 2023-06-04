package Shapecraft;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CountdownTimer extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private int timeRemaining = 10; // Initial time in seconds
    private Timer timer;

    public CountdownTimer() {
        timer = new Timer(1000, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48)); // Increase the font size to 48
        g.drawString("Time Remaining: " + timeRemaining, 100, 50);

        if (timeRemaining <= 0) {
            timer.stop();
            gameOverScreen(g);
        }
    }

    public void gameOverScreen(Graphics g) {
        setBackground(Color.RED);
        setForeground(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 72)); // Increase the font size to 72
        g.drawString("Game Over!", 100, 150);
    }

    public void actionPerformed(ActionEvent e) {
        timeRemaining--;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Countdown Timer");

        // Create a parent container with a null layout
        JPanel parentContainer = new JPanel(null);
        frame.setContentPane(parentContainer);
    }
}
