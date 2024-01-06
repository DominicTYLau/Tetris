import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame();
    private final Button play = new Button("Play");
    private final Button howToPlay = new Button("How To Play");
    private final Font f = new Font(Font.MONOSPACED, Font.BOLD, 50);
    private final Dimension screenSize;

    public Menu(Dimension s) {
        screenSize = s; // Finds the current screen size


        // Middle Panel

        // Set Buttons
        play.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        play.addActionListener(this);

        howToPlay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        howToPlay.addActionListener(this);

        // High score text
        JLabel highScoreLabel = new JLabel("High Score");
        highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highScoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        // Initialize
        JPanel score = new JPanel();
        score.setLayout(new GridLayout(5, 2));
        score.setSize(screenSize.height / 2 - 100, screenSize.height / 2 - 100);

        // Score Board
        SaveToFile saver = new SaveToFile();
        saver.read(); // Refreshes the values
        JLabel[][] highScores = new JLabel[5][2];
        for (int i = 0; i < 5; i++) {
            if (saver.getTopScores()[i] != null) {
                // Add name to GUI
                highScores[i][0] = new JLabel();
                highScores[i][0].setText(saver.getTopScores()[i].substring(0, saver.getTopScores()[i].indexOf(" ")));
                highScores[i][0].setHorizontalAlignment(SwingConstants.CENTER); // Center text
                highScores[i][0].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20)); // Change font size
                score.add(highScores[i][0]);

                // Add score to GUI
                highScores[i][1] = new JLabel();
                highScores[i][1].setText(saver.getTopScores()[i].substring(saver.getTopScores()[i].indexOf(" ")));
                highScores[i][1].setHorizontalAlignment(SwingConstants.CENTER); // Center text
                highScores[i][1].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20)); // Change font size
                score.add(highScores[i][1]);
            } else {
                // Set rest of the Jlabels
                highScores[i][0] = new JLabel();
                score.add(highScores[i][0]);
                highScores[i][1] = new JLabel();
                score.add(highScores[i][1]);
            }
        }

        // The buttons in the output will be aligned vertically

        // Initialize
        JPanel mid = new JPanel();
        mid.setSize(screenSize.height / 2 - 100, screenSize.height - 200);
        mid.setLayout(new GridLayout(4, 1));
        mid.setBackground(Color.white);

        // Add components to mid panel
        mid.add(play);
        mid.add(howToPlay);
        mid.add(highScoreLabel);
        mid.add(score);

        // Left panel

        // Initialize
        JPanel left = new JPanel();
        left.setSize(screenSize.height / 2 - 100, screenSize.height - 200);

        // Initialize and declaration
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 - 500, screenSize.height / 2 - 100, 200);
        JLabel playerScoreLabel = new JLabel(" ");
        playerScoreLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 - 300, screenSize.height / 2 - 100, 200);
        JLabel levelLabel = new JLabel("Level");
        levelLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 - 100, screenSize.height / 2 - 100, 200);
        JLabel playerLevelLabel = new JLabel(" ");
        playerLevelLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 + 100, screenSize.height / 2 - 100, 200);
        JLabel linesLabel = new JLabel("Lines");
        linesLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 + 300, screenSize.height / 2 - 100, 200);
        JLabel playerLinesLabel = new JLabel(" ");
        playerLinesLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 + 500, screenSize.height / 2 - 100, 200);

        // Aligns text to center
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        linesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLinesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Sets fonts
        scoreLabel.setFont(f);
        levelLabel.setFont(f);
        linesLabel.setFont(f);
        playerScoreLabel.setFont(f);
        playerLevelLabel.setFont(f);
        playerLinesLabel.setFont(f);

        // Changes text colour to white
        scoreLabel.setForeground(Color.white);
        levelLabel.setForeground(Color.white);
        linesLabel.setForeground(Color.white);
        playerScoreLabel.setForeground(Color.white);
        playerLevelLabel.setForeground(Color.white);
        playerLinesLabel.setForeground(Color.white);

        // Add texts in order
        left.add(scoreLabel);
        left.add(playerScoreLabel);
        left.add(levelLabel);
        left.add(playerLevelLabel);
        left.add(linesLabel);
        left.add(playerLinesLabel);

        left.setBackground(new Color(43, 43, 43));
        left.setLayout(new GridLayout(6, 1));


        // Right Panel
        // Initialize and declaration
        JLabel next = new JLabel("Next");
        next.setForeground(Color.white);
        next.setFont(f);
        next.setSize(screenSize.height / 2 - 100, 200);
        next.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize
        JPanel nextPanel = new JPanel();
        nextPanel.setBackground(new Color(43, 43, 43));
        nextPanel.setSize(screenSize.height / 2 - 200, screenSize.height / 2 - 200);

        // Initialize
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(2, 1));
        right.setBackground(new Color(43, 43, 43));
        right.setAlignmentY(Component.CENTER_ALIGNMENT); // Aligns vertically

        // Add componenets
        right.add(next);
        right.add(nextPanel);

        // Set sizes of panel when maximized screens
        left.setMaximumSize(new Dimension(screenSize.height / 2 - 100, screenSize.height - 200));
        mid.setMaximumSize(new Dimension(screenSize.height / 2 - 100, screenSize.height - 200));
        right.setMaximumSize(new Dimension(screenSize.height / 2 - 100, screenSize.height - 200));

        // Initialize
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // Aligns components horizontally
        panel.setBounds((screenSize.width - 3 * (screenSize.height / 2 - 100)) / 2, (screenSize.height - (screenSize.height - 50)) / 2, screenSize.width, screenSize.height);

        // Add components
        panel.add(left);
        panel.add(mid);
        panel.add(right);

        frame.add(panel);

        frame.setLayout(null);
        frame.setUndecorated(true); // Fullscreen
        frame.setBackground(Color.white);
        frame.setSize(screenSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            // Deletes menu and create new tetris game
            frame.dispose();
            new Tetris(screenSize);
        }
        if (e.getSource() == howToPlay) {
            frame.dispose();
            new HowToPlay(screenSize);

        }

    }
}