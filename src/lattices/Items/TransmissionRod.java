package lattices.Items;

import gui.Video;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lattices.Player;

import java.io.File;

public class TransmissionRod extends Items {
    private boolean exist = true;

    public TransmissionRod() {
        super();
    }

    @Override
    public String getAppearance() {
        return "传送权杖";
    }

    @Override
    public void affectWith(Player player) {
        Video.getVideo(Video.s4);
        System.out.println("拾得传送权杖");
        System.exit(0);
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
        Image image = new Image("file:pic/Items/传送权杖.png");
        ImageView imageView = new ImageView(image);
        stackPane.getChildren().add(imageView);
    }
}