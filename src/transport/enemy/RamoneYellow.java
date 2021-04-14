package transport.enemy;

import view.Road;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RamoneYellow extends Enemy
{
    public static Image image;
    public final static short SIZEMODEL_WIDTH = 220;
    public final static short SIZEMODEL_HEIGHT = 70;

    static
    {
        image = new ImageIcon("src/res/ramone.png").getImage();
    }

    public RamoneYellow(int x, int y, int speed, Road road)
    {
        super(SIZEMODEL_WIDTH, SIZEMODEL_HEIGHT, x, y, speed, road);

        //img = new ImageIcon("src/res/ramone.png").getImage();
        //this.x = x;
        //this.y = y;
        //this.speed = speed;
        //this.road = road;
    }
}
