package src.yusam.brickballgame.entities;

import java.util.List;
import src.yusam.brickballgame.config.GameConfig;

public class Ball {
    private double x, y; // Ball position
    private double dx, dy; // Velocity components
    private final int radius; // Ball radius

    public Ball(int startX, int startY) {
        // Initialize the ball's position and velocity here
        x = startX;
        y = startY;
        dx = 1;
        dy = -1;
        radius = 10;
    }

    public void move() {
        // Update the ball's position based on its velocity
        x += dx;
        y += dy;
    }

    public void checkCollisions(Paddle paddle, List<Brick> bricks) {
        // Check for collisions with the paddle and bricks
        if (y + radius >= paddle.getY() && x >= paddle.getX() && x <= paddle.getX() + paddle.getWidth()) {
            dy = -dy; // Reverse the y-velocity when colliding with the paddle
        }

        // Check for collisions with bricks
        for (Brick brick : bricks) {
            if (!brick.isDestroyed()) {
                if (x + radius >= brick.getX() && x - radius <= brick.getX() + brick.getWidth()
                        && y + radius >= brick.getY() && y - radius <= brick.getY() + brick.getHeight()) {
                    brick.setDestroyed(true); // Mark the brick as destroyed
                    dy = -dy; // Reverse the y-velocity when colliding with a brick
                }
            }
        }
    }   

    public void checkEdges() {
        // Check the left and right edges
        if (x - radius < 0 || x + radius > GameConfig.WIDTH) {
            dx = -dx; // Reverse the x-velocity when hitting the left or right edge
        }

        // Check the top edge
        if (y - radius < 0) {
            dy = -dy; // Reverse the y-velocity when hitting the top edge
        }

        // Check the bottom edge (game over condition)
        if (y + radius > GameConfig.HEIGHT) {
            // Handle game over logic here (e.g., reset the ball)
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
}
