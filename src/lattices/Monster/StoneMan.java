package lattices.Monster;

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
import lattices.Player;

import java.util.Timer;
import java.util.TimerTask;

public class StoneMan extends Monster{
    private int blood = 70;
    private int attack = 60;
    private int defence = 50;
    private int gold = 8;
    private boolean exist;
    private int attackFrequency = 0;

    public int getAttackFrequency(){
        return attackFrequency;
    }
    public StoneMan() {
        super();
        exist = true;
    }

    @Override
    public boolean getExist(){
        return exist;
    }

    @Override
    public String getAppearance() {
        if(exist) {
            return "石头人";
        } else return "";
    }

    public ImageView getPic(){
        ImageView imageView;
        if(exist){
            imageView = new ImageView(new Image("file:pic/Monsters/石头人2.png"));
            return imageView;
        } else{
            imageView = new ImageView(new Image("file:"));
            return imageView;
        }
    }
    @Override
    public void affectWith(Player player) {
        GUI.update5(this);
        int playerHP = player.getBlood();
        int stoneManHP = this.blood;
        int attack = this.attack;
        int defence = this.defence;
        loop:while(exist){
            if (player.getAttack() > defence) {
                attackFrequency++;
                stoneManHP -= (player.getAttack() - defence);
            }else{
                System.out.print("你打不过。");
                return;
            }
            attack += 2;
            defence += 2;
            if(stoneManHP > 0) {
                attackFrequency++;
                if (attack > player.getDefence()) {
                    playerHP -= (attack - player.getDefence());
                }
            } else {
                System.out.println("击败monster!");
                blood = stoneManHP;
                this.attack = attack;
                this.defence = defence;
                player.setBlood(playerHP);
                player.setGold(player.getGold() + this.gold);
                player.setExp(player.getExp() + 200);
                Timer timer = new Timer();
                int i = 0;
                for(;i < attackFrequency;i++){
                    timer.schedule(new TimerTask() {
                        public void run() {
                            Video.getVideo(Video.s1);
                        }
                    }, 300*i);}
                timer.schedule(new TimerTask() {
                    public void run() {
                        Video.getVideo(Video.s2);
                    }
                }, 300*i);
                break loop;
            }
            if(playerHP <= 0){
                System.out.println("你打不过。");
                return;
            }
        }
        player.move4(this);
    }

    public String virtualAttack(Player player){
        int vBlood = this.blood;
        int total = 0;
        int finalA = attack;
        int finalD = defence;
        while(true){
            if (player.getAttack() > finalD) {
                vBlood -= (player.getAttack() - finalD);
            }else{
                return "你攻击力太低，打不过";
            }
            finalA += 2;
            finalD += 2;
            if(vBlood > 0) {
                if (finalA > player.getDefence()) {
                    total += (finalA - player.getDefence());
                    player.setBlood(player.getBlood() - (finalA - player.getDefence()));
                }
            } else {
                player.setGold(player.getGold() + this.gold);
                return "可以击败石头人，共受到伤害"+total+"，其最终攻击力为"+finalA+",最终防御力为"+finalD+"。";
            }
            if(player.getBlood() <= 0){
                return "你会被石头人击杀！共受到伤害"+total+"，其最终攻击力为"+finalA+",最终防御力为"+finalD+"。";
            }
        }
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image i1 = new Image("file:pic/Monsters/石头人1.png");//形态一
        Image i2 = new Image("file:pic/Monsters/石头人2.png");//形态二
        ImageView v1 = new ImageView(i1);
        stackPane.getChildren().add(v1);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);//设置周期运行循环往复
        timeline.setAutoReverse(true);//设置动画的自动往返效果

        KeyValue keyValue1 = new KeyValue(v1.xProperty(),0);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0) , "kv1" , event -> v1.setImage(i1) ,keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.9) , "kv2" , event -> v1.setImage(i2) ,keyValue1);
        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2);
        timeline.play();
    }

    public void setExist(boolean flag){
        exist = flag;
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
