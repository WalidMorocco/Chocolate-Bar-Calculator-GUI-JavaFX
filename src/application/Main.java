package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Main extends Application {
	
	@Override
	
	public void start(Stage primaryStage) {
		try {
		
			
			GridPane grid = new GridPane();
			Scene scene = new Scene(grid,1000,1000);
			primaryStage.setScene(scene);
			primaryStage.show();
			scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
			
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25,25,25,25));
			
			Text scenetitle = new Text("Chocolate Bar Calculator");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);
			
			Label weightLabel = new Label("Weight In Pounds: ");
			grid.add(weightLabel, 0, 1);
			TextField weightTextField = new TextField();
			grid.add(weightTextField, 1, 1);
			
			Label heightLabel = new Label("Height In Pounds: ");
			grid.add(heightLabel, 0, 2);
			TextField heightTextField = new TextField();
			grid.add(heightTextField, 1, 2);
			
			Label ageLabel = new Label("Age in Years: ");
			grid.add(ageLabel, 0, 3);
			TextField ageTextField = new TextField();
			grid.add(ageTextField, 1, 3);
			
			Label sexLabel = new Label("Sex:");
			grid.add(sexLabel, 0, 4);
			RadioButton sexButtonMale = new RadioButton("Male");
//			sexButtonMale.setUserData("sexMale");
			RadioButton sexButtonFemale = new RadioButton("Female");
//			sexButtonMale.setUserData("sexFemale");
			grid.add(sexButtonMale, 1, 4);
			grid.add(sexButtonFemale, 2, 4);
			// create a toggle group
	        ToggleGroup tg = new ToggleGroup();
	        sexButtonMale.setToggleGroup(tg);
	        sexButtonFemale.setToggleGroup(tg);

			Label activityLevel = new Label("Activity Level: ");
			grid.add(activityLevel, 0, 5);
			final ComboBox<String> comboBox = new ComboBox<String>();
	        comboBox.getItems().addAll("Sedentary", "Somewhat Active", "Active", "Highly Active");
	        grid.add(comboBox, 1, 5);
			
			// make this a final variable so the event handler
			// can see it (or declare it as a class variable)
			final Text actiontarget = new Text();
			         grid.add(actiontarget, 1, 6);
			        
			Button btn = new Button("Sign in");
			// using the HBox layout so you can align the
			// control's alignment
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(btn);
			grid.add(hbBtn, 1, 8);
			
			btn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
				 
			    @Override
			    public void handle(ActionEvent e) {
			        
			    	
			    	// Declare variables
					double weight = Double.parseDouble(weightTextField.getText());
					double height = Double.parseDouble(heightTextField.getText());
					Integer age = Integer.valueOf(ageTextField.getText());
					String sex = "";
					String activityLevel = comboBox.getValue();
					double activityPercentage = 0;
					double BMR = 0;
			    	
			    	
//			    	tg.selectedToggleProperty().addListener(
//			     		   (observable, oldToggle, newToggle) -> {
//			     			   String gender = "";
//			     		       if (newToggle == sexButtonMale) {
//			     		            gender = String.valueOf("Male");
//			     		       } else if (newToggle == sexButtonFemale) {
//			     		    	    gender = String.valueOf("Female");
//			     		       } else {
//			     		    	    gender = String.valueOf("?");;
//			     		       }
//			     		       sex = gender;
//			     		    }
//			     		);
			    	
			    	if(sexButtonMale.isSelected()){
			    		sex = "Male";
		    		} else {
		    			sex = "Female";
		    		}
			        

					
					
						
					// Define percentage depending on the activity level
					if(activityLevel.equals("Highly Active")) {
						activityPercentage = 0.5;
					} else if(activityLevel.equals("Active")) {
						activityPercentage = 0.4;
					} else if(activityLevel.equals("Somewhat Active")) {
						activityPercentage = 0.3;
					} else { // Sedentary as the default
						activityPercentage = 0.2;
					}
					
					// BMR Calculation
					if ((sex.equals("Male"))){
						BMR = (66 + (6.3 * weight) + (12.9 * height) - (6.8 * age)) * (1 + activityPercentage);
					} else {
						BMR = (655 + (4.3 * weight) + (4.7 * height) - (4.7 * age)) * (1 + activityPercentage);
					}
					
					// Number of candy bars calculation
					double candyNb = BMR / 230;
					
					actiontarget.setFill(Color.FIREBRICK);
			        actiontarget.setText("A " + sex + " with those measurements should eat " + String.format("%.2f",candyNb) + " candy bars per day to maintain the same weight.");
				}
				
					
			});
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
