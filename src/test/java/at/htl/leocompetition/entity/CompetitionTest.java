//package at.htl.leocompetition.entity;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class CompetitionTest {
//    @Order(10)
//    @Test
//    public void createCompetition_Test() {
//        LocalDate date = LocalDate.parse("2017-12-15");
//
//        Competition competition = new Competition((long) 1,date);
//
//        assertThat(competition).isNotNull();
//    }
//
//    @Order(10)
//    @Test
//    public void toString_Test() {
//
//        LocalDate date = LocalDate.parse("2017-12-15");
//        Competition competition = new Competition((long) 1,date);
//
//
//        System.out.println(competition);
//        assertThat(competition.toString()).isEqualTo("Competition{competitionId=1, date=null}");
//    }
//
//
//
//
//}
