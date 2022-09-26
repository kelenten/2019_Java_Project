package lattices.Items.Diamond;

import gui.Video;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lattices.Items.Items;
import lattices.Player;

import java.io.File;

public class DefenceDiamond extends Items {
    private boolean exist = true;

    public DefenceDiamond() {
        super();
    }

    @Override
    public String getAppearance() {
        return "防御宝石";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist){
            Image p18 = new Image("file:pic/Items/防御宝石.png");
            ImageView v18 = new ImageView(p18);
            stackPane.getChildren().add(v18);
        }
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得防御宝石");
            player.setDefence(player.getDefence() + 2);
            exist=false;
            Video.getVideo(Video.s4);
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