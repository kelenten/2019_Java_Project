package lattices.Door;

import gui.Video;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lattices.Player;

import java.io.File;

public class BlueDoor extends Door{
    private boolean exist = true;

    public BlueDoor() {
        super();
        exist = true;
    }

    @Override
    public String getAppearance() {
        if(exist)
            return "蓝门";
        return "空";
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            if(player.getBlueKey() > 0){
                player.setBlueKey(player.getBlueKey() - 1);
                System.out.println("开门了。");
                Video.getVideo(Video.s3);
                exist = false;
            }
            else {
                System.out.println("你开不了门");
                return;
            }
        }
        player.move2();
    }

    @Override
    public boolean getExist(){
        return exist;
    }

    public void setExist(boolean flag){
        exist = flag;
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image p8 = new Image("file:pic/Constructions/蓝门.png");
        ImageView v8 = new ImageView(p8);
        stackPane.getChildren().add(v8);
    }
}