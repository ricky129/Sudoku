module org.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires java.desktop;
    requires java.sql;

    opens org.example.sudoku to javafx.fxml;
    exports org.example.sudoku;
}