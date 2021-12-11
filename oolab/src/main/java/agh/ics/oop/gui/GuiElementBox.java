package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image imgUp = null;
    private Image imgRight = null;
    private Image imgDown = null;
    private Image imgLeft = null;
    private Image imgGrass = null;

    public GuiElementBox() {
        try {
            this.imgUp = new Image(new FileInputStream("src/main/resources/up.png"));
            this.imgRight = new Image(new FileInputStream("src/main/resources/right.png"));
            this.imgDown = new Image(new FileInputStream("src/main/resources/down.png"));
            this.imgLeft = new Image(new FileInputStream("src/main/resources/left.png"));
            this.imgGrass = new Image(new FileInputStream("src/main/resources/grass.png"));
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't load files. " + ex);
        }
    }

    public VBox showElement(IMapElement element) {
        Label label = new Label(element.getPosition().toString());
        ImageView imageView;
        if (element instanceof Animal) {
            imageView = switch (((Animal) element).getDirection()) {
                case NORTH -> new ImageView(this.imgUp);
                case EAST -> new ImageView(this.imgRight);
                case SOUTH -> new ImageView(this.imgDown);
                case WEST -> new ImageView(this.imgLeft);
            };
        }
        else {
            imageView = new ImageView(this.imgGrass);
        }
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        VBox vbox = new VBox(imageView, label);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }
}
