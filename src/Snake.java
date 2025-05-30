import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake extends Rectangle {
    private Tile head;
    private ArrayList<Tile> body = new ArrayList<>();
    private int xDirection = 0;
    private int yDirection = 0;

    public Snake(Tile head) {
        this.head = head;
    }


    public void draw(Graphics g) {
        head.draw(g, Color.green);
        for (Tile bodyPart : body) {
            bodyPart.draw(g, Color.green);
        }
    }


    public void move() {
        int headOldxCord = head.getxCord();
        int headOldyCord = head.getyCord();
        int spaceMove = (head.getSize() * xDirection);
        if (xDirection != 0) {
            head.setxCord(headOldxCord + spaceMove);
        } else if (yDirection != 0) {
            head.setyCord(headOldyCord + (head.getSize() * yDirection));
        }

        int prevxCord = headOldxCord;
        int prevyCord = headOldyCord;

        for (Tile bodyPart : body) {
            int tempxCord = bodyPart.getxCord();
            int tempyCord = bodyPart.getyCord();
            bodyPart.setxCord(prevxCord);
            bodyPart.setyCord(prevyCord);
            prevxCord = tempxCord;
            prevyCord = tempyCord;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP:
                if (yDirection != 1) {
                    xDirection = 0;
                    yDirection = -1;
                }
                break;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                if (xDirection != 1) {
                    yDirection = 0;
                    xDirection = -1;
                }
                break;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                if (yDirection != -1) {
                    xDirection = 0;
                    yDirection = 1;
                }
                break;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                if (xDirection != -1) {
                    yDirection = 0;
                    xDirection = 1;
                }
                break;
            case KeyEvent.VK_SPACE:
                if (xDirection == 0 && yDirection == 0) {
                    xDirection = 1;
                }
                break;
        }
    }

    public Tile getHead() {
        return head;
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public void growBody() {
        Tile newBodyPart = new Tile(head.getSize());
        body.add(newBodyPart);

    }
}
