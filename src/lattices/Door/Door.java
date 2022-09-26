package lattices.Door;

import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import lattices.Lattice;
import lattices.Player;

public abstract class Door extends Lattice {

    protected Door() {
        super();
    }

    @Override
    public abstract String getAppearance();

//    @Override
//    public abstract void getAnimation(StackPane stackPane);

    @Override
    public abstract boolean getExist();

    @Override
    public abstract void affectWith(Player player);
}