package lattices.Monster.Slime;

import gui.GUI;
import gui.Video;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import lattices.Monster.Monster;
import lattices.Player;

import java.util.Timer;
import java.util.TimerTask;

public class GreenSlime extends Monster {
    private int blood = 50;
    private int attack = 20;
    private int defence = 1;
    private int gold = 1;
    private int criticalAttackRate = 20;
    private int dodgeRate = 20;
    private boolean exist;
    private int attackFrequency = 0;

    public GreenSlime(){
        super();
        exist = true;
    }

    @Override
    public String getAppearance(){
        if(exist) {
            return "绿史莱姆";
        } else
            return "";
    }

    @Override
    public void getAnimation(StackPane stackPane){
        Image image4 = new Image("file:pic/Monsters/绿史莱姆.png");
        ImageView imageView4 = new ImageView(image4);
        stackPane.getChildren().add(imageView4);
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(700), imageView4);
        translateTransition.setCycleCount(Timeline.INDEFINITE);
        translateTransition.setFromY(0);
        translateTransition.setToY(2.5f);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    @Override
    public void affectWith(Player player){
        GUI.update5(this);
        loop:
        while(exist) {
            int dodge = (int) (Math.random() * 99 + 1);
            if(player.getAttack() > this.defence) {
                attackFrequency++;
                if(dodge > dodgeRate)
                    blood -= (player.getAttack() - this.defence);
            } else {
                System.out.println("你打不过。");
                return;
            }
            if(this.blood > 0) {
                attackFrequency++;
                if((this.attack - player.getDefence()) > 0) {
                    int criticalAttack = (int) (Math.random() * 99 + 1);
                    if(criticalAttack <= criticalAttackRate) {
                        player.setBlood(player.getBlood() - 2 * (this.attack - player.getDefence()));
                    } else player.setBlood(player.getBlood() - (this.attack - player.getDefence()));
                }
                if(player.getBlood() <= 0) {
                    System.out.println("你被绿史莱姆击杀，游戏失败！");
                    System.exit(0);
                }
            } else {
                System.out.println("击败绿史莱姆!");
                player.setGold(player.getGold() + this.gold);
                player.setExp(player.getExp() + 30);
                Timer timer = new Timer();
                int i = 0;
                for(; i < attackFrequency; i++) {
                    timer.schedule(new TimerTask() {
                        public void run(){
                            Video.getVideo(Video.s1);
                        }
                    }, 200 * i);
                }
                timer.schedule(new TimerTask() {
                    public void run(){
                        Video.getVideo(Video.s2);
                    }
                }, 200 * i);
                break loop;
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

    public ImageView getPic(){
        ImageView imageView;
        if(exist) {
            imageView = new ImageView(new Image("file:pic/Monsters/绿史莱姆.png"));
            return imageView;
        } else {
            imageView = new ImageView(new Image("file:"));
            return imageView;
        }
    }

    public HBox getHBox(Player player){
        HBox hBox = new HBox();
        Font font = new Font("方正粗黑宋简体", 20);

        Label label1 = new Label("", getPic());
        setWidthHeight(label1, 100, 33);
        Label label2 = new Label(getAppearance());
        label2.setFont(font);
        setWidthHeight(label2, 100, 33);
        Label label3 = new Label("" + blood);
        label3.setFont(font);
        setWidthHeight(label3, 100, 33);
        Label label4 = new Label("" + attack);
        label4.setFont(font);
        setWidthHeight(label4, 100, 33);
        Label label5 = new Label("" + defence);
        label5.setFont(font);
        setWidthHeight(label5, 100, 33);
        Label label6 = new Label("" + gold);
        label6.setFont(font);
        setWidthHeight(label6, 100, 33);
        Label label7 = new Label("" + virtualAttack(player));
        label7.setFont(font);
        setWidthHeight(label7, 800, 33);

        hBox.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7);
        return hBox;
    }

    public String virtualAttack(Player player){
        int vBlood = this.blood;
        int total = 0;
        int cAF = 0;
        int dF = 0;
        loop:
        while(true){
            int dodge = (int) (Math.random() * 99 + 1);
            if(player.getAttack() > defence) {
                if(dodge > dodgeRate) {

                    vBlood -= (player.getAttack() - defence);
                } else dF++;
            } else {
                return "您的攻击力太低，打不过。";
            }
            if(vBlood > 0) {
                if((attack - player.getDefence()) > 0) {
                    int criticalAttack = (int) (Math.random() * 99 + 1);
                    if(criticalAttack <= criticalAttackRate) {
                        total += 2 * (attack - player.getDefence());
                        cAF++;
                        player.setBlood(player.getBlood() - 2 * (attack - player.getDefence()));
                    } else {
                        total += (attack - player.getDefence());
                        player.setBlood(player.getBlood() - (attack - player.getDefence()));
                    }
                }
                if(player.getBlood() <= 0) {
                    return "你可能会被绿史莱姆击杀!共受到" + total + "点伤害，其暴击了" + cAF + "次，" + "躲避了" + dF + "次";
                }
            } else {
                player.setBlood(player.getBlood());
                player.setGold(player.getGold() + this.gold);
                return "你可能击杀绿史莱姆。共受到" + total + "点伤害，其暴击了" + cAF + "次，" + "躲避了" + dF + "次";
            }
        }
    }

    public void setExist(boolean flag){
        exist = flag;
    }

    //设置组件大小
    private static void setWidthHeight(Region region, int width, int height){
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMaxWidth(width);
        region.setMinWidth(width);
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
}
