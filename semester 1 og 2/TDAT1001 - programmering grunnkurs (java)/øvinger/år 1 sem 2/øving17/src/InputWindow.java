//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//
//public class InputWindow {
//    Button button;
//
//
//    //Form
//    TextField value = new TextField();
//
//    button = new Button("enter");
//        button.setOnAction( e -> isInt(value.getText()) );
//
//    //Layout
//    VBox layout = new VBox(10);
//        layout.setPadding(new Insets(20, 20, 20, 20));
//        layout.getChildren().addAll(value, button);
//
//    scene = new Scene(layout, 300, 250);
//        window.setScene(scene);
//        window.show();
//}
//
//    //Validate age
//    private boolean isInt(String input){
//        try{
//            int value = Integer.parseInt(input);
//            System.out.println("value is: " + value);
//            return true;
//        }catch(NumberFormatException e){
//            System.out.println("Error: " + input + " is not a number");
//            return false;
//        }
//    }
//
//}
