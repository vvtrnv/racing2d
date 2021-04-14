package transport.enemy.factoryEnemy;

import transport.enemy.Enemy;
import transport.enemy.JacksonBlack;
import view.Road;

public class AbstractFactoryJacksonBlack implements AbstractFactoryEnemy
{
    @Override
    public Enemy enemyBorn(int x, int y, int speed, Road road)
    {
        return new JacksonBlack(x, y, speed, road);
    }
}
