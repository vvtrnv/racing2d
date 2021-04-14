package transport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player
{
    public static final int MAX_SPEED = 50;
    public static final int MAX_TOP = 240;
    public static final int MAX_BOTTOM = 625;
    public static final int MAX_TANK = 80;
    public static final int MIN_TANK = 0;

    public static final int SIZEMODEL_WIDTH = 215;
    public static final int SIZEMODEL_HEIGHT = 70;

    // Текущее изображение машины
    private Image img = new ImageIcon("src/res/player.png").getImage();

    private Image img_middle = new ImageIcon("src/res/player.png").getImage();
    private Image img_left = new ImageIcon("src/res/player_left1.png").getImage();
    private Image img_right = new ImageIcon("src/res/player_right1.png").getImage();

    private int speed = 0; // Скорость
    private int speedup = 0; // Ускорение
    private int distance = 0; // Проденное расстояние

    // Слои заднего фона
    private int layer1 = 0;
    private int layer2 = 1200;

    // Координаты машины
    private int x = 150;
    private int y = 300;

    private int dY = 0;

    private double tank = 80.0;

    // Бокс машины
    public Rectangle getRect()
    {
        return new Rectangle(x,  y, SIZEMODEL_WIDTH, SIZEMODEL_HEIGHT);
    }

    // Движение
    public void move()
    {
        if(speed != 0)
        {
            spendTank();
        }

        distance += speed;
        speed += speedup;

        y -= dY;

        // Обработка, чтобы машина не поехала назад
        if(speed <= 0) speed = 0;
        // Обработка, чтобы машина не ускорялась бесконечно
        if(speed >= MAX_SPEED) speed = MAX_SPEED;

        // Обработка, чтобы машина не могла уходить бесконечно вверх
        if(y <= MAX_TOP) y = MAX_TOP;
        // Обработка, чтобы машина не могла уходить бесконечно вниз
        if(y >= MAX_BOTTOM) y = MAX_BOTTOM;

        // Если 1 и 2 слои заднего фона пройдены
        if(layer2 - speed <= 0)
        {
            layer1 = 0;
            layer2 = 1200;
        }
        else
        {
            layer1 -= speed;
            layer2 -= speed;
        }
    }

    // Событие при нажатии клавиши
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
        {
            speedup = 1;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            speedup = -1;
        }

        if(key == KeyEvent.VK_UP)
        {
            if(y > MAX_TOP && speed != 0)
            {
                dY = 7;
                img = img_left;
            }
        }
        if(key == KeyEvent.VK_DOWN)
        {
            if(y < MAX_BOTTOM && speed != 0)
            {
                dY = -7;
                img = img_right;
            }
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT)
        {
            speedup = 0;
        }

        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)
        {
            dY = 0;
            img = img_middle;
        }

    }

    public void fillTank()
    {
        tank += 15.0;
        if(tank >= MAX_TANK)
            tank = MAX_TANK;
    }

    public void spendTank()
    {
        if(tank <= MIN_TANK)
            tank = MIN_TANK;
        else
            tank -= 0.06;
    }



    public Image getImg() { return img; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSpeed() { return speed; }
    public double getTank() { return tank;}


    public int getLayer1() { return layer1; }
    public int getLayer2() { return layer2; }
}
