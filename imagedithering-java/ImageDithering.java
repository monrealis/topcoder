public class ImageDithering {
	public int count(String dithered, String[] screen) {
		int count = 0;
		for (String row : screen)
			for (char c : row.toCharArray())
				if (dithered.contains(String.valueOf(c)))
					++count;
		return count;
	}
}
