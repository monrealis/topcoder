class GridSortMax:
    def findMax(self, n, m, grid):
        self.n = n
        self.m = m
        rows, columns = self.findOrder(n, m, grid)
        return self.compareWithPerfect(self.swap(grid, columns, rows))


    def findOrder(self, n, m, grid):
        return self.OrderFinder(n, m, grid).find()


    class OrderFinder:
        def __init__(self, n, m, grid):
            self.rows = [None] * n
            self.columns = [None] * m
            self.remainingRows = range(n)
            self.remainingColumns = range(m)
            self.grid = grid

        def find(self):
            for value in range(1, self.getCellCount() + 1):
                (expectedRow, expectedColumn) = self.getCoordinates(value - 1)
                (currentRow, currentColumn) = self.findCurrentCoordinates(value)
                canMatchRow = self.rows[expectedRow] == None or self.rows[expectedRow] == currentRow
                canMatchColumn = self.columns[expectedColumn] == None or self.columns[expectedColumn] == currentColumn
                if not canMatchRow:
                    continue;
                if not canMatchColumn:
                    continue
                if not self.rows.__contains__(currentRow):
                    self.rows[expectedRow] = currentRow
                    self.remainingRows.remove(currentRow)
                if not self.columns.__contains__(currentColumn):
                    self.columns[expectedColumn] = currentColumn
                    self.remainingColumns.remove(currentColumn)
            self.fillEmptyIndexes(self.rows, self.remainingRows)
            self.fillEmptyIndexes(self.columns, self.remainingColumns)
            return self.rows, self.columns

        def getCellCount(self):
            return self.getN() * self.getM()

        def getN(self):
            return self.rows.__len__()

        def getM(self):
            return self.columns.__len__()

        def getCoordinates(self, value):
            return (value / self.getM(), value % self.getM())


        def fillEmptyIndexes(self, all, remaining):
            empty = [i for i, val in enumerate(all) if val == None]
            for i, index in enumerate(empty):
                all[index] = remaining[i]

        def findCurrentCoordinates(self, value):
            return self.getCoordinates(self.grid.index(value))

    def swap(self, grid, columns, rows):
        rowsSwapped = self.swapRows(grid, rows)
        columnsSwapped = self.swapColumns(rowsSwapped, columns)
        return columnsSwapped

    def swapRows(self, grid, rows):
        source = lambda row, column: row * self.m + column
        target = lambda row, column: rows[row] * self.m + column
        return self.swapCells(grid, source, target)

    def swapColumns(self, grid, columns):
        source = lambda row, column: row * self.m + column
        target = lambda row, column: row * self.m + columns[column]
        return self.swapCells(grid, source, target)

    def swapCells(self, grid, source, target):
        r = [None] * grid.__len__()
        for row in range(self.n):
            for column in range(self.m):
                r[source(row, column)] = grid[target(row, column)]
        return r

    def compareWithPerfect(self, grid):
        r = "";
        for i in range(grid.__len__()):
            r += "1" if grid[i] == i + 1  else "0"
        return r
