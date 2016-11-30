import static java.lang.Integer.parseInt;
import static java.util.Collections.emptySet;

import java.util.HashSet;
import java.util.Set;

public class MineField {
	public String[] getMineField(String mineLocations) {
		Set<Location> locations = parseLocations(mineLocations);
		return new FieldBuilder(locations).build();
	}

	private Set<Location> parseLocations(String mineLocations) {
		if (mineLocations.trim().isEmpty())
			return emptySet();
		Set<Location> locations = new HashSet<>();
		String stripped = mineLocations.replaceAll("[()]", " ").trim();
		for (String oneLocation : stripped.split("\\s+")) {
			String[] parts = oneLocation.split(",");
			locations.add(new Location(parseInt(parts[0]), parseInt(parts[1])));
		}
		return locations;
	}
}

class FieldBuilder {
	private final Set<Location> mines;
	private final int[][] field = new int[9][9];

	public FieldBuilder(Set<Location> mines) {
		this.mines = mines;
	}

	public String[] build() {
		fillField();
		return createRowsAsStrings();
	}

	private void fillField() {
		for (Location mine : mines)
			for (int i = -1; i < 2; ++i)
				for (int j = -1; j < 2; ++j)
					incrementIfPossible(mine, i, j);
	}

	private void incrementIfPossible(Location mine, int deltaRow, int deltaColumn) {
		incrementIfPossible(mine.row + deltaRow, mine.column + deltaColumn);
	}

	private void incrementIfPossible(int row, int column) {
		if (row < 0)
			return;
		if (column < 0)
			return;
		if (row >= field.length)
			return;
		if (column >= field.length)
			return;
		field[row][column]++;
	}

	private String[] createRowsAsStrings() {
		String[] rows = new String[field.length];
		for (int i = 0; i < field.length; ++i)
			rows[i] = createRow(i);
		return rows;
	}

	private String createRow(int index) {
		String row = "";
		for (int j = 0; j < field[index].length; ++j)
			if (mines.contains(new Location(index, j)))
				row += "M";
			else
				row += String.valueOf(field[index][j]);
		return row;
	}
}

class Location {
	public final int row;
	public final int column;

	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public int hashCode() {
		return row << 16 | column;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(this instanceof Location))
			return false;
		Location other = (Location) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}