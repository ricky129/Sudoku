package org.example.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.IndexedCell;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.kordamp.bootstrapfx.BootstrapFX;

public class SudokuApplication extends Application {
    private static final Logger logger = Logger.getLogger(SudokuApplication.class.getName());
    private Stage primaryStage;
    private String commonCssUrl;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        primaryStage.setTitle("Sudoku Game");

        loadCommonResources();

        showIndexPage();
    }

    private void loadCommonResources() {
        try (java.io.InputStream fontStream = getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf")) {
            if (fontStream == null)
                System.err.println("Resource /fonts/Roboto-Regular.ttf not found in classpath");
            else
                Font.loadFont(fontStream, 14);

        } catch (Exception e) {
            System.err.println("Error loading font: " + e.getMessage());
        }

        try {
            java.net.URL cssUrl = SudokuApplication.class.getResource("sudoku.css");
            if (cssUrl != null)
                this.commonCssUrl = cssUrl.toExternalForm();
            else {
                System.err.println("sudoku.css resource not found. Make sure it's in the correct path.");
                this.commonCssUrl = null;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "An error occurred during an operation.", e);
        }
    }

    public void showIndexPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("index-view.fxml"));
            IndexController indexController = new IndexController(this);
            fxmlLoader.setController(indexController);

            Scene scene = new Scene(fxmlLoader.load(), 400, 450);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

            if (commonCssUrl != null)
                scene.getStylesheets().add(commonCssUrl);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed ot load index-view.fxml.", e);
        }
    }

    public void showSudokuGame(int randomNumbersAmount) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("sudoku-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 450);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

            SudokuController sudokuController = fxmlLoader.getController();
            if (sudokuController != null) {
                sudokuController.setMainApp(this);
                sudokuController.setRandomNumbersAmount(randomNumbersAmount);
                sudokuController.initialize();
            } else
                logger.log(Level.SEVERE, "SudokuController not found after FXML load.");

            if (commonCssUrl != null)
                scene.getStylesheets().add(commonCssUrl);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load sudoku-view.fxml.", e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}