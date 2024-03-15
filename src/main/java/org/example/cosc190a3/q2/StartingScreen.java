package org.example.cosc190a3.q2;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartingScreen extends Application {


    @Override
    public void start(Stage stage) throws Exception {


        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1000, 500);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        LINES     ///////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Rectangle blue = new Rectangle(scene.getWidth() * 0.01, scene.getHeight() * 0.8);
        blue.setFill(Color.BLUE);

        Rectangle red = new Rectangle(scene.getWidth() * 0.01, scene.getHeight() * 0.8);
        red.setFill(Color.RED);

        blue.heightProperty().bind(pane.heightProperty().multiply(0.8));
        blue.widthProperty().bind(pane.widthProperty().multiply(0.01));

        red.heightProperty().bind(pane.heightProperty().multiply(0.8));
        red.widthProperty().bind(pane.widthProperty().multiply(0.01));

//        Set layout of lines


        blue.layoutXProperty().bind(pane.widthProperty().multiply(0.2));
        blue.layoutYProperty().bind(pane.heightProperty().multiply(0.1));

        red.layoutXProperty().bind(pane.widthProperty().multiply(0.8));
        red.layoutYProperty().bind(pane.heightProperty().multiply(0.1));


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//          IMAGE     //////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Image carImage = new Image("file:image/cars.png");

        double originalRatio = carImage.getWidth() / carImage.getHeight();

        ImageView car = new ImageView(carImage);
        car.setPreserveRatio(true);

        car.setFitWidth(pane.getWidth() * 0.15);
        car.fitWidthProperty().bind(pane.widthProperty().multiply(0.15));


//        Set layout car


        double carX = pane.getWidth() * 0.2 - car.getFitWidth() ;
        double carY = pane.getHeight() / 2 - (car.getFitWidth() / originalRatio) / 2;

        car.setX(carX);
        car.setY(carY);

        car.xProperty().bind(pane.widthProperty().multiply(carX / pane.getWidth()));
        car.yProperty().bind(pane.heightProperty().multiply(carY / pane.getHeight()));


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Middle Line   ////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        double startX = pane.getWidth() * 0.2 - car.getFitWidth() / 2;
        double startY = pane.getHeight() / 2;
        double endX = pane.getWidth() * 0.8 + car.getFitWidth() / 2 + red.getWidth();
        double endY = pane.getHeight() / 2;

        Line middleLine = new Line(startX, startY, endX, endY);

//        middleLine.setStroke(Color.TRANSPARENT);

        middleLine.startXProperty().bind(pane.widthProperty().multiply(startX/pane.getWidth()));
        middleLine.startYProperty().bind(pane.heightProperty().divide(2));
        middleLine.endXProperty().bind(pane.widthProperty().multiply(endX/ pane.getWidth()));
        middleLine.endYProperty().bind(pane.heightProperty().divide(2));


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Path Transition   ////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setPath(middleLine);

        pathTransition.setNode(car);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Menu Bar   ///////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        MenuBar menuBar = new MenuBar();
        menuBar.setPrefWidth(pane.getWidth());
        menuBar.prefWidthProperty().bind(pane.widthProperty().multiply(1));

        Menu file = new Menu("File");
        Menu help = new Menu("Help");

        MenuItem start = new Menu("Start");
        MenuItem pause = new Menu("Pause");
        MenuItem stats = new Menu("Stats");
        MenuItem exit = new Menu("Exit");

        file.getItems().addAll(start, pause, stats, exit);

        start.setOnAction(actionEvent -> pathTransition.play());
        pause.setOnAction(actionEvent -> pathTransition.pause());

        exit.setOnAction(actionEvent -> System.exit(1));


        menuBar.getMenus().addAll(file, help);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Text Box   ///////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        GridPane input = new GridPane();


        TextField textField = new TextField("Text");

        Label label = new Label("Hello: ");

        input.add(label, 0,0);


        input.add(textField, 1, 0);

         double x = pane.getWidth()/2 - input.getWidth() / 2;

        input.setLayoutX(x);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pane.getChildren().addAll(menuBar, blue, red, car, middleLine, input);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        stage.setTitle("Math Racing");
        stage.setScene(scene);
        stage.show();

    }
}
