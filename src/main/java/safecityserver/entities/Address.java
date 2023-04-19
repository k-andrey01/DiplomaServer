package safecityserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String city;
    private String district;
    private String street;
    private Double coordX;
    private Double coordY;
    private String houseNumber;

    @OneToOne(mappedBy = "address")
    private Crime crime;
}
