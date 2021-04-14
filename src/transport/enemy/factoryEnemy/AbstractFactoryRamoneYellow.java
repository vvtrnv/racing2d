package transport.enemy.factoryEnemy;

import transport.enemy.Enemy;
import transport.enemy.RamoneYellow;
import view.Road;

public class AbstractFactoryRamoneYellow implements AbstractFactoryEnemy
{
    @Override
    public Enemy enemyBorn(int x, int y, int speed, Road road)
    {
        return new RamoneYellow(x, y, speed, road);
    }
}
