package view;

import objects.Fuel;
import threads.Audio;
import transport.Player;
import transport.enemy.Enemy;
import transport.enemy.JacksonBlack;
import transport.enemy.KruzWoman;
import transport.enemy.RamoneYellow;
import transport.enemy.factoryEnemy.AbstractFactoryEnemy;
import transport.enemy.factoryEnemy.AbstractFactoryJacksonBlack;
import transport.enemy.factoryEnemy.AbstractFactoryKruzWoman;
import transport.enemy.factoryEnemy.AbstractFactoryRamoneYellow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Road extends JPanel implements ActionListener, Runnable
{
    private Image img = new ImageIcon("src/res/bg_road1.png").getImage();

    private Image img_boom = new ImageIcon("src/res/boom.png").getImage();
    private boolean boom = false;

    private Timer mainTimer = new Timer(20, this);

    private Player player = new Player();

    private Thread enemiesFactory = new Thread(this);
    private Audio takeFuel = new Audio("src/res/sound/takeFuel.WAV", 0.7); // Звук подбора топлива
    private Audio backgroundAudio = new Audio("src/res/sound/background.WAV", 0.5);

    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<Fuel> fuelList = new ArrayList<Fuel>();

    public Road()
    {
        mainTimer.start();
        enemiesFactory.start(); // Запуск потока

        // Музыка на заднем фоне
        backgroundAudio.sound();
        backgroundAudio.setVolumeC();

        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    // Метод отрисовки дороги
    public void paint(Graphics g)
    {
        g = (Graphics2D)g;

        // Отрисовка слоёв
        g.drawImage(img, player.getLayer1(), 0, null);
        g.drawImage(img, player.getLayer2(), 0, null);

        // Отрисовка игрока
        g.drawImage(player.getImg(), player.getX(), player.getY(), null);

        // Спидометр
        double speed = (200 / Player.MAX_SPEED) * player.getSpeed();
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Скорость: " + speed + "км/ч", 50, 755);

        //
        g.drawString("Объём бака: " + (int)player.getTank(), 300, 755);

        // Отрисовка врагов
        for(int i = 0; i < enemyList.size(); i++)
        {
            Enemy enemy = enemyList.get(i);

            if(enemy instanceof JacksonBlack)
                g.drawImage(JacksonBlack.image, enemy.getX(), enemy.getY(), null);
            else if(enemy instanceof KruzWoman)
                g.drawImage(KruzWoman.image, enemy.getX(), enemy.getY(), null);
            else if(enemy instanceof RamoneYellow)
                g.drawImage(RamoneYellow.image, enemy.getX(), enemy.getY(), null);
        }

        // Отрисовка канистр
        for(int i = 0; i < fuelList.size(); i++)
        {
            Fuel fuel = fuelList.get(i);
            g.drawImage(fuel.image, fuel.getX(), fuel.getY(), null);
        }

        // Нарисовать при столкновении
        if(boom) g.drawImage(img_boom, player.getX() - 40 , player.getY() - 110, null);
    }

    @Override
    public void run()
    {
        while(true)
        {
            Random rand = new Random();
            try
            {
                Thread.sleep(rand.nextInt(2800));

                // Другие машины
                AbstractFactoryEnemy factory = null;
                int chooseModel = rand.nextInt(3);
                if(chooseModel == 0)
                {
                    factory = new AbstractFactoryJacksonBlack();
                }
                if(chooseModel == 1)
                {
                    factory = new AbstractFactoryKruzWoman();
                }
                if(chooseModel == 2)
                {
                    factory = new AbstractFactoryRamoneYellow();
                }
                Enemy newEnemy = factory.enemyBorn(1200,
                        240 + rand.nextInt(380),
                        30 + rand.nextInt(12),
                        this);
                enemyList.add(newEnemy);

                // Канистры
                Fuel newObj = new Fuel( 1200, 240 + rand.nextInt(380), this);
                fuelList.add(newObj);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }


    // Привязка клавиш
    private class MyKeyAdapter extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {

            player.keyPressed(e);
        }

        public void keyReleased(KeyEvent e)
        {

            player.keyReleased(e);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        player.move();

        Fuel fuel;
        for(int i = 0; i < fuelList.size();i++)
        {
            fuel = fuelList.get(i);
            if(fuel.getX() < 2400 || fuel.getX() > -2400)
                // Движение врагов
                fuel.showOnRoad();
            else
                fuelList.remove(i);
        }

        Enemy enemy;
        for(int i = 0; i < enemyList.size(); i++)
        {
            enemy = enemyList.get(i);

            if(enemy.getX() < 2400 || enemy.getX() > -2400)
                // Движение врагов
                enemy.move();
            else
                enemyList.remove(i);
        }

        repaint();
        testCollisionWithEnemies();
        testPickUpFuel();
        testForEmptyTank();
    }

    private void testCollisionWithEnemies()
    {
        Iterator<Enemy> i = enemyList.iterator();
        while(i.hasNext())
        {
            Enemy enemy = i.next();
            // Проверка столкновений с врагами
            if(player.getRect().intersects(enemy.getRect()))
            {
                boom = true;
                JOptionPane.showMessageDialog(null, "LOSER!");

                System.exit(0);
            }
        }
    }

    private void testPickUpFuel()
    {
        for(int i = 0; i < fuelList.size();i++)
        {
            Fuel fuel = fuelList.get(i);

            if(player.getRect().intersects(fuel.getRect()))
            {
                takeFuel.sound();
                takeFuel.setVolumeC(); // Проигрывание звука
                player.fillTank(); // Устанавливаем громкость
                fuelList.remove(i);
                break;
            }
        }
    }

    public void testForEmptyTank()
    {
        if(player.getTank() == 0)
        {
            JOptionPane.showMessageDialog(null, "Какая неожиданность, мне неоткуда брать силы \nПоэтому я проиграл");
            System.exit(0);
        }
    }

    public Player getPlayer() { return player;}
}
