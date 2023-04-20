package safecityserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Crime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date timeCrime;
    private Date timeRecord;
    private Integer typeId;
    private Integer addressId;
    private Integer witnessId;
    private String comment;

    @OneToOne(optional = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "witness_id", referencedColumnName = "id")
    private User witness;

    @OneToMany
    private List<Victim> victims;
}
