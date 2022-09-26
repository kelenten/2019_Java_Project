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

public class AttackDiamond extends Items {
    private boolean exist = true;

    public AttackDiamond() {
        super();
    }

    @Override
    public String getAppearance() {
        return "攻击宝石";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        if(exist) {
            Image p17 = new Image("file:pic/Items/攻击宝石.png");
            ImageView v17 = new ImageView(p17);
            stackPane.getChildren().add(v17);
        }
    }

    @Override
    public void affectWith(Player player) {
        if(exist){
            System.out.println("拾得攻击宝石");
            player.setAttack(player.getAttack() + 2);
            exist =false;
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
