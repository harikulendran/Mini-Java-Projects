package NoughtsCrosses.Game;

public class Bot {
	private Logic logic;
	private Board board;
	private int[] odds;
	private int player;
	private int opp;

	public Bot(Board board, int player) {
		this.board = board;
		this.player = player;
		opp = (player==1)?2:1;
		logic = new Logic();
	}

	public int nextMove() {
		odds = new int[9];
		for (int i=0; i<9; i++) {
			odds[i] += checkMove(i, player, new Board(board));
		}

		int best = Integer.MIN_VALUE;
		int bestIndex = -1;
		for (int i=0; i<9; i++) {
			if (board.get(i)==0) {
				bestIndex = odds[i] > best ? i : bestIndex;
				best = odds[i] > best ? odds[i] : best;
			}
			System.out.print(odds[i]+",");
			if ((i+1)%3==0)
				System.out.println();
		}
		if (bestIndex>-1)
			board.set(bestIndex, player);
		return bestIndex;
	}

	//values a win as +1, a loss as -1 and a tie as 0
	public int nextMove(int p, Board b) {
		int out = 0;
		for (int i=0; i<9; i++)
			out += checkMove(i,p,new Board(b));
		return out;
	}
	public int checkMove(int i, int p, Board b) {
		if (b.get(i)==0) {
			b.set(i,p);
			if (logic.isWinner(player,b))
				return 1;
			else if (logic.isWinner(opp,b))
				return -1;
			else
				return nextMove(p==1?2:1,b);
		} else 
			return 0;
	}
}
