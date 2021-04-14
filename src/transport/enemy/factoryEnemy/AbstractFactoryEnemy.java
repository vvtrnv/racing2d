package transport.enemy.factoryEnemy;

import transport.enemy.Enemy;
import view.Road;

public interface AbstractFactoryEnemy
{
    Enemy enemyBorn(int x, int y, int speed, Road road);
}
