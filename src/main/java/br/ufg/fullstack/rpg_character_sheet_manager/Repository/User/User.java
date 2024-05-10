package br.ufg.fullstack.rpg_character_sheet_manager.Repository.User;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sheets.CharacterSheet;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CharacterSheet> characterSheet;

    // Constructor
    public User() {
    }

    // Constructor with parameters
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Get id
    public Integer GetId() {
        return id;
    }

    // Getters and Setters for username
    public String SetUserName() {
        return username;
    }
    public void SetUserName(String username) {
        this.username = username;
    }

    // Getters and Setters for password
    public String SetPassword() {
        return password;
    }
    public void SetPassword(String password) {
        this.password = password;
    }

    // Getters and Setters for email
    public String SetEmail() {
        return email;
    }
    public void SetEmail(String email) {
        this.email = email;
    }

    // Getters and Setters for characterSheet
    public List<CharacterSheet> SetCharacterSheet() { return characterSheet; }
    public void SetCharacterSheet(List<CharacterSheet> characterSheet) { this.characterSheet = characterSheet; }

    //Add and Remove a characterSheet
    public void addCharacterSheet(CharacterSheet characterSheet) { this.characterSheet.add(characterSheet); }
    public void removeCharacterSheet(CharacterSheet characterSheet) { this.characterSheet.remove(characterSheet); }

    
    // toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
