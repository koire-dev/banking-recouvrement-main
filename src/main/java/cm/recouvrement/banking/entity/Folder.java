package cm.recouvrement.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.JDBCType;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Folder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    private LocalDate dateCreation = LocalDate.now();
    private boolean actif = true;
    //@Column(nullable = false)
    private LocalDate datePromesse;
    @Column(nullable = false)
    private Double montant;
    @JsonIgnore
    private Double pourcentage;
    private Double versement = 0.0;
    private Double montantTotal;
    private Long delai;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    //@JoinColumn(name = "cycleId", referencedColumnName = "idCycle")
    //@JsonIgnoreProperties("folder")
    private Cycle cycle;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    //@JoinColumn(name = "userId", referencedColumnName = "idUser")
    //@JsonIgnoreProperties("folder")
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    //@JoinColumn(name = "clientId", referencedColumnName = "idClient")
    //@JsonIgnoreProperties("folder")
    private Client client;

    //Methode pour dire si oui ou non la date d'expiration d'une promesse est atteinte
    public Boolean expiration(LocalDate datePromesse) {

        if (LocalDate.now().isBefore(datePromesse)){
            return false;
        }
        return true;
    }
}
