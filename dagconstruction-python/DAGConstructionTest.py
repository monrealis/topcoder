import DAGConstruction
import unittest

class DAGConstructionTest(unittest.TestCase):

	def setUp(self):
		self.solution = DAGConstruction.DAGConstruction()

	def test_0(self):
		x = [2, 1]

		expected = [0, 1]
		actual = self.solution.construct(x)

		self.assertEqual(expected, actual)

	def test_1(self):
		x = [1, 1]

		expected = []
		actual = self.solution.construct(x)

		self.assertEqual(expected, actual)

	def test_2(self):
		x = [1, 3, 1]

		expected = [1, 0, 1, 2]
		actual = self.solution.construct(x)

		self.assertEqual(expected, actual)

	def test_3(self):
		x = [5, 5, 5, 5, 5]

		expected = [-1]
		actual = self.solution.construct(x)

		self.assertEqual(expected, actual)

	def test_4(self):
		x = [4, 2, 2, 1]

		expected = [0, 1, 0, 2, 1, 3, 2, 3]
		actual = self.solution.construct(x)

		self.assertEqual(expected, actual)

if __name__ == '__main__': unittest.main()
