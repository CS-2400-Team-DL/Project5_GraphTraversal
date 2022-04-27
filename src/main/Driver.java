package main;

public class Driver{
    public static void main(String[] args){

        AdjacencyMatrixGraph g2 = Task2Graph();
        g2.printMatrix();
        g2.clear();
        g2.printMatrix();

    }

    private static AdjacencyMatrixGraph Task2Graph(){

        //V = { A, B, C, D, E, F, G, H, I }
        //E = { (A,B), (A, D), (A, E), (B, E), (D, G), (E, F), (E, H), (G, H), (F, C), (F, H), (H, I), (C, B), (I, F) }

        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(9);

        g.addVertex('A');
        g.addVertex('B');
        g.addVertex('C');
        g.addVertex('D');
        g.addVertex('E');
        g.addVertex('F');
        g.addVertex('G');
        g.addVertex('H');
        g.addVertex('I');
        //g.addEdges(0, 1);
        g.addEdges('A', 'B');
        g.addEdges(0, 3);
        g.addEdges(0, 4);
        g.addEdges(1, 4);
        g.addEdges(3, 6);
        g.addEdges(4, 5);
        g.addEdges(4, 7);
        g.addEdges(6, 7);
        g.addEdges(5, 2);
        g.addEdges(5, 7);
        g.addEdges(7, 8);
        g.addEdges(2, 1);
        g.addEdges(8, 5);

        return g;
    }
}
