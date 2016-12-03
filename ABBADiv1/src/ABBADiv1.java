public class ABBADiv1 {
    private int differenceOfBs;

    public String canObtain(String initial, String target) {
        prepare(initial, target);
        return findCanObtain(reverseInitialIfNecessary(initial), target);
    }

    private void prepare(String initial, String target) {
        differenceOfBs = getDifferencesOfB(initial, target);
    }

    private String reverseInitialIfNecessary(String initial) {
        String initialPossiblyReversed;
        if (differenceOfBs % 2 == 1)
            initialPossiblyReversed = reverse(initial);
        else
            initialPossiblyReversed = initial;
        return initialPossiblyReversed;
    }

    private String findCanObtain(String initial, String target) {
        int from = 0;
        int position;
        while ((position = target.indexOf(initial, from)) >= 0) {
            from = position + 1;
            String before = target.substring(0, position);
            String after = target.substring(position + initial.length());
            if (isCanObtain(before, after))
                return "Possible";
        }
        return "Impossible";
    }

    private boolean isCanObtain(String stringBefore, String stringAfter) {
        int bBefore = count(stringBefore, 'B');
        int bAfter = count(stringAfter, 'B');
        if (differenceOfBs % 2 == 0)
            return bBefore == bAfter;
        else
            return bBefore - bAfter == 1;
    }

    private int getDifferencesOfB(String initial, String target) {
        return count(target, 'B') - count(initial, 'B');
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
