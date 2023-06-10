package maslov.aptitos.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    //    fetch = FetchType.EAGER by default
    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "telephone_id")
    private Telephone telephone;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "division_id")
    private Division division;

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

    public Telephone getTelephone() {
        return telephone;
    }

    public Division getDivision() {
        return division;
    }

    public void setTelephone(Telephone telephone_id) {
        this.telephone = telephone_id;
    }

    public void setDivision(Division division_id) {
        this.division = division_id;
    }
}