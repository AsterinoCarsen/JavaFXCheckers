package ui;


import core.CheckersComputerPlayer;
import core.CheckersLogic;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * A graphical user interface implementation of checkers.
 * 
 * Includes gamemodes for playing against a computer or in a versus mode.
 * @author Carsen
 *
 */
public class CheckersGUI extends Application {
	protected CheckersLogic logic;
	protected CheckersComputerPlayer computerPlayer;
	protected static boolean isComputerMode;
	
	private GridPane grid;
	private TextField textField;
	private char currMove = 'o';
	
	/**
	 * Initalizes AI and logic.
	 */
	public CheckersGUI() {
		logic = new CheckersLogic();
		computerPlayer = new CheckersComputerPlayer();
	}
	
	/**
	 * Start point for JavaFX thread.
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			isComputerMode = true;
		}
		
		launch(args);
	}
	
	/**
	 * Generate inital interface including buttons and grids.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Handler handler = new Handler();
		StackPane root = new StackPane();
		
		root.setStyle("-fx-background-color: #212121;");
		
		grid = new GridPane();
		
		textField = new TextField();
		textField.setPromptText("Enter move, ex. '1a-2b'");
		
		Button playButton = new Button("Play");
		playButton.setOnAction(handler);
		
		double cellSize = 100.0;
		int numRows = 8;
		int numCols = 8;
		
		for (int i = 0; i < numRows; i++) {
			for (int k = numCols - 1; k >= 0; k--) {
				Pane cell = new Pane();
				cell.setPrefSize(cellSize, cellSize);
				cell.setStyle("-fx-border-color: #3d3d3d;");
				
				Circle circ = new Circle(cellSize / 2);
				circ.setFill(Color.RED);
				
				circ.setTranslateX(cellSize / 2);
				circ.setTranslateY(cellSize / 2);
				
				cell.getChildren().add(circ);
				
				cell.setId(this.generateCellID(i, k));
				Text text = new Text(cellSize / 2 - 5, cellSize / 2 + 5, this.generateCellID(i, k));
				text.setFill(Color.WHITESMOKE);
				
				cell.getChildren().add(text);
				
				grid.add(cell, i, k);
			}
		}
		
		HBox bottomPane = new HBox(textField, playButton);
		bottomPane.setAlignment(Pos.CENTER);
		
		VBox vBox = new VBox(grid, bottomPane);
		vBox.setAlignment(Pos.CENTER);
		
		root.getChildren().add(vBox);
		
		Scene scene = new Scene(root);
		
		stage.setTitle("Checkers");
		stage.setScene(scene);
		stage.show();
		
		handler.handle(null);
		
	}
	
	/**
	 * Helper method to turn numbered rows and columns into chess board notation.
	 * @param row
	 * @param col
	 * @return string representation, ex "1a" or "8h".
	 */
	protected String generateCellID(int row, int col) {
		char letter = (char) ('a' + col);
		int number = row + 1;
		return number + Character.toString(letter);
	}
	
	/**
	 * Event handler class for changing the state of the board and managing plays.
	 * @author Carsen
	 *
	 */
	protected class Handler implements EventHandler<ActionEvent> {
		
		/**
		 * Updates the graphical cells if the gamemode is in versus.
		 */
		private void updateCellsVersus() {
			for (Node node : grid.getChildren()) {
			    if (node instanceof Pane) {
			        Pane cell = (Pane) node;
			        for (Node cellNode : cell.getChildren()) {
			            if (cellNode instanceof Circle) {
			                Circle circ = (Circle) cellNode;
			                String nodeId = node.getId();
			                char contains = logic.getCell(nodeId).get();
			                
			                if (contains == 'x') {
			                    circ.setFill(Color.RED);
			                } else if (contains == 'o') {
			                    circ.setFill(Color.BLUE);
			                } else {
			                    circ.setFill(Color.TRANSPARENT);
			                }
			            }
			        }
			    }
			}
		}
		
		/**
		 * Updates the graphical cells if the gamemode is versus computer.
		 */
		private void updateCellsComputer() {
			for (Node node : grid.getChildren()) {
			    if (node instanceof Pane) {
			        Pane cell = (Pane) node;
			        for (Node cellNode : cell.getChildren()) {
			            if (cellNode instanceof Circle) {
			                Circle circ = (Circle) cellNode;
			                String nodeId = node.getId();
			                char contains = computerPlayer.getCell(nodeId).get();
			                
			                if (contains == 'x') {
			                    circ.setFill(Color.RED);
			                } else if (contains == 'o') {
			                    circ.setFill(Color.BLUE);
			                } else {
			                    circ.setFill(Color.TRANSPARENT);
			                }
			            }
			        }
			    }
			}
		}
		
		/**
		 * Loop for managing computer gamemode.
		 */
		private void computerMoveLoop() {
			updateCellsComputer();
			String playerMove = textField.getText();
			if (playerMove.length() == 5) {
				String[] parts = playerMove.split("-");
				computerPlayer.play(parts[0], parts[1], 'x');
				
				textField.clear();
				
				updateCellsComputer();
			}	
			
			String[] computerMove = computerPlayer.generateMove();
			computerPlayer.play(computerMove[0], computerMove[1], 'o');
			
			updateCellsComputer();
		}
		
		/**
		 * Loop for managing versus gamemode.
		 */
		private void versusMoveLoop() {
			String move = textField.getText();
			
			if (move.length() == 5) {
				String[] parts = move.split("-");
				
				if (currMove == 'x') {
					currMove = 'o';
				} else {
					currMove = 'x';
				}
				
				System.out.println(currMove);
				
				logic.play(parts[0], parts[1], currMove);
				textField.clear();
			}
			
			updateCellsVersus();
		}
		
		/**
		 * Start point of the event, will chose which loop to proceed to depending on the selected gamemode.
		 */
		@Override
		public void handle(ActionEvent arg0) {
			if (isComputerMode == true) {
				computerMoveLoop();
			} else {
				versusMoveLoop();
			}
		}
		
	}
	
}
