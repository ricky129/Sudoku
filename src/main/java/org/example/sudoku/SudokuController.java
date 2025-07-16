package org.example.sudoku;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class SudokuController {
    @FXML
    private GridPane sudokuGrid;
    @FXML
    private Label label;

    private TextField[][] cells = new TextField[9][9];

    private Node getNodeByRowColumnIndex(final int row, final int col, GridPane gridPane) {
        Node result = null;
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                result = node;
                break;
            }
        }
        return result;
    }

    private boolean isBlockCorrect(int row, int col, int value) {
        if (row < 3 && col < 3) { // Block 0
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (r == row && c == col) continue; // Skip the input cell itself
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row < 3 && col > 2 && col < 6) { // Block 1
            for (int r = 0; r < 3; r++) {
                for (int c = 3; c < 6; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row < 3 && col > 5) { // Block 2
            for (int r = 0; r < 3; r++) {
                for (int c = 6; c < 9; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row > 2 && row < 6 && col < 3) { // Block 3
            for (int r = 3; r < 6; r++) {
                for (int c = 0; c < 3; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row > 2 && row < 6 && col > 2 && col < 6) { // Block 4
            for (int r = 3; r < 6; r++) {
                for (int c = 3; c < 6; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row > 2 && row < 6 && col > 5) { // Block 5
            for (int r = 3; r < 6; r++) {
                for (int c = 6; c < 9; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row > 5 && col < 3) { // Block 6
            for (int r = 6; r < 9; r++) {
                for (int c = 0; c < 3; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row > 5 && col > 2 && col < 6) { // Block 7
            for (int r = 6; r < 9; r++) {
                for (int c = 3; c < 6; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        } else if (row > 5 && col > 5) { // Block 8
            for (int r = 6; r < 9; r++) {
                for (int c = 6; c < 9; c++) {
                    if (r == row && c == col) continue;
                    String cellText = cells[r][c].getText();
                    int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                    if (cellValue == value) return false;
                }
            }
        }
        return true;
    }

    private boolean isRowColCorrect(int row, int col, int value) {
        for (int r = 0; r < 9; r++) {
            if (r != row) {
                if (Integer.parseInt(cells[r][col].getText().isEmpty() ? "0" : cells[r][col].getText()) == value)
                    return false;
            }
        }

        for (int c = 0; c < 9; c++) {
            if (c != col) {
                if (Integer.parseInt(cells[row][c].getText().isEmpty() ? "0" : cells[row][c].getText()) == value)
                    return false;
            }
        }
        return true;
    }

    private void fillRandom(int amount) {

        Random random = new Random();

        int cellsFilled = 0;
        while (cellsFilled < amount) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);

            int randomValue = random.nextInt(9) + 1;

            if (cells[r][c].getText().isEmpty() && isBlockCorrect(r, c, randomValue) && isRowColCorrect(r, c, randomValue)) {
                // If the TextField is empty AND the randomValue is valid, then proceed
                cells[r][c].setText(String.valueOf(randomValue)); // Set the text of the TextField
                cellsFilled++;
            }

        }
    }

    @FXML
    public void initialize() {
        label.setText("");
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++) {
                TextField cell = (TextField) getNodeByRowColumnIndex(row, col, sudokuGrid);
                cells[row][col] = cell;

                int finalRow = row;
                int finalCol = col;
                cell.textProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println("Cell [" + finalRow + "][" + finalCol + "] changed from " + oldValue + " to " + newValue);

                    if (newValue.matches("[1-9]")) {
                        int inputValue = Integer.parseInt(newValue);
                        boolean isBlockCorrectBoolean = isBlockCorrect(finalRow, finalCol, inputValue);
                        System.out.println("isBlockCorrect: " + isBlockCorrectBoolean);
                        boolean isRowColCorrectBoolean = isRowColCorrect(finalRow, finalCol, inputValue);
                        System.out.println("isRowColCorrect " + isRowColCorrectBoolean);
                        if (isBlockCorrectBoolean && isRowColCorrectBoolean) label.setText("Corretto!");
                        else label.setText("Sbagliato!");
                    } else if (newValue.isEmpty()) {
                        System.out.println("Cell cleared.");
                    } else {
                        cell.setText(oldValue);
                        System.out.println("Invalid input. Please enter a single digit from 1-9.");
                    }
                });
            }
        fillRandom(10);
    }
}