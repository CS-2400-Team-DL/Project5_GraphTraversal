package main;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class AdjacencyMatrixGraph{
    
    private int[][] matrix;
    private Vertex[] vertices; 
    private int verticesCount;
    final int MAXSIZE = 100000;
    final int DEFAULT = 10;

    public AdjacencyMatrixGraph(){
        matrix = new int[DEFAULT][DEFAULT];
        vertices = new Vertex[DEFAULT];
        verticesCount = 0;
    }

    /**
     * Constructor for the graph. Initialize the variables of the graph including the adjacency matrix.
     * @param size - The max amount of vertexes that will fit in the graph.
     */
    public AdjacencyMatrixGraph(int size){
        if(size <= MAXSIZE){
            matrix = new int[size][size];
            vertices = new Vertex[size];
            verticesCount = 0;
        }else{
            matrix = new int[MAXSIZE][MAXSIZE];
            vertices = new Vertex[MAXSIZE];
            verticesCount = 0;
        }
    }

    /**
     * Adds a floating vertex to the graph as long as there is space. 
     * @param label - The char that will identify the vertex
     * @return True if the vertex was created, false if the graph is at max capacity.
     */
    public boolean addVertex(Character label){
        try {
            vertices[verticesCount] = new Vertex(label, verticesCount);
            verticesCount++;
            return true;
        } catch(ArrayIndexOutOfBoundsException e) {
            if(growGraph())
                return addVertex(label);
            System.out.println("\n\n The Graph is full\n\n");
            return false;
        }
    }

    private boolean growGraph(){

        if(vertices.length > MAXSIZE) { return false; }
        
        int newSize = (vertices.length * 2);
        if(newSize > MAXSIZE) { newSize = MAXSIZE; }
        
        Vertex[] newVertices = new Vertex[newSize];
        for(int i=0;i<vertices.length;i++){
            newVertices[i] = vertices[i];
        }
        vertices = newVertices;

        int newMatrix[][] = new int[newSize][newSize];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        matrix = newMatrix;
        return true;
    }

    /**
     * Creates an edge between two given indexes. Their edge is updated on the adjacency matrix.
     * @param src - index of the source vertex
     * @param dst - index of the destination vertex
     * @return
     */
    public boolean addEdges(int src, int dst){
        
        if (indexExist(src) && indexExist(dst)){
            matrix[src][dst] = 1;
            return true;
        } else {
            System.out.println(" \n\n (INT)Source or Destination Vertex does not exist\n\n");
            return false;
        }

    }
    
    /**
     * Creates an edge between two vertexes. Given two char labels for the vertexes their index will be found
     * and an edge will be created if they exist.
     * @param src - label of src vertex
     * @param dst - label of dst vertex
     * @return True- if the edge was succefeully created.
     */
    public boolean addEdges(char src, char dst){

        int srcIndex = labelToInt(src);
        int dstIndex = labelToInt(dst);
        try{
            matrix[srcIndex][dstIndex] = 1;
            return true;
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(" \n\n (CHAR)Source or Destination Vertex does not exist\n\n");
            return false;
        }
    }

    /**
     * Finds the respective index of a given label. If no matching label is found it will return a number out bounds of the
     * vertices array. If no index is found will return a number out of bound, given to veritices array will cause an {@link ArrayIndexOutOfBoundsException}
     * @param label - char to be searched
     * @return the index of the matching label vertex.
     */
    private int labelToInt(char label){

        for(int i=0; i<vertices.length; i++){
            if(vertices[i] != null){
                char currentLabel = vertices[i].getLabel();
                if (currentLabel == label){
                        return vertices[i].getIndex();
                }
            }
        } // FOR-END
        return vertices.length;
    }

    /**
     * Checks if a given index exist in the vertices array and makes sure its index is correct.
     * @param index - The index that is being search for.
     * @return True if the index was found in a vertex and the index fits the appropiate vertex.
     */
    private boolean indexExist(int index){
        for(int i=0; i<vertices.length;i++){
            if (vertices[i] != null && vertices[i].getIndex() == index && index == i) 
            // Checks if there is a vertex in vertices[i] and makes sure its index is correct.
                return true;
            
        } // FOR-END
        return false;
    }
    /**
     * Checks the adjacency matrix to see if an edge exist.
     * @param src - index of the source vertex
     * @param dst - index of the destination vertex
     * @return True if the edge exist.
     */
    public boolean hasEdge(int src, int dst){
        try{
            return matrix[src][dst] == 1;
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\n\nSRC or DST is out of bound or not found");
            return false;
        }
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
    	
        System.out.print("\n\nMartix - Row=Src Col=Dst\n   ");
        
        for(int i=0; i<verticesCount; i++) { // prints a row of labels on top of the matrix
        	System.out.print(vertices[i].toString()+ " ");
        } System.out.println("");
        
        for(int i=0; i<verticesCount; i++){
        	System.out.print(" " + vertices[i].toString() + " "); // Print the label before the matrix's row
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
    	String toPrint = new String("");
    	
    	try { // Checks if given index is valid and pushes it to the stack. Will not enter while loop unless it succedes.
        	stack.push(vertices[tStart]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("\n\nIndex not in bound");
		}
    	
    	toPrint = toPrint.concat("Depth First Traversal:\n");
        
    	while(!stack.isEmpty()) {

    		Vertex active = stack.pop(); // Get Next vertex

    		if(!active.wasVisited()) { 
                active.setVisited(true);
                
                toPrint = toPrint.concat(active.toString() + " ");
    			
    			for(int i = (matrix.length-1); i!=0; i--) { // Iterate through all of the vertex's row in the matrix to find neightbors
    				if (hasEdge(active.getIndex(), i)) { // active -> i
    					stack.push(vertices[i]); // push to the stack
    				}
    			} 	
    		} 	
    	} 
    	
    	toPrint = toPrint.concat("\n");

    	for(int i=0; i<vertices.length;i++) { //RESET visited status of vertexes
            if(vertices[i] != null)
    		    vertices[i].setVisited(false);
    	}
    	
    	System.out.print(toPrint);
    	
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

    //Traverses the graph using a Breadth First Traversal. Will print the path of traversal to console
    public void BreadthFirstTraversalPrint(int startIndex){
    	
    	ArrayQueue<Vertex> queue = new ArrayQueue<Vertex>();
    	String toPrint = new String("");
    	
    	try { // Checks if given index is valid and adds it to the queue. Will not enter while loop unless it succedes.
        	queue.enqueue(vertices[startIndex]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("\n\nIndex not in bound");
		}
    	
    	toPrint = toPrint.concat("Breadth First Traversal:\n");
        
    	while(!queue.isEmpty()) {

    		Vertex active = queue.dequeue(); // Get Next vertex

    		if(!active.wasVisited()) { 
                active.setVisited(true);
                
                toPrint = toPrint.concat(active.toString() + " ");
    			
    			for(int i = 0; i!=(matrix.length-1); i++) { // Iterate through all of the vertex's row in the matrix to find neightbors
    				if (hasEdge(active.getIndex(), i)) { // active -> i
    					queue.enqueue(vertices[i]); // add to the queue
    				}
    			} 	
    		} 	
    	} 
    	
    	toPrint = toPrint.concat("\n");

    	for(int i=0; i<vertices.length;i++) { //RESETS visited status of vertexes
            if(vertices[i] != null)
    		    vertices[i].setVisited(false);
    	}
    	System.out.print(toPrint);	
    }

    //Does a breadth first traversal starting at a specified label's index
    public void BreadthFirstTraversalPrint(char labelOfVertex){
        int index = labelToInt(labelOfVertex);
        if(index < vertices.length){
            BreadthFirstTraversalPrint(index);
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
        return label;
    }

    public void setLabel(char input){
        this.label = input;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int input){
        this.index = input;
    }
    
    public void setVisited(boolean input) {
    	this.visited = input;
    }
    
    public boolean wasVisited() {
    	return visited;
    }

    @Override
    public String toString(){
        return String.valueOf(label);
    }

}

final class ArrayStack<T>{
		
	private T[] stack;
	private int position;
	final private int MAX_SIZE = 100000;
	
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
		int newSize = (int) (stack.length * 1.5);
		
		if(newSize < MAX_SIZE){
			tmpStk = (T[]) new Object[newSize];
			
			for(int i=0;i<stack.length;i++) {
				tmpStk[i] = stack[i];
			}
			stack = tmpStk;
			return true;
			
		} else if ((newSize>MAX_SIZE) && (stack.length<MAX_SIZE+1)) {
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


final class ArrayQueue<T>{
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_CAPACITY = 10000;
    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    } 
    public ArrayQueue(int initialCapacity)
    {
        checkCapacity(initialCapacity);
    
        
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
    } 

    private boolean checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY){
            return false;
        }
        return true;
    }
    public void enqueue(T newEntry)
    {
    ensureCapacity();
    backIndex = (backIndex + 1) % queue.length;
    queue[backIndex] = newEntry;
    } 

    private void ensureCapacity()
    {
        if (frontIndex == ((backIndex + 2) % queue.length)) 
        {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize);
         
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++)
            {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }
         
            frontIndex = 0;
            backIndex = oldSize - 2;
        }
    }

    public T dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else
        {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        }
    }

    public T getFront()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return queue[frontIndex];
    }

    public boolean isEmpty()
    {
        return frontIndex == ((backIndex + 1) % queue.length);
    }

    public void clear()
    {
        if (!isEmpty())
        {
            for (int index = frontIndex; index != backIndex; index = (index + 1) % queue.length)
            {
                queue[index] = null;
            }
            queue[backIndex] = null;
        }
        frontIndex = 0;
        backIndex = queue.length - 1;
    }
}
