package gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BeginPopup {

    public static Stage getStage2(Stage stage) throws Exception {
        Stage primaryStage = new Stage();
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(new ImageView(new Image("file:pic/大石墙背景.png")));

        Font font1 = new Font("方正粗黑宋简体" , 20);
        ImageView imageView = new ImageView(new Image("file:pic/Log/字幕.png"));
        stackPane.getChildren().add(imageView);

        Button bt1 = new Button("继续");
        bt1.setStyle("-fx-background-color: rgba(79,79,79, 1);" +
                "-fx-alignment: center; " +
                "-fx-background-radius: 5;"
        );
        bt1.setTextFill(Color.WHITE);
        bt1.setFont(font1);
        bt1.setOnMousePressed(event -> {
                primaryStage.close();
                stage.show();
        });
        bt1.setTranslateX(190);
        bt1.setTranslateY(160);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(10000),imageView);
        translateTransition.setFromY(400);
        translateTransition.setToY(0);

        Timeline timeline = new Timeline();
        KeyValue kv1 = new KeyValue(new ImageView(new Image("file:")).yProperty(),0);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0),"",event -> {
            translateTransition.play();
        },kv1);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(10),"",event -> {
            stackPane.getChildren().add(bt1);
        },kv1);
        timeline.getKeyFrames().addAll(kf1,kf2);
        timeline.play();

        Scene scene = new Scene(stackPane,500,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("引言");
        return primaryStage;
    }

}
