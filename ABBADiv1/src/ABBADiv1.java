import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ABBADiv1 {
    private int n;
    private int differenceOfAs;
    private int differenceOfBs;
    private String initial;
    private String target;
    private boolean found;

    public String canObtain(String initial, String target) {
        findDifferences(initial, target);
        this.initial = initial;
        this.target = target;
        collectPermutations("");
        if (found)
            return "Possible";
        return "Impossible";
    }

    private void findDifferences(String initial, String target) {
        differenceOfAs = count(target, 'A') - count(initial, 'A');
        differenceOfBs = count(target, 'B') - count(initial, 'B');
        n = differenceOfBs + differenceOfAs;
    }


    private void collectPermutations(String permutation) {
        if (found)
            return;
        if (count(permutation, 'a') > differenceOfAs)
            return;
        if (count(permutation, 'b') > differenceOfBs)
            return;
        if (permutation.length() == n) {
            if (target.equals(apply(initial, permutation))) {
                found = true;
                return;
            }
        } else
            for (String action : new String[]{"a", "b"})
                collectPermutations(permutation + action);
    }

    private String apply(String initial, String permutation) {
        String r = initial;
        for (char c : permutation.toCharArray())
            if (c == 'a')
                r += "A";
            else {
                r += "B";
                r = reverse(r);
            }
        return r;
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
