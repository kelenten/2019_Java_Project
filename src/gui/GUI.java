package gui;

import Control.Control;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import lattices.DirectionEnum;
import lattices.Lattice;
import lattices.Monster.Monster;
import lattices.Player;
import lattices.Street;
import motamap.MotaMap;

import java.io.File;

public class GUI extends Application {

    private static GUI gui;
    private StackPane[][] stackPanes = new StackPane[13][13];;
    private GridPane heroInfoPane;
    private GridPane monsterInfoPane;
    private GridPane gradeInfoPane = new GridPane();
    private ImageView heroImageFront = new ImageView("file:pic/Items/勇者正面.png");;
    private Player player;
    private MotaMap map;

    public static void update1(Player player) {
        gui.updatePlayer1(player);
    }

    public static void update2(Player player) {
        gui.updatePlayer2(player);
    }

    public static void update3(Player player) {
        gui.updatePlayer3(player);
    }

    public static void update6(){
        gui.updateHIP();
    }

    public static void update5(Monster monster){
        gui.updateMIP(monster);
    }

    public static void update4(Player player, Monster monster){
        gui.updatePlayer4(player,monster);}

    public static Player getPlayer(){
        return gui.getP();
    }

    public static MotaMap getMap(){return gui.getM();}

    @Override
    public void start(Stage stage1) throws Exception {
        gui = this;
        player = new Player();
        map = new MotaMap(player);
        Control.save();

        //BGM
        File file = new File("audio/BGM.mp3");
        String url = file.toURI().toString();
        Media media = new Media(url);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        //显示地图
        //最底层的面板
        GridPane pane = new GridPane();
        pane.setVgap(0);
        pane.setHgap(0);

        //显示地图的面板
        StackPane pane1 = new StackPane();
        pane.add(pane1 , 1 , 0);
        Image image1 = new Image("file:pic/壁纸.png");
        ImageView imageView1 = new ImageView(image1);
        pane1.getChildren().add(imageView1);
        GridPane mapPane = getPane1_1();
        pane1.getChildren().add(mapPane);

        //右面板
        StackPane rightPane =  new StackPane();
        pane.add(rightPane , 2 , 0);
        setWidthHeight(rightPane , 270 , 480);
        ImageView imageView2 = new ImageView(new Image("file:pic/右蓝背景 .png"));
        rightPane.getChildren().add(imageView2);
        GridPane pane2_1 = new GridPane();
        setWidthHeight(pane2_1 , 270 , 480);
        rightPane.getChildren().add(pane2_1);
        //显示玩家状态的面板
        heroInfoPane = getPane2_2();
        setTranslate(heroInfoPane , 5 , 5);
        pane2_1.add(heroInfoPane , 0 , 0);
        //显示等级的面板
        updateGradeInfoPane(player);
        setTranslate(gradeInfoPane , 5 , 10);
        pane2_1.add(gradeInfoPane , 0 , 1);

        //显示怪物信息和帮助信息的面板
        StackPane pane3 = getPane3();
        pane.add(pane3 , 0 , 0);

        Scene scene = new Scene(pane , 950 , 480);
        scene.setOnKeyPressed(e -> {
            if( e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                map.update(player.willMoveTo(DirectionEnum.UP));
            }
            if( e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                map.update(player.willMoveTo(DirectionEnum.DOWN));
            }
            if( e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                map.update(player.willMoveTo(DirectionEnum.LEFT));
            }
            if( e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                map.update(player.willMoveTo(DirectionEnum.RIGHT));
            }
            if(e.getCode() == KeyCode.X){
                Stage stage3 = userBrochure.getHelpStage();
                stage3.show();
            }
            if(e.getCode() == KeyCode.R){
                stage1.close();
                GUI gui = new GUI();
                Control.clearSave();
                try {
                    gui.start(new Stage());
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(e.getCode() == KeyCode.E){
                System.out.println("游戏结束");
                System.exit(0);
            }
            if(e.getCode() == KeyCode.Q){
                try {
                    Control.record();
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(e.getCode() == KeyCode.H){
                try {
                    Control.readSave();
                   update3(player);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(e.getCode() == KeyCode.T){
                Control.undo();
                update3(player);
            }
            if(e.getCode() == KeyCode.M){
                Stage stage = MonsterBrochure.getMonsterBrochure();
                stage.show();
            }
        });
        stage1.setScene(scene);
        stage1.setTitle("魔塔重制版");
        
        Stage stage2 = BeginPopup.getStage2(stage1);
        stage2.show();
    }

    // 面板1_1
    private GridPane getPane1_1(){
        //地图的框架图片
        GridPane pane1_1 = new GridPane();
        ImageView fre;
        for (int i = 0; i < 14; i++) {
            fre = new ImageView(new Image("file:pic/Constructions/石墙.png"));
            pane1_1.add(fre , i , 0);
            fre = new ImageView(new Image("file:pic/Constructions/石墙.png"));
            pane1_1.add(fre , 14 , i);
            fre = new ImageView(new Image("file:pic/Constructions/石墙.png"));
            pane1_1.add(fre , i+1  , 14);
            fre = new ImageView(new Image("file:pic/Constructions/石墙.png"));
            pane1_1.add(fre , 0 , i+1);
        }
        //打印地图
        int i = player.getPosition(0);
            for (int j = 0; j < 13; j++) {
                for (int k = 0; k < 13; k++) {
                    Lattice lattice = map.getLattices(i,j,k);
                    StackPane stackPane = new StackPane();
                    int finalK = k;
                    int finalJ = j;
                    stackPane.setOnMouseClicked(event -> {
                        if(player.isNearThePosition(finalJ , finalK)){
                            player.setNextPosition(i,finalJ,finalK);
                            try {
                                lattice.affectWith(player);
                            }catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            System.out.println("无效点击！");
                        }
                    });
                    if(lattice.getExist()){
                        lattice.getAnimation(stackPane);
                    }
                    if(player.isOnThePosition(j,k)){
                        stackPane.getChildren().add(heroImageFront);
                    }
                    stackPanes[j][k] = stackPane;
                    pane1_1.add(stackPane,k+1,j+1);
                }
            }
        return pane1_1;
    }

    //获取面板2-2
    private GridPane getPane2_2() {
        GridPane pane = new GridPane();
        setWidthHeight(pane , 260 , 270);
        pane.setStyle("-fx-background-color: rgba(0,0,0,0.6);" +
                "-fx-background-radius: 5;");

        //第一行，显示魔塔的层数
        Label label1 = new Label(" 魔塔      " + player.getPosition(0) + "  层  ");
        setWidthHeight(label1 , 250 , 33);
        label1.setStyle("-fx-background-color: rgba(0,229,238, 1);" + // 设置背景色
                "-fx-alignment: center; " + // 设置文字居中
                "-fx-background-radius: 5;");
        javafx.scene.text.Font font1 = new javafx.scene.text.Font("方正粗黑宋简体" , 22);
        label1.setFont(font1);
        pane.add(label1 , 0 , 0);
        setTranslate(label1 , 5 , 5);
        //第一个图片：红心
        Image image1 = new Image("file:pic/Log/红心.png");
        ImageView imageView1 = new ImageView(image1);
        pane.add(imageView1 , 0 , 1);
        imageView1.setTranslateX(5);
        imageView1.setTranslateY(20);
        //显示玩家血量
        Label label2 = new Label("" + player.getBlood());
        setWidthHeight(label2 , 195 , 33);
        label2.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label2.setFont(font1);
        pane.add(label2 , 1 , 1);
        setTranslate(label2 , -190 , 20);
        //第二个图片：剑
        Image image2 = new Image("file:pic/Log/铁剑.png");
        ImageView imageView2 = new ImageView(image2);
        pane.add(imageView2 , 0 , 2);
        imageView2.setTranslateX(5);
        imageView2.setTranslateY(30);
        //显示玩家的攻击力
        Label label3 = new Label("" + player.getAttack());
        setWidthHeight(label3 , 195 , 33);
        label3.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label3.setFont(font1);
        pane.add(label3 , 1 , 2);
        setTranslate(label3 , -190 , 30);
        //第三个图片：盾牌
        Image image3 = new Image("file:pic/Log/铁盾.png");
        ImageView imageView3 = new ImageView(image3);
        pane.add(imageView3 , 0 , 3);
        imageView3.setTranslateX(5);
        imageView3.setTranslateY(40);
        //显示玩家的防御力
        Label label4 = new Label("" + player.getDefence());
        setWidthHeight(label4 , 195 , 33);
        label4.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label4.setFont(font1);
        pane.add(label4 , 1 , 3);
        setTranslate(label4 , -190 , 40);
        //第四个图片：金币
        Image image4 = new Image("file:pic/Log/金币.png");
        ImageView imageView4 = new ImageView(image4);
        pane.add(imageView4 , 0 , 4);
        imageView4.setTranslateX(5);
        imageView4.setTranslateY(50);
        //显示玩家的金币数
        Label label5 = new Label("" + player.getGold());
        setWidthHeight(label5 , 195 , 33);
        label5.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label5.setFont(font1);
        pane.add(label5 , 1 , 4);
        setTranslate(label5 , -190 , 50);
        //第五个图片：黄钥匙
        Image image5 = new Image("file:pic/Items/黄钥匙.png");
        ImageView imageView5 = new ImageView(image5);
        pane.add(imageView5 , 0 , 5);
        imageView5.setTranslateX(10);
        imageView5.setTranslateY(60);
        //显示玩家的黄钥匙数
        Label label6 = new Label("" + player.getYellowKey());
        setWidthHeight(label6 , 30 , 33);
        label6.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label6.setFont(font1);
        pane.add(label6 , 1 , 5);
        setTranslate(label6 , -190 , 60);
        //第六个图片：蓝钥匙
        Image image6 = new Image("file:pic/Items/蓝钥匙.png");
        ImageView imageView6 = new ImageView(image6);
        pane.add(imageView6 , 2 , 5);
        imageView6.setTranslateX(-350);
        imageView6.setTranslateY(60);
        //显示玩家的蓝钥匙数
        Label label7 = new Label("" + player.getBlueKey());
        setWidthHeight(label7 , 30 , 33);
        label7.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label7.setFont(font1);
        pane.add(label7 , 3 , 5);
        setTranslate(label7 , -340 , 60);
        //第七个图片：红钥匙
        Image image7 = new Image("file:pic/Items/红钥匙.png");
        ImageView imageView7 = new ImageView(image7);
        pane.add(imageView7 , 4 , 5);
        imageView7.setTranslateX(-335);
        imageView7.setTranslateY(60);
        //显示玩家的红钥匙数
        Label label8 = new Label("" + player.getRedKey());
        setWidthHeight(label8 , 30 , 33);
        label8.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label8.setFont(font1);
        pane.add(label8 , 5 , 5);
        setTranslate(label8 , -315 , 60);

        return pane;
    }

    private void updateGradeInfoPane(Player player){
        player.reSetLv();
        gradeInfoPane.setPadding(new Insets(45,10,10,10));
        gradeInfoPane.setVgap(30);
        gradeInfoPane.setHgap(15);
        setWidthHeight(gradeInfoPane , 260 , 185);
        gradeInfoPane.setStyle("-fx-background-color: rgba(0,0,0,0.6);" + "-fx-background-radius: 5;");
        Font font = new Font("方正粗黑宋简体" , 20);

        ImageView imageView = new ImageView(new Image("file:pic/Items/勇者正面.png"));
        gradeInfoPane.add(imageView,0,0);
        imageView.setTranslateY(30);

        Label lb1 = new Label("等级");
        setWidthHeight(lb1,60,33);
        lb1.setFont(font);
        lb1.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        gradeInfoPane.add(lb1,1,0);
        Label lb2 = new Label(player.getLv() + "");
        setWidthHeight(lb2,120,33);
        lb2.setFont(font);
        lb2.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        gradeInfoPane.add(lb2,2,0);

        Label lb3 = new Label("经验值");
        setWidthHeight(lb3,60,33);
        lb3.setFont(font);
        lb3.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        gradeInfoPane.add(lb3,1,1);

        Label lb4 = new Label(player.getExp() + "");
        setWidthHeight(lb4,120,33);
        lb4.setFont(font);
        lb4.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        gradeInfoPane.add(lb4,2,1);

    }

    //获取面板3
    private StackPane getPane3() {
        StackPane pane = new StackPane();
        setWidthHeight(pane , 200 , 480);

        ImageView imageView = new ImageView(new Image("file:pic/左蓝背景.png"));

        GridPane gridPane = new GridPane();
        setWidthHeight(gridPane , 200 , 480);
        pane.getChildren().addAll(imageView , gridPane);

        monsterInfoPane = getMonsterPane(new Monster());
        gridPane.add(monsterInfoPane, 0 , 0);
        monsterInfoPane.setStyle("-fx-background-color: rgba(0,0,0,0.6);" + "-fx-background-radius: 5;");
        setWidthHeight(monsterInfoPane , 180 , 230);
        setTranslate(monsterInfoPane , 10 , 5);

        GridPane pane2 = getHelpPane();
        gridPane.add(pane2 , 0 , 1);
        pane2.setStyle("-fx-background-color: rgba(0,0,0,0.6);" + "-fx-background-radius: 5;");
        setWidthHeight(pane2 , 180 , 225);
        setTranslate(pane2 , 10 , 10);

        return pane;
    }

    //面板3中的怪物信息面板
    private GridPane getMonsterPane(Monster monster) {
        GridPane pane = new GridPane();

        Font font1 = new Font("方正粗黑宋简体" , 20);

        StackPane pane1 = new StackPane();
        setWidthHeight(pane1 , 180 , 50);
        StackPane pane1_1 = new StackPane();
        pane1_1.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        setWidthHeight(pane1_1,35,35);
        setTranslate(pane1_1,0,-3);
        pane1_1.getChildren().add(monster.getPic());
        pane1.getChildren().add(pane1_1);
        pane.add(pane1 , 0 , 0);

        Label label1 = new Label("" + monster.getAppearance());
        label1.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label1.setFont(font1);
        pane.add(label1 , 0 , 1);
        setWidthHeight(label1 , 150 , 30);
        setTranslate(label1 , 15 , -5);

        Image image2 = new Image("file:pic/Log/红心.png");
        ImageView imageView2 = new ImageView(image2);
        pane.add(imageView2 , 0 , 2);
        imageView2.setTranslateX(15);
        imageView2.setTranslateY(-2);

        Label label2 = new Label("" + monster.getBlood());
        label2.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label2.setFont(font1);
        setWidthHeight(label2 , 100 , 30);
        setTranslate(label2 , -115 , 0);
        pane.add(label2 , 1 , 2);

        Image image3 = new Image("file:pic/Log/铁剑.png");
        ImageView imageView3 = new ImageView(image3);
        pane.add(imageView3 , 0 , 3);
        imageView3.setTranslateX(15);
        imageView3.setTranslateY(2);

        Label label3 = new Label("" + monster.getAttack());
        label3.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label3.setFont(font1);
        setWidthHeight(label3 , 100 , 30);
        setTranslate(label3 , -115 , 4);
        pane.add(label3 , 1 , 3);

        Image image4 = new Image("file:pic/Log/铁盾.png");
        ImageView imageView4 = new ImageView(image4);
        pane.add(imageView4 , 0 , 4);
        imageView4.setTranslateX(15);
        imageView4.setTranslateY(6);

        Label label4 = new Label("" + monster.getDefence());
        label4.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label4.setFont(font1);
        setWidthHeight(label4 , 100 , 30);
        setTranslate(label4 , -115 , 8);
        pane.add(label4 , 1 , 4);


        Image image5 = new Image("file:pic/Log/金币.png");
        ImageView imageView5 = new ImageView(image5);
        pane.add(imageView5 , 0 , 5);
        imageView5.setTranslateX(15);
        imageView5.setTranslateY(10);

        Label label5 = new Label("" + monster.getGold());
        label5.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label5.setFont(font1);
        setWidthHeight(label5 , 100 , 30);
        setTranslate(label5 , -115 , 12);
        pane.add(label5 , 1 , 5);

        return pane;
    }

    //面板3中的帮助信息面板
    private GridPane getHelpPane() {
        GridPane pane = new GridPane();
        javafx.scene.text.Font font1 = new javafx.scene.text.Font("方正粗黑宋简体" , 16);

        Label label1 = new Label("按X显示帮助");
        label1.setTextFill(Color.WHITE);
        label1.setFont(font1);
        pane.add(label1 , 0 , 0);
        setTranslate(label1 , 3 , 10);

        Label label2 = new Label("按R重启游戏");
        label2.setTextFill(Color.WHITE);
        label2.setFont(font1);
        pane.add(label2 , 0 , 1);
        setTranslate(label2 , 3 , 20);

        Label label3 = new Label("按Q存档");
        label3.setTextFill(Color.WHITE);
        label3.setFont(font1);
        pane.add(label3 , 0 , 2);
        setTranslate(label3 , 3 , 30);

        Label label4 = new Label("按H载入存档");
        label4.setTextFill(Color.WHITE);
        label4.setFont(font1);
        pane.add(label4 , 0 , 3);
        setTranslate(label4 , 3 , 40);

        Label label5 = new Label("按M显示怪物手册");
        label5.setTextFill(Color.WHITE);
        label5.setFont(font1);
        pane.add(label5 , 0 , 4);
        setTranslate(label5 , 3 , 50);

        Label label6 = new Label("按T撤回");
        label6.setTextFill(Color.WHITE);
        label6.setFont(font1);
        pane.add(label6 , 0 , 5);
        setTranslate(label6 , 3 , 60);

        Label label7 = new Label("按E退出游戏");
        label7.setTextFill(Color.WHITE);
        label7.setFont(font1);
        pane.add(label7 , 0 , 6);
        setTranslate(label7 , 3 , 70);
        return pane;
    }

    //设置组件大小
    private void setWidthHeight(Region region , int width , int height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }

    //设置组件的位移
    private void setTranslate(Region region , int x , int y) {
        region.setTranslateX(x);
        region.setTranslateY(y);
    }

    public void updatePlayer1(Player player) {
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200),heroImageFront);
//        System.out.println(heroImageFront.getX()+""+heroImageFront.getY());
//        System.out.println(player.getPosition(1)+""+player.getPosition(2));
//        translateTransition.setToY(heroImageFront.getY()+32*player.getDirection(0));
//        translateTransition.setToX(heroImageFront.getX()+32*player.getDirection(1));
//        Timeline timeline = new Timeline();
//        KeyValue kv1 = new KeyValue(heroImageFront.xProperty(),0);
//        KeyValue kv2 = new KeyValue(heroImageFront.yProperty(),-32);
//        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200) , "kv" , event -> {
//            // stackPanes[player.getPosition(1)][player.getPosition(2)].getChildren().add(heroImageFront);
//        } , kv1,kv2);
//        timeline.getKeyFrames().add(keyFrame1);
//        timeline.play();
//        translateTransition.play();
        stackPanes[player.getPosition(1)][player.getPosition(2)].getChildren().add(heroImageFront);
        updateHIP();
        updateMIP(new Monster());
        updateGradeInfoPane(player);
    }

    public void updatePlayer2(Player player) {
        Image image = new Image("file:pic/Constructions/路.png");
        ImageView imageView = new ImageView(image);
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500),heroImageFront);
//        System.out.println(heroImageFront.getX()+""+heroImageFront.getY());
//        translateTransition.setToY(heroImageFront.getY()+32*player.getDirection(0));
//        translateTransition.setToX(heroImageFront.getX()+32*player.getDirection(1));
//        heroImageFront.setY(heroImageFront.getY()+32*player.getDirection(0));
//        heroImageFront.setX(heroImageFront.getX()+32*player.getDirection(1));
//        translateTransition.play();
        stackPanes[player.getPosition(1)][player.getPosition(2)].getChildren().add(imageView);
        updateHIP();
        updateMIP(new Monster());
    }

    public void updatePlayer3(Player player) {
        int i = player.getPosition(0);
        for (int j = 0; j < 13; j++) {
            for (int k = 0; k < 13; k++) {
                StackPane stackPane = stackPanes[j][k];
                Lattice lattice = map.getLattices(i , j , k);
                int finalK = k;
                int finalJ = j;
                stackPane.setOnMouseClicked(event -> {
                    if(player.isNearThePosition(finalJ , finalK)){
                        player.setNextPosition(i,finalJ,finalK);
                        try {
                            lattice.affectWith(player);
                        }catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("无效点击！");
                    }
                });
                new Street().getAnimation(stackPane);
                if(lattice.getExist()) {
                    lattice.getAnimation(stackPane);
                }
                try {
                    if( player.isOnThePosition(j , k) ) {
                        stackPane.getChildren().add(heroImageFront);
//                        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500),heroImageFront);
//                        translateTransition.setToY(heroImageFront.getY()+32*player.getDirection(0));
//                        translateTransition.setToX(heroImageFront.getX()+32*player.getDirection(1));
//                        heroImageFront.setY(heroImageFront.getY()+32*player.getDirection(0));
//                        heroImageFront.setX(heroImageFront.getX()+32*player.getDirection(1));
//                        translateTransition.play();
                    }
                } catch (IllegalArgumentException ex){
//                    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500),heroImageFront);
//                    System.out.println(heroImageFront.getX()+""+heroImageFront.getY());
//                    translateTransition.setToY(heroImageFront.getY()+32*player.getDirection(0));
//                    translateTransition.setToX(heroImageFront.getX()+32*player.getDirection(1));
//                    heroImageFront.setY(heroImageFront.getY()+32*player.getDirection(0));
//                    heroImageFront.setX(heroImageFront.getX()+32*player.getDirection(1));
//                    translateTransition.play();
                    stackPanes[0][0].getChildren().add(heroImageFront);
                    stackPane.getChildren().add(heroImageFront);
                }
            }
        }
        updateMIP(new Monster());
        updateHIP();
    }

    public void updatePlayer4(Player player, Monster monster){
        Image image = new Image("file:pic/Constructions/路.png");
        ImageView imageView2 = new ImageView(image);
        stackPanes[player.getPosition(1)][player.getPosition(2)].getChildren().add(imageView2);
        stackPanes[player.getPosition(1)][player.getPosition(2)].getChildren().add(heroImageFront);
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500),heroImageFront);
//        System.out.println(heroImageFront.getX()+""+heroImageFront.getY());
//        translateTransition.setToY(heroImageFront.getY()+32*player.getDirection(0));
//        translateTransition.setToX(heroImageFront.getX()+32*player.getDirection(1));
//        heroImageFront.setY(heroImageFront.getY()+32*player.getDirection(0));
//        heroImageFront.setX(heroImageFront.getX()+32*player.getDirection(1));
//        translateTransition.setDelay(Duration.millis(2000));
//        translateTransition.play();
        if(monster.getExist()){
            ImageView imageView1 = new ImageView(new Image("file:pic/Log/攻击.png"));
            stackPanes[player.getNextPo(1)][player.getNextPo(2)].getChildren().add(imageView1);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(200),imageView1);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setCycleCount(monster.getAttackFrequency());
            fadeTransition.play();
        }
        monster.setExist(false);
        updateGradeInfoPane(player);
        updateHIP();
    }

    //更新

    //更新heroInfoPane
    public void updateHIP(){
        setWidthHeight(heroInfoPane , 260 , 270);
        heroInfoPane.setStyle("-fx-background-color: rgba(0,0,0,0.6);" +
                "-fx-background-radius: 5;");

        //第一行，显示魔塔的层数
        Label label1 = new Label(" 魔塔      " + player.getPosition(0) + "  层  ");
        setWidthHeight(label1 , 250 , 33);
        label1.setStyle("-fx-background-color: rgba(0,229,238, 1);" + // 设置背景色
                "-fx-alignment: center; " + // 设置文字居中
                "-fx-background-radius: 5;");
        javafx.scene.text.Font font1 = new javafx.scene.text.Font("方正粗黑宋简体" , 22);
        label1.setFont(font1);
        heroInfoPane.add(label1 , 0 , 0);
        setTranslate(label1 , 5 , 5);
        //第一个图片：红心
        Image image1 = new Image("file:pic/红心2.png");
        ImageView imageView1 = new ImageView(image1);
        heroInfoPane.add(imageView1 , 0 , 1);
        imageView1.setTranslateX(5);
        imageView1.setTranslateY(20);
        //显示玩家血量
        Label label2 = new Label("" + player.getBlood());
        setWidthHeight(label2 , 195 , 33);
        label2.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label2.setFont(font1);
        heroInfoPane.add(label2 , 1 , 1);
        setTranslate(label2 , -190 , 20);
        //第二个图片：剑
        Image image2 = new Image("file:pic/铁剑.png");
        ImageView imageView2 = new ImageView(image2);
        heroInfoPane.add(imageView2 , 0 , 2);
        imageView2.setTranslateX(5);
        imageView2.setTranslateY(30);
        //显示玩家的攻击力
        Label label3 = new Label("" + player.getAttack());
        setWidthHeight(label3 , 195 , 33);
        label3.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label3.setFont(font1);
        heroInfoPane.add(label3 , 1 , 2);
        setTranslate(label3 , -190 , 30);
        //第三个图片：盾牌
        Image image3 = new Image("file:pic/铁盾.png");
        ImageView imageView3 = new ImageView(image3);
        heroInfoPane.add(imageView3 , 0 , 3);
        imageView3.setTranslateX(5);
        imageView3.setTranslateY(40);
        //显示玩家的防御力
        Label label4 = new Label("" + player.getDefence());
        setWidthHeight(label4 , 195 , 33);
        label4.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label4.setFont(font1);
        heroInfoPane.add(label4 , 1 , 3);
        setTranslate(label4 , -190 , 40);
        //第四个图片：金币
        Image image4 = new Image("file:pic/金币2.png");
        ImageView imageView4 = new ImageView(image4);
        heroInfoPane.add(imageView4 , 0 , 4);
        imageView4.setTranslateX(5);
        imageView4.setTranslateY(50);
        //显示玩家的金币数
        Label label5 = new Label("" + player.getGold());
        setWidthHeight(label5 , 195 , 33);
        label5.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label5.setFont(font1);
        heroInfoPane.add(label5 , 1 , 4);
        setTranslate(label5 , -190 , 50);
        //第五个图片：黄钥匙
        Image image5 = new Image("file:pic/Items/101-01.png");
        ImageView imageView5 = new ImageView(image5);
        heroInfoPane.add(imageView5 , 0 , 5);
        imageView5.setTranslateX(10);
        imageView5.setTranslateY(60);
        //显示玩家的黄钥匙数
        Label label6 = new Label("" + player.getYellowKey());
        setWidthHeight(label6 , 30 , 33);
        label6.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label6.setFont(font1);
        heroInfoPane.add(label6 , 1 , 5);
        setTranslate(label6 , -190 , 60);
        //第六个图片：蓝钥匙
        Image image6 = new Image("file:pic/Items/101-02.png");
        ImageView imageView6 = new ImageView(image6);
        heroInfoPane.add(imageView6 , 2 , 5);
        imageView6.setTranslateX(-350);
        imageView6.setTranslateY(60);
        //显示玩家的蓝钥匙数
        Label label7 = new Label("" + player.getBlueKey());
        setWidthHeight(label7 , 30 , 33);
        label7.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label7.setFont(font1);
        heroInfoPane.add(label7 , 3 , 5);
        setTranslate(label7 , -340 , 60);
        //第七个图片：红钥匙
        Image image7 = new Image("file:pic/Items/101-03.png");
        ImageView imageView7 = new ImageView(image7);
        heroInfoPane.add(imageView7 , 4 , 5);
        imageView7.setTranslateX(-335);
        imageView7.setTranslateY(60);
        //显示玩家的红钥匙数
        Label label8 = new Label("" + player.getRedKey());
        setWidthHeight(label8 , 30 , 33);
        label8.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;");
        label8.setFont(font1);
        heroInfoPane.add(label8 , 5 , 5);
        setTranslate(label8 , -315 , 60);
    }

    //更新monsterInfoPane
    public void updateMIP(Monster monster){
        Font font1 = new javafx.scene.text.Font("方正粗黑宋简体" , 20);
        StackPane pane1 = new StackPane();
        setWidthHeight(pane1 , 180 , 50);
        StackPane pane1_1 = new StackPane();
        pane1_1.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        setWidthHeight(pane1_1,35,35);
        setTranslate(pane1_1,0,-3);
        pane1_1.getChildren().add(monster.getPic());
        pane1.getChildren().add(pane1_1);
        monsterInfoPane.add(pane1 , 0 , 0);

        Label label1 = new Label("" + monster.getAppearance());
        label1.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label1.setFont(font1);
        monsterInfoPane.add(label1 , 0 , 1);
        setWidthHeight(label1 , 150 , 30);
        setTranslate(label1 , 15 , -5);

        Label label2 = new Label("" + monster.getBlood());
        label2.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label2.setFont(font1);
        setWidthHeight(label2 , 100 , 30);
        setTranslate(label2 , -115 , 0);
        monsterInfoPane.add(label2 , 1 , 2);

        Label label3 = new Label("" + monster.getAttack());
        label3.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label3.setFont(font1);
        setWidthHeight(label3 , 100 , 30);
        setTranslate(label3 , -115 , 4);
        monsterInfoPane.add(label3 , 1 , 3);

        Label label4 = new Label("" + monster.getDefence());
        label4.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label4.setFont(font1);
        setWidthHeight(label4 , 100 , 30);
        setTranslate(label4 , -115 , 8);
        monsterInfoPane.add(label4 , 1 , 4);

        Label label5 = new Label("" + monster.getGold());
        label5.setStyle("-fx-background-color: rgba(0,229,238, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 3;");
        label5.setFont(font1);
        setWidthHeight(label5 , 100 , 30);
        setTranslate(label5 , -115 , 12);
        monsterInfoPane.add(label5 , 1 , 5);

    }

    public Player getP(){
        return player;
    }

    public MotaMap getM(){
        return map;
    }


}