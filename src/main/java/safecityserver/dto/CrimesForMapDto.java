package safecityserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import safecityserver.entities.Victim;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrimesForMapDto {
    private Integer id;
    private Double coordX;
    private Double coordY;
    @JsonFormat(pattern = "yyyy-MM-d'T'HH:mm")
    private LocalDateTime timeCrime;
    private String comment;
    private String city;
    private String street;
    private String house;
    private String type;
    private String kind;
    private List<Victim> victims;
}