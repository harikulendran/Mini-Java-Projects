package NoughtsCrosses.Game;

public class Bot {
	private final double WIN_VALUE = 1;
	private final double LOSE_VALUE = -4;
	private final double TIE_VALUE = 0;
	private final double DEGREDATION_RATE = 3;

	private Logic logic;
	private Board board;
	private double[] odds;
	private int player;
	private int opp;

	public Bot(Board board, int player) {
		this.board = board;
		this.player = player;
		opp = (player==1)?2:1;
		logic = new Logic();
	}

	public int nextMove() {
		odds = new double[9];
		for (int i=0; i<9; i++) {
			odds[i] += checkMove(i, player, new Board(board),1.0)[0];
		}

		double best = -20000.0;
		int bestIndex = -1;
		for (int i=0; i<9; i++) {
			if (board.get(i)==0) {
				bestIndex = odds[i] > best ? i : bestIndex;
				best = odds[i] > best ? odds[i] : best;
			}
		}

		//print out odds for testing
		/*
		for (int i=0; i<9; i++) {
			System.out.print(odds[i]+",");
			if ((i+1)%3==0)
				System.out.println();
		}
		System.out.println();
		*/

		if (bestIndex>-1)
			board.set(bestIndex, player);
		return bestIndex;
	}

	public double[]	nextMove(int p, Board b, double depth) {
		double out = 0;
		for (int i=0; i<9; i++) {
			double[] d = checkMove(i,p,new Board(b),depth);
			out += d[0]/d[1];
		}
		return new double[] {out,1};
	}
	public double[]	checkMove(int i, int p, Board b, double depth) {
		if (b.get(i)==0) {
			b.set(i,p);
			if (logic.isWinner(player,b))
				return new double[]{WIN_VALUE,depth};
			else if (logic.isWinner(opp,b))
				return new double[]{LOSE_VALUE,depth};
			else {
				boolean tie = true;
				for (int j=0; j<9; j++)
					if (b.get(j)==0)
						tie = false;
				return tie ? new double[]{TIE_VALUE,depth} : nextMove(p==1?2:1,b,depth+DEGREDATION_RATE);
			}
				
		} else 
			return new double[] {0,depth};
	}
}
