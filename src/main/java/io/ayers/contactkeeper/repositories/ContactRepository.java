package io.ayers.contactkeeper.repositories;

import io.ayers.contactkeeper.models.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository
        extends JpaRepository<Contact, Long> {

    Contact findByContactId(String contactId);

    void deleteByContactId(String contactId);
}
