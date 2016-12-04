import itertools

class GridSortMax:
    def findMax(self, n, m, grid):
        self.n = n
        self.m = m
        self.grid = grid
        self.perfect = self.createPerfectMatrix()
        self.best = set()
        for rows in itertools.permutations(range(n), n):
            swappedRows = self.swapRows(grid, rows)
            for columns in itertools.permutations(range(m), m):
                swappedColumns = self.swapColumns(swappedRows, columns)
                comparison = self.compareWithPerfect(swappedColumns)
                self.best.add(comparison);
        return max(self.best)

    def createPerfectMatrix(self):
        r = []
        for i in range(self.n):
            for j in range(self.m):
                r.append(i * self.m + j + 1)
        return r

    def compareWithPerfect(self, grid):
        r = "";
        for i in range(self.n * self.m):
            r += "1" if grid[i] == self.perfect[i] else "0"
        return r

    def swapRows(self, grid, rows):
        r = grid[:]
        for row in range(self.n):
            for column in range(self.m):
                r[row * self.m + column] = grid[rows[row] * self.m + column]
        return r

    def swapColumns(self, grid, columns):
        r = grid[:]
        for row in range(self.n):
            for column in range(self.m):
                r[row * self.m + column] = grid[row * self.m + columns[column]]
        return r


