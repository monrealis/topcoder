import itertools

class GridSortMax:
    def findMax(self, n, m, grid):
        self.n = n
        self.m = m
        self.grid = grid
        self.perfect = self.createPerfectMatrix()
        self.best = set()
        for r in itertools.combinations_with_replacement(range(n), 2):
            swappedRows = self.swapRows(grid, r[0], r[1])
            self.best.add(self.compareWithPerfect(swappedRows));
            for c in itertools.combinations_with_replacement(range(m), 2):
                swappedColumns = self.swapColumns(swappedRows, c[0], c[1])
                self.best.add(self.compareWithPerfect(swappedColumns));
        return max(self.best)

    def createPerfectMatrix(self):
        r = []
        for i in range(self.n):
            for j in range(self.m):
                r.append(i * self.n + j + 1)
        return r

    def compareWithPerfect(self, grid):
        r = "";
        for i in range(self.n * self.m):
            r += "1" if grid[i] == self.perfect[i] else "0"
        return r

    def swapRows(self, grid, row1, row2):
        r = grid[:]
        for column in range(self.m):
            r[row1 * self.m + column] = grid[row2 * self.m + column]
            r[row2 * self.m + column] = grid[row1 * self.m + column]
        return r

    def swapColumns(self, grid, column1, column2):
        print ("%d %d %d" % (grid.__len__(), column1, column2))
        r = grid[:]
        for row in range(self.n):
            r[row * self.m + column1] = grid[row * self.m + column2]
            r[row * self.m + column2] = grid[row * self.m + column1]
        return r


