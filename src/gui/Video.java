package gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Video {
    public static String s1 = "audio/攻击.mp3";
    public static String s2 = "audio/战斗结束.mp3";
    public static String s3 = "audio/开门.mp3";
    public static String s4 = "audio/拾取.mp3";
    public static String s5 = "audio/得到血瓶.mp3";
    public static String s6 = "audio/脚步声.mp3";

    public static void getVideo(String s){
        File file = new File(s);
        String url = file.toURI().toString();
        Media media = new Media(url);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
