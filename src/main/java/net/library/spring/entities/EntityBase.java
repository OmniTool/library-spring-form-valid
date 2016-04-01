package net.library.spring.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "increment_id")
    @SequenceGenerator(name = "increment_id", sequenceName = "increment_id", allocationSize = 1)
    @Column(name = "id", insertable = false)
    protected int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
