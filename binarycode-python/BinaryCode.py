class BinaryCode:
	def decode(self, q):
		a = self.getValidP(self.getP(q, '0'), q)
		b = self.getValidP(self.getP(q, '1'), q)
		return [a, b]

	def getValidP(self, p, q):
		if (p == 'NONE'):
			return 'NONE'
		if (self.getQ(p) != q):
			return 'NONE'
		return p
	
	def getQ(self, p):
		q = ''
		for i in range(p.__len__()):
			q += self.getNextQDigit(p, i);
		return q
	
	def getNextQDigit(self, p, i):
		q = int(p[i])
		if (i > 0):
			q += int(p[i - 1])
		if (i < p.__len__() - 1):
			q += int(p[i + 1])
		return str(q);
		
	def getP(self, q, initialP):
		p = initialP
		for i in range(initialP.__len__(), q.__len__()):
			nextP = self.getNextPDigit(p, q, i)
			if (nextP != 0 and nextP != 1):
				return 'NONE'
			p += str(nextP)
		return p
	
	def getNextPDigit(self, p, q, i):
		r = int(q[i - 1]) - int(p[i - 1])
		if (i > 1):
			return r - int(p[i - 2])
		else:
			return r
	
	
