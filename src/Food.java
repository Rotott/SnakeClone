import java.awt.*;

public class Food {
    private Tile tile;

    public Food(Tile tile) {
        this.tile = tile;
    }

    public void draw(Graphics g) {
        tile.draw(g, Color.red);
    }

    public Tile getTile() {
        return tile;
    }

}
