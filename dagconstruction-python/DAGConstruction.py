import itertools;


class DAGConstruction:
    def construct(self, x):
        self.remaining_x = x + []
        self.remaining_indexes = list(range(len(x)))
        self.x = x;
        return self.inspect_remaining()

    def inspect_remaining(self):
        solutions = [[]]
        while self.remaining_indexes:
            next_from = self.take_next_from_node()
            solutions = self.get_next_solutions(next_from, solutions)
            for solution in solutions:
                print('%s %s' % (next_from, solution))
        for solution in solutions:
            if self.found(solution):
                if self.is_minimal(solution):
                    return self.flatten_edges(solution)
        return [-1]

    def get_next_solutions(self, next_from, solutions):
        next_solutions = []
        for solution in solutions:
            next = self.inspect_next_node(next_from, solution)
            next_solutions.extend(next);
        return next_solutions

    def inspect_next_node(self, from_node, edges):
        reaching = self.get_reaching(from_node, edges)
        delta = self.x[from_node] - reaching
        solutions = []
        r = range(len(self.x))
        #TODO loop
        to_nodes_combinations = list(itertools.combinations(r, delta))
        #print(to_nodes_combinations)
        for to_nodes in to_nodes_combinations:
            if self.loop_would_exist(from_node, to_nodes, edges):
                continue
            new_edges = [];
            for t in to_nodes:
                new_edges.append((from_node, t));
            reaching_with_edge = self.get_reaching(from_node, edges + new_edges)
            if reaching_with_edge == self.x[from_node]:
                solutions.append(edges + new_edges)
        return solutions

    def loop_would_exist(self, from_node, to_nodes, edges):
        if from_node in to_nodes:
            return True
        for to in to_nodes:
            if (to, from_node) in edges:
                return True
        return False

    def found(self, edges):
        reachings = self.get_reachings(edges)
        return reachings == self.x

    def flatten_edges(self, edges):
        result = []
        for edge in edges:
            result += list(edge)
        return result

    def take_next_from_node(self):
        value = self.remaining_x[0]
        index = self.remaining_x.index(value)
        x_index = self.remaining_indexes[index]
        del self.remaining_x[index]
        del self.remaining_indexes[index]
        return x_index

    def is_minimal(self, edges):
        reachings = self.get_reachings(edges)
        for i in range(len(edges)):
            less_edges = edges + []
            del less_edges[i]
            less_reachings = self.get_reachings(less_edges)
            if less_reachings == reachings:
                return False;
        return True

    def get_reachings(self, edges):
        reachings = []
        for i in range(len(self.x)):
            reachings.append(self.get_reaching(i, edges))
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
