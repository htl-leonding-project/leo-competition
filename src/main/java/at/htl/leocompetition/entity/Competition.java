package at.htl.leocompetition.entity;

import java.time.LocalDate;

public class Competition {

    private Long competitionId;
    private LocalDate date;


    public Competition(Long competitionId, LocalDate date) {
        this.competitionId = competitionId;
        this.date = date ;

    }

    public Competition() {
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "competitionId=" + competitionId +
                ", date=" + date +
                '}';
    }
}
