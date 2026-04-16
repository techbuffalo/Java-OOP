//HW3 - Noah Duran - CMU nduran

package hw3;

import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** TextEditor is a simple application to demonstrate how to develop an 
 * end-to-end application with text-file as 'Model', 
 * GUI as 'View', and various event-handlers as 'Controllers'  
 */
public class TextEditor extends Application {
	Stage stage;
	BorderPane root = new BorderPane(); 	//holds all GUI components
	TextArea fileTextArea = new TextArea(); //displays the file content
	Label statusLabel = new Label();				//shows the status of various actions
	String searchString;					//used in Search function
	
	FileUtilities fileUtilities = new FileUtilities();
	
	BooleanProperty isFileClosed = new SimpleBooleanProperty(false);
	StringProperty fileContentProperty = new SimpleStringProperty();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		setScreen();
		Scene scene = new Scene(root, 500, 600);
		stage.setTitle("Text Editor");
		stage.setScene(scene);
		stage.show();
	}

	public void setScreen() {
		//create menus
		Menu fileMenu = new Menu("File");
		Menu toolsMenu = new Menu("Tools");
		Menu helpMenu = new Menu("Help");

		//attach File menu items and their event handlers
		MenuItem openFileMenuItem = new MenuItem("Open");
		openFileMenuItem.setOnAction(new OpenFileHandler());
		MenuItem saveFileMenuItem = new MenuItem("Save");
		saveFileMenuItem.setOnAction(new SaveFileHandler());
		MenuItem closeFileMenuItem = new MenuItem("Close");
		closeFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fileContentProperty.setValue("");
				statusLabel.setText("");
				root.setCenter(null);
				isFileClosed.set(true);
			}
		});
		MenuItem exitFileMenuItem = new MenuItem("Exit");
		exitFileMenuItem.setOnAction(actionEvent->Platform.exit());

		//attach Tools menu items and their event handlers
		MenuItem searchToolsMenuItem = new MenuItem("Search");
		searchToolsMenuItem.setOnAction(new SearchToolHandler());
		MenuItem replaceToolsMenuItem = new MenuItem("Replace");
		replaceToolsMenuItem.setOnAction(new ReplaceToolHandler());
		MenuItem wordCountToolsMenuItem = new MenuItem("Word Count");
		wordCountToolsMenuItem.setOnAction(new WordCountToolHandler());
		MenuItem uniqueWordCountToolsMenuItem = new MenuItem("Unique word count");
		uniqueWordCountToolsMenuItem.setOnAction(new UniqueWordCountToolHandler());

		//set Help menu
		MenuItem aboutHelpMenuItem = new MenuItem("About");
		aboutHelpMenuItem.setOnAction(new AboutHandler());

		//set menubar
		MenuBar menuBar = new MenuBar();

		fileMenu.getItems().addAll(openFileMenuItem, saveFileMenuItem, closeFileMenuItem, new SeparatorMenuItem(), exitFileMenuItem);
		toolsMenu.getItems().addAll(searchToolsMenuItem, replaceToolsMenuItem, new SeparatorMenuItem(),wordCountToolsMenuItem, uniqueWordCountToolsMenuItem);
		helpMenu.getItems().addAll(aboutHelpMenuItem);
		menuBar.getMenus().addAll(fileMenu, toolsMenu, helpMenu);	

		//set status bar
		statusLabel.setPrefWidth(this.stage.getMaxWidth());
		statusLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		//set layout 
		root.setTop(menuBar);
		root.setBottom(statusLabel);
		
		//initialize bindings
		isFileClosed.set(true);
		toolsMenu.disableProperty().bind(isFileClosed);
		saveFileMenuItem.disableProperty().bind(isFileClosed);
		closeFileMenuItem.disableProperty().bind(isFileClosed);
		fileTextArea.textProperty().bindBidirectional(fileContentProperty);
	}

	/**OpenHandler provides the functionality for opening a file when the File-Open menu option 
	 * is selected by the user. 
	 */
	private class OpenFileHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			//Using file chooser so that it looks like the video in HW2 rather than a text entry box. 
			FileChooser fileChooser = new FileChooser();

			//This title is what's shown in the window when the user searches for a file to work with (when the user is using Windows)
			fileChooser.setTitle("Select the text file you want to work with");
			statusLabel.setText("Looking for that file huh?");

			//Showing the contents of the file that the user chose to the app
			File selectedFile = fileChooser.showOpenDialog(stage);

			//A check to close the application if the user were to click cancel or something of the like
			if (selectedFile == null) {
				statusLabel.setText("Open cancelled.");
				return;
			}

			//Setting the file contents out to the main display
			StringBuilder fileContent = fileUtilities.readFile(selectedFile.getAbsolutePath());
			fileContentProperty.setValue(fileContent.toString());
			root.setCenter(fileTextArea);
			statusLabel.setText("Opened: " + selectedFile.getName());

			isFileClosed.set(false);

			
		}
	}

	/** getString() is a helper method that opens a dialog box to take user input for a 'purpose'. 
	 * This 'purpose' is meant to be either 'Search' or 'Replace' which
	 * appears as the title and label in the dialog box.
	 * The method returns the string entered by the user.
	 * When user presses Cancel, it returns null.
	 */
	private String getString(String purpose) {   
		String inputString = null;
		TextInputDialog searchTextInputDialog = new TextInputDialog();
		searchTextInputDialog.setTitle(purpose);
		searchTextInputDialog.setHeaderText(String.format("%s string", purpose));
		searchTextInputDialog.setContentText(String.format("Enter %s string ", purpose));
		Optional <String> searchStringOptional = searchTextInputDialog.showAndWait();
		if (searchStringOptional.isPresent()) {
			statusLabel.setText("");
			inputString = searchStringOptional.get();
		}
		if (inputString ==  null || inputString.isEmpty()) return null;  //to handle empty string on Cancel
		else return inputString;
	}

	/**SearchToolHandler provides the functionality for searching a string when the Tool-Search menu option
	 * is selected by the user. It uses the getString() method as a helper method to capture the user input. 
	 */
	private class SearchToolHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if (isFileClosed.get()) {
				statusLabel.setText("Open a file first.");
				return;
			}
			searchString = getString("Search");
			if (searchString == null || searchString.isEmpty()) {
				statusLabel.setText("Search canceled");
				return;
			}
	
			//Using search all to find the desired strings and hold their positions in the file in an array so they can be counted
			int[] stringPos = fileUtilities.searchAll(
				new StringBuilder(fileContentProperty.getValue() == null ? "" : fileContentProperty.getValue()),
				searchString
			);
			if (stringPos == null) {
				statusLabel.setText(searchString +  " not found.");
				return;
			}


			/*Setting up the positioning for  the old string, this way when the program finds what the user is looking
			for it will have a left and right bounds of its lenght to move the cursor and to highlight the target string */
			int firstPosition = stringPos[0];
			int endPosition = firstPosition + searchString.length();

			//Timing issues were causing the highlight to appear and then go away instantly. 
			// This runLater enables the highlighting to stay after the dialog box goes away.
			Platform.runLater(() -> {
				fileTextArea.requestFocus();
    			fileTextArea.selectRange(firstPosition, endPosition);
			});

			//Moving the cursor to the end of the old string
			fileTextArea.requestFocus();
			fileTextArea.selectRange(firstPosition, endPosition);
			fileTextArea.positionCaret(endPosition);

			String wordFinding = searchString + " found " + stringPos.length + " times";
			
			statusLabel.setText(wordFinding);
		}
	}

	/**ReplaceToolHandler provides the functionality for searching and replaceing a string when the Tool-Replace menu option
	 * is selected by the user. It uses the getString() method as a helper method to capture the user input. 
	 */
	private class ReplaceToolHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if (isFileClosed.get()) {
				statusLabel.setText("Open a file first.");
				return;
			}

			//Prompting the user for which string they want to replace
			String oldString = getString("Search");
			if (oldString == null || oldString.isEmpty()) {
				statusLabel.setText("Replace canceled");
				return;
			}

			//Prompting the user for what they want to replace it with
			String newString = getString("Replace");
			if (newString == null) {
				statusLabel.setText("Replace canceled");
				return;
			}

			//Using repalce function to look through fileContent, located the oldString, and then locate the new string
			StringBuilder fileContent =
				new StringBuilder(fileContentProperty.getValue() == null ? "" : fileContentProperty.getValue());
			int count = fileUtilities.replace(fileContent, oldString, newString);
			if (count == 0) {
				statusLabel.setText(oldString + " was not found in the text");
			} else {
				fileContentProperty.setValue(fileContent.toString());
				statusLabel.setText("Replaced " + oldString+ " with " + newString + " " + count + " times");
			}
		}
	}

	/**WordCountToolHandler provides the functionality for counting the total number of words 
	 * in the text file that is currently open.  
	 */
	private class WordCountToolHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//Using countWords here to look through the file content and return the word count
			int words = fileUtilities.countWords(
				new StringBuilder(fileContentProperty.getValue() == null ? "" : fileContentProperty.getValue()));
			
			statusLabel.setText("There are " + words + " words in the file");
	}
}
	
	private class AboutHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("Heinz Text Editor");
			alert.setContentText("Version 1.0 \nRelease 1.0\nCopyright CMU\nDeveloped by a minion!");
			Image image = new Image(this.getClass().getResource("image.png").toString());
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitWidth(150);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			alert.setGraphic(imageView);
			alert.showAndWait();
		}
	}
	
	
	/**UniqueWordCountToolHandler provides the count of unique words in the text file currently open */
	private class UniqueWordCountToolHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			int words = fileUtilities.countUniqueWords(
				new StringBuilder(fileContentProperty.getValue() == null ? "" : fileContentProperty.getValue()));			
			
				statusLabel.setText("There are " + words + " unique words in the file");
	}
}

	
	/**SaveFileHandler provides the FileChooser dialog box to save the current file*/
	private class SaveFileHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			//Filechooser similar to when the file opens so that the user can choose where to save and what to name it
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose where to save your file");
			File selectedFile = fileChooser.showSaveDialog(stage);

			//A check to close the application if the user were to click cancel
			if (selectedFile == null) {
				statusLabel.setText("Saving cancelled.");
				return;
			}

			//implementing the write file in order to save it and display that it was successful to the user
			String currentText = fileContentProperty.getValue();
			String saveStatus = fileUtilities.writeFile(selectedFile.getAbsolutePath(), currentText);
			statusLabel.setText(saveStatus);
			if ("File saved".equals(saveStatus)) {
				isFileClosed.set(false);
			}
		}
	}

}
