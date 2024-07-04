package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import java.io.Serializable;

import jakarta.persistence.*;

/**
 * Represents an iten in the system.
 */
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * The unique identifier for the item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the item.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The attributes of the item.
     */
    @Column(nullable = false)
    private String attribute;

    /**
     * The quantity of the item.
     */
    @Column(nullable = false)
    private int quantity;

    /**
     * If item is equipable.
     */
    @Column(nullable = false)
    private boolean equipable;

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

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEquipable() {
        return equipable;
    }

    public void setEquipable(boolean equipable) {
        this.equipable = equipable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
        result = prime * result + quantity;
        result = prime * result + (equipable ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (attribute == null) {
            if (other.attribute != null)
                return false;
        } else if (!attribute.equals(other.attribute))
            return false;
        if (quantity != other.quantity)
            return false;
        if (equipable != other.equipable)
            return false;
        return true;
    }

}
