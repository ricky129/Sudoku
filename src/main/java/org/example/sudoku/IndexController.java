package org.example.sudoku;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

public class IndexController {
    private SudokuApplication mainApp;

    public IndexController(SudokuApplication mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleStartEasyGame(ActionEvent event) {
        System.out.println("Starting Easy Game...");
        mainApp.showSudokuGame(15);
    }

    @FXML
    private void handleStartMediumGame(ActionEvent event) {
        System.out.println("Starting Medium Game...");
        mainApp.showSudokuGame(10);
    }

    @FXML
    private void handleStartHardGame(ActionEvent event) {
        System.out.println("Starting Hard Game...");
        mainApp.showSudokuGame(5);
    }

}
