package lattices.Monster.Skeleton;

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

public class SkeletonSoldier extends Monster {
    private int blood = 200;
    private int attack = 100;
    private int defence = 5;
    private int gold = 8;
    private int specialAttackRate = 25;
    private boolean exist = true;
    private int attackFrequency = 0;


    public SkeletonSoldier(){
        super();
    }

    @Override
    public String getAppearance() {
        if(exist) {
            return "骷髅士兵";
        } else return "";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image i1 = new Image("file:pic/Monsters/骷髅士兵1.png");//形态一
        Image i2 = new Image("file:pic/Monsters/骷髅士兵2.png");//形态二
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
        loop:while (exist){
            if (player.getAttack() > this.defence) {
                attackFrequency++;
                this.blood -= (player.getAttack() - this.defence);
            }else{
                System.out.print("你打不过。");
                return;
            }
            if(this.blood > 0) {
                attackFrequency++;
                int specialAttack = (int)(Math.random()*99 + 1);
                if(specialAttack <= specialAttackRate){
                    int actualDefence = (int)Math.ceil(0.8* player.getDefence());
                    if (this.attack > actualDefence) {
                        player.setBlood(player.getBlood() - (this.attack - actualDefence));
                    }
                } else if (this.attack > player.getDefence()) {
                    player.setBlood(player.getBlood() - (this.attack - player.getDefence()));
                }
                if(player.getBlood() <= 0) {
                    System.out.println("你被骷髅士兵击杀，游戏失败！");
                    System.exit(0);
                }
            } else {
                System.out.println("击败骷髅士兵!");
                player.setGold(player.getGold() + this.gold);
                player.setExp(player.getExp() + 200);
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
        }
        player.move4(this);
    }

    public String virtualAttack(Player player){
        int vBlood = this.blood;
        int total = 0;
        int sAF = 0;
        while (true){
            if (player.getAttack() > defence) {
                vBlood -= (player.getAttack() - defence);
            }else{
                return "你攻击力太低，打不过。";
            }
            if(vBlood> 0) {
                int specialAttack = (int)(Math.random()*99 + 1);
                if(specialAttack <= specialAttackRate){
                    sAF++;
                    int actualDefence = (int)Math.ceil(0.8* player.getDefence());
                    int actualAttack = (int)Math.ceil(1.2*attack);
                    if (actualAttack > actualDefence) {
                        total += (actualAttack- actualDefence);
                        player.setBlood(player.getBlood() - (actualAttack- actualDefence));
                    }
                } else if (attack > player.getDefence()) {
                    total += attack - player.getDefence();
                    player.setBlood(player.getBlood() - (attack - player.getDefence()));
                }
                if(player.getBlood() <= 0) {
                    return "你可能被骷髅士兵击杀！共受到伤害"+total+"，其中特殊攻击"+sAF+"次";
                }
            } else {
                player.setGold(player.getGold() + gold);
                return "你可能击败骷髅士兵，共受到伤害" +total + "，其中特殊攻击"+sAF+"次";
            }
        }
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

    public ImageView getPic(){
        ImageView imageView;
        if(exist){
            imageView = new ImageView(new Image("file:pic/Monsters/骷髅士兵1.png"));
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