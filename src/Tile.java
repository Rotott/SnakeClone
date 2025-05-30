import java.awt.*;

public class Tile {
    private int xCord;
    private int yCord;
    private int size;

    public Tile(int xCord, int yCord, int size) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.size = size;
    }

    public Tile(int size) {
        this.size=size;
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public int getSize() {
        return size;
    }

    public void draw(Graphics g, Color color){
        g.setColor(color);
        g.fillRect(xCord,yCord,size,size);
    }
}
