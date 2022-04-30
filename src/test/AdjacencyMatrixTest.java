package test;

import main.AdjacencyMatrixGraph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AdjacencyMatrixTest{

    AdjacencyMatrixGraph g;

    @BeforeEach
    void inibeforet(){

        g = new AdjacencyMatrixGraph(4);
        g.addVertex('A');
        g.addVertex('B');
        g.addVertex('C');
        g.addVertex('D');

        g.addEdges('A', 'B');
        g.addEdges('B', 'C');
        g.addEdges('C', 'D');
        g.addEdges('D', 'A');

    }

    @Test
    void TraversalFromA(){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        g.DepthFirstTraversalPrint('A');
        System.out.println(baos.toString());

        assertEquals("A B C D", baos.toString());
    }

    @AfterEach
    void after(){
        g.clear();
    }


}

