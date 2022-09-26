package lattices.Items.Keys;

import gui.Video;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lattices.Items.Items;
import lattices.Player;

import java.io.File;

public class RedKey extends Items {
    private boolean exist = true;

    public RedKey() {
        super();
    }

    @Override
    public String getAppearance() {
        return "红钥匙";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist) {
            Image p12 = new Image("file:pic/Items/红钥匙.png");
            ImageView v12 = new ImageView(p12);
            stackPane.getChildren().add(v12);
        }
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得红钥匙");
            player.setRedKey(player.getRedKey() + 1);
            Video.getVideo(Video.s4);
            exist =false;
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
}
