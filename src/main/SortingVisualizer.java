package main;

import java.awt.*;
import java.util.Random;

public class SortingVisualizer implements Screen {
    private MainApp app;
    private int[] array;
    private SortStrategy algorithm;
    private boolean sorted = false;
    private Thread sortThread;

    public SortingVisualizer(MainApp app, SortStrategy algorithm) {
        this.app = app;
        this.algorithm = algorithm;

        generateArray();

        sortThread = new Thread(() -> {
            try {
                algorithm.sort(array, this::repaint);
                sorted = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        sortThread.start();
    }

    private void generateArray() {
        array = new int[100];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(MainApp.HEIGHT - 100);
        }
    }

    private void repaint() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {}
    }

    @Override
    public void update() {
        // no-op
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MainApp.WIDTH, MainApp.HEIGHT);

        for (int i = 0; i < array.length; i++) {
            int height = array[i];
            g.setColor(Color.CYAN);
            g.fillRect(i * (MainApp.WIDTH / array.length), MainApp.HEIGHT - height, (MainApp.WIDTH / array.length) - 2, height);
        }

        if (sorted) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Monospaced", Font.BOLD, 18));
            g.drawString("Sorted!", 350, 50);
        }
    }
}
