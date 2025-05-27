package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends MouseAdapter implements Screen {
    private MainApp app;
    private boolean clicked = false;

    public MainMenu(MainApp app) {
        this.app = app;
        app.addMouseListener(this);
    }

    @Override
    public void update() {
        if (clicked) {
            System.out.println("Clicked! You could now switch to the visualizer screen.");
            // app.setScreen(new SortingVisualizer(app)); // placeholder
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MainApp.WIDTH, MainApp.HEIGHT);

        g.setColor(Color.GREEN);
        g.setFont(new Font("Monospaced", Font.BOLD, 28));
        g.drawString("Sorting Battleground", 220, 150);

        g.setFont(new Font("Monospaced", Font.PLAIN, 14));
        g.drawString("Click anywhere to begin...", 300, 300);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
    }
}
