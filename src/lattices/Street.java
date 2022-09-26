package lattices;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Street extends Lattice{

    public Street(){
        super();
    }

    @Override
    public String getAppearance(){
        return "空";
    }

    @Override
    public boolean getExist(){
        return true;
    }

    @Override
    public void affectWith(Player player){
        player.move1();
    }

    @Override
    public void setExist(boolean flag){
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image image = new Image("file:pic/Constructions/路.png");
        ImageView imageView = new ImageView(image);
        stackPane.getChildren().add(imageView);
    }
}
