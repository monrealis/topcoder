class TestTaking:
    def findMax(self, questions, guessed, actual):
        bestOfTrue = min(guessed, actual)
        bestOfFalse = min(questions - guessed, questions - actual)
        return bestOfTrue + bestOfFalse
