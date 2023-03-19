package fr.limayrac.infovente.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 50)
    private Long user_id;

    @Column(name = "motant_total", nullable = false, length = 50)
    private Float montant_total;

    @Column(name = "date_commande", nullable = false, length = 50)
    private Date date_commande;

    @Column(name = "statut_commande", nullable = false, length = 50)
    private String statut_commande;
}
