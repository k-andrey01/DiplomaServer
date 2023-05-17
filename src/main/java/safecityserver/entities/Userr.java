package safecityserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Userr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;
    private String password;
    private String name;
    private String surname;
    private Date birthdate;
    private String gender;

    @OneToMany
    private List<Crime> crimes;
}