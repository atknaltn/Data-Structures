import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
* A ListGraph is an extension of the AbstractGraph abstract class
* that uses an array of lists to represent the edges.
* @author Koffman and Wolfgang
*/

public class ListGraph<E extends User> extends AbstractGraph<E> {
    // Data Field
    /**
    * An array of Lists to contain the edges that
    * originate with each vertex. 
    */
    private List < Edge<E> > [] edges;
    /**size of the graph*/
    private int size;
    /**
    * Construct a graph with the specified number of
    * vertices and directionality.
    * @param numV The number of vertices
    * @param directed The directionality flag
    */
    public ListGraph(int numV, boolean directed) {
        super(numV, directed);
        edges = new List[numV];
        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList < Edge<E> > ();
        }
        size = 0;
    }

    /**
    * Determine whether an edge exists.
    * @param source The source vertex
    * @param dest The destination vertex
    * @return true if there is an edge from source to dest
    */
    public boolean isEdge(E source, E dest) {
        return edges[source.getPersonalData().getId()].contains(new Edge<E>(source, dest));
    }

    /** 
    * Insert a new edge into the graph.
    * @param edge The new edge
    */
    public void insert(Edge<E> edge) {
        if(!edges[edge.getSource().getPersonalData().getId()].contains(edge)){
            edges[edge.getSource().getPersonalData().getId()].add(edge);
            size++;
            if (!isDirected()) {
                edges[edge.getDest().getPersonalData().getId()].add(new Edge<E>(edge.getDest(),
                                                                                edge.getSource(),
                                                                                edge.getWeight()));
            }
        } else{
          System.out.println("WARNING: Cannot insert, the edge already exists.");
        }
    }

    /** 
    * Remove the edge from the graph.
    * @param edge The edge
    */
    public void remove(Edge<E> edge){
        if(edges[edge.getSource().getPersonalData().getId()].contains(edge)){
            edges[edge.getSource().getPersonalData().getId()].remove(edge);
            size--;
            if (!isDirected()) {
                edges[edge.getDest().getPersonalData().getId()].remove(new Edge<E>(edge.getDest(),
                                                                                edge.getSource(),
                                                                                edge.getWeight()));
            }
        } else{
          System.out.println("WARNING: Cannot remove, the edge does not exist.");
        }
    }

    /** 
    * Iterator.
    * @param source The source
    * @return iterator
    */
    public Iterator < Edge<E> > edgeIterator(E source) {
        return edges[source.getPersonalData().getId()].iterator();
    }

    /** 
    * Get the edge between two vertices. If an
    * edge does not exist, an Edge with a weight
    * of Double.POSITIVE_INFINITY is returned.
    * @param source The source
    * @param dest The destination
    * @return the edge between these two vertices
    */
    public Edge<E> getEdge(E source, E dest) {
        Edge<E> target = new Edge<E>(source, dest, Double.POSITIVE_INFINITY);
        for (Edge edge : edges[source.getPersonalData().getId()]) {
            if (edge.equals(target))
              return edge; // Desired edge found, return it.
        }
        // Assert: All edges for source checked.
        return target; // Desired edge not found.
    }

    /**
    * Print function to print edges
    * @return String
    */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if( size == 0 ){
            stringBuilder.append("There is no suggestion.\n");
            return stringBuilder.toString();
        }
        for(int i=0;i<size;i++){
            stringBuilder.append(edges[i].toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
    * Specific print function to print edges of the source
    * @param source the source
    * @return String
    */
    public String print(E source) {
        StringBuilder stringBuilder = new StringBuilder();
        if( edges[source.getPersonalData().getId()].size() == 0 ){
            stringBuilder.append("There is no suggestion.\n");
            return stringBuilder.toString();
        }
        for(int i=0;i<edges[source.getPersonalData().getId()].size();i++){
            stringBuilder.append(" -> ");
            stringBuilder.append(edges[source.getPersonalData().getId()].get(i).getDest().toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
