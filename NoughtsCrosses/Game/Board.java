package NoughtsCrosses.Game;

public class Board {
	private static final int SIZE = 3;
	private int[] board;
	private int lastMove = -1;
	
	public Board() {
		board = new int[SIZE*SIZE];
	}
	public Board(Board other) {
		this();
		for (int i=0; i<board.length; i++)
			this.board[i] = other.board[i];
	}

	public int get(int i) {
		return this.board[i];
	}
	public int get(int i, int j) {
		return this.board[i + SIZE * j];
	}
	public void set(int i, int val) {
		this.board[i] = val;
		lastMove = i;
	}
	public void set(int i, int j, int val) {
		this.set(i + SIZE * j, val);
	}

	public int last() {
		return lastMove;
	}

	public void print() {
		for (int i=0; i<9; i++) {
			System.out.print(board[i]+",");
			if ((i+1)%3==0)
				System.out.println();
		}
	}
}
