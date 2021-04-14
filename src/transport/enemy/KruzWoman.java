package transport.enemy;

import view.Road;

import javax.swing.*;
import java.awt.*;

public class KruzWoman extends Enemy
{
    public static Image image;
    public final static short SIZEMODEL_WIDTH = 220;
    public final static short SIZEMODEL_HEIGHT = 65;

    static
    {
        image = new ImageIcon("src/res/kruz.png").getImage();
    }

    public KruzWoman(int x, int y, int speed, Road road)
    {
        super(SIZEMODEL_WIDTH, SIZEMODEL_HEIGHT,x, y, speed, road);
    }
}
