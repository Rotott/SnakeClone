import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() throws HeadlessException {

        this.setTitle("Snake");
        this.setBackground(Color.black);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new GamePanel(this));
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);



    }
}
