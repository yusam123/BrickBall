package src.yusam.brickballgame.entities;

public class Brick {
    private double x, y; // Brick position
    private int width, height; // Brick dimensions
    private boolean isDestroyed; // Flag to check if the brick is destroyed

    public Brick(int startX, int startY, int brickWidth, int brickHeight) {
        x = startX;
        y = startY;
        width = brickWidth;
        height = brickHeight;
        isDestroyed = false;
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

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }   
}
