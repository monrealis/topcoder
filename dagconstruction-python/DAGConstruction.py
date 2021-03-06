import itertools;


class DAGConstruction:
    def construct(self, x):
        self.x = x;
        self.remaining_x = x + []
        self.remaining_indexes = self.get_node_indexes()
        self.inspected_x = []
        return self.inspect_remaining()

    def inspect_remaining(self):
        return self.create_result(self.find_all_solutions())

    def find_all_solutions(self):
        solutions = [[]]
        while self.remaining_indexes:
            next_from = self.take_next_from_node()
            solutions = self.get_next_solutions(next_from, solutions)
            self.inspected_x.append(next_from)
        return solutions

    def create_result(self, solutions):
        for solution in solutions:
            if self.is_solution(solution):
                return self.flatten_edges(sorted(solution))
        return [-1]

    def get_next_solutions(self, next_from, solutions):
        next_solutions = []
        for solution in solutions:
            next = self.inspect_next_node(next_from, solution)
            next_solutions.extend(next);
        return next_solutions

    def print_solutions(self, next_from, solutions):
        for solution in solutions:
            print('%s %s' % (next_from, solution))

    def inspect_next_node(self, from_node, edges):
        reaching = self.get_reaching(from_node, edges)
        max_delta = self.x[from_node] - reaching
        solutions = []
        for delta in range(max_delta + 1):
            to_nodes_combinations = list(itertools.combinations(self.inspected_x, delta))
            for to_nodes in to_nodes_combinations:
                if self.loop_would_exist(from_node, to_nodes, edges):
                    continue
                if not self.is_minimal(edges):
                    continue
                edges_with_new = edges + self.get_new_edges(from_node, to_nodes)
                if not self.is_minimal(edges_with_new):
                    continue # pass?
                reaching_with_new_edges = self.get_reaching(from_node, edges_with_new)
                if reaching_with_new_edges == self.x[from_node]:
                    solutions.append(edges_with_new)
        return solutions

    def get_new_edges(self, from_node, to_nodes):
        new_edges = [];
        for t in to_nodes:
            new_edges.append((from_node, t));
        return new_edges

    def loop_would_exist(self, from_node, to_nodes, edges):
        if from_node in to_nodes:
            return True
        for to in to_nodes:
            if (to, from_node) in edges:
                return True
        return False

    def is_solution(self, edges):
        reachings = self.get_reachings(edges)
        return reachings == self.x

    def flatten_edges(self, edges):
        result = []
        for edge in edges:
            result += list(edge)
        return result

    def take_next_from_node(self):
        min_value = min(self.remaining_x)
        min_index = self.remaining_x.index(min_value)
        x_index = self.remaining_indexes[min_index]
        del self.remaining_x[min_index]
        del self.remaining_indexes[min_index]
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
        for i in self.get_node_indexes():
            reachings.append(self.get_reaching(i, edges))
        return reachings

    def get_node_indexes(self):
        return list(range(len(self.x)))

    def get_reaching(self, index, edges):
        r = [index]
        e = set(edges)
        proceed = True
        while proceed:
            proceed = False
            for from_, to in edges:
                if from_ not in r:
                    continue;
                if to in r:
                    continue;
                r.append(to)
                e.remove((from_, to))
                proceed = True
        return len(r)
