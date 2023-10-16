package src.yusam.brickballgame.core;

import src.yusam.brickballgame.entities.Ball;
import src.yusam.brickballgame.entities.Brick;
import src.yusam.brickballgame.entities.Paddle;
import src.yusam.brickballgame.config.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class BrickBallGame extends JPanel implements KeyListener, ActionListener {
    private static final int WIDTH = GameConfig.WIDTH;
    private static final int HEIGHT = GameConfig.HEIGHT;
    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;   
    private GameState gameState;

    public BrickBallGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        paddle = new Paddle(WIDTH / 2 - 50, 550, 100, 10); // Adjust the position and dimensions
        ball = new Ball(WIDTH / 2, HEIGHT / 2);
        bricks = new ArrayList<>();
        gameState = GameState.START;

        // Initialize bricks (you can customize this part)
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                bricks.add(new Brick(col * 75, row * 20 + 50, 70, 15)); // Adjust dimensions and spacing
            }
        }

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameState == GameState.PLAY){
            // Update game logic here
            ball.move();
            ball.checkCollisions(paddle, bricks);
            ball.checkEdges();
            repaint(); // Redraw the game screen
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw game objects (paddle, ball, bricks) here
        if (gameState == GameState.START) {
            // Draw "Press Enter to Start" message when in the "start" state
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            String message = "Press Enter to Start";
            int textWidth = g.getFontMetrics().stringWidth(message);
            int x = (WIDTH - textWidth) / 2;
            int y = HEIGHT / 2;
            g.drawString(message, x, y);
        } else{
            // Example: Draw paddle
            g.setColor(Color.WHITE);
            g.fillRect((int) paddle.getX(), HEIGHT - 30, paddle.getWidth(), paddle.getHeight());

            // Example: Draw ball
            g.setColor(Color.WHITE);
            g.fillOval((int) (ball.getX() - ball.getRadius()), (int) (ball.getY() - ball.getRadius()),
                    ball.getRadius() * 2, ball.getRadius() * 2);

            // Example: Draw bricks (iterate through the bricks list)
            for (Brick brick : bricks) {
                if (!brick.isDestroyed()) {
                    g.setColor(Color.RED);
                    g.fillRect((int) brick.getX(), (int) brick.getY(), brick.getWidth(), brick.getHeight());
                }
            }
        }


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gameState == GameState.START) {
                // Start the game when Enter is pressed in the "start" state
                gameState = GameState.PLAY;
            }
        }

        // Handle paddle movement keys if the game is in the "play" state
        if (gameState == GameState.PLAY) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                paddle.moveLeft();
            } else if (key == KeyEvent.VK_RIGHT) {
                paddle.moveRight();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Brick Ball Game");
            BrickBallGame game = new BrickBallGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
