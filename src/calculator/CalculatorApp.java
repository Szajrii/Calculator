package calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Michal Szarek
 **/
public class CalculatorApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane layout = new BorderPane();
        layout.setId("background");

        TextField textField = new TextField("0");
        textField.setDisable(true);

        Label label = new Label();

        HBox hBox = new HBox();
        HBox hBox_1 = new HBox();
        HBox hBox_2 = new HBox();
        HBox hBox_3 = new HBox();
        HBox hBox_4 = new HBox();
        VBox vBox = new VBox();
        VBox vbox2 = new VBox();

        Calculator calculator = new Calculator();

        //generate 1-9 digit button

        for (int i= 1; i<=9; i++) {
            Button button = new Button("" + i);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    textField.setText(calculator.setValue(button.getText()));
                }
            });
            if(i < 4){
                hBox_3.getChildren().add(button);
            }else if(i < 7) {
                hBox_2.getChildren().add(button);
            }else {
                hBox_1.getChildren().add(button);
            }
        }

        //0 row

        Button clear = new Button("C");
            clear.setOnAction(action -> {
                textField.setText(calculator.clear());
                label.setText("");
        });
        Button remove = new Button("←");
            remove.setOnAction(action -> {
                textField.setText(calculator.remove());
            });
        Button divide = new Button("÷");
            divide.setOnAction(action -> {
                label.setText("÷ " + calculator.getValue());
                calculator.divide();
                textField.setText(calculator.getValue());
            });

        hBox.getChildren().addAll(clear, remove, divide);
        hBox.setAlignment(Pos.CENTER);


        //1st row
        Button sqrt = new Button("√");
            sqrt.setOnAction(action -> textField.setText(calculator.sqrt()));
        Button multiply = new Button("x");
            multiply.setOnAction(action -> {
                label.setText("X " + calculator.getValue());
                calculator.multiply();
                textField.setText(calculator.getValue());
            });
        hBox_1.getChildren().add(0, sqrt);
        hBox_1.getChildren().add(4, multiply);
        hBox_1.setAlignment(Pos.CENTER);

        //2nd row
        Button minus = new Button("-");
            minus.setOnAction(action -> {
                label.setText("- " + calculator.getValue());
                calculator.minus();
                textField.setText(calculator.getValue());
            });
        Button x2 = new Button("x"+ "²");
            x2.setOnAction(action -> textField.setText(calculator.x2()));
        hBox_2.getChildren().add(0, x2);
        hBox_2.getChildren().add(4, minus);
        hBox_2.setAlignment(Pos.CENTER);

        //3th row
        Button x3 = new Button("x" + "³");
            x3.setOnAction(action -> textField.setText(calculator.x3()));
        Button plusMark = new Button("+");
            plusMark.setOnAction(action -> {
                label.setText("+ " + calculator.getValue());
                calculator.sum();
                textField.setText(calculator.getValue());
            });
        hBox_3.getChildren().add(0, x3);
        hBox_3.getChildren().add(4, plusMark);
        hBox_3.setAlignment(Pos.CENTER);

        //4th row
        Button comma = new Button(",");
            comma.setOnAction( action -> textField.setText(calculator.setComma()));
        Button equal = new Button("=");
            equal.setOnAction(action -> {
                textField.setText(calculator.makeCalculation());
            });
        Button zero = new Button("0");
            zero.setOnAction(action -> textField.setText(calculator.setValue(zero.getText())));
        hBox_4.getChildren().addAll(comma, zero, equal);
        hBox_4.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBox, hBox_1, hBox_2, hBox_3, hBox_4);
        vBox.setAlignment(Pos.CENTER);

        vbox2.getChildren().addAll(textField, label);
        vbox2.setSpacing(10);

        layout.setTop(vbox2);
        layout.setCenter(vBox);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add("calculator/Style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();

    }
}
