package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class userBrochure{

    public static Stage getHelpStage(){
        GridPane gridPane1 = getPane1();
        GridPane gridPane2 = getPane2();
        GridPane gridPane3 = getPane3();
        GridPane gridPane4 = getPane4();
        VBox hBox = getPane5();

        String[] help = new String[]{"基本玩法", "属性说明","地图说明", "物品说明", "npc说明"};
        Parent[] panes = new Parent[]{hBox,gridPane4,gridPane1,gridPane2,gridPane3};

        Stage stage = new Stage();
       GridPane pane = new GridPane();
       pane.setPadding(new Insets(2,2,2,2));

        Font font = new Font("方正粗黑宋简体" , 20);

        Label lb1 = new Label("帮助");
        lb1.setStyle("-fx-alignment: center; "
        );
        lb1.setFont(font);
        lb1.setTranslateX(400);
       ;

        ObservableList<String> list = FXCollections.observableArrayList(help);
        ListView<String> listView = new ListView<>(list);
        listView.setItems(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setWidthHeight(listView,790,145);

        StackPane stackPane = new StackPane();

       pane.add(lb1,0,0);
        pane.add(listView,0,1);
        pane.add(stackPane,0,2);

        listView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            stackPane.getChildren().clear();
            for(Integer i:listView.getSelectionModel().getSelectedIndices()){
                stackPane.getChildren().add(panes[i]);
            }
        });

//        Label lb2 = new Label();
//        lb2.textProperty().bind(listView.getSelectionModel().);
//        hBox.setRight(lb2);




        Scene scene = new Scene(pane,800,800);
        stage.setScene(scene);
        return stage;
    }
    //地图说明
    private static GridPane getPane1(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(11,12,13,14));
        gridPane.setHgap(20);
        gridPane.setVgap(30);

        Font font = new Font("方正粗黑宋简体" , 20);

        ImageView imageView1= new ImageView(new Image("file:pic/Constructions/路.png"));
        gridPane.add(imageView1,0,0);
        Label lb1 = new Label("平地，可以通行。");
        lb1.setFont(font);
        gridPane.add(lb1,1,0);

        ImageView imageView2 = new ImageView(new Image("file:pic/Constructions/墙壁.png"));
        gridPane.add(imageView2,2,0);
        Label lb2 = new Label("墙壁，不可通行。");
        lb2.setFont(font);
        gridPane.add(lb2,3,0);

        ImageView imageView3 = new ImageView(new Image("file:pic/Constructions/上楼楼梯.png"));
        gridPane.add(imageView3,0,1);
        Label lb3 = new Label("上楼楼梯，可进入上面的楼层");
        lb3.setFont(font);
        gridPane.add(lb3,1,1);

        ImageView imageView4 = new ImageView(new Image("file:pic/Constructions/下楼楼梯.png"));
        gridPane.add(imageView4,2,1);
        Label lb4 = new Label("下楼楼梯，可进入下面的楼层");
        lb4.setFont(font);
        gridPane.add(lb4,3,1);

        ImageView imageView5 = new ImageView(new Image("file:pic/Constructions/黄门.png"));
        gridPane.add(imageView5,0,2);
        Label lb5 = new Label("黄门，可用黄钥匙打开");
        lb5.setFont(font);
        gridPane.add(lb5,1,2);

        ImageView imageView6 = new ImageView(new Image("file:pic/Constructions/蓝门.png"));
        gridPane.add(imageView6,2,2);
        Label lb6 = new Label("蓝门，可用蓝钥匙打开");
        lb6.setFont(font);
        gridPane.add(lb6,3,2);

        ImageView imageView7 = new ImageView(new Image("file:pic/Constructions/红门.png"));
        gridPane.add(imageView7,0,3);
        Label lb7 = new Label("红门，可用红钥匙打开");
        lb7.setFont(font);
        gridPane.add(lb7,1,3);


        return gridPane;
    }
    //物品说明
    private static GridPane getPane2(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(11,12,13,14));
        gridPane.setHgap(20);
        gridPane.setVgap(30);

        Font font = new Font("方正粗黑宋简体" , 20);

        ImageView imageView1 = new ImageView(new Image("file:pic/Items/黄钥匙.png"));
        gridPane.add(imageView1,0,0);
        Label lb1 = new Label("黄钥匙，可用于打开黄门");
        lb1.setFont(font);
        gridPane.add(lb1,1,0);

        ImageView imageView2 = new ImageView(new Image("file:pic/Items/蓝钥匙.png"));
        gridPane.add(imageView2,2,0);
        Label lb2 = new Label("蓝钥匙，可用于打开蓝门");
        lb2.setFont(font);
        gridPane.add(lb2,3,0);

        ImageView imageView3 = new ImageView(new Image("file:pic/Items/红钥匙.png"));
        gridPane.add(imageView3,0,1);
        Label lb3 = new Label("红钥匙，可用于打开红门");
        lb3.setFont(font);
        gridPane.add(lb3,1,1);

        ImageView imageView4 = new ImageView(new Image("file:pic/Items/小血瓶.png"));
        gridPane.add(imageView4,2,1);
        Label lb4 = new Label("小血瓶，拾取可获得50HP");
        lb4.setFont(font);
        gridPane.add(lb4,3,1);

        ImageView imageView5 = new ImageView(new Image("file:pic/Items/中血瓶.png"));
        gridPane.add(imageView5,0,2);
        Label lb5 = new Label("中血瓶，拾取可获得100HP");
        lb5.setFont(font);
        gridPane.add(lb5,1,2);

        ImageView imageView6 = new ImageView(new Image("file:pic/Items/大血瓶.png"));
        gridPane.add(imageView6,2,2);
        Label lb6 = new Label("大血瓶，拾取可获得250HP");
        lb6.setFont(font);
        gridPane.add(lb6,3,2);

        ImageView imageView7 = new ImageView(new Image("file:pic/Items/攻击宝石.png"));
        gridPane.add(imageView7,0,3);
        Label lb7 = new Label("攻击水晶，拾取可获得2攻击力");
        lb7.setFont(font);
        gridPane.add(lb7,1,3);

        ImageView imageView8 = new ImageView(new Image("file:pic/Items/防御宝石.png"));
        gridPane.add(imageView8,2,3);
        Label lb8 = new Label("防御水晶，拾取可获得2防御力");
        lb8.setFont(font);
        gridPane.add(lb8,3,3);

        ImageView imageView9 = new ImageView(new Image("file:pic/Items/传送权杖.png"));
        gridPane.add(imageView9,0,4);
        Label lb9 = new Label("传送权杖，拾取即可获得魔塔前5层的胜利");
        lb9.setFont(font);
        gridPane.add(lb9,1,4);

        return gridPane;
    }
    //NPC说明
    private static GridPane getPane3(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(11,12,13,14));
        gridPane.setHgap(20);
        gridPane.setVgap(40);

        Font font = new Font("方正粗黑宋简体" , 20);

        ImageView imageView1 = new ImageView(new Image("file:pic/NPC/老人1.png"));
        gridPane.add(imageView1,0,0);
        Label lb1 = new Label("神秘老人，可以购买各种钥匙");
        lb1.setFont(font);
        gridPane.add(lb1,1,0);

        ImageView imageView2 = new ImageView(new Image("file:pic/NPC/商人.png"));
        gridPane.add(imageView2,0,1);
        Label lb2 = new Label("神秘商人，可以购买血量，攻击力，防御力");
        lb2.setFont(font);
        gridPane.add(lb2,1,1);


        return gridPane;
    }
    //属性说明
    private static GridPane getPane4(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(11,12,13,14));
        gridPane.setHgap(20);
        gridPane.setVgap(40);

        Font font = new Font("方正粗黑宋简体" , 20);

        ImageView imageView1= new ImageView(new Image("file:pic/Log/红心.png"));
        gridPane.add(imageView1,0,0);
        Label lb1 = new Label("生命值，小于或等于零时死亡");
        lb1.setFont(font);
        gridPane.add(lb1,1,0);

        ImageView imageView2 = new ImageView(new Image("file:pic/Log/铁剑.png"));
        gridPane.add(imageView2,0,1);
        Label lb2 = new Label("攻击力");
        lb2.setFont(font);
        gridPane.add(lb2,1,1);

        ImageView imageView3 = new ImageView(new Image("file:pic/Log/铁盾.png"));
        gridPane.add(imageView3,0,2);
        Label lb3 = new Label("防御力");
        lb3.setFont(font);
        gridPane.add(lb3,1,2);

        ImageView imageView4 = new ImageView(new Image("file:pic/Log/金币.png"));
        gridPane.add(imageView4,0,3);
        Label lb4 = new Label("金币，击杀怪物会掉落金币，可用于购买攻击力或防御力。");
        lb4.setFont(font);
        gridPane.add(lb4,1,3);

        return gridPane;
    }
    //基本玩法
    private static VBox getPane5(){
        VBox hBox = new VBox();
        hBox.setPadding(new Insets(11,12,13,14));
        hBox.setSpacing(20);

        Font font = new Font("方正粗黑宋简体" , 20);

        Label lb1 = new Label("操作说明：");
        lb1.setFont(font);
        Label lb2 = new Label("W  前进");
        lb2.setFont(font);
        Label lb3 = new Label("S  后退");
        lb3.setFont(font);
        Label lb4 = new Label("A  左移动");
        lb4.setFont(font);
        Label lb5 = new Label("D  右移动");
        lb5.setFont(font);
        Label lb8 = new Label("支持键盘移动和鼠标移动两种方式，每次只能移动一格。");
        lb8.setFont(font);
        Label lb6 = new Label("战斗说明：");
        lb6.setFont(font);
        Label lb7 = new Label("回合制战斗模式，造成的伤害 = 进攻方攻击力 - 被攻击方防御力，" + "\n" +
                "战斗直至一方死亡。若主角攻击力小于怪物防御力，则无法攻击。"+ "\n" +
                "若主角死亡，则游戏失败。");
        lb7.setFont(font);
        hBox.getChildren().addAll(lb1,lb2,lb3,lb4,lb5,lb8,lb6,lb7);

        return hBox;
    }

    private static void setWidthHeight(Region region , int width , int height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }

}
