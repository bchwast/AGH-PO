package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    IWorldMap map;
    
    @Override
    public void init() {
        List<String> args = new ArrayList<>(List.of("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"));
//        List<String> args = getParameters().getRaw();
        List<MoveDirection> directions = OptionsParser.parse(args.toArray(new String[0]));
        this.map = new GrassField(20);
        List<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(2, 2), new Vector2d(2, 4)));
        IEngine engine = new SimulationEngine(directions, this.map, positions);
        engine.run();
    }

    private GridPane createGrid() {
        Vector2d[] corners = this.map.getCorners();
        int left = corners[0].x;
        int down = corners[0].y;
        int right = corners[1].x;
        int up = corners[1].y;

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        Label yx = new Label("y \\ x");
        grid.add(yx, 0, 0, 1, 1);
        grid.getColumnConstraints().add(new ColumnConstraints(30));
        grid.getRowConstraints().add(new RowConstraints(30));
        GridPane.setHalignment(yx, HPos.CENTER);

        for (int i = 1; i <= right - left + 1; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(30));
            Label label = new Label(String.format("%d", left + i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, i, 0, 1, 1);
        }

        for (int i = 1; i <= up - down + 1; i++) {
            grid.getRowConstraints().add(new RowConstraints(30));
            Label label = new Label(String.format("%d", up - i + 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, i, 1, 1);
        }

        for (int i = 1; i <= up - down + 1; i++) {
            for (int j = 1; j <= right - left + 1; j++) {
                Object object = map.objectAt(new Vector2d(left + j - 1, up - i + 1));
                Label label;
                if (object == null) {
                    label = new Label("");
                }
                else {
                    label = new Label(map.objectAt(new Vector2d(left + j - 1, up - i + 1)).toString());
                }
                GridPane.setHalignment(label, HPos.CENTER);
                grid.add(label, j, i, 1, 1);
            }
        }

        return grid;
    }


    public void start(Stage primaryStage) {
        GridPane grid = createGrid();
        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
