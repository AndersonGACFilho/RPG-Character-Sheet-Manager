package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class CharacterClass implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the CharacterClass.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    //Será que precisa dessa classe experience mesmo?
    private List<Experience> experience;

    @ManyToMany
    private List<Skill> skills;

    @ManyToMany
    private List<SubClass> subclass;

    @ManyToMany
    private List<AttributeInfluence> attributeinfluence;

    @OneToMany
    private List<CharacterSheet> sheets;

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

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<SubClass> getSubclass() {
        return subclass;
    }

    public void setSubclass(List<SubClass> subclass) {
        this.subclass = subclass;
    }

    public List<AttributeInfluence> getAttributeinfluence() {
        return attributeinfluence;
    }

    public void setAttributeinfluence(List<AttributeInfluence> attributeinfluence) {
        this.attributeinfluence = attributeinfluence;
    }

    public List<CharacterSheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<CharacterSheet> sheets) {
        this.sheets = sheets;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((experience == null) ? 0 : experience.hashCode());
        result = prime * result + ((skills == null) ? 0 : skills.hashCode());
        result = prime * result + ((subclass == null) ? 0 : subclass.hashCode());
        result = prime * result + ((attributeinfluence == null) ? 0 : attributeinfluence.hashCode());
        result = prime * result + ((sheets == null) ? 0 : sheets.hashCode());
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
        CharacterClass other = (CharacterClass) obj;
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
        if (experience == null) {
            if (other.experience != null)
                return false;
        } else if (!experience.equals(other.experience))
            return false;
        if (skills == null) {
            if (other.skills != null)
                return false;
        } else if (!skills.equals(other.skills))
            return false;
        if (subclass == null) {
            if (other.subclass != null)
                return false;
        } else if (!subclass.equals(other.subclass))
            return false;
        if (attributeinfluence == null) {
            if (other.attributeinfluence != null)
                return false;
        } else if (!attributeinfluence.equals(other.attributeinfluence))
            return false;
        if (sheets == null) {
            if (other.sheets != null)
                return false;
        } else if (!sheets.equals(other.sheets))
            return false;
        return true;
    }

    

}
