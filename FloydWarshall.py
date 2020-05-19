import networkx as nx
import matplotlib.pyplot as plt

g = nx.DiGraph()

g.add_edge('M', 'E', weight=3)
g.add_edge('M', 'A', weight=0)
g.add_edge('A', 'S', weight=-2)
g.add_edge('E', 'A', weight=1)
g.add_edge('E', 'M', weight=5)
g.add_edge('S', 'E', weight=4)

predecessor, distance = nx.floyd_warshall_predecessor_and_distance(g)


distancias = [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]

for i in distance:
    d = distance[i]
    for j in d:
        distancias[int(i)-1][int(j)-1] = d[j]

print("Distancias:")
for i in distancias:
    print(i)

print("Predecesores:", predecessor)

nx.draw(g, with_labels=True)
plt.show()
