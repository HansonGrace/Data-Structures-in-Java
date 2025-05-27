package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class MainApp extends Canvas implements Runnable {
    private JFrame frame;
    private boolean running = false;
    private Screen currentScreen;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static void main(String[] args) {
        new MainApp().start();
    }

    public void start() {
        frame = new JFrame("Sorting Battleground");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.currentScreen = new MainMenu(this);
        running = true;
        new Thread(this).start();
    }

    public void run() {
        this.createBufferStrategy(3);
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g;

        while (running) {
            currentScreen.update();

            g = bs.getDrawGraphics();
            currentScreen.render(g);
            g.dispose();
            bs.show();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setScreen(Screen screen) {
        this.currentScreen = screen;
    }
}
