package NoughtsCrosses.UI;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import NoughtsCrosses.Game.*;

public class Display extends Application {
	private VBox content;
	private Text title;
	private int playerTurn = 1;
	private Board board;
	private Space[] spaces;
	private Logic logic;
	private boolean gameOver = false;
	private Bot bot;

	public void start(Stage primaryStage) {
		//layout
		content = new VBox();
		content.setAlignment(Pos.TOP_CENTER);
		content.setPadding(new Insets(25, 25, 25, 25));
		content.setSpacing(20);

		//Title
		content.getChildren().add(title=new Text("Noughts and Crosses"));
		title.setFont(Font.font("Consolas", FontWeight.BOLD, 30));

		//grid
		initGame();
		addGrid();

		//init
		Scene scene = new Scene(content, 600, 800);
		primaryStage.setTitle("Noughts and Crosses");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void initGame() {
		board = new Board();
		logic = new Logic();
		bot = new Bot(board, 2);
	}

	public void addGrid() {
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		spaces = new Space[9];
		for (int j=0; j<3; j++)
			for (int i=0; i<3; i++) {
				Text t = new Text(".");
				t.setFont(Font.font("Consolas", FontWeight.NORMAL, 30));
				Space space = new Space(i + 3 * j, t);
				spaces[i + 3 * j] = space;
				space.setOnMouseClicked(e -> clicked(e));
				gp.add(space,i,j);
			}
		content.getChildren().add(gp);
	}

	public void clicked(MouseEvent e) {
		Space s = (Space)e.getSource();
		if (s.text.getText().equals(".") && !gameOver) {
			s.text.setText((playerTurn==1)?"O":"X");
			board.set(s.i, playerTurn);
			if (logic.isWinner(playerTurn, board)) {
				title.setText("Player "+playerTurn+" Wins!");
				gameOver=true;
			}
		}
		if (!gameOver) {
			int bm = bot.nextMove();
			System.out.println("BEST: "+bm);
			spaces[bm].text.setText("X");
		}
	}

	class Space extends StackPane {
		Text text;
		int i;
		public Space(int i, Text text) {
			this.setPrefSize(100,100);
			this.setAlignment(Pos.CENTER);
			this.i = i;
			this.text = text;
			this.getChildren().add(text);
		}
	}
}
