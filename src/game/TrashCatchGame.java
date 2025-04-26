package game;

import javax.swing.*;
import java.awt.*;

public class TrashCatchGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Trash Catch Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // Initialize panels
            MenuPanel menuPanel = new MenuPanel(cardLayout, cardPanel);
            GamePanel gamePanel = new GamePanel(cardLayout, cardPanel);
            GameOverPanel gameOverPanel = new GameOverPanel(cardLayout, cardPanel);

            // Add panels to card layout with String identifiers
            cardPanel.add(menuPanel, "Menu");
            cardPanel.add(gamePanel, "Game");
            cardPanel.add(gameOverPanel, "GameOver");

            frame.setContentPane(cardPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}