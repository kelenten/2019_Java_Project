package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lattices.Monster.Bat.BigBat;
import lattices.Monster.Bat.SmallBat;
import lattices.Monster.Monster;
import lattices.Monster.Skeleton.SkeletonCaptain;
import lattices.Monster.Skeleton.SkeletonMan;
import lattices.Monster.Skeleton.SkeletonSoldier;
import lattices.Monster.Slime.BlackSlime;
import lattices.Monster.Slime.GreenSlime;
import lattices.Monster.Slime.RedSlime;
import lattices.Monster.StoneMan;
import lattices.Player;
import motamap.MotaMap;

public class MonsterBrochure {

    public static Stage getMonsterBrochure() {
        MotaMap map = GUI.getMap();
        Player player = map.getPlayer();
        boolean stoneMan = true;
        boolean greenSlime = true;
        boolean redSlime = true;
        boolean blackSlime = true;
        boolean skMan = true;
        boolean skSoldier = true;
        boolean skCaptain = true;
        boolean smallBat = true;
        boolean bigBat = true;

        Font font = new Font("方正粗黑宋简体", 20);

        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(11, 12, 13, 14));

        HBox hBox = new HBox();
        vBox.getChildren().add(hBox);
        Label label1 = new Label("头像");
        label1.setFont(font);
        setWidthHeight(label1, 100, 33);
        Label label2 = new Label("名称");
        label2.setFont(font);
        setWidthHeight(label2, 100, 33);
        Label label3 = new Label("血量");
        label3.setFont(font);
        setWidthHeight(label3, 100, 33);
        Label label4 = new Label("攻击力");
        label4.setFont(font);
        setWidthHeight(label4, 100, 33);
        Label label5 = new Label("防御力");
        label5.setFont(font);
        setWidthHeight(label5, 100, 33);
        Label label6 = new Label("金币");
        label6.setFont(font);
        setWidthHeight(label6, 100, 33);
        Label label7 = new Label("战斗结果");
        label7.setFont(font);
        setWidthHeight(label7, 100, 33);

        hBox.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7);


        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if( map.getLattices(player.getPosition(0), i, j) instanceof Monster && map.getLattices(player.getPosition(0), i, j).getExist() ) {
                    if( map.getLattices(player.getPosition(0), i, j) instanceof GreenSlime && greenSlime == true ) {
                        greenSlime = false;
                        GreenSlime m = new GreenSlime();
                        vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                    }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof RedSlime && redSlime == true ) {
                            redSlime = false;
                            RedSlime m = new RedSlime();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof BlackSlime && blackSlime == true ) {
                            blackSlime = false;
                            BlackSlime m = new BlackSlime();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof SkeletonMan && skMan == true ) {
                            skMan = false;
                            SkeletonMan m = new SkeletonMan();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof SkeletonSoldier && skSoldier == true ) {
                            skSoldier = false;
                            SkeletonSoldier m = new SkeletonSoldier();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof SkeletonCaptain && skCaptain == true ) {
                            skCaptain = false;
                            SkeletonCaptain m = new SkeletonCaptain();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof SmallBat && smallBat == true ) {
                            smallBat = false;
                            SmallBat m = new SmallBat();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof BigBat && bigBat == true ) {
                            bigBat = false;
                            BigBat m = new BigBat();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                        if( map.getLattices(player.getPosition(0), i, j) instanceof StoneMan && stoneMan == true ) {
                            stoneMan = false;
                            StoneMan m = new StoneMan();
                            vBox.getChildren().add(m.getHBox(new virtualPlayer(player)));
                        }
                    }
                }
            }

            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.setTitle("怪物手册");
            stage.show();

            return stage;
        }


    static class virtualPlayer extends Player{
        private virtualPlayer(Player player){
            this.setBlood(player.getBlood());
            this.setAttack(player.getAttack());
            this.setDefence(player.getDefence());
            this.setGold(player.getGold());
            this.setYellowKey(player.getYellowKey());
            this.setBlueKey(player.getBlueKey());
            this.setRedKey(player.getRedKey());
        }
    }

    //设置组件大小
    private static void setWidthHeight(Region region , int width , int height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }
}
