package lattices.Door;

import gui.Video;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lattices.Player;

import java.io.File;

public class YellowDoor extends Door{
    private boolean exist;

    public YellowDoor() {
        super();
        exist = true;
    }

    @Override
    public String getAppearance() {
        return "黄门";
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            if(player.getYellowKey() > 0){
                player.setYellowKey(player.getYellowKey() - 1);
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
        Image p7 = new Image("file:pic/Constructions/黄门.png");
        ImageView v7 = new ImageView(p7);
        stackPane.getChildren().add(v7);
    }
}

