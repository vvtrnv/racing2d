package threads;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioThread
{
    private String pathToAudio;
    private Clip clip = null;
    private FloatControl volumeC = null; // Контроллер громкости
    private double wt; // Уровень громкости

    public AudioThread(String pathToAudio, double wt)
    {
        this.pathToAudio = pathToAudio;
        this.wt = wt;
    }

    public void sound() {
        File file = new File(pathToAudio);

        // Поток для записи и считывания
        AudioInputStream tr = null; // Объект потока AudioInputStream пуст
        try {
            tr = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            // Старт вопроизведения
            clip = AudioSystem.getClip(); // Получаем реализацию интерфейса Clip
            clip.open(tr); // Загружаем звуковой поток в Clip

            // Получаем контроллер громкости
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            clip.setFramePosition(0); // Устанавливаем указатель на старт
            clip.start();
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setVolumeC()
    {
        if(wt < 0) wt = 0;
        if(wt > 1) wt = 1;

        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max - min) * (float) wt + min);
    }

    public void stopSound()
    {
        this.clip.stop();
    }

}
