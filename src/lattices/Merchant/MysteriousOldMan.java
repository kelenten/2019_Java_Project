package lattices.Merchant;

import gui.GUI;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lattices.Player;

public class MysteriousOldMan extends Merchant{
    int yKBuyTimes;
    int bKBuyTimes;
    int rKBuyTimes;

    public MysteriousOldMan() {
        super();
        yKBuyTimes = 0;
        bKBuyTimes = 0;
        rKBuyTimes = 0;
    }

    @Override
    public String getAppearance() {
        return "防御商人";
    }

    @Override
    public boolean getExist(){
        return true;
    }

    @Override
    public void affectWith(Player player) {
        player.move1();
        Stage stage = getStage(player);
        stage.show();
    }

    public Stage getStage(Player player){
        Stage primaryStage = new Stage();

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(11,11.5,12,12.5));
        vBox.setSpacing(10);

        Font font = new Font("方正粗黑宋简体" , 20);
        Label label1 = new Label("神秘老人:"+"\n"+"如果你给我一些金币，我可以帮你提升一种能力");
        label1.setFont(font);
        label1.setWrapText(true);
        setWidthHeight(label1,400,120);

        Button bt1 = new Button("使用" + 20*(int)Math.pow(2,yKBuyTimes)+ "金币购买一个黄钥匙");
        bt1.setFont(font);
        setWidthHeight(bt1,400,40);
        bt1.setOnMouseClicked(event -> {
            if(player.getGold() >= (int)Math.pow(2,yKBuyTimes)) {
                player.setYellowKey(player.getYellowKey() + 1);
                player.setGold(player.getGold() - (int)Math.pow(2,yKBuyTimes));
                yKBuyTimes++;
                primaryStage.close();
                Stage stage = this.getStage(player);
                stage.show();
                GUI.update6();
            } else System.out.println("你没有足够的金币。");
        });
        Button bt2 = new Button( "使用" + 30*(int)Math.pow(2,bKBuyTimes) + "金币购买一个蓝钥匙");
        bt2.setFont(font);
        setWidthHeight(bt2,400,40);
        bt2.setOnMouseClicked(event -> {
            if(player.getGold() >= 30*(int)Math.pow(2,bKBuyTimes)) {
                player.setBlueKey(player.getBlueKey() + 1);
                player.setGold(player.getGold() - 30*(int)Math.pow(2,bKBuyTimes));
                bKBuyTimes++;
                primaryStage.close();
                Stage stage = this.getStage(player);
                stage.show();
                GUI.update6();
            } else System.out.println("你没有足够的金币。");
        });

        Button bt3 = new Button("使用" + 40*(int)Math.pow(2,rKBuyTimes) + "金币购买一个红钥匙");
        bt3.setFont(font);
        setWidthHeight(bt3,400,40);
        bt3.setOnMouseClicked(event -> {
            if(player.getGold() >= 40*(int)Math.pow(2,rKBuyTimes)) {
                player.setRedKey(player.getRedKey() + 1);
                player.setGold(player.getGold() - 40*(int)Math.pow(2,rKBuyTimes));
                rKBuyTimes++;
                primaryStage.close();
                Stage stage = this.getStage(player);
                stage.show();
                GUI.update6();
            } else System.out.println("你没有足够的金币。");
        });

        Button bt4 = new Button("下次一定！");
        bt4.setFont(font);
        setWidthHeight(bt4,400,40);
        bt4.setOnMouseClicked(event -> {
            primaryStage.close();
        });

        vBox.getChildren().add(label1);
        vBox.getChildren().add(bt1);
        vBox.getChildren().add(bt2);
        vBox.getChildren().add(bt3);
        vBox.getChildren().add(bt4);

        Scene scene = new Scene(vBox);
        primaryStage.setTitle("对话");
        primaryStage.setScene(scene);
        return primaryStage;
    }

    public void setExist(boolean flag){
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image image = new Image("file:pic/NPC/老人1.png");
        ImageView imageView = new ImageView(image);
        stackPane.getChildren().add(imageView);
    }

    private void setWidthHeight(Region region , int width , int height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }
}
