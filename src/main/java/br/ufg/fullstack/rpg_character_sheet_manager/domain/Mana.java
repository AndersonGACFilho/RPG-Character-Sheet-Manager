package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class Mana implements Serializable{
    private static final long serialVersionUID = 1L;

    private String type;

    private int maxenergy;

    private int energy;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxenergy() {
        return maxenergy;
    }

    public void setMaxenergy(int maxenergy) {
        this.maxenergy = maxenergy;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + maxenergy;
        result = prime * result + energy;
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
        Mana other = (Mana) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (maxenergy != other.maxenergy)
            return false;
        if (energy != other.energy)
            return false;
        return true;
    }

    

}
