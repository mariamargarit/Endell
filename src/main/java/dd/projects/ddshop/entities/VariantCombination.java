package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class VariantCombination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
