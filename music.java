/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonsimulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Fitri
 */
public class music {
        private static AudioPlayer MGP = AudioPlayer.player;
        private static AudioStream BGM;
        private static AudioData MD;
    public static void intromusic() {
       
       

        

        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("C:\\Users\\LEGION\\Documents\\Courses\\Fundamentals of Programming\\PokemonSimulator\\res\\intromusic.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
    }
    
    public static void battlemusic() {
            AudioPlayer.player.stop(BGM);
        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("C:\\Users\\LEGION\\Documents\\Courses\\Fundamentals of Programming\\PokemonSimulator\\res\\battlemusic.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
    }
    
    public static void victorymusic() {
            AudioPlayer.player.stop(BGM);
        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("C:\\Users\\LEGION\\Documents\\Courses\\Fundamentals of Programming\\PokemonSimulator\\res\\victorymusic.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
    }

}
