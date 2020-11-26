//package at.htl.leocompetition.entity;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class MatchTest {
//
//    @Order(10)
//    @Test
//    public void createMatch_Test() {
//        List<Node> nodeList = new ArrayList<>();
//        LocalDate date = LocalDate.parse("2017-12-15");
//        Competition competition = new Competition((long) 1, date);
//        Phase phase = new Phase((long) 1, "phase 1", nodeList, competition);
//        Team team1 = new Team((long) 1, "1AHITM", competition);
//        Team team2 = new Team((long) 1, "1AHIF", competition);
//        Match match = new Match((long) 1, team1, team2, 2, 1, phase);
//
//
//        assertThat(match).isNotNull();
//    }
//
//    @Order(20)
//    @Test
//    public void toString_Test() {
//        List<Node> nodeList = new ArrayList<>();
//        LocalDate date = LocalDate.parse("2017-12-15");
//        Competition competition = new Competition((long) 1, date);
//        Phase phase = new Phase((long) 1, "phase 1", nodeList, competition);
//        Team team1 = new Team((long) 1, "1AHITM", competition);
//        Team team2 = new Team((long) 1, "1AHIF", competition);
//        Match match = new Match((long) 1, team1, team2, 2, 1, phase);
//
//        assertThat(match.toString()).isEqualTo("Match{matchId=1, team1=Team{teamId=1, teamName='1AHITM'," +
//                " competition=Competition{competitionId=1, date=null}}, team2=Team{teamId=1, teamName='1AHIF'," +
//                " competition=Competition{competitionId=1, date=null}}, pointsTeam1=2, pointsTeam2=1," +
//                " phase=Phase{phaseId=1, phaseName='phase 1', nodeList=[], competition=Competition{competitionId=1, date=null}}}");
//    }
//}
