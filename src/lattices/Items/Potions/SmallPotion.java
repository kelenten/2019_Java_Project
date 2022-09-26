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

public class SmallPotion extends Items {
    private boolean exist = true;

    public SmallPotion() {
        super();
    }

    @Override
    public String getAppearance() {
        return "小血瓶";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist) {
            Image p14 = new Image("file:pic/Items/小血瓶.png");
            ImageView v14 = new ImageView(p14);
            stackPane.getChildren().add(v14);
        }
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得小血瓶");
            player.setBlood(player.getBlood() + 50);
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