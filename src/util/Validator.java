package util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Validator {
    public static Object validate(HashMap<TextField,Pattern>map){
        for (TextField txt:map.keySet()
             ) {
            Pattern pattern = map.get(txt);
            if (!pattern.matcher(txt.getText()).matches()){
                if (!txt.getText().equals("")) {
                    setErrorBorderColor(txt);
                }
                return txt;
            }setCorrectBorderColor(txt);
        }return true;
    }
    public static void setCorrectBorderColor(TextField textfield) {
        textfield.setStyle("-fx-border-color: green");
    }

    public static void setErrorBorderColor(TextField textfield) {
        textfield.setStyle("-fx-border-color: red");
    }
}
