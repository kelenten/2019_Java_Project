package lattices.Merchant;

import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import lattices.Lattice;
import lattices.Player;
import motamap.MotaMap;

public abstract class Merchant extends Lattice {

    protected Merchant() {
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
