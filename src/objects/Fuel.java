package objects;

import view.Road;

import javax.swing.*;
import java.awt.*;

public class Fuel
{
    public static Image image;
    private final static int MODELSIZE_WIDTH = 55;
    private final static int MODELSIZE_HEIGHT = 85;


    private int x;
    private int y;
    private Road road;

    public Fuel(int x, int y, Road road)
    {
        this.x = x;
        this.y = y;
        this.road = road;
    }

    public void showOnRoad()
    {
        x = x - (int)road.getPlayer().getSpeed() + 0;
    }

    static
    {
        image = new ImageIcon("src/res/fuel.png").getImage();
    }

    public Rectangle getRect()
    {
        return new Rectangle(x, y, MODELSIZE_WIDTH, MODELSIZE_HEIGHT);
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
}
