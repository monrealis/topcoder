import static java.util.Arrays.asList;

public class BombSweeper {
	private int rows;
	private int columns;
	private String[] extendedBoard;

	public double winPercentage(String[] board) {
		rows = board.length;
		columns = board[0].length();
		extendedBoard = extendBoard(board);
		return calculatePercentage();
	}

	private double calculatePercentage() {
		int wins = getNumberOfWins();
		int losses = getNumberOfLosses();
		return 100.0 * wins / (wins + losses);
	}

	private int getNumberOfLosses() {
		int failed = 0;
		for (int i = 0; i < rows; ++i)
			for (int j = 0; j < columns; ++j)
				if (isBomb(i, j))
					++failed;
		return failed;
	}

	private int getNumberOfWins() {
		int passed = 0;
		for (int i = 0; i < rows; ++i)
			for (int j = 0; j < columns; ++j) {
				if (isWin(i, j))
					++passed;
			}
		return passed;
	}

	private boolean isWin(int row, int column) {
		if (isBomb(row, column))
			return false;
		for (int i : asList(-1, 0, 1))
			for (int j : asList(-1, 0, 1))
				if (isBomb(row + i, column + j))
					return false;
		return true;
	}

	private boolean isBomb(int row, int column) {
		return charAt(row, column) == 'B';
	}

	private char charAt(int i, int j) {
		return extendedBoard[i + 1].charAt(j + 1);
	}

	private String[] extendBoard(String[] board) {
		String[] extended = new String[rows + 2];
		extended[0] = createEmptyRow(columns + 2);
		extended[rows + 1] = createEmptyRow(columns + 2);
		for (int i = 0; i < rows; ++i)
			extended[i + 1] = "." + board[i] + ".";
		return extended;
	}

	private String createEmptyRow(int length) {
		String r = "";
		for (int i = 0; i < length; ++i)
			r += ".";
		return r;
	}
}
