package test;

import main.AdjacencyMatrixGraph;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

public class AdjacencyMatrixTest{


    @Test
    public void addVertexTest(){
        
        AdjacencyMatrixGraph g1 = new AdjacencyMatrixGraph(1);
        assertTrue(g1.addVertex('A'));

    }

    @Test
    public void growGraphTest(){

        AdjacencyMatrixGraph g2 = new AdjacencyMatrixGraph(1);
        g2.addVertex('A');
        assertTrue(g2.addVertex('B'));

    }

    @Test
    public void edgeCreateTest(){

        AdjacencyMatrixGraph g3 = new AdjacencyMatrixGraph(2);
        g3.addVertex('A');
        g3.addVertex('B');

        assertTrue(g3.addEdges('A', 'B'));

    }

    @Test
    public void hasEdgeTest1(){

        AdjacencyMatrixGraph g4 = new AdjacencyMatrixGraph(2);
        g4.addVertex('A');
        g4.addVertex('B');
        g4.addEdges('A', 'B');

        assertTrue(g4.hasEdge(0, 1));
    
    }

    @Test
    public void hasEdgeTest2(){

        AdjacencyMatrixGraph g4 = new AdjacencyMatrixGraph(2);
        g4.addVertex('A');
        g4.addVertex('B');
        g4.addEdges('A', 'B');

        assertFalse(g4.hasEdge(1, 0));
    
    }


    @Test
    public void SquareTraversal(){

        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(4);
        g.addVertex('A');
        g.addVertex('B');
        g.addVertex('C');
        g.addVertex('D');

        g.addEdges('A', 'B');
        g.addEdges('B', 'C');
        g.addEdges('C', 'D');
        g.addEdges('D', 'A');

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        g.DepthFirstTraversalPrint('A');

        assertEquals("Depth First Traversal:\nA B C D \n", baos.toString());
    }       


}

