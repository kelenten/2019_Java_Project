package lattices.Items.Potions;

import gui.Video;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lattices.Items.Items;
import lattices.Player;

import java.io.File;


public class BigPotion extends Items {
    private boolean exist = true;

    public BigPotion() {
        super();

    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist) {
            Image p16 = new Image("file:pic/Items/大血瓶.png");
            ImageView v16 = new ImageView(p16);
            stackPane.getChildren().add(v16);
        }
    }

    @Override
    public String getAppearance() {
        return "大血瓶";
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得大血瓶");
            player.setBlood(player.getBlood() + 250);
            exist =false;
            Video.getVideo(Video.s5);
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
