package lattices.Monster.Bat;

import gui.GUI;
import gui.Video;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import lattices.Monster.Monster;
import lattices.Player;

import java.util.Timer;
import java.util.TimerTask;

public class SmallBat extends Monster {
    private int blood = 50;
    private int attack = 20;
    private int defence = 1;
    private int gold = 1;
    private double poison = 0.99;
    private int additionalAt = 5;
    private boolean exist = true;
    private int attackFrequency = 0;


    public SmallBat() {
        super();
    }

    @Override
    public String getAppearance() {
        if(exist) {
            return "小蝙蝠";
        } else return "";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image i1 = new Image("file:pic/Monsters/蝙蝠1.png");//形态一
        Image i2 = new Image("file:pic/Monsters/蝙蝠2.png");//形态二
        ImageView v1 = new ImageView(i1);
        stackPane.getChildren().add(v1);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);//设置周期运行循环往复
        timeline.setAutoReverse(true);//设置动画的自动往返效果
        KeyValue keyValue1 = new KeyValue(v1.xProperty() , 0);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0) , "kv1" , event -> v1.setImage(i1) , keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.9) , "kv2" , event -> v1.setImage(i2) , keyValue1);
        timeline.getKeyFrames().addAll(keyFrame1 , keyFrame2);
        timeline.play();
    }

    @Override
    public void affectWith(Player player) {
        GUI.update5(this);
        boolean poison = false;
        int playerHP = player.getBlood();
        int blood = this.blood;
        loop:while (exist){
            if(poison) {
                playerHP = (int) Math.ceil(playerHP * this.poison);
                if(playerHP <= 0) {
                    System.out.println("你打不过。");
                    return;
                }
            }
            if (player.getAttack() > this.defence) {
                attackFrequency++;
                blood -= (player.getAttack() - this.defence);
            }else{
                System.out.print("你打不过。");
                return;
            }
            if(blood > 0) {
                attackFrequency++;
                poison = true;
                if ((this.attack - player.getDefence()) > 0) {

                    playerHP -= (this.attack - player.getDefence() + additionalAt);
                } else playerHP -= additionalAt;
            } else {
                System.out.println("击败小蝙蝠!");
                this.blood = blood;
                player.setBlood(playerHP);
                player.setGold(player.getGold() + this.gold);
                player.setExp(player.getExp() + 50);
                Timer timer = new Timer();
                int i = 0;
                for(;i < attackFrequency;i++){
                    timer.schedule(new TimerTask() {
                        public void run() {
                            Video.getVideo(Video.s1);
                        }
                    }, 200*i);}
                timer.schedule(new TimerTask() {
                    public void run() {
                        Video.getVideo(Video.s2);
                    }
                }, 200*i);
                break loop;
            }
            if(playerHP <= 0){
                System.out.println("你打不过。");
                return;
            }
        }
        player.move4(this);
    }

    public int getAttackFrequency(){
        return attackFrequency;
    }
    @Override
    public boolean getExist(){
        return exist;
    }

    public void setExist(boolean flag){
        exist = flag;
    }

    public String virtualAttack(Player player){
        int vBlood = this.blood;
        int total = 0;
        int exD = 0;
        boolean poison = false;
        loop:while (true){
            if(poison) {
                exD += player.getBlood()-(int) Math.ceil(player.getBlood() * this.poison);
                total += exD;
                player.setBlood((int) Math.ceil(player.getBlood() * this.poison));
                if(player.getBlood() <= 0) {
                    return "你打不过，将受到" + total + "点伤害，其中中毒和额外伤害共" + exD;
                }
            }
            if (player.getAttack() > defence) {
                vBlood -= (player.getAttack() - defence);
            }else{
                return "您的攻击力太低，打不过。";
            }
            if(vBlood > 0) {
                exD += additionalAt;
                poison = true;
                if ((attack - player.getDefence()) > 0) {
                    total += (attack - player.getDefence() + additionalAt);
                    player.setBlood(player.getBlood() - (attack - player.getDefence() + additionalAt));
                } else {
                    total += additionalAt;
                    player.setBlood(player.getBlood() - additionalAt);
                }
            } else {
                player.setGold(player.getGold() + this.gold);
                return "可以击败小蝙蝠，共受到" + total + "点伤害，其中中毒和额外伤害共" + exD;
            }
            if(player.getBlood() <= 0){
                return "你打不过，将受到" + total + "点伤害，其中中毒和额外伤害共" + exD;
            }
        }
    }

    public ImageView getPic(){
        ImageView imageView;
        if(exist){
            imageView = new ImageView(new Image("file:pic/Monsters/蝙蝠1.png"));
            return imageView;
        } else{
            imageView = new ImageView(new Image("file:"));
            return imageView;
        }
    }
    public int getAttack(){
        if(exist)
            return attack;
        else return 0;
    }

    public int getBlood(){
        if(exist)
            return blood;
        else return 0;
    }

    public int getDefence(){
        if(exist)
            return defence;
        else return 0;
    }

    public int getGold(){
        if(exist)
            return gold;
        else return 0;
    }

    public HBox getHBox(Player player){
        HBox hBox = new HBox();
        Font font = new Font("方正粗黑宋简体" , 20);

        Label label1 = new Label("",getPic());
        setWidthHeight(label1,100,33);
        Label label2 = new Label(getAppearance());
        label2.setFont(font);
        setWidthHeight(label2,100,33);
        Label label3 = new Label("" + blood);
        label3.setFont(font);
        setWidthHeight(label3,100,33);
        Label label4 = new Label("" + attack);
        label4.setFont(font);
        setWidthHeight(label4,100,33);
        Label label5 = new Label("" + defence);
        label5.setFont(font);
        setWidthHeight(label5,100,33);
        Label label6 = new Label("" + gold);
        label6.setFont(font);
        setWidthHeight(label6,100,33);
        Label label7 = new Label("" + virtualAttack(player));
        label7.setFont(font);
        setWidthHeight(label7,800,33);

        hBox.getChildren().addAll(label1,label2,label3,label4,label5,label6,label7);
        return hBox;
    }

    private static void setWidthHeight(Region region , int width , int height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }
}
