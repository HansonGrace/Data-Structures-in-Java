package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends MouseAdapter implements Screen {
    private MainApp app;
    private Rectangle bubbleButton = new Rectangle(300, 250, 200, 40);
    private Rectangle mergeButton = new Rectangle(300, 310, 200, 40);
    private Rectangle insertionButton = new Rectangle(300, 370, 200, 40);

    public MainMenu(MainApp app) {
        this.app = app;
        app.addMouseListener(this);
    }

    @Override
    public void update() {
        // no-op
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MainApp.WIDTH, MainApp.HEIGHT);

        g.setColor(Color.GREEN);
        g.setFont(new Font("Monospaced", Font.BOLD, 28));
        g.drawString("Sorting Battleground", 220, 150);

        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(bubbleButton.x, bubbleButton.y, bubbleButton.width, bubbleButton.height);
        g.fillRect(mergeButton.x, mergeButton.y, mergeButton.width, mergeButton.height);
        g.fillRect(insertionButton.x, insertionButton.y, insertionButton.width, insertionButton.height);

        g.setColor(Color.BLACK);
        g.drawString("Bubble Sort", bubbleButton.x + 45, bubbleButton.y + 25);
        g.drawString("Merge Sort", mergeButton.x + 50, mergeButton.y + 25);
        g.drawString("Insertion Sort", insertionButton.x + 40, insertionButton.y + 25);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if (bubbleButton.contains(p)) {
            app.setScreen(new SortingVisualizer(app, new BubbleSort()));
    
        }
    }
}
