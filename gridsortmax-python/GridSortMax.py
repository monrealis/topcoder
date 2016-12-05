import itertools


class GridSortMax:
    def findMax(self, n, m, grid):
        self.n = n
        self.m = m
        self.grid = grid
        self.perfect = self.createPerfectMatrix()

        rows = [None] * self.n
        columns = [None] * self.m
        allRows = range(self.n)
        allColumns = range(self.m)
        for value in range(1, self.n * self.m + 1):
            (wantedRow, wantedColumn) = ((value - 1) / self.m, (value - 1) % self.m)
            (row, column) = self.find(value)
            canMatchRow = rows[wantedRow] == None or rows[wantedRow] == row
            canMatchColumn = columns[wantedColumn] == None or columns[wantedColumn] == column
            if not canMatchRow:
                continue;
            if not canMatchColumn:
                continue
            if not rows.__contains__(row):
                rows[wantedRow] = row
                allRows.remove(row)
            if not columns.__contains__(column):
                columns[wantedColumn] = column
                allColumns.remove(column)
        emptyRows = [i for i, val in enumerate(rows) if val == None]
        emptyColumns = [i for i, val in enumerate(columns) if val == None]
        for i, rowIndex in enumerate(emptyRows):
            rows[rowIndex] = allRows[i]
        for i, columnIndex in enumerate(emptyColumns):
            columns[columnIndex] = allColumns[i]
        rowsSwapped = self.swapRows(self.grid, rows + allRows)
        columnsSwapped = self.swapColumns(rowsSwapped, columns + allColumns)
        return self.compareWithPerfect(columnsSwapped)

    def find(self, value):
        index = self.grid.index(value)
        return (index / self.m, index % self.m)

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
        r = [None] * (self.n * self.m)
        for row in range(self.n):
            for column in range(self.m):
                r[row * self.m + column] = grid[rows[row] * self.m + column]
        return r

    def swapColumns(self, grid, columns):
        r = [None] * (self.n * self.m)
        for row in range(self.n):
            for column in range(self.m):
                r[row * self.m + column] = grid[row * self.m + columns[column]]
        return r
