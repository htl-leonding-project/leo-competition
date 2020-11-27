package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Prototyp {

    @Test
    void create_and_execute_a_KO_Competition() {

        // Create a Competition
        LocalDate date = LocalDate.parse("2020-11-26");
        Competition competition = new Competition((long) 1, date);

        // Create all teams
        Team bhdv3 = new Team((long) 1, "3BHDV", competition);
        Team ahel1 = new Team((long) 1, "1AHEL", competition);
        Team afel3 = new Team((long) 1, "3AFEL", competition);
        Team chdv3 = new Team((long) 1, "3CHDV", competition);
        Team ahdv1 = new Team((long) 1, "1AHDV", competition);
        Team dhdv1 = new Team((long) 1, "1DHDV", competition);
        Team bhdv1 = new Team((long) 1, "1BHDV", competition);
        Team chdvm4 = new Team((long) 1, "4CHDVM", competition);

        // Erstellen einer Datenstruktur für die Phase 1
        Map<Integer,Phase> phases = new HashMap<>();
        phases.put(1, new Phase("Round 1", competition));
        phases.put(2, new Phase("Round 2", competition));
        phases.put(3, new Phase("Round 3", competition));
        phases.get(3).description = "Play for first and second place";
        phases.put(4, new Phase("Round 4", competition));
        phases.get(4).description = "Play for third and fourth place";

        /**
         * Runde 1
         * 4 Nodes werden zugeordnet mit je einem Match und je 2 Teams
         */
        Phase round1 = phases.get(1);
        Match match1 = new Match(afel3,chdv3, round1);
        Match match2 = new Match(ahdv1,dhdv1, round1);
        Match match3 = new Match(bhdv1,chdvm4, round1);
        Match match4 = new Match(bhdv3,ahel1, round1);
        Node node1 = new Node(match1, round1);
        Node node2 = new Node(match2, round1);
        Node node3 = new Node(match3, round1);
        Node node4 = new Node(match4, round1);

        round1.addNode(node1);
        round1.addNode(node2);
        round1.addNode(node3);
        round1.addNode(node4);

        // Erstellen einer Datenstruktur für die Phase 2
        Phase round2 = phases.get(2);
        Match match5 = new Match(afel3,dhdv1, round2);
        Match match6 = new Match(bhdv1,ahel1, round2);
        Node node5 = new Node(match5, round2);
        Node node6 = new Node(match6, round2);

        round2.addNode(node5);
        round2.addNode(node6);

        // Erstellen einer Datenstruktur für die Phase 3
        Phase round3 = phases.get(3);
        Match match7 = new Match(afel3,ahel1, round3);
        Node node7 = new Node(match7, round3);

        round3.addNode(node7);

        // Erstellen einer Datenstruktur für die Phase 4
        Phase round4 = phases.get(4);
        Match match8 = new Match(dhdv1,bhdv1, round4);
        Node node8 = new Node(match8, round4);

        round4.addNode(node8);

        System.out.println("Finished");

    }


}
