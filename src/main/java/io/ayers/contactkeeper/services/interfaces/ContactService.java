package io.ayers.contactkeeper.services.interfaces;

import io.ayers.contactkeeper.models.dto.ContactDto;

import java.util.Collection;

public interface ContactService {
    Collection<ContactDto> getAllContacts();

    ContactDto getContactById(String contactId);

    ContactDto createContact(ContactDto dto);

    ContactDto editContact(String contactId, ContactDto dto);

    void deleteContactById(String contactId);
}
