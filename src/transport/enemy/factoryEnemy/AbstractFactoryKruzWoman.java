package transport.enemy.factoryEnemy;

import transport.enemy.Enemy;
import transport.enemy.KruzWoman;
import view.Road;

public class AbstractFactoryKruzWoman implements AbstractFactoryEnemy
{
    @Override
    public Enemy enemyBorn(int x, int y, int speed, Road road)
    {
        return new KruzWoman(x, y, speed, road);
    }
}
