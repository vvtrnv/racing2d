package transport.enemy;

import view.Road;

import javax.swing.*;
import java.awt.*;

public interface IEnemy
{
    // Получить размеры автомобиля
    public Rectangle getRect();

    // Движение
    public void move();

    // Получить значения полей
    public int getX();
    public int getY();
    public int getSpeed();
}
