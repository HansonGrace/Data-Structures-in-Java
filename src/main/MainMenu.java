package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends MouseAdapter implements Screen {
    private MainApp app;

    private Rectangle bubbleButton, mergeButton, insertionButton, selectionButton, quickButton, gnomeButton;

    public MainMenu(MainApp app) {
        this.app = app;
        bubbleButton = new Rectangle(300, 220, 200, 35);
        mergeButton = new Rectangle(300, 270, 200, 35);
        insertionButton = new Rectangle(300, 320, 200, 35);
        selectionButton = new Rectangle(300, 370, 200, 35);
        quickButton = new Rectangle(300, 420, 200, 35);
        gnomeButton = new Rectangle(300, 470, 200, 35);
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

        // Title (kept white as requested)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 36));
        String title = "SORTING BATTLE GROUND";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (MainApp.WIDTH - titleWidth) / 2, 100);

        // Author credit
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));
        String credit = "Created by Grace Hanson";
        int creditWidth = g.getFontMetrics().stringWidth(credit);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(credit, (MainApp.WIDTH - creditWidth) / 2, 130);

        // Buttons
        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        Point p = MouseInfo.getPointerInfo().getLocation();

        drawButton(g, bubbleButton, "Bubble Sort", p);
        drawButton(g, mergeButton, "Merge Sort", p);
        drawButton(g, insertionButton, "Insertion Sort", p);
        drawButton(g, selectionButton, "Selection Sort", p);
        drawButton(g, quickButton, "Quick Sort", p);
        drawButton(g, gnomeButton, "Gnome Sort", p);
    }

    private void drawButton(Graphics g, Rectangle button, String text, Point p) {
        Point frameOffset = app.getLocationOnScreen();
        Point relative = new Point(p.x - frameOffset.x, p.y - frameOffset.y);

        g.setColor(button.contains(relative) ? Color.WHITE : Color.LIGHT_GRAY);
        g.fillRect(button.x, button.y, button.width, button.height);
        g.setColor(Color.BLACK);
        g.drawString(text, button.x + 35, button.y + 23);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();

        if (bubbleButton.contains(p)) {
            app.setScreen(new SortingVisualizer(app, new BubbleSort()));
        } else if (mergeButton.contains(p)) {
            app.setScreen(new SortingVisualizer(app, new MergeSort()));
        } else if (insertionButton.contains(p)) {
            app.setScreen(new SortingVisualizer(app, new InsertionSort()));
        }     }
}


