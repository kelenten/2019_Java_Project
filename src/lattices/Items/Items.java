package lattices.Items;

import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import lattices.Lattice;
import lattices.Player;

public abstract class Items extends Lattice {
    private boolean exist;

    protected Items() {
        super();
    }

    @Override
    public abstract boolean getExist();

    @Override
    public abstract String getAppearance();

//    @Override
//    public abstract void getAnimation(StackPane stackPane);

    @Override
    public abstract void affectWith(Player player);
}



