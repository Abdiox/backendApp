package dat3.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Enum resultType;
    private Date date;
    private int resultValue;

    public Result() {
    }

    public Result(Enum resultType, Date date, int resultValue) {
        this.resultType = resultType;
        this.date = date;
        this.resultValue = resultValue;
    }
}
