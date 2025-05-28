package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends MouseAdapter implements Screen {
    private MainApp app;

    private Rectangle bubbleButton = new Rectangle(300, 300, 200, 40);
    private Rectangle mergeButton = new Rectangle(300, 360, 200, 40);
    private Rectangle insertionButton = new Rectangle(300, 420, 200, 40);

    private boolean hoverBubble = false;
    private boolean hoverMerge = false;
    private boolean hoverInsertion = false;

    public MainMenu(MainApp app) {
        this.app = app;
        app.addMouseListener(this);
        app.addMouseMotionListener(this);
    }

    @Override
    public void update() {
        // No-op
    }

    @Override
    public void render(Graphics g) {
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MainApp.WIDTH, MainApp.HEIGHT);

        // Title
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 36));
        String title = "SORTING BATTLE GROUND";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (MainApp.WIDTH - titleWidth) / 2, 100);

        // Author name just below the title
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));
        String credit = "Created by Grace Hanson";
        int creditWidth = g.getFontMetrics().stringWidth(credit);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(credit, (MainApp.WIDTH - creditWidth) / 2, 130);

        // Sort buttons with hover effects
        g.setFont(new Font("Monospaced", Font.PLAIN, 16));

        g.setColor(hoverBubble ? Color.WHITE : Color.LIGHT_GRAY);
        g.fillRect(bubbleButton.x, bubbleButton.y, bubbleButton.width, bubbleButton.height);

        g.setColor(hoverMerge ? Color.WHITE : Color.LIGHT_GRAY);
        g.fillRect(mergeButton.x, mergeButton.y, mergeButton.width, mergeButton.height);

        g.setColor(hoverInsertion ? Color.WHITE : Color.LIGHT_GRAY);
        g.fillRect(insertionButton.x, insertionButton.y, insertionButton.width, insertionButton.height);

        // Button labels
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
        } else if (mergeButton.contains(p)) {
            app.setScreen(new SortingVisualizer(app, new MergeSort()));
        } 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        hoverBubble = bubbleButton.contains(p);
        hoverMerge = mergeButton.contains(p);
        hoverInsertion = insertionButton.contains(p);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used
    }
}

