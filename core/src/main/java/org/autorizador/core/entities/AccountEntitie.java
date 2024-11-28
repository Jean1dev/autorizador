package org.autorizador.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "account_event")
@Entity
public class AccountEntitie {
    @Id
    private String id;
    @Column(name = "active_card")
    private boolean activeCard;
    @Column(name = "available_limit")
    private double availableLimit;

    public AccountEntitie() {}

    public AccountEntitie(String id, boolean activeCard, double availableLimit) {
        this.id = id;
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
    }

    public String getId() {
        return id;
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public double getAvailableLimit() {
        return availableLimit;
    }
}
