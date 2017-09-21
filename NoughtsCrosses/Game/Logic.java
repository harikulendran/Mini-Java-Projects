public class Logic {
	public boolean isWinner(int p, Board board) {
		Triple[] t = getTriples();
		for (int i=0; i<t.length; i++) {
			int a;
			if ((a=board.get(t[i].i))==p && (a=board.get(t[i].j))==p && (a=board.get(t[i].k))==p)
				return true;
			else
				invalidate(t,a);
		}
		return false;
	}

	//Currently only supports 3x3 boards
	private Triple[] getTriples() {
		int[][] intTriples = new int[][] {{1,2,3},{4,5,6},{7,8,9},
						  {1,4,7},{2,5,8},{3,6,9},
						  {1,5,9},{3,5,7}};
		Triple[] triples = new Triple[intTriples.length];
		for (int i=0; i<intTriples.length; i++)
			triples[i] = new Triple(intTriples[i]);
		return triples;
	}
	private void invalidate(Triple[] triples, int i) {
		for (Triple t : triples)
			if (t.i==i || t.j==i || t.k==i)
				t.valid = false;
	}

	private class Triple {
		int i,j,k;
		boolean valid;

		public Triple(int[] a) {
			this(a[0],a[1],a[2]);
		}
		public Triple(int i, int j, int k) {
			this.i = i;
			this.j = j;
			this.k = k;
			valid = true;
		}
	}
}
