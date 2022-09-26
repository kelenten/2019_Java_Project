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

public class BlueKey extends Items {
    private boolean exist = true;

    public BlueKey() {
        super();
    }

    @Override
    public String getAppearance() {
        return "蓝钥匙";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist) {
            Image p11 = new Image("file:pic/Items/蓝钥匙.png");
            ImageView v11 = new ImageView(p11);
            stackPane.getChildren().add(v11);
        }
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得蓝钥匙");
            player.setBlueKey(player.getBlueKey() + 1);
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
