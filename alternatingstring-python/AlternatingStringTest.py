import AlternatingString
import unittest

class AlternatingStringTest(unittest.TestCase):

	def setUp(self):
		self.solution = AlternatingString.AlternatingString()

	def test_0(self):
		s = '111101111'

		expected = 3
		actual = self.solution.maxLength(s)

		self.assertEqual(expected, actual)

	def test_1(self):
		s = '1010101'

		expected = 7
		actual = self.solution.maxLength(s)

		self.assertEqual(expected, actual)

	def test_2(self):
		s = '000011110000'

		expected = 2
		actual = self.solution.maxLength(s)

		self.assertEqual(expected, actual)

	def test_3(self):
		s = '1011011110101010010101'

		expected = 8
		actual = self.solution.maxLength(s)

		self.assertEqual(expected, actual)

	def test_4(self):
		s = '0'

		expected = 1
		actual = self.solution.maxLength(s)

		self.assertEqual(expected, actual)

if __name__ == '__main__': unittest.main()
