import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    private final GameFrame gameFrame;
    private final Thread gameThread;
    private final int panelWidth = 1000;
    private final int panelHeight = panelWidth;
    private final int tileSize = panelWidth / 50;
    private int score;
    private JLabel scoreLabel;
    private final Random random = new Random();
    private Food food;
    private Snake snake;
    private Image image;
    private Graphics graphics;


    public GamePanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        Dimension windowDimension = new Dimension(panelWidth, panelHeight);
        this.setPreferredSize(windowDimension);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());

        this.setLayout(null);
        score = 0;
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.lightGray);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setBounds(panelWidth/2, 10, 200, 30);
        this.add(scoreLabel);


        gameThread = new Thread(this);
        gameThread.start();

    }

    private void newSnake() {
        snake = new Snake(new Tile(0, 0, tileSize));
    }

    private void newFood() {
        int randomxCord = tileSize * (random.nextInt(panelWidth / tileSize));
        int randomyCord = tileSize * (random.nextInt(panelHeight / tileSize));
        food = new Food(new Tile(randomxCord, randomyCord, tileSize));
    }

    private boolean checkCollision() {
        Tile snakeHeadTile = snake.getHead();
        Tile foodTile = food.getTile();

        if (snakeHeadTile.getxCord() == foodTile.getxCord() && snakeHeadTile.getyCord() == foodTile.getyCord()) {
            snake.growBody();
            newFood();
            score++;
            scoreLabel.setText("Score: " + score);
            return true;
        }
        if (snakeHeadTile.getxCord() < 0 || snakeHeadTile.getyCord() < 0 || snakeHeadTile.getxCord() + tileSize > panelWidth || snakeHeadTile.getyCord() + tileSize > panelHeight) {
            return false;
        }


        for (Tile bodypart : snake.getBody()) {
            if (bodypart.getxCord() == snakeHeadTile.getxCord() && bodypart.getyCord() == snakeHeadTile.getyCord()) {
                return false;
            }
        }
        return true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    private void draw(Graphics g) {
        snake.draw(g);
        food.draw(g);
    }

    private void move() {
        snake.move();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ticks = 10.00;
        double nanoSeconds = 1000000000 / ticks;
        double delta = 0;
        newSnake();
        newFood();
        boolean gameRunning = true;
        while (gameRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;

            if (delta >= 1) {
                move();
                gameRunning = checkCollision();
                repaint();
                delta--;
            }
        }
        gameFrame.dispose(); //TODO snyggare "Game Over"

    }


    private class ActionListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            snake.keyPressed(e);
        }

    }
}

