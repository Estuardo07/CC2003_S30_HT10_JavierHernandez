//Javier Hernández 19202
//Clase Vértice
//Referencias: Libro Java Structures, Duane A. Bailey

class Vertex<E>
{
	public Vertex(E label)
	// post: constructs unvisited vertex with label
	
	public E label()
	// post: returns user label associated w/vertex
	
	public boolean visit()
	// post: returns, then marks vertex as being visited
	
	public boolean isVisited()
	// post: returns true iff vertex has been visited
	
	public void reset()
	// post: marks vertex unvisited
	
	public boolean equals(Object o)
	// post: returns true iff vertex labels are equal
}
