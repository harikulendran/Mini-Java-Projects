public class Board {
	private static final int SIZE = 3;
	private int[] board;
	
	public Board() {
		board = new int[SIZE*SIZE];
	}
	public Board(Board other) {
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
	}
	public void set(int i, int j, int val) {
		this.board[i * SIZE * j] = val;
	}
}
