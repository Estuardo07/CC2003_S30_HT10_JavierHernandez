//Javier Hernández 19202
//Clase Edge
//Referencias: Libro Java Structures, Duane A. Bailey

public class Edge<V,E>
{
	public Edge(V vtx1, V vtx2, E label,
	boolean directed)
	// post: edge associates vtx1 and vtx2; labeled with label
	// directed if "directed" set true
	
	public V here()
	// post: returns first node in edge
	
	public V there()
	// post: returns second node in edge
	
	public void setLabel(E label)
	// post: sets label of this edge to label
	
	public E label()
	// post: returns label associated with this edge
	
	public boolean visit()
	// post: visits edge, returns whether previously visited
	
	public boolean isVisited()
	// post: returns true iff edge has been visited
	
	public boolean isDirected()
	// post: returns true iff edge is directed
	
	public void reset()
	// post: resets edge's visited flag to initial state
	
	public boolean equals(Object o)
	// post: returns true iff edges connect same vertices
}
