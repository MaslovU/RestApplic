package maslov.aptitos.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;

//    fetch = FetchType.EAGER by default
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "telephone_id")
    private Telephones telephone;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "division_id")
    private Divisions division;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Telephones getTelephone() {
        return telephone;
    }

    public Divisions getDivision() {
        return division;
    }

    public void setTelephone(Telephones telephone_id) {
        this.telephone = telephone_id;
    }

    public void setDivision(Divisions division_id) {
        this.division = division_id;
    }
}