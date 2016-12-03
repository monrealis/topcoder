import BinaryCode
import unittest

class BinaryCodeTest(unittest.TestCase):

	def setUp(self):
		self.solution = BinaryCode.BinaryCode()

	def test_0(self):
		message = '123210122'

		expected = ['011100011', 'NONE']
		actual = self.solution.decode(message)

		self.assertEqual(expected, actual)

	def test_1(self):
		message = '11'

		expected = ['01', '10']
		actual = self.solution.decode(message)

		self.assertEqual(expected, actual)

	def test_2(self):
		message = '22111'

		expected = ['NONE', '11001']
		actual = self.solution.decode(message)

		self.assertEqual(expected, actual)

	def test_3(self):
		message = '123210120'

		expected = ['NONE', 'NONE']
		actual = self.solution.decode(message)

		self.assertEqual(expected, actual)

	def test_4(self):
		message = '3'

		expected = ['NONE', 'NONE']
		actual = self.solution.decode(message)

		self.assertEqual(expected, actual)

	def test_5(self):
		message = '12221112222221112221111111112221111'

		expected = ['01101001101101001101001001001101001', '10110010110110010110010010010110010']
		actual = self.solution.decode(message)

		self.assertEqual(expected, actual)

if __name__ == '__main__': unittest.main()
