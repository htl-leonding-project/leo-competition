package at.htl.leocompetition.control;

import at.htl.leocompetition.entity.Node;
import at.htl.leocompetition.entity.Phase;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class NodeCanvas {

    private StringBuilder[] field = new StringBuilder[100];

    public void drawPhases(Map<Integer, Phase> phases) {

        // initialize field
        for (int i = 0; i < 100; i++) {
            field[i] = new StringBuilder();
            for (int j = 0; j < 100; j++) {
                field[i].append(" ");
            }
            field[i].append("\n");
        }

//        // draw nodes
//        Node n = phases.get(1).get(1);
//        field = drawNode(n, 2, 2, field);
//
//        n = phases.get(1).get(2);
//        field = drawNode(n, 20, 20, field);
//
//        n = phases.get(1).get(3);
//        field = drawNode(n, 15, 30, field);
//
//        // draw lines


        for (int i = 0; i < phases.get(1).size() ; i++) {
            Node n = phases.get(1).get(i);
            field = drawNode(n, 0, i * 7, field);
        }
    }


    public void printCanvasToFile() {
        // Create a BufferedOutputStream by wrapping a FileOutputStream
        try (OutputStream outputStream = Files.newOutputStream(Paths.get("demo.txt"));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            for (int y = 0; y < 100; y++) {
                // Write binary data to a file
                bufferedOutputStream.write(field[y].toString().getBytes(StandardCharsets.UTF_8));
            }

        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }

    }

    // https://en.wikipedia.org/wiki/Box-drawing_character (use Unicode)
    private StringBuilder[] drawNode(Node node, int x, int y, StringBuilder[] field) {

        int maxLineLength = 0;

        // Hole die Daten aus den Match- und Team-Objekten und erstelle die zu auszugebende Zeile
        // Aber noch ohne rechten Rahmen, da wir noch nicht wissen, wie bereit der Knoten wird
        String[] lines = new String[4];
        lines[0] = String.format("\u2551 Team 1: %s", node.getMatch().getTeam1().getTeamName());
        lines[1] = String.format("\u2551 Team 2: %s", node.getMatch().getTeam2().getTeamName());
        lines[2] = String.format("\u2551 Score 1: %d", node.getMatch().getPointsTeam1());
        lines[3] = String.format("\u2551 Score 2: %d", node.getMatch().getPointsTeam2());

        // Bestimme die längste Zeile und somit die Position für den rechten Rahmen
        for (String line : lines) {
            maxLineLength = Math.max(maxLineLength, line.length());
        }

        // Eintragen der Werte in das Feld
        field[y].insert(x, "\u2554");
        field[y + 1].insert(x, lines[0]);
        field[y + 2].insert(x, lines[1]);
        field[y + 3].insert(x, lines[2]);
        field[y + 4].insert(x, lines[3]);
        field[y + 5].insert(x, "\u255A");

        // Eintragen der hinteren Umrandung
        for (int i = 0; i < lines.length; i++) {
            field[y + 1 + i].insert(x + maxLineLength + 1, '\u2551');
        }

        // Eintragen der oberen Umrandung
        for (int i = 0; i < maxLineLength; i++) {
            field[y].insert(x + i + 1, '\u2550');
        }
        field[y].insert(x + maxLineLength + 1, '\u2557');

        // Eintragen der unteren Umrandung
        for (int i = 0; i < maxLineLength; i++) {
            field[y + 5].insert(x + i + 1, '\u2550');
        }
        field[y + 5].insert(x + maxLineLength + 1, '\u255D');


        return field;
    }

}
