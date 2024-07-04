package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SheetTemplate implements Serializable {
   private static  final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String description;

   private List<String> attributes;

   private List<String> inventory;

   @OneToMany
   private List<CharacterSheet> characterSheets;

public static long getSerialversionuid() {
    return serialVersionUID;
}

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

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public List<String> getAttributes() {
    return attributes;
}

public void setAttributes(List<String> attributes) {
    this.attributes = attributes;
}

public List<String> getInventory() {
    return inventory;
}

public void setInventory(List<String> inventory) {
    this.inventory = inventory;
}

public List<CharacterSheet> getCharacterSheets() {
    return characterSheets;
}

public void setCharacterSheets(List<CharacterSheet> characterSheets) {
    this.characterSheets = characterSheets;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
    result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
    result = prime * result + ((characterSheets == null) ? 0 : characterSheets.hashCode());
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
    SheetTemplate other = (SheetTemplate) obj;
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
    if (description == null) {
        if (other.description != null)
            return false;
    } else if (!description.equals(other.description))
        return false;
    if (attributes == null) {
        if (other.attributes != null)
            return false;
    } else if (!attributes.equals(other.attributes))
        return false;
    if (inventory == null) {
        if (other.inventory != null)
            return false;
    } else if (!inventory.equals(other.inventory))
        return false;
    if (characterSheets == null) {
        if (other.characterSheets != null)
            return false;
    } else if (!characterSheets.equals(other.characterSheets))
        return false;
    return true;
}


}
