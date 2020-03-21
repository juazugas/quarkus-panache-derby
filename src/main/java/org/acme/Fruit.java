package org.acme;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Fruit
 */
@Entity
public class Fruit extends PanacheEntity {

    public String name;
    
}