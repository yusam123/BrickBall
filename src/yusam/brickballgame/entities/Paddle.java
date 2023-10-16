package src.yusam.brickballgame.entities;

import src.yusam.brickballgame.config.GameConfig;

public class Paddle {
    private double x; // Paddle position
    private double y; // Paddle position
    private int width; // Paddle width
    private final int height; // Paddle height

    public Paddle(int startX, int startY, int paddleWidth, int paddleHeight) {
        x = startX;
        y = startY;
        width = paddleWidth;
        height = paddleHeight;
    }

    public void moveLeft() {
        // Move the paddle left
        x -= 5; // Adjust the paddle speed as needed
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight() {
        // Move the paddle right
        x += 5; // Adjust the paddle speed as needed
        if (x > GameConfig.WIDTH - width) {
            x = GameConfig.WIDTH - width;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }    
}
