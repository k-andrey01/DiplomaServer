package safecityserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Victim {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer age;
    private String gender;
    //private Integer crimeId;

    @ManyToOne
    @JoinColumn(name = "crime_id", referencedColumnName = "id")
    private Crime crime;
}
