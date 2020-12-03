package at.htl.leocompetition.control;

import at.htl.leocompetition.entity.Node;
import at.htl.leocompetition.entity.Prototyp;
import org.junit.jupiter.api.Test;

class NodeCanvasTest {

    @Test
    void drawPhase() {

        Prototyp p = new Prototyp();
        NodeCanvas canvas = new NodeCanvas();

        canvas.drawPhases(p.createKoTree());

        canvas.printCanvasToFile();


    }
}
