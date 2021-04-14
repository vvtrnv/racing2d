package transport.enemy;

import view.Road;

import javax.swing.*;
import java.awt.*;

public class JacksonBlack extends Enemy
{
    public final static short SIZEMODEL_WIDTH = 230;
    public final static short SIZEMODEL_HEIGHT = 60;

    public static Image image;

    static
    {
        image = new ImageIcon("src/res/jackson.png").getImage();
    }

    public JacksonBlack(int x, int y, int speed, Road road)
    {
        super(SIZEMODEL_WIDTH, SIZEMODEL_HEIGHT, x, y, speed, road);

        //img = new ImageIcon("src/res/jackson.png").getImage();
        //this.x = x;
        //this.y = y;
        //this.speed = speed;
        // this.road = road;
    }

}
