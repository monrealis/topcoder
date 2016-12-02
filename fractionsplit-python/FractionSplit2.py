class FractionSplit:
    def __init__(self):
        self.fractions = []

    def getSum(self, n, d):
        self.remaining = Fraction(n, d)
        self.denom = 1;
        self._fillFractions()
        return self.fractions
    
    def _fillFractions(self):
        while not self.remaining.zero():
            self._handleNextFraction()

    def _handleNextFraction(self):
        nextFraction = self._getNextFraction()
        self.fractions.append(str(nextFraction))
        self.remaining = self.remaining.minus(nextFraction)
        
    def _getNextFraction(self):
        while self.remaining.value() < 1.0 / self.denom:
            self.denom += 1
        nextFraction = Fraction(1, self.denom)
        return nextFraction
    
    
class Fraction:
    def __init__(self, a, b):
        self.a = a;
        self.b = b;
    
    def minus(self, other):
        n = self.a * other.b - self.b * other.a
        d = self.b * other.b
        return Fraction(n, d)
    
    def value(self):
        return 1.0 * self.a / self.b
    
    def zero(self):
        return self.a == 0
    
    def __str__(self):
        return "%d/%d" % (self.a, self.b)
    
        
        