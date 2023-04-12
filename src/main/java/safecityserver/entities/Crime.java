package safecityserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
}
