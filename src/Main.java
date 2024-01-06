import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args){
        // Shows Tetris text

        // Finds the current screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setUndecorated(true); // Fullscreen
        frame.setBackground(Color.white);
        frame.setSize(screenSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel tetrisText = new JLabel("TETRIS");
        tetrisText.setHorizontalAlignment(SwingConstants.CENTER);
        tetrisText.setSize(screenSize);
        tetrisText.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 100));
        frame.add(tetrisText);
        frame.setVisible(true);
        try {
            // Wait 3 seconds
            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        frame.dispose();
        new Menu(screenSize);
    }
}