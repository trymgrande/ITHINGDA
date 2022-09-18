import heapq
import Map
from typing import List


class Vertex:
    def __init__(self, weight, x, y) -> None:
        self.id = '{},{}'.format(x, y) # 'x,y' for lookup, printing, and object comparison
        self.neighbors: List[Vertex] = list() # list of all successor nodes, whether or not this node is currently their best parent
        self.x: int = x # coordinate
        self.y: int = y # coordinate
        self.weight = str(weight) # -1, 1, 2, 3
        self.penalty = int(self.weight)
        self.walkable = self.weight != '-1'
        self.g: int = None # cost of getting to this node
        self.h: int = None # estimated cost to goal
        self.f: int = None # estimated total cost of a solution
        self.parent: Vertex = None # pointer to best parent node


    def __str__(self) -> str:
        return '({})'.format(self.id)

    def __lt__(self, other) -> bool:
        return self.f < other.f

    def __gt__(self, other) -> bool:
        return self.f > other.f

    def __eq__(self, other) -> bool:
        return self.f == other.f

    def add_neighbor(self, v) -> bool:
        # check if neighbor is a vertex
        if isinstance(v, Vertex):
            # check if neighbor is already added
            for neighbor in self.neighbors:
                if v.id == neighbor.id:
                    return False
            self.neighbors.append(v)
            return True
        return False


class Graph:
    def __init__(self, map) -> None:
        self.map = map
        self.task = map.task
        self.start_pos, self.goal_pos, self.end_goal_pos, self.path_to_map = self.map.fill_critical_positions(self.task)
        self.vertices = {} # locate vertex given id
        self.DEBUG = None
        self.DEBUG_MORE = None
        self.open_coordinates = None

    def generate_graph_from_map(self):
        map_str = self.map.get_maps()[0]
        # add vertices
        Y = len(map_str)
        X = len(map_str[0])
        for y in range(Y):
            for x in range(X):
                v_current = Vertex(map_str[y][x], x, y)
                self.add_vertex(v_current)
        # add edges
        for y in range(Y-1):
            for x in range(X-1):    
                # get vertices
                v_current = self.vertices[self.generate_id(x, y)]
                v_east = self.vertices[self.generate_id(x+1, y)]
                v_south = self.vertices[self.generate_id(x, y+1)]
                # add neighbor edges 
                self.add_edges(v_current, [v_east, v_south])                

    def add_vertices(self, vertices):
        for v in vertices:
            self.add_vertex(v)
        return True

    def add_vertex(self, vertex):
        if isinstance(vertex, Vertex) and vertex.weight not in self.vertices:
            self.vertices[vertex.id] = vertex
            return True
        else:
            return False

    def add_edges(self, current_vertex, neighbors):
        for neighbor in neighbors:
            if neighbor is not None:
                self.add_edge(current_vertex, neighbor)

    def add_edge(self, u, v):
        if v is not None and u.id in self.vertices and v.id in self.vertices:
            self.vertices[u.id].add_neighbor(v)
            self.vertices[v.id].add_neighbor(u)
            if self.DEBUG_MORE: print("edge added: ", u, '-', v)
            return True
        else:
            return False
    
    def print_graph(self):
        for key in sorted(list(self.vertices.keys())):
            neighbor_list = list()
            for neighbor in self.vertices[key].neighbors:
                neighbor_list.append(str(neighbor))
            print(key + ': ' + str(neighbor_list))

    # heuristic function
    def manhattan_distance(self, u, v):
        return abs(u.x-v.x) + abs(u.y-v.y)

    def calculate_g(self, v):
        return v.parent.g + v.penalty

    def calculate_h(self, v):
        return self.manhattan_distance(v, self.goal)

    def calculate_f(self, v):
        if v.id == self.find_start().id:
            return v.g + v.h
        return self.calculate_g(v) + self.calculate_h(v)

    def a_star(self):
        self.open_coordinates = []
        self.DEBUG = True # set to True to see what is going on
        self.DEBUG_MORE = False # set to True to see what is going on
        self.start = self.find_start()
        self.goal = self.find_goal()
        self.start.g, self.start.h, self.start.f = 0, 0, 0
        open, closed = [self.start], []
        heapq.heapify(open) # potential paths
        heapq.heapify(closed) # already explored nodes

        # agenda loop (search)
        current_vertex: Vertex = None
        while True:
            if len(open) == 0:
                if self.DEBUG: print("open list is empty, breaking (no solution)")
                break
            current_vertex = heapq.heappop(open)
            if self.DEBUG: print("searching, current node is:", current_vertex)
            if current_vertex.id == self.goal.id:
                if self.DEBUG: print("found goal node:", current_vertex)
                break
            heapq.heappush(closed, current_vertex)
            for neighbor in current_vertex.neighbors:
                if self.DEBUG_MORE: print("looking at neighbor", neighbor)
                if not neighbor.walkable or neighbor in closed:
                    if self.DEBUG_MORE: print("neighbor unwalkable or closed, skipping")
                    continue
                # if unexplored: add to open, add parent, add cost values
                if neighbor not in open:
                    if self.DEBUG_MORE: print("neighbor not explored, exploring")
                    neighbor.parent = current_vertex
                    neighbor.g = self.calculate_g(neighbor)
                    neighbor.h = self.calculate_h(neighbor)
                    neighbor.f = self.calculate_f(neighbor)
                    heapq.heappush(open, neighbor)
                    self.open_coordinates.append((current_vertex.x, current_vertex.y))
                # if already explored: check if this path is better. if so, update parent and cost values
                elif neighbor in open:
                    if self.DEBUG_MORE: print("neighbor explored, checking if this is better path")
                    if (current_vertex.g + neighbor.penalty) < (neighbor.g):
                        neighbor.parent = current_vertex
                        neighbor.g = self.calculate_g(neighbor)
                        neighbor.h = self.calculate_h(neighbor)
                        neighbor.f = self.calculate_f(neighbor)
                        if self.DEBUG_MORE: print("updated to better path")
                    else:
                        if self.DEBUG_MORE: print("path was not improved")

        self.backtrack(current_vertex)
        self.draw_to_map()

    def backtrack(self, current_vertex):
        self.path_coordinates = []
        while not current_vertex.id == self.start.id:
            self.path_coordinates.insert(0, (current_vertex.x, current_vertex.y))
            current_vertex = current_vertex.parent
            if self.DEBUG: print("backtracking, current node is: ", current_vertex)
        if self.DEBUG: print("path coordinates:\n", self.path_coordinates)
        if self.DEBUG: print("steps in path:", len(self.path_coordinates))

    def draw_to_map(self):
        # draw opened nodes
        for coordinate in self.open_coordinates:
            self.map.replace_map_values([coordinate[1], coordinate[0]], -1, self.goal_pos)
        # draw path
        for coordinate in self.path_coordinates:
            self.map.replace_map_values([coordinate[1], coordinate[0]], 0, self.goal_pos)
        # draw start and goal pixel again in case of overwrite
        self.map.replace_map_values(self.start_pos, -2, self.goal_pos)
        self.map.replace_map_values(self.goal_pos, -3, self.goal_pos)

    def find_start(self) -> Vertex:
        x = self.start_pos[1]
        y = self.start_pos[0]
        id = self.generate_id(x, y)
        return self.vertices[id]

    def find_goal(self) -> Vertex:
        x = self.goal_pos[1]
        y = self.goal_pos[0]
        id = self.generate_id(x, y)
        return self.vertices[id]

    def generate_id(self, x, y) -> str:
        return '{},{}'.format(x, y)

def main():
    TASK = 1
    map = Map.Map_Obj(TASK)
    g = Graph(map)
    g.generate_graph_from_map()
    # g.print_graph()
    g.a_star()
    map.show_map()

if __name__ == "__main__":
    main()