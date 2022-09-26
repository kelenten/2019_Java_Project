package lattices;

import javafx.scene.layout.StackPane;

public abstract class Lattice {

    private boolean exist;

    {exist = false;}

    protected Lattice() {

    }

    public abstract void affectWith(Player player) throws InterruptedException;

    public abstract String getAppearance();

    public abstract void getAnimation(StackPane stackPane);

    public abstract boolean getExist();

    public abstract void setExist(boolean flag);
}
