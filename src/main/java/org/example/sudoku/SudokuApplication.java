package org.example.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.kordamp.bootstrapfx.BootstrapFX;

public class SudokuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("sudoku-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        scene.getStylesheets().add(SudokuApplication.class.getResource("sudoku.css").toExternalForm());

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setWidth(400);
        stage.setHeight(450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}