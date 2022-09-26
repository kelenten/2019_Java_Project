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

public class YellowKey extends Items {
    public boolean exist = true;

    public YellowKey() {
        super();
    }

    @Override
    public String getAppearance() {
        return "黄钥匙";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist) {
            Image p10 = new Image("file:pic/Items/黄钥匙.png");
            ImageView v10 = new ImageView(p10);
            stackPane.getChildren().add(v10);
        }
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得黄钥匙");
            player.setYellowKey(player.getYellowKey() + 1);
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