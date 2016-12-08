class GridSortMax:
    def findMax(self, n, m, grid):
        self.n = n
        self.m = m
        self.grid = grid
        self.perfect = self.createPerfectMatrix()

        rows = [None] * self.n
        columns = [None] * self.m
        remainingRows = range(self.n)
        remainingColumns = range(self.m)
        for value in range(1, self.getCellCount() + 1):
            (expectedRow, expectedColumn) = self.getCoordinates(value - 1)
            (currentRow, currentColumn) = self.findCurrentCoordinates(value)
            canMatchRow = rows[expectedRow] == None or rows[expectedRow] == currentRow
            canMatchColumn = columns[expectedColumn] == None or columns[expectedColumn] == currentColumn
            if not canMatchRow:
                continue;
            if not canMatchColumn:
                continue
            if not rows.__contains__(currentRow):
                rows[expectedRow] = currentRow
                remainingRows.remove(currentRow)
            if not columns.__contains__(currentColumn):
                columns[expectedColumn] = currentColumn
                remainingColumns.remove(currentColumn)
        self.fillEmptyIndexes(rows, remainingRows)
        self.fillEmptyIndexes(columns, remainingColumns)
        rowsSwapped = self.swapRows(self.grid, rows )
        columnsSwapped = self.swapColumns(rowsSwapped, columns)
        return self.compareWithPerfect(columnsSwapped)

    def fillEmptyIndexes(self, all, remaining):
        empty = [i for i, val in enumerate(all) if val == None]
        for i, index in enumerate(empty):
            all[index] = remaining[i]

    def findCurrentCoordinates(self, value):
        return self.getCoordinates(self.grid.index(value))

    def getCoordinates(self, value):
        return (value / self.m, value % self.m)

    def createPerfectMatrix(self):
        r = []
        for i in range(self.n):
            for j in range(self.m):
                r.append(i * self.m + j + 1)
        return r

    def compareWithPerfect(self, grid):
        r = "";
        for i in range(self.getCellCount()):
            r += "1" if grid[i] == self.perfect[i] else "0"
        return r

    def swapRows(self, grid, rows):
        source = lambda row, column: row * self.m + column
        target = lambda row, column: rows[row] * self.m + column
        return self.swapCells(grid, source, target)

    def swapColumns(self, grid, columns):
        source = lambda row, column: row * self.m + column
        target = lambda row, column: row * self.m + columns[column]
        return self.swapCells(grid, source, target)

    def swapCells(self, grid, source, target):
        r = [None] * self.getCellCount()
        for row in range(self.n):
            for column in range(self.m):
                r[source(row, column)] = grid[target(row, column)]
        return r

    def getCellCount(self):
        return self.n * self.m
