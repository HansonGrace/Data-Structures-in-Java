package main;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.function.IntConsumer;

public class SortingVisualizer extends MouseAdapter implements Screen, MouseMotionListener {
    private MainApp app;
    private int[] array;
    private SortStrategy algorithm;
    private boolean sorted = false;
    private boolean mute = false;
    private boolean cancelled = false;
    private MidiSoundPlayer sound;
    private Rectangle backButton;
    private Point mousePoint = new Point(); // for hover effect

    public SortingVisualizer(MainApp app, SortStrategy algorithm) {
        this.app = app;
        this.algorithm = algorithm;
        generateArray();

        backButton = new Rectangle(MainApp.WIDTH - 100, 20, 70, 30);

        app.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {
                    mute = !mute;
                    System.out.println("Sound " + (mute ? "muted" : "unmuted"));
                }
            }
        });

        app.addMouseListener(this);
        app.addMouseMotionListener(this); // for hover tracking

        Thread sortThread = new Thread(() -> {
            try {
                sound = new MidiSoundPlayer();

                algorithm.sort(
                    array,
                    () -> {
                        if (!cancelled) pause();
                    },
                    (val) -> {
                        if (!mute && !cancelled) {
                            sound.playNote(val % 88 + 30, 80, 10);
                        }
                    }
                );

                if (!cancelled) {
                    sound.close();
                    sorted = true;
                }

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

    private void pause() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void update() {}

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
            g.drawString("Sorted!", MainApp.WIDTH / 2 - 50, 50);
        }

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));
        g.drawString("Press 'M' to toggle sound: " + (mute ? "OFF" : "ON"), 20, 30);

        // BACK button with hover
        boolean hoveringBack = backButton.contains(mousePoint);
        g.setColor(hoveringBack ? Color.LIGHT_GRAY : Color.DARK_GRAY);
        g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
        g.setColor(Color.WHITE);
        g.drawString("BACK", backButton.x + 10, backButton.y + 20);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (backButton.contains(e.getPoint())) {
            cancelled = true;
            if (sound != null) sound.close();
            app.setScreen(new MainMenu(app));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}
}
