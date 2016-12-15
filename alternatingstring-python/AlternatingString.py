class AlternatingString:
    def maxLength(self, s):
        extended = ' ' + s + ' '
        streaks = [0]
        for i in range(1, len(extended) - 1):
            differs_from_previous = extended[i] != extended[i - 1]
            streaks.append(streaks[i - 1] + 1 if differs_from_previous else 1)
        return max(streaks)
