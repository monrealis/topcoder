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
            self.remaining_rows = range(n)
            self.remaining_columns = range(m)
            self.grid = grid

        def find(self):
            for value in range(1, self.get_cell_count() + 1):
                self.add_next_value(value)
            self.fill_empty_rows_and_columns()
            return self.rows, self.columns

        def add_next_value(self, value):
            expected_row, expected_column = self.get_coordinates(value - 1)
            current_row, current_column = self.find_current_coordinates(value)
            if self.rows[expected_row] not in (None, current_row):
                return
            if self.columns[expected_column] not in (None, current_column):
                return
            self.add_row(current_row, expected_row)
            self.add_column(current_column, expected_column)

        def add_row(self, current_row, expected_row):
            if current_row not in self.rows:
                self.rows[expected_row] = current_row
                self.remaining_rows.remove(current_row)

        def add_column(self, current_column, expected_column):
            if current_column not in self.columns:
                self.columns[expected_column] = current_column
                self.remaining_columns.remove(current_column)

        def fill_empty_rows_and_columns(self):
            self.fill_empty_indexes(self.rows, self.remaining_rows)
            self.fill_empty_indexes(self.columns, self.remaining_columns)

        def get_coordinates(self, value):
            return value / self.get_m(), value % self.get_m()

        def fill_empty_indexes(self, all, remaining):
            empty = [i for i, val in enumerate(all) if val is None]
            for i, index in enumerate(empty):
                all[index] = remaining[i]

        def find_current_coordinates(self, value):
            return self.get_coordinates(self.grid.index(value))
gimėgimė
        def get_cell_count(self):
            return self.get_n() * self.get_m()

        def get_n(self):
            return len(self.rows)

        def get_m(self):
            return len(self.columns)

    def swap(self, grid, columns, rows):
        rows_swapped = self.swap_rows(grid, rows)
        columns_swapped = self.swap_columns(rows_swapped, columns)
        return columns_swapped

    def swap_rows(self, grid, rows):
        def source(row, column): return row * self.m + column

        def target(row, column): return rows[row] * self.m + column

        return self.swap_cells(grid, source, target)

    def swap_columns(self, grid, columns):
        def source(row, column): return row * self.m + column

        def target(row, column): return row * self.m + columns[column]

        return self.swap_cells(grid, source, target)

    def swap_cells(self, grid, source, target):
        r = [None] * len(grid)
        for row in range(self.n):
            for column in range(self.m):
                r[source(row, column)] = grid[target(row, column)]
        return r

    def compare_with_perfect(self, grid):
        r = ""
        for i in range(len(grid)):
            r += "1" if grid[i] == i + 1  else "0"
        return r
