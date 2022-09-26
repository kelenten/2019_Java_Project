package lattices.Stairs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lattices.Lattice;
import lattices.Player;


public class DownStairs extends Lattice {

    public DownStairs() {
        super();
    }

    @Override
    public String getAppearance() {
        return "下楼楼梯";
    }

    @Override
    public boolean getExist(){
        return true;
    }

    public void setExist(boolean flag){
    }

    @Override
    public void affectWith(Player player) {
        System.out.println("下楼。");
        if(player.getPosition(0) == 1)
            player.move3(-1,1);
        else player.move3(-1,0);
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image p6 = new Image("file:pic/Constructions/下楼楼梯.png");
        ImageView v6 = new ImageView(p6);
        stackPane.getChildren().add(v6);
    }
}
