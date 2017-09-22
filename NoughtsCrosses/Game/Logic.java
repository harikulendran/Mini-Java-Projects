package NoughtsCrosses.Game;

public class Logic {
	public boolean isWinner(int p, Board board) {
		int[][] wins = getWins();
		for (int i=0; i<wins.length; i++)
			if (board.last()!=-1 && contains(wins[i],board.last()) && board.get(wins[i][0])==p && board.get(wins[i][1])==p && board.get(wins[i][2])==p)
				return true;
		return false;
	}

	//Currently only supports 3x3 boards
	private int[][] getWins() {
		return new int[][] {{0,1,2},{3,4,5},{6,7,8},
				    {0,3,6},{1,4,7},{2,5,8},
				    {0,4,8},{2,4,6}};
	}

	private boolean contains(int[] a, int v) {
		for (int i : a)
			if (i==v)
				return true;
		return false;
	}
}
