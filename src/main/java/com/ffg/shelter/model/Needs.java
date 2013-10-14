package com.ffg.shelter.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Needs {
    private Long id;
    private Need need;
    protected Set<Client> clients;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "needs_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    @ManyToMany
    @JoinColumn(name = "needs_id", updatable = false, insertable = false, nullable = false)
    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }


}
