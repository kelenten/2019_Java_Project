package lattices.Door;

import gui.Video;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lattices.Player;

import java.io.File;

public class RedDoor extends Door{
    private boolean exist = true;

    public RedDoor() {
        super();
    }

    @Override
    public String getAppearance() {
        return "红门";
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            if(player.getRedKey() > 0){
                player.setRedKey(player.getRedKey() - 1);
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
        Image p9 = new Image("file:pic/Constructions/红门.png");
        ImageView v9 = new ImageView(p9);
        stackPane.getChildren().add(v9);
    }
}