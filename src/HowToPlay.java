import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlay extends JFrame implements ActionListener {
    private JFrame frame = new JFrame();
    private final JButton back = new JButton("Back");
    private Dimension screenSize;

    HowToPlay(Dimension s) {
        screenSize = s;

        // Adds the picture with the instructions
        JLabel instructions = new JLabel(new ImageIcon(getClass().getResource("HowToPlay.png")));

        // Set button parameters
        back.setBounds(0, 0, 300, 200);
        back.addActionListener(this);
        back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40)); // Sets font

        // Add components
        frame.add(back);
        frame.add(instructions);

        frame.setUndecorated(true); // Fullscreen
        frame.setSize(screenSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose();
            new Menu(screenSize);
        }
    }
}
