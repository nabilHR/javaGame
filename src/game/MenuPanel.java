package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public MenuPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        // Title
        JLabel titleLabel = new JLabel("TRASH CATCHER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(100, 200, 100));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 80, 0));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(new Color(30, 30, 40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 100, 100));

        // Buttons
        JButton startButton = createMenuButton("START GAME", new Color(70, 180, 70));
        JButton instructionsButton = createMenuButton("INSTRUCTIONS", new Color(70, 130, 180));
        JButton exitButton = createMenuButton("EXIT", new Color(180, 70, 70));

        // Button actions
        startButton.addActionListener(e -> cardLayout.show(cardPanel, "Game"));
        instructionsButton.addActionListener(this::showInstructions);
        exitButton.addActionListener(e -> System.exit(0));

        // Add components
        buttonPanel.add(startButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(exitButton);

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return button;
    }

    private void showInstructions(ActionEvent e) {
        String instructions = """
            <html><div style='text-align: center;'>
                <h1>HOW TO PLAY</h1>
                <p>Use LEFT and RIGHT arrow keys to move the catcher</p>
                <p>Catch falling trash to earn points</p>
                <p>Different trash types give different points</p>
                <p>You have 5 lives - don't let trash hit the ground!</p>
                <p style='color:red'>Watch out for hazardous waste!</p>
            </div></html>
            """;
        JOptionPane.showMessageDialog(this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }
}