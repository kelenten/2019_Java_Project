package motamap;

import lattices.*;
import lattices.Door.*;
import lattices.Items.*;
import lattices.Items.Diamond.AttackDiamond;
import lattices.Items.Diamond.DefenceDiamond;
import lattices.Items.Keys.BlueKey;
import lattices.Items.Keys.RedKey;
import lattices.Items.Keys.YellowKey;
import lattices.Items.Potions.BigPotion;
import lattices.Items.Potions.MediumPotion;
import lattices.Items.Potions.SmallPotion;
import lattices.Merchant.*;
import lattices.Monster.Bat.*;
import lattices.Monster.Slime.*;
import lattices.Monster.Skeleton.*;
import lattices.Monster.*;
import lattices.Stairs.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MotaMap {
    private Lattice[][][] lattices;
    private Player player;

    public MotaMap(Player player) {
        try{
            this.lattices = this.initializeMap();
        }catch(FileNotFoundException ex){
            ex.fillInStackTrace();
        }
        this.player = player;
    }

    public Lattice[][][] initializeMap() throws FileNotFoundException {
        Lattice[][][] map = new Lattice[5][13][13];
        File file;
        Scanner input;
        for (int i = 0; i < 5; i++) {
            String pathName = "map/" + i + ".txt";
            file = new File(pathName);
            input = new Scanner(file);
            for (int j = 0; j < 13; j++) {
                String l = input.nextLine();
                for (int k = 0; k < 13; k++) {
                    String str = l.substring(k,k + 1);
                    switch (str) {
                        case"b":
                            map[i][j][k] = new Wall();
                            break;
                        case"a":
                            map[i][j][k] = new Street();
                            break;
                        case"c":
                            map[i][j][k] = new GreenSlime();
                            break;
                        case"d":
                            map[i][j][k] = new RedSlime();
                            break;
                        case"e":
                            map[i][j][k] = new BlackSlime();
                            break;
                        case"f":
                            map[i][j][k] = new SmallBat();
                            break;
                        case"F":
                            map[i][j][k] = new BigBat();
                            break;
                        case"g":
                            map[i][j][k] = new SkeletonMan();
                            break;
                        case"h":
                            map[i][j][k] = new SkeletonSoldier();
                            break;
                        case"i":
                            map[i][j][k] = new UpStairs();
                            break;
                        case"j":
                            map[i][j][k] = new DownStairs();
                            break;
                        case"k":
                            map[i][j][k] = new MysteriousMerchant();
                            break;
                        case"l":
                            map[i][j][k] = new MysteriousOldMan();
                            break;
                        case"m":
                            map[i][j][k] = new AttackDiamond();
                            break;
                        case"n":
                            map[i][j][k] = new DefenceDiamond();
                            break;
                        case"o":
                            map[i][j][k] = new SmallPotion();
                            break;
                        case"p":
                            map[i][j][k] = new MediumPotion();
                            break;
                        case"q":
                            map[i][j][k] = new BigPotion();
                            break;
                        case"r":
                            map[i][j][k] = new YellowKey();
                            break;
                        case"s":
                            map[i][j][k] = new YellowDoor();
                            break;
                        case"t":
                            map[i][j][k] = new BlueKey();
                            break;
                        case"u":
                            map[i][j][k] = new BlueDoor();
                            break;
                        case"v":
                            map[i][j][k] = new RedKey();
                            break;
                        case"w":
                            map[i][j][k] = new RedDoor();
                            break;
                        case"x":
                            map[i][j][k] = new SkeletonCaptain();
                            break;
                        case"y":
                            map[i][j][k] = new TransmissionRod();
                            break;
                        case"z":
                            map[i][j][k] = new StoneMan();
                            break;
                    }
                }
            }
            input.close();
        }
        return map;
    }

    // 勇者确定目标后更新地图
    public void update(int[] targetPosition) {
        Lattice target = lattices[targetPosition[0]][targetPosition[1]][targetPosition[2]];
        try {
            target.affectWith(player);
        } catch (InterruptedException ex){
            ex.fillInStackTrace();
        }
    }

    public Lattice getLattices(int z,int y,int x){
        return lattices[z][y][x];
    }

    public Player getPlayer() {
        return player;
    }

    public void setLattices(Lattice[][][] lattices){
        this.lattices = lattices;
    }

}
