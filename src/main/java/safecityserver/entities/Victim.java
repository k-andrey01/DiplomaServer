package safecityserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "crime_id", referencedColumnName = "id")
    @JsonIgnore
    private Crime crime;
}
