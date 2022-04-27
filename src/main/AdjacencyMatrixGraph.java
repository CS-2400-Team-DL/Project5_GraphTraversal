package main;

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

        int srcIndex = verticesCount;
        int dstIndex = verticesCount;

        for(int i=0;i<vertices.length;i++){

            char label = vertices[i].getLabel();

            if(label == src){
                srcIndex = vertices[i].getIndex();
            }
            if(label == dst){
                dstIndex = vertices[i].getIndex();
            }
        }
        return addEdges(srcIndex, dstIndex);
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


}

class Vertex{

    private char label;
    private int index;

    public Vertex(char label, int index){

        this.label = label;
        this.index = index;
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

    @Override
    public String toString(){
        return String.valueOf(this.label);
    }

}
