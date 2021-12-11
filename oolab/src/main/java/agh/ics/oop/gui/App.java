package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application implements IMapUpdateObserver {
    private IWorldMap map;
    private final GridPane grid = new GridPane();
    private IEngine engine;
    private int moveDelay;
    private GuiElementBox elementCreator;
    private int cellSize;
    
    @Override
    public void init() {
//        List<String> args = new ArrayList<>(List.of("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"));
//        List<String> args = getParameters().getRaw();
//        List<MoveDirection> directions = OptionsParser.parse(args.toArray(new String[0]));
        this.map = new GrassField(20);
        this.moveDelay = 300;
        List<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(2, 2), new Vector2d(2, 4)));
        this.engine = new SimulationEngine(this.map, positions);
        this.engine.addObserver(this);
        this.engine.setMoveDelay(this.moveDelay);
        this.elementCreator = new GuiElementBox();
        this.cellSize = 45;
    }

    private void createGrid() {
        Vector2d[] corners = this.map.getCorners();
        int left = corners[0].x;
        int down = corners[0].y;
        int right = corners[1].x;
        int up = corners[1].y;
        this.grid.setGridLinesVisible(false);
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();

        this.grid.setGridLinesVisible(true);

        Label yx = new Label("y \\ x");
        this.grid.add(yx, 0, 0, 1, 1);
        this.grid.getColumnConstraints().add(new ColumnConstraints(this.cellSize));
        this.grid.getRowConstraints().add(new RowConstraints(this.cellSize));
        GridPane.setHalignment(yx, HPos.CENTER);

        for (int i = 1; i <= right - left + 1; i++) {
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.cellSize));
            Label label = new Label(String.format("%d", left + i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.add(label, i, 0, 1, 1);
        }

        for (int i = 1; i <= up - down + 1; i++) {
            this.grid.getRowConstraints().add(new RowConstraints(this.cellSize));
            Label label = new Label(String.format("%d", up - i + 1));
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.add(label, 0, i, 1, 1);
        }

        for (int i = 1; i <= up - down + 1; i++) {
            for (int j = 1; j <= right - left + 1; j++) {
                Object object = map.objectAt(new Vector2d(left + j - 1, up - i + 1));
                if (object instanceof IMapElement) {
                    VBox element = this.elementCreator.showElement((IMapElement) object);
                    GridPane.setHalignment(element, HPos.CENTER);
                    this.grid.add(element, j, i, 1, 1);
                }
            }
        }
    }

    @Override
    public void positionChanged() {
        Platform.runLater(() -> {
            this.grid.getChildren().clear();
            createGrid();
        });
    }

    public void start(Stage primaryStage) {
        Button start = new Button("Start");
        TextField moves = new TextField();
        VBox controls = new VBox(moves, start);
        HBox main = new HBox(this.grid, controls);

        start.setOnAction(click -> {
            List<MoveDirection> directions = OptionsParser.parse(moves.getText().split(" "));
            this.engine.setDirections(directions);

            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });

        Scene scene = new Scene(main, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
