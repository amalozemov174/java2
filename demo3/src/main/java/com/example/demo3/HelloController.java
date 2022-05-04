package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    public TextArea printedText;
    @FXML
    public TextField enterText;

    @FXML
    public void PrintText(ActionEvent action) {
        printedText.appendText(enterText.getText().trim() + "\n");
        enterText.clear();
    }
}