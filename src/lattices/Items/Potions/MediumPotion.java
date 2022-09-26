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

public class MediumPotion extends Items {
    private boolean exist = true;

    public MediumPotion() {
        super();
    }

    @Override
    public String getAppearance() {
        return "中血瓶";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist) {
            Image p15 = new Image("file:pic/Items/中血瓶.png");
            ImageView v15 = new ImageView(p15);
            stackPane.getChildren().add(v15);
        }
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得中血瓶");
            player.setBlood(player.getBlood() + 100);
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