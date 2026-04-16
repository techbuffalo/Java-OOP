package lab6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FractionGUI extends Application{

	GridPane grid = new GridPane();


	@Override
	public void start(Stage primaryStage) throws Exception {
		setScreen();
		Scene scene = new Scene (grid, 350, 250);
		primaryStage.setTitle("Fraction Adder");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setScreen() {		

		TextField numerator1 = new TextField();
		TextField denominator1 = new TextField();
		TextField numerator2 = new TextField();
		TextField denominator2 = new TextField();
		Label numerator3 = new Label();
		Label denominator3 = new Label();
		Label decimal = new Label();

		numerator1.setPrefWidth(50);
		denominator1.setPrefWidth(50);
		numerator2.setPrefWidth(50);
		denominator2.setPrefWidth(50);
		numerator3.setPrefWidth(50);
		denominator3.setPrefWidth(50);

		numerator1.setAlignment(Pos.CENTER);
		denominator1.setAlignment(Pos.CENTER);
		numerator2.setAlignment(Pos.CENTER);
		denominator2.setAlignment(Pos.CENTER);
		numerator3.setAlignment(Pos.CENTER);
		denominator3.setAlignment(Pos.CENTER);

		numerator1.setFont(Font.font(15));
		denominator1.setFont(Font.font(15));
		numerator2.setFont(Font.font(15));
		denominator2.setFont(Font.font(15));
		numerator3.setFont(Font.font(15));
		denominator3.setFont(Font.font(15));
		decimal.setFont(Font.font(15));

		Label plus = new Label("+");
		plus.setPrefWidth(20);
		plus.setFont(Font.font(25));
		Label divide1 = new Label("__________");
		Label divide2 = new Label("__________");
		Label divide3 = new Label("__________");
		Label equals = new Label("=");
		equals.setPrefWidth(20);
		equals.setFont(Font.font(25));

		Button goButton = new Button("Go!");  		//to be attached to an event handler
		Button clearButton = new Button("Clear"); 	//to be attached to an event handler
		goButton.setPrefWidth(75);
		clearButton.setPrefWidth(75);

		//Adding in event handlers for the go and clear buttons 

		goButton.setOnMouseClicked(e -> {
			//getting numerator 1 and 2 and denominator 1 and 2 from the text feilds and creating new fractions
			Fraction f1 = new Fraction(Integer.parseInt(numerator1.getText()), Integer.parseInt(denominator1.getText()));
			Fraction f2 = new Fraction(Integer.parseInt(numerator2.getText()), Integer.parseInt(denominator2.getText()));
			
			//using the Fraction add method we get the sum of the input fractions and output them in numerator3 and denominator3 as well as the decimal value
			Fraction sum = f1.add(f2);
			numerator3.setText(Integer.toString(sum.numerator));
			denominator3.setText(Integer.toString(sum.denominator));
			decimal.setText(Double.toString(sum.toDecimal()));
		});

		clearButton.setOnMouseClicked(e -> {
			numerator1.clear();
			denominator1.clear();
			numerator2.clear();
			denominator2.clear();
			numerator3.setText("");
			denominator3.setText("");
			decimal.setText("");
		});

		grid.add(new Label("Numerator"), 0, 0);
		grid.add(new Label("Denominator"), 0, 2);
		grid.add(numerator1, 1, 0);
		grid.add(divide1, 1, 1);
		grid.add(denominator1, 1, 2);
		grid.add(plus, 2, 1);
		grid.add(numerator2, 3, 0);
		grid.add(divide2, 3, 1);
		grid.add(denominator2, 3,2);
		grid.add(equals, 4, 1);
		grid.add(numerator3, 5, 0);
		grid.add(divide3, 5, 1);
		grid.add(denominator3, 5, 2);
		grid.add(decimal, 5, 4);

		grid.add(goButton, 0, 4, 3, 1);
		grid.add(clearButton, 1, 4, 3, 1);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
	}

}
