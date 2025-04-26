package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    // Game constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int CATCHER_WIDTH = 100;
    private static final int CATCHER_HEIGHT = 20;
    private static final int TRASH_SIZE = 30;
    private static final int CATCHER_SPEED = 10;
    
    // Game objects
    private int catcherX;
    private int trashX, trashY;
    private int score;
    private boolean gameRunning;
    private final Timer timer;
    private final Random random;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public GamePanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
        // Initialize game state
        random = new Random();
        catcherX = WIDTH / 2 - CATCHER_WIDTH / 2;
        spawnTrash();
        score = 0;
        gameRunning = true;
        
        // Set up timer (60 FPS)
        timer = new Timer(1000/60, this);
        timer.start();
        
        // Set up panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        
        // Keyboard controls
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    catcherX = Math.max(0, catcherX - CATCHER_SPEED);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    catcherX = Math.min(WIDTH - CATCHER_WIDTH, catcherX + CATCHER_SPEED);
                }
            }
        });
    }

    private void spawnTrash() {
        trashX = random.nextInt(WIDTH - TRASH_SIZE);
        trashY = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw catcher (rectangle)
        g.setColor(Color.BLUE);
        g.fillRect(catcherX, HEIGHT - CATCHER_HEIGHT - 10, CATCHER_WIDTH, CATCHER_HEIGHT);
        
        // Draw trash (rectangle)
        g.setColor(Color.GREEN);
        g.fillRect(trashX, trashY, TRASH_SIZE, TRASH_SIZE);
        
        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameRunning) return;
        
        // Move trash down
        trashY += 5;
        
        // Check if caught
        if (trashY + TRASH_SIZE > HEIGHT - CATCHER_HEIGHT - 10 && 
            trashX + TRASH_SIZE > catcherX && 
            trashX < catcherX + CATCHER_WIDTH) {
            score++;
            spawnTrash();
        }
        
        // Check if missed
        if (trashY > HEIGHT) {
            spawnTrash();
        }
        
        repaint();
    }
    
    public void resetGame() {
        catcherX = WIDTH / 2 - CATCHER_WIDTH / 2;
        spawnTrash();
        score = 0;
        gameRunning = true;
        if (!timer.isRunning()) {
            timer.start();
        }
        requestFocusInWindow();
    }
}