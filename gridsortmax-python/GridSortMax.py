class GridSortMax:
    def findMax(self, n, m, grid):
        self.n = n
        self.m = m
        rows, columns = self.find_order(n, m, grid)
        return self.compare_with_perfect(self.swap(grid, columns, rows))

    def find_order(self, n, m, grid):
        return self.OrderFinder(n, m, grid).find()

    class OrderFinder:
        def __init__(self, n, m, grid):
            self.rows = [None] * n
            self.columns = [None] * m
            self.remainingRows = range(n)
            self.remainingColumns = range(m)
            self.grid = grid

        def find(self):
            for value in range(1, self.get_cell_count() + 1):
                self.add_next_value(value)
            self.fill_empty_rows_and_columns()
            return self.rows, self.columns

        def add_next_value(self, value):
            (expectedRow, expectedColumn) = self.get_coordinates(value - 1)
            (currentRow, currentColumn) = self.find_current_coordinates(value)
            if self.rows[expectedRow] not in (None, currentRow):
                return
            if self.columns[expectedColumn] not in (None, currentColumn):
                return
            self.add_row(currentRow, expectedRow)
            self.add_column(currentColumn, expectedColumn)

        def add_row(self, currentRow, expectedRow):
            if not self.rows.__contains__(currentRow):
                self.rows[expectedRow] = currentRow
                self.remainingRows.remove(currentRow)

        def add_column(self, currentColumn, expectedColumn):
            if not self.columns.__contains__(currentColumn):
                self.columns[expectedColumn] = currentColumn
                self.remainingColumns.remove(currentColumn)

        def fill_empty_rows_and_columns(self):
            self.fill_empty_indexes(self.rows, self.remainingRows)
            self.fill_empty_indexes(self.columns, self.remainingColumns)

        def get_coordinates(self, value):
            return (value / self.get_m(), value % self.get_m())

        def fill_empty_indexes(self, all, remaining):
            empty = [i for i, val in enumerate(all) if val == None]
            for i, index in enumerate(empty):
                all[index] = remaining[i]

        def find_current_coordinates(self, value):
            return self.get_coordinates(self.grid.index(value))

        def get_cell_count(self):
            return self.get_n() * self.get_m()

        def get_n(self):
            return self.rows.__len__()

        def get_m(self):
            return self.columns.__len__()

    def swap(self, grid, columns, rows):
        rowsSwapped = self.swap_rows(grid, rows)
        columnsSwapped = self.swap_columns(rowsSwapped, columns)
        return columnsSwapped

    def swap_rows(self, grid, rows):
        source = lambda row, column: row * self.m + column
        target = lambda row, column: rows[row] * self.m + column
        return self.swap_cells(grid, source, target)

    def swap_columns(self, grid, columns):
        source = lambda row, column: row * self.m + column
        target = lambda row, column: row * self.m + columns[column]
        return self.swap_cells(grid, source, target)

    def swap_cells(self, grid, source, target):
        r = [None] * grid.__len__()
        for row in range(self.n):
            for column in range(self.m):
                r[source(row, column)] = grid[target(row, column)]
        return r

    def compare_with_perfect(self, grid):
        r = ""
        for i in range(grid.__len__()):
            r += "1" if grid[i] == i + 1  else "0"
        return r
