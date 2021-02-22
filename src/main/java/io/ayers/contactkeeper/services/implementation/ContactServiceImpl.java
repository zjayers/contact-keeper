package io.ayers.contactkeeper.services.implementation;

import io.ayers.contactkeeper.models.dto.ContactDto;
import io.ayers.contactkeeper.models.entity.Contact;
import io.ayers.contactkeeper.models.mappings.ContactMapper;
import io.ayers.contactkeeper.repositories.ContactRepository;
import io.ayers.contactkeeper.services.interfaces.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl
        implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public Collection<ContactDto> getAllContacts() {
        List<Contact> allContacts = contactRepository.findAll();
        return contactMapper.domainToDto(allContacts);
    }

    @Override
    public ContactDto getContactById(String contactId) {
        Contact contact = contactRepository.findByContactId(contactId);

        if (contact == null) throw new RuntimeException(contactId);

        return contactMapper.domainToDto(contact);
    }

    @Override
    public ContactDto createContact(ContactDto dto) {

        // TODO verify passed in email
        // This can be done on the front end before the request is made
        // This can also be done by providing a custom constraint in the JPA entity
        // Another way is to use the @PrePersist annotation to validate the email before saving the entity

        Contact contact = contactMapper.dtoToDomain(dto);

        String contactId = UUID.randomUUID().toString();
        contact.setContactId(contactId);

        Contact savedContact = contactRepository.save(contact);

        return contactMapper.domainToDto(savedContact);
    }

    @Override
    public ContactDto editContact(String contactId, ContactDto dto) {
        Contact contactWithUpdates = contactMapper.dtoToDomain(dto);
        Contact contactFromDatabase = contactRepository.findByContactId(contactId);

        if (contactFromDatabase == null) throw new RuntimeException(contactId);

        String firstName = contactWithUpdates.getFirstName();
        String lastName = contactWithUpdates.getLastName();
        String email = contactWithUpdates.getEmail();

        if (firstName != null) contactFromDatabase.setFirstName(firstName);
        if (lastName != null) contactFromDatabase.setLastName(lastName);
        if (email != null) contactFromDatabase.setEmail(email);

        Contact savedContact = contactRepository.save(contactFromDatabase);

        return contactMapper.domainToDto(savedContact);
    }

    @Override
    @Transactional
    public void deleteContactById(String contactId) {
        Contact contact = contactRepository.findByContactId(contactId);

        if (contact == null) throw new RuntimeException(contactId);

        contactRepository.deleteByContactId(contactId);
    }
}
