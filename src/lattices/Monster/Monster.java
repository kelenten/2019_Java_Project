package lattices.Monster;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lattices.Lattice;
import lattices.Player;

public class Monster extends Lattice {
    private int blood;
    private int attack;
    private int defence;
    private int gold;
    private boolean exist;
    private int attackFrequency = 0;


    public Monster() {
        super();
    }

    public int getAttackFrequency(){
        return attackFrequency;
    }

    @Override
    public String getAppearance(){
        return "";
    }

    public void setExist(boolean flag){
    }

    public ImageView getPic(){
        ImageView imageView  = new ImageView(new Image("file:"));
        return imageView;
    }
    @Override
    public void affectWith(Player player) throws InterruptedException {}

    @Override
    public void getAnimation(StackPane stackPane){}

    @Override
    public boolean getExist(){
        return true;
    }

    public int getAttack(){
        return 0;
    }

    public int getBlood(){
        return 0;
    }

    public int getDefence(){
        return 0;
    }

    public int getGold(){
        return 0;
    }
}