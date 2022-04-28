package main;

public class Driver{
    public static void main(String[] args){

        AdjacencyMatrixGraph g2 = Task2Graph();
        g2.printMatrix();
        g2.DepthFirstTraversalPrint('A');
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
        g.addEdges('A', 'D');
        g.addEdges('A', 'E');
        g.addEdges('B', 'E');
        g.addEdges('D', 'G');
        g.addEdges('E', 'F');
        g.addEdges('E', 'H');
        g.addEdges('G', 'H');
        g.addEdges('F', 'C');
        g.addEdges('F', 'H');
        g.addEdges('H', 'I');
        g.addEdges('C', 'B');
        //g.addEdges(8, 5);
        g.addEdges('I', 'F');

        return g;
    }
}
