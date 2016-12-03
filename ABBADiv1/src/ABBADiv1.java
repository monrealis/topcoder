import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ABBADiv1 {
    private int n;
    private List<String> permutations;

    public String canObtain(String initial, String target) {
        n = target.length() - initial.length();
        permutations = new ArrayList();
        collectPermutations("");
        for (String permutation : permutations)
            if (target.equals(apply(initial, permutation)))
                return "Possible";
        return "Impossible";
    }


    private void collectPermutations(String permutation) {
        if (permutation.length() == n)
            permutations.add(permutation);
        else
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
}
