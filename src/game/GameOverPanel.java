package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;

    public GameOverPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        // Title
        JLabel titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(200, 100, 100));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));

        // Score display
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2, 1, 10, 10));
        scorePanel.setBackground(new Color(30, 30, 40));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 30, 100));

        scoreLabel = new JLabel("Your Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 36));
        scoreLabel.setForeground(Color.WHITE);

        highScoreLabel = new JLabel("High Score: 0", SwingConstants.CENTER);
        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        highScoreLabel.setForeground(Color.YELLOW);

        scorePanel.add(scoreLabel);
        scorePanel.add(highScoreLabel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonPanel.setBackground(new Color(30, 30, 40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 50, 100));

        JButton restartButton = createButton("PLAY AGAIN", new Color(70, 180, 70));
        JButton menuButton = createButton("MAIN MENU", new Color(70, 130, 180));

//        restartButton.addActionListener(e -> {
//            cardLayout.show(cardPanel, "Game");
//            ((GamePanel) cardPanel.getComponent(1)).startGame();
//        });

        menuButton.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));

        buttonPanel.add(restartButton);
        buttonPanel.add(menuButton);

        add(titleLabel, BorderLayout.NORTH);
        add(scorePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setScore(int score) {
        scoreLabel.setText("Your Score: " + score);
        
        // Update high score (simple implementation - would normally persist)
        int highScore = Math.max(score, Integer.parseInt(highScoreLabel.getText().replaceAll("\\D+", "")));
        highScoreLabel.setText("High Score: " + highScore);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return button;
    }
}