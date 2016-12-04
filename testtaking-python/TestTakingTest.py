import TestTaking
import unittest

class TestTakingTest(unittest.TestCase):

	def setUp(self):
		self.solution = TestTaking.TestTaking()

	def test_0(self):
		questions = 3
		guessed = 1
		actual = 2

		expected = 2
		actual = self.solution.findMax(questions, guessed, actual)

		self.assertEqual(expected, actual)

	def test_1(self):
		questions = 3
		guessed = 2
		actual = 1

		expected = 2
		actual = self.solution.findMax(questions, guessed, actual)

		self.assertEqual(expected, actual)

	def test_2(self):
		questions = 5
		guessed = 5
		actual = 0

		expected = 0
		actual = self.solution.findMax(questions, guessed, actual)

		self.assertEqual(expected, actual)

	def test_3(self):
		questions = 10
		guessed = 8
		actual = 8

		expected = 10
		actual = self.solution.findMax(questions, guessed, actual)

		self.assertEqual(expected, actual)

	def test_4(self):
		questions = 7
		guessed = 0
		actual = 2

		expected = 5
		actual = self.solution.findMax(questions, guessed, actual)

		self.assertEqual(expected, actual)

if __name__ == '__main__': unittest.main()
