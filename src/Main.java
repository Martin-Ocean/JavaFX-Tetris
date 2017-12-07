import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.XMLFormatter;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane base = new BorderPane();
        VBox scoreBoard = new VBox(50);
        HBox control = new HBox(50);

        Button start = new Button("Start");
        Button pause = new Button("Pause");

        control.getChildren().addAll(start, pause);
        control.setAlignment(Pos.TOP_LEFT);
        control.setMargin(start, new Insets(0,100,30,100));

        Label scoreLabel = new Label("Score");
        Label score = new Label("000");


        scoreBoard.setAlignment(Pos.CENTER);
        scoreBoard.getChildren().addAll(scoreLabel,score);





        GridPane gameBoard = new GridPane();
        for (int i = 0; i < 15; i++) { // columns
            for (int j = 0; j < 20; j++) { //rows
                Rectangle rectangle = new Rectangle(25, 25, Color.TRANSPARENT);
                rectangle.setStrokeWidth(.15);
                rectangle.setStroke(Color.BLACK);
                gameBoard.add(rectangle, i, j);
            }
        }


        base.setCenter(gameBoard);
        base.setMargin(gameBoard, new Insets(30,30,30,30));
        base.setRight(scoreBoard);
        base.setMargin(scoreBoard, new Insets(0,80,0,0));
        base.setBottom(control);



        primaryStage.setTitle("Tetris");
        primaryStage.setScene(new Scene(base,600, 650, Color.BLACK));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

class shape {
    int direction, color, shape, x;

    public shape(int direction, int color, int shape, int x) {
        this.direction = direction;
        this.color = color;
        this.shape = shape;
        this.x=x;
    }

    shape chooseShape() {
        Random random = new Random();
        int shape = random.nextInt(7);
        int direction = random.nextInt(4);
        int color = random.nextInt(6);
        int x = 1;
        do {
            x = random.nextInt(12);
        } while (x == 0);

        return new shape(direction,color,shape,x);
    }

    ArrayList<int[]> makeShape(int shape, int x, int y, int direction) {
        ArrayList<int[]> result = new ArrayList<>();
        switch (shape) {
            /* square shape |XX|
                            |XX|

             */
            case 1: result.add(new int[]{x, y});
                    result.add(new int[]{x + 1, y});
                    result.add(new int[]{x , y+1});
                    result.add(new int[]{x + 1, y+1});
                    result.add(new int[] {y+1});
                    break;



            /* S shape  up && down  |  X|   left && right |X X  |
                                    |X X|                 |  X X|
                                    |X  |
             */
            case 2: if (direction == 1 || direction == 3) {
                result.add(new int[]{x+1,y});
                result.add(new int[]{x,y+1});
                result.add(new int[]{x,y+2});
                result.add(new int[]{x+1,y+1});
                result.add(new int[] {x+1, y+2});
            } else {
                result.add(new int[]{x,y});
                result.add(new int[]{x+1,y});
                result.add(new int[]{x+1,y+1});
                result.add(new int[]{x+2,y+1});
                result.add(new int[] {x+2,y+1});
            }
                    break;



            /*
            Z shape     Up & Down       |X  |  Left & Right |  X X|
                                        |X X|               |X X  |
                                        |  X|
            */
            case 3: if (direction == 1 || direction == 3) {
                result.add(new int[]{x,y});
                result.add(new int[]{x,y+1});
                result.add(new int[]{x+1,y+1});
                result.add(new int[]{x+1,y+2});
                result.add(new int[] {x+1,y+2});
            } else {
                result.add(new int[]{x+1,y});
                result.add(new int[]{x+2,y});
                result.add(new int[]{x,y+1});
                result.add(new int[]{x+1,y+1});
                result.add(new int[] {x+2,y+1});
            }
                    break;



            /*
            L shape  Up |X X|  Down |  X|  Left |X    |  Right |X X X|
                        |X  |       |  X|       |X X X|        |    X|
                        |X  |       |X X|
            */
            case 4: if (direction == 1) { //
                result.add(new int[]{x,y});
                result.add(new int[]{x+1,y});
                result.add(new int[]{x,y+1});
                result.add(new int[]{x,y+2});
                result.add(new int[] {x+1,y+2});
            } else if (direction == 2) {
                result.add(new int[]{x+1,y});
                result.add(new int[]{x+1,y+1});
                result.add(new int[]{x+1,y+2});
                result.add(new int[]{x,y+2});
                result.add(new int[] {x+1,y+2});

            } else if (direction == 3) { //
                result.add(new int[]{x,y});
                result.add(new int[]{x,y+1});
                result.add(new int[]{x+1,y+1});
                result.add(new int[]{x+2,y+1});
                result.add(new int[] {x+2,y+1});

            } else {
                result.add(new int[]{x,y});
                result.add(new int[]{x+1,y});
                result.add(new int[]{x+2,y});
                result.add(new int[]{x+2,y+1});
                result.add(new int[] {x+2,y+1});

            }
                    break;


            /*
            Mirrored L-Shape Up |X  |  Down |X X|  Left |    X|  Right |X X X|
                                |X  |       |  X|       |X X X|        |X    |
                                |X X|       |  X|

            */
            case 5: if (direction == 1) { //
                result.add(new int[]{x,y});
                result.add(new int[]{x,y+1});
                result.add(new int[]{x,y+2});
                result.add(new int[]{x+1,y+2});
                result.add(new int[] {});

            } else if (direction == 2) {
                result.add(new int[]{x,y});
                result.add(new int[]{x+1,y});
                result.add(new int[]{x+1,y+1});
                result.add(new int[]{x+1,y+2});
                result.add(new int[] {});

            } else if (direction == 3) { //
                result.add(new int[]{x,y+1});
                result.add(new int[]{x+1,y+1});
                result.add(new int[]{x+2,y+1});
                result.add(new int[]{x+2,y});
                result.add(new int[] {});

            } else {
                result.add(new int[]{x,y});
                result.add(new int[]{x+1,y});
                result.add(new int[]{x+2,y});
                result.add(new int[]{x,y+1});
                result.add(new int[] {});

            }
                break;
        }
        return result;
    }

}

class score {

}