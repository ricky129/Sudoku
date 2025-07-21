package org.example.sudoku;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.converter.IntegerStringConverter;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SudokuController {
    @FXML
    private GridPane sudokuGrid;
    @FXML
    private Label CorrectFalse;
    @FXML
    private Label wrongMovesLabel;
    private TextField[][] cells = new TextField[9][9];
    private static int RandomNumbersAmount;
    private boolean randomInput = false;
    private int wrongMovesCounter = 0;
    private SudokuApplication mainApp;
    private TextField activeCell;
    private static final Logger logger = Logger.getLogger(SudokuController.class.getName());
    private static int score = 10000;

    public SudokuController(SudokuApplication mainApp) {
        this.mainApp = mainApp;
    }

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

    private int getBlockByPosition(int row, int col) {
        if (row < 3 && col < 3) { // Block 0
            for (int r = 0; r < 3; r++)
                for (int c = 0; c < 3; c++)
                    return 0;

        } else if (row < 3 && col > 2 && col < 6) { // Block 1
            for (int r = 0; r < 3; r++)
                for (int c = 3; c < 6; c++)
                    return 1;
        } else if (row < 3 && col > 5) {// Block 2
            for (int r = 0; r < 3; r++)
                for (int c = 6; c < 9; c++)
                    return 2;

        } else if (row > 2 && row < 6 && col < 3) { // Block 3
            for (int r = 3; r < 6; r++) {
                for (int c = 0; c < 3; c++)
                    return 3;
            }
        } else if (row > 2 && row < 6 && col > 2 && col < 6) { // Block 4
            for (int r = 3; r < 6; r++)
                for (int c = 3; c < 6; c++)
                    return 4;

        } else if (row > 2 && row < 6 && col > 5) { // Block 5
            for (int r = 3; r < 6; r++) {
                for (int c = 6; c < 9; c++)
                    return 5;
            }
        } else if (row > 5 && col < 3) { // Block 6
            for (int r = 6; r < 9; r++)
                for (int c = 0; c < 3; c++)
                    return 6;

        } else if (row > 5 && col > 2 && col < 6) { // Block 7
            for (int r = 6; r < 9; r++)
                for (int c = 3; c < 6; c++)
                    return 7;

        } else if (row > 5 && col > 5) // Block 8
            for (int r = 6; r < 9; r++)
                for (int c = 6; c < 9; c++)
                    return 8;
        return -1;
    }

    private boolean isBlockCorrect(int row, int col, int value) {
        switch (getBlockByPosition(row, col)) {
            case '0':
                for (int r = 0; r < 3; r++)
                    for (int c = 0; c < 3; c++) {
                        if (r == row && c == col)
                            continue; // Skip the input cell itself
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '1':
                for (int r = 0; r < 3; r++)
                    for (int c = 3; c < 6; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '2':
                for (int r = 0; r < 3; r++)
                    for (int c = 6; c < 9; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '3':
                for (int r = 3; r < 6; r++)
                    for (int c = 0; c < 3; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '4':
                for (int r = 3; r < 6; r++)
                    for (int c = 3; c < 6; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '5':
                for (int r = 3; r < 6; r++)
                    for (int c = 6; c < 9; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '6':
                for (int r = 6; r < 9; r++)
                    for (int c = 0; c < 3; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '7':
                for (int r = 6; r < 9; r++)
                    for (int c = 3; c < 6; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            case '8':
                for (int r = 6; r < 9; r++)
                    for (int c = 6; c < 9; c++) {
                        if (r == row && c == col)
                            continue;
                        String cellText = cells[r][c].getText();
                        int cellValue = cellText.isEmpty() ? 0 : Integer.parseInt(cellText);
                        if (cellValue == value)
                            return false;
                    }
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean isRowColCorrect(int row, int col, int value) {
        for (int r = 0; r < 9; r++)
            if (r != row)
                if (Integer.parseInt(cells[r][col].getText().isEmpty() ? "0" : cells[r][col].getText()) == value)
                    return false;

        for (int c = 0; c < 9; c++)
            if (c != col)
                if (Integer.parseInt(cells[row][c].getText().isEmpty() ? "0" : cells[row][c].getText()) == value)
                    return false;

        return true;
    }

    private void fillRandom() {
        randomInput = true;

        Random random = new Random();

        int cellsFilled = 0;
        while (cellsFilled < RandomNumbersAmount) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);

            int randomValue = random.nextInt(9) + 1;

            if (cells[r][c].getText().isEmpty() && isBlockCorrect(r, c, randomValue) && isRowColCorrect(r, c, randomValue)) {
                // If the TextField is empty AND the randomValue is valid, then proceed
                cells[r][c].setText(String.valueOf(randomValue)); // Set the text of the TextField
                cells[r][c].setEditable(false);
                cellsFilled++;
            }
        }

        randomInput = false;
    }

    private boolean isComplete() {
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (Objects.equals(cells[r][c].getText(), ""))
                    return false;
        return true;
    }

    private void clearGrid() {
        try {
            for (int r = 0; r < 9; r++)
                for (int c = 0; c < 9; c++) {
                    cells[r][c].setText("");
                    cells[r][c].setEditable(true);
                }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Null cell found.");
        }
    }

    @FXML
    private void InsertNumberHint() {
        int[] blocksNumbers = new int[9];
        int[] colNumbres = new int[9];
        int[] rowNumbers = new int[9];
        int arrayIndex = 0;
        int missingNumber = 0;
        int row = GridPane.getRowIndex(activeCell);
        int col = GridPane.getColumnIndex(activeCell);

        switch (getBlockByPosition(row, col)) {
            case '0':
                for (int r = 0; r < 3; r++)
                    for (int c = 0; c < 3; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '1':
                for (int r = 0; r < 3; r++)
                    for (int c = 3; c < 6; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '2':
                for (int r = 0; r < 3; r++)
                    for (int c = 6; c < 9; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '3':
                for (int r = 3; r < 6; r++)
                    for (int c = 0; c < 3; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '4':
                for (int r = 3; r < 6; r++)
                    for (int c = 3; c < 6; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '5':
                for (int r = 3; r < 6; r++)
                    for (int c = 6; c < 9; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '6':
                for (int r = 6; r < 9; r++)
                    for (int c = 0; c < 3; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '7':
                for (int r = 6; r < 9; r++)
                    for (int c = 3; c < 6; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            case '8':
                for (int r = 6; r < 9; r++)
                    for (int c = 6; c < 9; c++)
                        blocksNumbers[arrayIndex++] = Integer.parseInt(cells[r][c].getText());
                break;
            default:
                logger.log(Level.WARNING, "Couldn't check block's numbers.");
        }

        arrayIndex = 0;
        for (int r = 0; r < 9; r++)
            rowNumbers[arrayIndex] = Integer.parseInt(cells[r][col].getText());

        arrayIndex = 0;
        for (int c = 0; c < 9; c++)
            rowNumbers[arrayIndex] = Integer.parseInt(cells[row][c].getText());

        boolean present[] = new boolean[9];

        for (int num : blocksNumbers)
            present[num] = true;

        for (int num : rowNumbers)
            present[num] = true;

        for (int num : colNumbres)
            present[num] = true;

        arrayIndex = 0;
        while (arrayIndex < 8) {
            if (!present[arrayIndex])
                missingNumber = arrayIndex;
            arrayIndex++;
        }

        cells[row][col].setText(String.valueOf(missingNumber));
    }

    @FXML
    public void initialize() {
        CorrectFalse.setText("");
        wrongMovesLabel.setText("0");
        clearGrid();
        Instant start = Instant.now();
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++) {
                TextField cell = (TextField) getNodeByRowColumnIndex(row, col, sudokuGrid);
                cells[row][col] = cell;

                int finalRow = row;
                int finalCol = col;

                cell.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue)
                        activeCell = cell;
                });

                cell.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, change -> {
                    String newText = change.getControlNewText();
                    if (newText.isEmpty())
                        return change;
                    if (newText.matches("[1-9]")) {
                        if (newText.length() > 1)
                            return null;
                        return change;
                    }
                    return null;
                }));

                cell.textProperty().addListener((observable, oldValue, newValue) -> {

                    if (newValue.matches("[1-9]")) {


                        cell.getStyleClass().remove("wrong-input");

                        int inputValue = Integer.parseInt(newValue);

                        if (!randomInput)
                            if (isBlockCorrect(finalRow, finalCol, inputValue) && isRowColCorrect(finalRow, finalCol, inputValue)) {
                                CorrectFalse.setText("Correct!");
                                cell.getStyleClass().remove("wrong-input");
                                if (isComplete()) {
                                    Instant end = Instant.now();
                                    long TimeElapsedMillis = Duration.between(start, end).toMillis();
                                    CorrectFalse.setText("Complete! Time Elapsed: " + TimeElapsedMillis / 1000);
                                }
                            } else {
                                CorrectFalse.setText("Wrong!");
                                cell.getStyleClass().add("wrong-input");
                                wrongMovesCounter++;
                                wrongMovesLabel.setText(String.valueOf(wrongMovesCounter));
                            }
                    } else if (newValue.isEmpty()) {
                        System.out.println("Cell cleared.");
                        cell.getStyleClass().remove("wrong-input");
                    } else {
                        cell.setText(oldValue);
                        System.out.println("Invalid input. Please enter a single digit from 1-9.");
                        cell.getStyleClass().add("wrong-input");
                    }
                });
            }
        fillRandom();
    }

    @FXML
    private void goToIndexButton() {
        mainApp.showIndexPage();
    }

    public void setRandomNumbersAmount(int randomNumbersAmount) {
        this.RandomNumbersAmount = randomNumbersAmount;
    }
}