package main;

import java.util.EmptyStackException;

//V = { A, B, C, D, E, F, G, H, I }
//E = { (A,B), (A, D), (A, E), (B, E), (D, G), (E, F), (E, H), (G, H), (F, C), (F, H), (H, I), (C, B), (I, F) }

public class AdjacencyMatrixGraph{
    
    private int[][] matrix;
    private Vertex[] vertices; 
    private int verticesCount;

    public AdjacencyMatrixGraph(int size){
        matrix = new int[size][size];
        vertices = new Vertex[size];
        verticesCount = 0;
    }

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

    public boolean addEdges(int src, int dst){
        try {
            matrix[src][dst] = 1;
            return true;
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(" \n\n Source or Destination Vertex does not exist\n\n");
            return false;
        }
    }

    public boolean addEdges(char src, char dst){

        int srcIndex = labelToInt(src);
        int dstIndex = labelToInt(dst);

        return addEdges(srcIndex, dstIndex);

    }

    private int labelToInt(char label){

        for(int i=0; i<vertices.length; i++){

            char currentLabel = vertices[i].getLabel();
            if (currentLabel == label){
                    return vertices[i].getIndex();
            }

        }
        return vertices.length;
    }

    public boolean checkEdge(int src, int dst){
        return matrix[src][dst] == 1;
    }

    public boolean isEmpty(){
        return verticesCount == 0;
    }

    public void printMatrix(){
        System.out.println("\nMartix ==");
        for(int i=0; i<verticesCount; i++){
            for(int j=0; j<verticesCount; j++){
                System.out.print( matrix[i][j] + " " );
            }
            System.out.println("\n");
        }
    }

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

    public void DepthFirstTraversalPrint(int tStart){
    	
    	ArrayStack<Vertex> stack = new ArrayStack<Vertex>();
    	
    	try {
        	stack.push(vertices[tStart]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\n\n Index not in bound");
		}
    	
    	while(!stack.isEmpty()) {
    		Vertex active = stack.pop();
    		if(active!=null && !active.getVisited()) {
        		int activeIndex = active.getIndex();
    			active.setVisited(true);
    			System.out.println(active.toString());
    			
    			//get all neighbors push to stack in reverse
    			for(int i=matrix.length; i!=0;) {
    				i--;
    				Vertex vertex = vertices[i];
    				if (matrix[activeIndex][i] == 1) {
    					stack.push(vertex);
    				}
    			} // FOR-END
    			
    		} // IF-END
    		
    	} // WHILE_END
    	
    } // TRAVERSAL-END

    public void DepthFirstTraversalPrint(char label){
        int index = labelToInt(label);
        if(index < vertices.length){
            DepthFirstTraversalPrint(index);
        } else {
            System.out.println("\n\n Label is not found\n\n");
        }
    }

}

class Vertex{

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
    
    public boolean getVisited() {
    	return this.visited;
    }

    @Override
    public String toString(){
        return String.valueOf(this.label);
    }

}

class ArrayStack<T>{
		
	private T[] stack;
	private int position;
	final int MAX_SIZE = 10000;
	
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

