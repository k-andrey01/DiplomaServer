package safecityserver.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Crime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeCrime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeRecord;
    private String comment;

    @OneToOne(optional = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "witness_id", referencedColumnName = "id")
    private Userr witness;

    @OneToMany
    private List<Victim> victims;
}
