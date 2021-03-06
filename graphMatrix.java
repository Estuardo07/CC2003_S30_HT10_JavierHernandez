//Javier Hernández 19202
//Clase graph matrix
//Referencias: Libro Java Structures, Duane A. Bailey

class graphMatrix
{
	protected int size; // allocation size for graph
	protected Object data[][]; // matrix - array of arrays
	protected Map<V,GraphMatrixVertex<V>> dict; // labels -> vertices
	protected List<Integer> freeList; // available indices in matrix
	protected boolean directed; // graph is directed
	
	protected GraphMatrix(int size, boolean dir)
	{
		this.size = size; // set maximum size
		directed = dir; // fix direction of edges
		// the following constructs a size x size matrix
	
		data = new Object[size][size];
		// label to index translation table
	
		dict = new Hashtable<V,GraphMatrixVertex<V>>(size);
		// put all indices in the free list
	
		freeList = new SinglyLinkedList<Integer>();
		for (int row = size-1; row >= 0; row--)
			freeList.add(new Integer(row));
	}

	public GraphMatrixDirected(int size)
	// pre: size > 0
	// post: constructs an empty graph that may be expanded to
	// at most size vertices. Graph is directed if dir true
	// and undirected otherwise
	{
	super(size,true);
	}

	public void add(V label)
	// pre: label is a non-null label for vertex
	// post: a vertex with label is added to graph;
	// if vertex with label is already in graph, no action
	{
	// if there already, do nothing
	if (dict.containsKey(label)) return;
	Assert.pre(!freeList.isEmpty(), "Matrix not full");
	// allocate a free row and column
	int row = freeList.removeFirst().intValue();
	// add vertex to dictionary
	dict.put(label, new GraphMatrixVertex<V>(label, row));
	}

	public V remove(V label)
	// pre: label is non-null vertex label
	// post: vertex with "equals" label is removed, if found
	{
	// find and extract vertex
	GraphMatrixVertex<V> vert;
	vert = dict.remove(label);
	if (vert == null) return null;
	// remove vertex from matrix
	int index = vert.index();
	// clear row and column entries
	for (int row=0; row<size; row++)
	{
		data[row][index] = null;
		data[index][row] = null;
	}
	// add node index to free list
	freeList.add(new Integer(index));
	return vert.label();
	}

	public void addEdge(V vLabel1, V vLabel2, E label)
	// pre: vLabel1 and vLabel2 are labels of existing vertices
	// post: an edge is inserted between vLabel1 and vLabel2;
	// if edge is new, it is labeled with label (can be null)
	{
	GraphMatrixVertex<V> vtx1,vtx2;
	// get vertices
	vtx1 = dict.get(vLabel1);
	vtx2 = dict.get(vLabel2);
	// update matrix with new edge
	Edge<V,E> e = new Edge<V,E>(vtx1.label(),vtx2.label(),label,true);
	data[vtx1.index()][vtx2.index()] = e;
	}

	public E removeEdge(V vLabel1, V vLabel2)
	// pre: vLabel1 and vLabel2 are labels of existing vertices
	// post: edge is removed, its label is returned
	{
	// get indices
	int row = dict.get(vLabel1).index();
	int col = dict.get(vLabel2).index();
	// cache old value
	Edge<V,E> e = (Edge<V,E>)data[row][col];
	// update matrix
	data[row][col] = null;
	data[col][row] = null;
	if (e == null) return null;
	else return e.label();
	}

	public boolean visit(V label)
	// post: sets visited flag on vertex, returns previous value
	{
	Vertex<V> vert = dict.get(label);
	return vert.visit();
	}

	public Iterator<V> iterator()
	// post: returns traversal across all vertices of graph
	{
	return dict.keySet().iterator();
	}

	public Iterator<V> neighbors(V label)
	// pre: label is label of vertex in graph
	// post: returns traversal over vertices adj. to vertex
	// each edge beginning at label visited exactly once
	{
	GraphMatrixVertex<V> vert;
	vert = dict.get(label);
	List<V> list = new SinglyLinkedList<V>();
	for (int row=size-1; row>=0; row--)
	{
		Edge<V,E> e = (Edge<V,E>)data[vert.index()][row];
		if (e != null)
		{
			if (e.here().equals(vert.label()))
				list.add(e.there());
			else list.add(e.here());
		}
	}
	return list.iterator();
	}

	public Iterator<Edge<V,E>> edges()
	// post: returns traversal across all edges of graph (returns Edges)
	{
	List<Edge<V,E>> list = new SinglyLinkedList<Edge<V,E>>();
	for (int row=size-1; row>=0; row--)
		for (int col=size-1; col >= row; col--)
		{
		Edge<V,E> e = (Edge<V,E>)data[row][col];
		if (e != null) list.add(e);
		}
	return list.iterator();
	}
}
