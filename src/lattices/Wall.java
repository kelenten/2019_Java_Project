package lattices;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import motamap.MotaMap;

public class Wall extends Lattice {

    public Wall() {
        super();
    }

    @Override
    public String getAppearance(){
        return "墙";
    }

    @Override
    public boolean getExist(){
        return true;
    }

    @Override
    public void affectWith(Player player) {
        System.out.println("撞墙了！");
    }

    public void setExist(boolean flag){
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image image = new Image("file:pic/Constructions/墙壁.png");
        ImageView imageView = new ImageView(image);
        stackPane.getChildren().add(imageView);
    }
}
