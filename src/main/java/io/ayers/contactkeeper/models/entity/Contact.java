package io.ayers.contactkeeper.models.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts")
public class Contact
        implements Serializable {

    // An abstracted database ID will not be exposed to the client
    // This prevents malicious attacks to the database based on users guessing id's OR id generation methods

    private static final long serialVersionUID = -8466971312467140921L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String contactId;

    @Column(nullable = false, length = 24)
    private String firstName;

    @Column(nullable = false, length = 24)
    private String lastName;

    @Column(nullable = false, length = 40)
    private String email;

}
