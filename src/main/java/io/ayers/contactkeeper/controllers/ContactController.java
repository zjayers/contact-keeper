package io.ayers.contactkeeper.controllers;

import io.ayers.contactkeeper.models.dto.ContactDto;
import io.ayers.contactkeeper.models.mappings.ContactMapper;
import io.ayers.contactkeeper.models.request.ContactRequestModel;
import io.ayers.contactkeeper.models.response.ContactResponseModel;
import io.ayers.contactkeeper.models.response.OperationStatusResponseModel;
import io.ayers.contactkeeper.services.interfaces.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<ContactResponseModel>> getAllContacts() {

        Collection<ContactDto> allContacts = contactService.getAllContacts();
        Collection<ContactResponseModel> contactResponseModels = contactMapper.dtoToResponse(allContacts);

        return ResponseEntity.status(HttpStatus.OK).body(contactResponseModels);
    }

    @GetMapping(path = "/{contactId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContactResponseModel> getContactById(@PathVariable(name = "contactId") String contactId) {

        ContactDto contactById = contactService.getContactById(contactId);
        ContactResponseModel contactResponseModel = contactMapper.dtoToResponse(contactById);

        return ResponseEntity.status(HttpStatus.OK).body(contactResponseModel);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContactResponseModel> createContact(@RequestBody ContactRequestModel contactRequestModel) {

        ContactDto contactDto = contactMapper.requestToDto(contactRequestModel);
        ContactDto savedContactDto = contactService.createContact(contactDto);
        ContactResponseModel contactResponseModel = contactMapper.dtoToResponse(savedContactDto);

        return ResponseEntity.status(HttpStatus.OK).body(contactResponseModel);
    }

    @PutMapping(path = "/{contactId}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContactResponseModel> editContactById(@PathVariable(name = "contactId") String contactId,
                                                                @RequestBody ContactRequestModel contactRequestModel) {

        ContactDto contactDto = contactMapper.requestToDto(contactRequestModel);
        ContactDto updatedContactDto = contactService.editContact(contactId, contactDto);
        ContactResponseModel contactResponseModel = contactMapper.dtoToResponse(updatedContactDto);

        return ResponseEntity.status(HttpStatus.OK).body(contactResponseModel);
    }

    @DeleteMapping(path = "/{contactId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OperationStatusResponseModel> deleteContactById(@PathVariable(name = "contactId") String contactId) {

        contactService.deleteContactById(contactId);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(OperationStatusResponseModel.builder()
                                                               .operationName("DELETE")
                                                               .operationResult("SUCCESS")
                                                               .build());
    }
}
