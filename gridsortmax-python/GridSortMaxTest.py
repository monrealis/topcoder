import GridSortMax
import unittest

class GridSortMaxTest(unittest.TestCase):

	def setUp(self):
		self.solution = GridSortMax.GridSortMax()

	def test_0(self):
		n = 2
		m = 2
		grid = [1, 2, 3, 4]

		expected = '1111'
		actual = self.solution.findMax(n, m, grid)

		self.assertEqual(expected, actual)

	def test_1(self):
		n = 2
		m = 2
		grid = [2, 1, 3, 4]

		expected = '1100'
		actual = self.solution.findMax(n, m, grid)

		self.assertEqual(expected, actual)

	def test_2(self):
		n = 2
		m = 2
		grid = [4, 2, 3, 1]

		expected = '1001'
		actual = self.solution.findMax(n, m, grid)

		self.assertEqual(expected, actual)

	@unittest.skip
	def test_3(self):
		n = 1
		m = 10
		grid = [10, 6, 2, 3, 5, 7, 1, 9, 4, 8]

		expected = '1111111111'
		actual = self.solution.findMax(n, m, grid)

		self.assertEqual(expected, actual)

	def test_4(self):
		n = 3
		m = 5
		grid = [5, 2, 10, 7, 9, 3, 4, 14, 11, 1, 15, 12, 6, 8, 13]

		expected = '101100100100000'
		actual = self.solution.findMax(n, m, grid)

		self.assertEqual(expected, actual)

	def test_5(self):
		n = 6
		m = 2
		grid = [3, 9, 5, 1, 10, 6, 2, 7, 8, 11, 12, 4]

		expected = '100000101010'
		actual = self.solution.findMax(n, m, grid)

		self.assertEqual(expected, actual)

if __name__ == '__main__': unittest.main()
