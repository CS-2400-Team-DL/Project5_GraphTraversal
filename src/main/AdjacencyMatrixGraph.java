package main;

import java.util.EmptyStackException;

public class AdjacencyMatrixGraph{
    
    private int[][] matrix;
    private Vertex[] vertices; 
    private int verticesCount;

    /**
     * Constructor for the graph. Initialize the variables of the graph including the adjacency matrix.
     * The graph's size cannot be changed after it is constructed thus a size must be given. 
     * @param size - The max amount of vertexes in the graph. Cannot be changed.
     */
    public AdjacencyMatrixGraph(int size){
        matrix = new int[size][size];
        vertices = new Vertex[size];
        verticesCount = 0;
    }

    /**
     * Adds a vertex to the graph as long as there is space. 
     * @param label - The char that will identify the vertex
     * @return True if the vertex was created, false if the graph is full.
     */
    public boolean addVertex(Character label){
        try {
            vertices[verticesCount] = new Vertex(label, verticesCount);
            verticesCount++;
            return true;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("\n\n The Graph is full\n\n");
            return false;
        }
    }
    /**
     * Creates an edge between two given indexes. Their edge is updated on the adjacency matrix.
     * @param src - index of the source vertex
     * @param dst - index of the destination vertex
     * @return
     */
    public boolean addEdges(int src, int dst){
        
        try {
            matrix[src][dst] = 1;
            return true;
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(" \n\n Source or Destination Vertex does not exist\n\n");
            return false;
        }
    }
    
    /**
     * Creates an edge between two vertexes. Given two char labels for the vertexes their index will be found
     * and handed of to addEdges(int src, int dst).
     * @param src - label of src vertex
     * @param dst - label of dst vertex
     * @return
     */
    public boolean addEdges(char src, char dst){

        int srcIndex = labelToInt(src);
        int dstIndex = labelToInt(dst);

        return addEdges(srcIndex, dstIndex);

    }

    /**
     * Finds the respective index of a given label. If no matching label is found it will return a number out bounds of the
     * vertices array.
     * @param label - char to be searched
     * @return the index of the matching label vertex.
     */
    private int labelToInt(char label){

        for(int i=0; i<vertices.length; i++){

            char currentLabel = vertices[i].getLabel();
            if (currentLabel == label){
                    return vertices[i].getIndex();
            }

        }
        return vertices.length;
    }
    /**
     * Checks the adjacency matrix to see if an edge exist.
     * @param src - index of the source vertex
     * @param dst - index of the destination vertex
     * @return True if the edge exist.
     */
    public boolean checkEdge(int src, int dst){
        return matrix[src][dst] == 1;
    }
    
    /**
     * Checks if the graph has any vertexes
     * @return True if vertex is empty
     */
    public boolean isEmpty(){
        return verticesCount == 0;
    }
    
    /**
     * Prints the adjacency matrix of the graph to console.
     */
    public void printMatrix(){
    	
        System.out.println("\nMartix - Row=Src Col=Dst ");
        
        System.out.print("   ");
        for(int i=0; i<verticesCount; i++) {
        	System.out.print(vertices[i].toString()+ " ");
        } System.out.println("");
        
        for(int i=0; i<verticesCount; i++){
        	System.out.print(" " + vertices[i].toString() + " ");
            for(int j=0; j<verticesCount; j++){
                System.out.print( matrix[i][j] + " " );
            } System.out.println("");
        } System.out.print("\n");  
    }
    
    /**
     * Empties the graph but does not change size of the graph.
     */
    public void clear(){
        if(verticesCount > 0){
            int reset = verticesCount - 1;
            while(verticesCount != 0){
                verticesCount--;
                for(int i=reset; i!=0;i-- ){
                    this.matrix[verticesCount][i] = 0;
                }
                vertices[verticesCount] = null;
            }
            verticesCount = 0;
        }
    }
    
    /**
     * Traverses the graph using a Depth First Traversal. Will print the path of traversal to console
     * @param tStart the index of the stating vertex.
     */
    public void DepthFirstTraversalPrint(int tStart){
    	
    	ArrayStack<Vertex> stack = new ArrayStack<Vertex>();
    	
    	try { // Checks if given index is valid and pushes it to the stack. Will not enter while loop unless it succedes.
        	stack.push(vertices[tStart]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("\n\nIndex not in bound");
		}
    	
    	System.out.println("Depth First Traversal: ");
        
        Vertex active, vertex;
    	while(!stack.isEmpty()) {

    		active = stack.pop(); // Get Next working vertex

    		if(!active.wasVisited()) { 

                active.setVisited(true);
        		int activeIndex = active.getIndex();
    			System.out.print( active.toString() + " "); // Prints Current Vertex
    			
    			for(int i=matrix.length; i!=0;) { // Iterate through all of the vertex's row in the matrix to find neightbors
    				i--; // Start from the end. Fill the stack backwards.
    				if (checkEdge(activeIndex, i)) {
                        vertex = vertices[i];
    					stack.push(vertex); // if the vertex is adjacent push to the stack
    				}
    			} // FOR-END
    			
    		} // IF-END
    		
    	} // WHILE_END
    	System.out.println("");

    	for(int i=0; i<vertices.length;i++) { //RESET visited status of vertexes
    		vertices[i].setVisited(false);
    	}
    	
    } // TRAVERSAL-END

    /**
     * Finds the given label's index and hands it over to do a depth first traversal.
     * @param label - the label of a vertex where traversal will start
     */
    public void DepthFirstTraversalPrint(char label){
        int index = labelToInt(label);
        if(index < vertices.length){
            DepthFirstTraversalPrint(index);
        } else {
            System.out.println("\n\n Label is not found\n\n");
        }
    }

}

final class Vertex{

    private char label;
    private int index;
    private boolean visited;

    public Vertex(char label, int index){

        this.label = label;
        this.index = index;
        this.visited = false;
    }

    public char getLabel(){
        return this.label;
    }

    public void setLabel(char input){
        this.label = input;
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int input){
        this.index = input;
    }
    
    public void setVisited(boolean input) {
    	this.visited = input;
    }
    
    public boolean wasVisited() {
    	return this.visited;
    }

    @Override
    public String toString(){
        return String.valueOf(this.label);
    }

}

final class ArrayStack<T>{
		
	private T[] stack;
	private int position;
	final private int MAX_SIZE = 10000;
	
	public ArrayStack() {
		
		@SuppressWarnings("unchecked")
		T[] tmpStack = (T[]) new Object[5];
		stack = tmpStack;
		position = 0;
	}
	
	boolean push(T input) {
		try {
			
		stack[position] = input;
		position++;
		return true;
		
		} catch (ArrayIndexOutOfBoundsException e) {
			if(growArray()) {
				return push(input);
			} else {
				return false;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean growArray() {
		
		T[] tmpStk;
		int newSize = stack.length * 2;
		
		if(newSize < MAX_SIZE){
			tmpStk = (T[])new Object[newSize];
			
			for(int i=0;i<stack.length;i++) {
				tmpStk[i] = stack[i];
			}
			stack = tmpStk;
			return true;
			
		} else if ((newSize>MAX_SIZE) && (stack.length<MAX_SIZE)) {
			tmpStk = (T[])new Object[MAX_SIZE];
			for(int i=0;i<stack.length;i++) {
				tmpStk[i] = stack[i];
			}
			stack = tmpStk;
			return true;
		} else {
			System.out.println("\n\nTHE ARRAY IS AT MAX CAPACITY\n\n");
			return false;
		}
	}
	
	T pop() {
		
		if(isEmpty()) {
			throw new EmptyStackException();
		} else {
			
			position--;
			T value = stack[position];
			stack[position] = null;
			return value;
			
		}
	}
	
	T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		} else {
			
			T value = stack[position];
			return value;
			
		}
	}
	
	boolean isEmpty() {
		return position == 0;
	}
	
	void clear() {
		while(!isEmpty()) {
			this.pop();
		}
	}
	
}

