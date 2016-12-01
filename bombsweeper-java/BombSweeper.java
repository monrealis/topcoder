public class BombSweeper {
	public double winPercentage(String[] board) {
		return getWinPercentage(new Board(board));
	}

	private double getWinPercentage(Board board) {
		int wins = 0;
		int losses = 0;
		for (int i = 0; i < board.getRowCount(); ++i)
			for (int j = 0; j < board.getColCount(); ++j)
				if (board.isBomb(i, j))
					++losses;
				else if (board.isWin(i, j))
					++wins;
		return 1.0 * wins / (wins + losses) * 100;
	}
}

class Board {
	private static final int[] NEIGHBOUR_DELTAS = { -1, 0, 1 };
	private final String[] board;

	public Board(String[] board) {
		this.board = board;
	}

	public boolean isWin(int row, int column) {
		for (int i : NEIGHBOUR_DELTAS)
			for (int j : NEIGHBOUR_DELTAS)
				if (isBomb(row + i, column + j))
					return false;
		return true;
	}

	public boolean isBomb(int row, int column) {
		return charAt(row, column) == 'B';
	}

	private char charAt(int i, int j) {
		if (i < 0)
			return '.';
		if (j < 0)
			return '.';
		if (i >= getRowCount())
			return '.';
		if (j >= getRowCount())
			return '.';
		return board[i].charAt(j);
	}

	public int getRowCount() {
		return board.length;
	}

	public int getColCount() {
		return board[0].length();
	}
}
