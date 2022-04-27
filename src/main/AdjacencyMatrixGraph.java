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
        String output =  String.valueOf(this.label);
        return output;
    }

}
