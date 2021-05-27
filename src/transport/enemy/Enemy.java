package transport.enemy;

import view.Road;

import java.awt.*;

public abstract class Enemy implements IEnemy
{
    private int widthModel;
    private int heightModel;

    private int x;
    private int y;
    private int speed;

    private static Road road;

    public Enemy(short WIDTH_MODEL, short HEIGHT_MODEL, int x, int y, int speed, Road road)
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.road = road;
        this.widthModel = WIDTH_MODEL;
        this.heightModel = HEIGHT_MODEL;
    }


    // Переопределение функций интерфейса
    @Override
    public void move()
    {
        x = x - (int)road.getPlayer().getSpeed() + speed;
    }

    @Override
    public Rectangle getRect()
    {
        return new Rectangle(this.x,  this.y, this.widthModel, this.heightModel);
    }


    @Override
    public int getX() { return this.x; }
    @Override
    public int getY() { return this.y; }
    @Override
    public int getSpeed() { return this.speed; }
}
