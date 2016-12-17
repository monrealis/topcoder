class DAGConstruction:
    def construct(self, x):
        self.remaining_x = x + []
        self.remaining_indexes = list(range(len(x)))
        self.edges = []
        self.x = x;
        while self.remaining_indexes:
            index = self.take_next_index()
            reaching = self.get_reaching(index, self.edges)
            if reaching == self.x[index]:
                pass
            else:
                for to in list(range(len(self.x))):
                    if index == to:
                        continue
                    edge = (index, to)
                    nn = self.get_reaching(index, self.edges + [edge])
                    if nn == self.x[index]:
                        self.edges.append(edge)
        result = []
        for edge in self.edges:
            result += list(edge);
        return result

    def take_next_index(self):
        min_value = min(self.remaining_x)
        min_index = self.remaining_x.index(min_value)
        x_index = self.remaining_indexes[min_index]
        del self.remaining_x[min_index]
        del self.remaining_indexes[min_index]
        return x_index

    def get_reaching(self, index, edges):
        r = [index]
        while True:
            changed = False
            for from_, to in edges:
                if from_ in r and not to in r:
                    r.append(to)
                    changed = True
            if not changed:
                break
        return len(r)
