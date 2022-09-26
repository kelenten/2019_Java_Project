package lattices.Stairs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lattices.Lattice;
import lattices.Player;

public class UpStairs extends Lattice {

    public UpStairs() {
        super();
    }

    @Override
    public String getAppearance() {
        return "上楼楼梯";
    }

    @Override
    public boolean getExist(){
        return true;
    }

    public void setExist(boolean flag){
    }

    @Override
    public void affectWith(Player player) {
        System.out.println("上楼。");
        if(player.getPosition(0) == 0)
            player.move3(1,-1);
        else player.move3(1,0);
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image p3 = new Image("file:pic/Constructions/上楼楼梯.png");
        ImageView v3 = new ImageView(p3);
        stackPane.getChildren().add(v3);
    }
}