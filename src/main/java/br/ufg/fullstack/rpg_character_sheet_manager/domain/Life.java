package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Life implements Serializable {
    private static final long serialVersionUID = 1L;

    private int maxlife;

    private int life;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getMaxlife() {
        return maxlife;
    }

    public void setMaxlife(int maxlife) {
        this.maxlife = maxlife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maxlife;
        result = prime * result + life;
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
        Life other = (Life) obj;
        if (maxlife != other.maxlife)
            return false;
        if (life != other.life)
            return false;
        return true;
    }

    
}
