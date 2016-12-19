import itertools;

class DAGConstruction:
    def construct(self, x):
        self.remaining_x = x + []
        self.remaining_indexes = list(range(len(x)))
        self.edges = []
        self.x = x;
        while self.remaining_indexes:
            index = self.take_next_index()
            reaching = self.get_reaching(index, self.edges)
            delta = self.x[index] - reaching
            if delta != 0:
                combinations = list(itertools.combinations(range(len(self.x)), delta))
                print(combinations)
                for to in combinations:
                    if index in to:
                        continue
                    newEdges = [];
                    for t in to:
                        newEdges.append((index, t));
                    reaching_with_edge = self.get_reaching(index, self.edges + newEdges)
                    if reaching_with_edge == self.x[index]:
                        self.edges.extend(newEdges)
        if self.found():
            return self.flatten_edges()
        else:
            return [-1]

    def found(self):
        return self.get_reachings() == self.x

    def flatten_edges(self):
        result = []
        for edge in self.edges:
            result += list(edge)
        return result

    def take_next_index(self):
        min_value = min(self.remaining_x)
        min_index = self.remaining_x.index(min_value)
        x_index = self.remaining_indexes[min_index]
        del self.remaining_x[min_index]
        del self.remaining_indexes[min_index]
        return x_index

    def get_reachings(self):
        reachings = []
        for i in range(len(self.x)):
            reachings.append(self.get_reaching(i, self.edges))
        return reachings

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
