import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ABBADiv1 {
    private int differenceOfAs;
    private int differenceOfBs;

    public String canObtain(String initial, String target) {
        findDifferences(initial, target);
        if (differenceOfBs % 2 == 1)
            initial = reverse(initial);
        if (target.contains(initial))
            return "Possible";
        return "Impossible";
    }

    private void findDifferences(String initial, String target) {
        differenceOfAs = count(target, 'A') - count(initial, 'A');
        differenceOfBs = count(target, 'B') - count(initial, 'B');
    }

    private String reverse(String text) {
        char[] chars = text.toCharArray();
        char[] r = new char[chars.length];
        for (int i = 0; i < chars.length; ++i)
            r[chars.length - 1 - i] = chars[i];
        return new String(r);
    }

    private int count(String target, char c) {
        int r = 0;
        for (char t : target.toCharArray())
            if (t == c)
                ++r;
        return r;
    }
}
