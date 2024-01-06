import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Tetris extends JFrame {
    private final JFrame frame = new JFrame();
    private JPanel nextPanel = new JPanel();
    private JLabel[][] nextLabels;
    private final JLabel[][] GUIboard = new JLabel[20][10];
    private JLabel playerScoreLabel = new JLabel("0");
    private JLabel playerLevelLabel = new JLabel("0");
    private JLabel playerLinesLabel = new JLabel("0");
    private final PlayerInfo score = new PlayerInfo(this);
    private final Font f = new Font(Font.MONOSPACED, Font.BOLD, 50);
    private final Timer timer = new Timer();
    private int[][] board = new int[20][10];
    private int dropDelay = 1000; // In milliseconds
    private boolean isContinue = true;
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;
    private KeyAdapter keyAdapter; // For key presses

    private final SaveToFile saver = new SaveToFile();
    private Dimension screenSize;

    public Tetris(Dimension s) {
        if (isContinue) {
            screenSize = s;
            // Middle Panel
            JPanel mid = new JPanel();
            mid.setSize(screenSize.height / 2 - 100, screenSize.height - 200);
            mid.setLayout(new GridLayout(20, 10, 1, 1));
            mid.setBackground(new Color(43, 43, 43));
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    // Initialize and declaration
                    board[i][j] = 0;
                    GUIboard[i][j] = new JLabel();
                    GUIboard[i][j].setOpaque(true);
                    GUIboard[i][j].setBackground(Color.black);
                    mid.add(GUIboard[i][j]);
                }
            }

            // Create new Tetromino to be put onto the board
            newTetromino();

            // Left panel

            // Initialize
            JPanel left = new JPanel();
            left.setSize(screenSize.height / 2 - 100, screenSize.height - 200);

            // Initialize and declaration
            JLabel scoreLabel = new JLabel("Score");
            scoreLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 - 500, screenSize.height / 2 - 100, 200);
            playerScoreLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 - 300, screenSize.height / 2 - 100, 200);
            JLabel levelLabel = new JLabel("Level");
            levelLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 - 100, screenSize.height / 2 - 100, 200);
            playerLevelLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 + 100, screenSize.height / 2 - 100, 200);
            JLabel linesLabel = new JLabel("Lines");
            linesLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 + 300, screenSize.height / 2 - 100, 200);
            playerLinesLabel.setBounds(screenSize.width / 3 - (screenSize.height / 2 - 100) / 2, screenSize.height / 2 + 500, screenSize.height / 2 - 100, 200);

            // Centers the text field
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
            linesLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerLinesLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Sets font of text
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

            // Add values in order
            left.add(scoreLabel);
            left.add(playerScoreLabel);
            left.add(levelLabel);
            left.add(playerLevelLabel);
            left.add(linesLabel);
            left.add(playerLinesLabel);

            left.setBackground(new Color(43,43,43));
            left.setLayout(new GridLayout(6, 1));

            // Right Panel

            // Initialize
            JLabel next = new JLabel("Next");
            next.setFont(f);
            next.setForeground(Color.white);
            next.setHorizontalAlignment(SwingConstants.CENTER);
            next.setSize(screenSize.height / 2 - 100, 200);

            nextPanel.setBackground(new Color(43,43,43));
            nextPanel.setSize(screenSize.height / 2 - 200, screenSize.height / 2 - 200);

            // Initialize
            JPanel right = new JPanel();
            right.setBackground(new Color(43,43,43));
            right.setAlignmentY(Component.CENTER_ALIGNMENT);
            right.setSize(screenSize.height / 2 - 100, screenSize.height - 200);
            right.setLayout(new GridLayout(2, 1)); // Organizes components

            // Adds componenets
            right.add(next);
            right.add(nextPanel);



            // Sizes when maximized screens
            left.setMaximumSize(new Dimension(screenSize.height / 2 - 100, screenSize.height - 200));
            mid.setMaximumSize(new Dimension(screenSize.height / 2 - 100, screenSize.height - 200));
            right.setMaximumSize(new Dimension(screenSize.height / 2 - 100, screenSize.height - 200));


            // Initialize
            JPanel panel = new JPanel();
            // Centers the panels
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            panel.add(left);
            panel.add(mid);
            panel.add(right);



            panel.setMaximumSize(screenSize); // Size when maximized
            panel.setBounds((screenSize.width - 3 * (screenSize.height / 2 - 100)) / 2, (screenSize.height - (screenSize.height - 50)) / 2, screenSize.width, screenSize.height);

            frame.add(panel);
            frame.setBackground(Color.white); // Set background colour
            frame.setSize(screenSize);
            frame.setUndecorated(true); // Fullscreen
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setVisible(true);

            try {
                Thread.sleep(1000); // Wait till it loads
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Inputs for the arrow keys
            keyAdapter = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    // Arrow key up rotates Tetromino
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        board = currentTetromino.rotate(board, currentTetromino);
                    }
                    // Arrow key down moves Tetromoino down
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        board = currentTetromino.move(board, 0, 1);
                    }
                    // Arrow key left moves Tetromoino right
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        board = currentTetromino.move(board, -1, 0);
                    }
                    // Arrow key right moves Tetromoino left
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        board = currentTetromino.move(board, 1, 0);
                    }
                    // Refreshes the graphical board
                    refresh();
                    playerScoreLabel.setText(score.getScore() + "");
                    playerLevelLabel.setText(score.getLevel() + "");
                    playerLinesLabel.setText(score.getLines() + "");

                }
            };
            frame.addKeyListener(keyAdapter);

            // Automatic moving down
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    board = currentTetromino.move(board, 0, 1);
                    refresh();
                }
            }, 0, dropDelay);
        }
    }


    public void newTetromino() {
        // On the first turn, current will be null
        if (nextTetromino == null) {
            // Randomize current Tetromino
            int random = (int) Math.floor(Math.random() * (6 + 1));
            switch (random) {
                case 0 -> currentTetromino = new I_Tetromino(this);
                case 1 -> currentTetromino = new J_Tetromino(this);
                case 2 -> currentTetromino = new L_Tetromino(this);
                case 3 -> currentTetromino = new O_Tetromino(this);
                case 4 -> currentTetromino = new S_Tetromino(this);
                case 5 -> currentTetromino = new T_Tetromino(this);
                case 6 -> currentTetromino = new Z_Tetromino(this);
            }
            // Randomize current Tetromino
            random = (int) Math.floor(Math.random() * (6 + 1));
            switch (random) {
                case 0 -> nextTetromino = new I_Tetromino(this);
                case 1 -> nextTetromino = new J_Tetromino(this);
                case 2 -> nextTetromino = new L_Tetromino(this);
                case 3 -> nextTetromino = new O_Tetromino(this);
                case 4 -> nextTetromino = new S_Tetromino(this);
                case 5 -> nextTetromino = new T_Tetromino(this);
                case 6 -> nextTetromino = new Z_Tetromino(this);
            }
        } else {
            // When not on first turn
            currentTetromino = nextTetromino;
            int random = (int) Math.floor(Math.random() * (6 + 1));
            switch (random) {
                case 0 -> nextTetromino = new I_Tetromino(this);
                case 1 -> nextTetromino = new J_Tetromino(this);
                case 2 -> nextTetromino = new L_Tetromino(this);
                case 3 -> nextTetromino = new O_Tetromino(this);
                case 4 -> nextTetromino = new S_Tetromino(this);
                case 5 -> nextTetromino = new T_Tetromino(this);
                case 6 -> nextTetromino = new Z_Tetromino(this);
            }
        }


        // Add Tetromino to board
        boolean hit = false;

        currentTetromino.findValues(); // Resets values
        // Checks if player loses
        for (int i = 0; i < currentTetromino.getTetromino()[0][0].length; i++) {
            for (int j = currentTetromino.getMostLeft(); j < currentTetromino.getMostRight() + 1; j++) {
                // Checks if the shape hits any block
                if (currentTetromino.getTetromino()[currentTetromino.getRotation()][i][j] != 0 && board[currentTetromino.getPosY() + i][currentTetromino.getPosX() + j] != 0) {
                    hit = true;
                    i = currentTetromino.getTetromino()[0][0].length;
                    break;
                }
            }
        }

        // If loses
        if (hit) {
            lose();
        } else {
            // Add to board
            for (int i = 0; i < currentTetromino.getTetromino()[0][0].length; i++) {
                for (int j = 0; j < currentTetromino.getTetromino()[0][0].length; j++) {
                    if (currentTetromino.getTetromino()[currentTetromino.getRotation()][i][j] != 0 && board[currentTetromino.getPosY() + i][currentTetromino.getPosX() + j] == 0) {
                        board[i][j + currentTetromino.getPosX()] = currentTetromino.getTetromino()[0][i][j];
                    }
                }
            }

            // Change next Tetromino GUI on the right panel

            // Remove the piece from the next Panel
            if (nextLabels != null) {
                for (JLabel[] nextLabel : nextLabels) {
                    for (int j = 0; j < nextLabels.length; j++) {
                        nextPanel.remove(nextLabel[j]);
                    }
                }
            }

            // Sets the layout
            nextPanel.setLayout(new GridLayout(nextTetromino.getTetromino()[0].length, nextTetromino.getTetromino()[0].length, 1, 1));
            nextPanel.setBackground(new Color(43,43,43));

            nextLabels = new JLabel[nextTetromino.getTetromino()[0].length][nextTetromino.getTetromino()[0].length];

            // Adds the next Tetromino to the GUI
            for (int i = 0; i < nextLabels.length; i++) {
                for (int j = 0; j < nextLabels.length; j++) {
                    nextLabels[i][j] = new JLabel();
                    nextLabels[i][j].setOpaque(true);

                    switch (nextTetromino.getTetromino()[0][i][j]) {
                        case 0 -> nextLabels[i][j].setBackground(new Color(43,43,43)); // Background
                        case 1 -> nextLabels[i][j].setBackground(new Color(28, 177, 195));
                        case 2 -> nextLabels[i][j].setBackground(new Color(19, 111, 208));
                        case 3 -> nextLabels[i][j].setBackground(new Color(215, 136, 41));
                        case 4 -> nextLabels[i][j].setBackground(new Color(224, 203, 52));
                        case 5 -> nextLabels[i][j].setBackground(new Color(35, 225, 73));
                        case 6 -> nextLabels[i][j].setBackground(new Color(153, 0, 189));
                        case 7 -> nextLabels[i][j].setBackground(new Color(210, 0, 28));
                    }
                    nextPanel.add(nextLabels[i][j]);
                }
            }
            frame.getContentPane().repaint();
            frame.getContentPane().revalidate();
        }
    }

    private void refresh() {
        // Turns the integer array board to a graphical
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                switch (board[i][j]) {
                    case 0 -> GUIboard[i][j].setBackground(Color.black); // Background
                    case 1 -> GUIboard[i][j].setBackground(new Color(28, 177, 195)); // I
                    case 2 -> GUIboard[i][j].setBackground(new Color(19, 111, 208)); // J
                    case 3 -> GUIboard[i][j].setBackground(new Color(215, 136, 41)); // L
                    case 4 -> GUIboard[i][j].setBackground(new Color(224, 203, 52)); // O
                    case 5 -> GUIboard[i][j].setBackground(new Color(35, 225, 73)); // S
                    case 6 -> GUIboard[i][j].setBackground(new Color(153, 0, 189)); // T
                    case 7 -> GUIboard[i][j].setBackground(new Color(210, 0, 28)); // Z
                }

            }
        }
    }

    public void clearLine() {
        // Checks if any lines are completed, and if there are, delete them
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            boolean lineFull = true;
            // Checks if rows are completed
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    lineFull = false;
                    break;
                }
            }
            // If line is full, move everything above it one row down
            if (lineFull) {
                count++;
                for (int j = i - 1; j >= 0; j--) {
                    System.arraycopy(board[j], 0, board[j + 1], 0, board[0].length);
                }
                i--;
            }
        }

        // How many lines were cleared
        if (count == 1) score.oneLine();
        else if (count == 2) score.twoLine();
        else if (count == 3) score.threeLine();
        else if (count == 4) score.fourLine();
    }

    public void lose() {
        // Stops automatic moving, keyboard inputs, and the game
        timer.cancel();
        isContinue = false;
        frame.removeKeyListener(keyAdapter);

        // Clear screen
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = 0;
                GUIboard[i][j].setBackground(Color.black);
            }
        }

        // Create a pop up that asks for a name

        // Add to file I/O
        // If name is not empty
        if (score.getScore() != 0) {
            String name = JOptionPane.showInputDialog(frame, "Enter a name:");
            if (name != null) saver.write(name + " " + score.getScore());
            else saver.write("unknown " + score.getScore());
        }

        // Remove game and return to menu
        frame.dispose();
        new Menu(screenSize);
    }

    public void subDropDelay() {
        // Changes the drop delay
        if (dropDelay <= 100) dropDelay -= 5;
        else dropDelay -= 100;
    }

}
