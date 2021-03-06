package org.motoc.gamelibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The publisher of a game
 *
 * @author RouzicJ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank(message = "Name cannot be null or blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<Game> games = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "fk_contact")
    private Contact contact;

    // Helper methods
    public void addGame(Game game) {
        this.games.add(game);
        game.setPublisher(this);
    }

    public void addContact(Contact contact) {
        this.setContact(contact);
        contact.setPublisher(this);
    }

    /**
     * This helper method is to use before deleting the contact of a publisher.
     */
    public void removeContact(Contact contact) {
        this.setContact(null);
        contact.setPublisher(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id &&
                name.equals(publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
