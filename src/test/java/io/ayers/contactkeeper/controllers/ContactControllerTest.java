package io.ayers.contactkeeper.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ayers.contactkeeper.models.dto.ContactDto;
import io.ayers.contactkeeper.models.entity.Contact;
import io.ayers.contactkeeper.models.mappings.ContactMapper;
import io.ayers.contactkeeper.models.request.ContactRequestModel;
import io.ayers.contactkeeper.models.response.ContactResponseModel;
import io.ayers.contactkeeper.services.interfaces.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO
// Integration tests to check if service & repository implementations work correctly

@WebMvcTest
class ContactControllerTest {
    @MockBean
    ContactService contactService;
    @MockBean
    ContactMapper contactMapper;

    @Autowired
    private MockMvc mockMvc;
    private Contact contact;
    private ContactRequestModel contactRequestModel;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        long id = 1L;
        String contactId = UUID.randomUUID().toString();
        String firstName = "Test";
        String lastName = "User";
        String email = "test@test.com";

        contact = Contact.builder()
                         .id(id)
                         .contactId(contactId)
                         .firstName(firstName)
                         .lastName(lastName)
                         .email(email)
                         .build();

        ContactDto contactDto = ContactDto.builder()
                                          .id(id)
                                          .contactId(contactId)
                                          .firstName(firstName)
                                          .lastName(lastName)
                                          .email(email)
                                          .build();

        contactRequestModel = ContactRequestModel.builder()
                                                 .firstName(firstName)
                                                 .lastName(lastName)
                                                 .email(email)
                                                 .build();

        ContactResponseModel contactResponseModel = ContactResponseModel.builder()
                                                                        .contactId(contactId)
                                                                        .firstName(firstName)
                                                                        .lastName(lastName)
                                                                        .email(email)
                                                                        .build();

        when(contactService.getAllContacts()).thenReturn(List.of(contactDto));
        when(contactService.getContactById(anyString())).thenReturn(contactDto);
        when(contactService.createContact(any(ContactDto.class))).thenReturn(contactDto);
        when(contactService.editContact(anyString(), any(ContactDto.class))).thenReturn(contactDto);
        doNothing().when(contactService).deleteContactById(anyString());

        when(contactMapper.requestToDto(any(ContactRequestModel.class))).thenReturn(contactDto);
        when(contactMapper.dtoToResponse(any(ContactDto.class))).thenReturn(contactResponseModel);
        when(contactMapper.dtoToResponse(anyCollection())).thenReturn(List.of(contactResponseModel));
    }

    @Test
    void getAllContacts() throws Exception {

        mockMvc.perform(get("/api/v1/contacts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.[0].contactId").value(contact.getContactId()))
               .andExpect(jsonPath("$.[0].firstName").value(contact.getFirstName()))
               .andExpect(jsonPath("$.[0].lastName").value(contact.getLastName()))
               .andExpect(jsonPath("$.[0].email").value(contact.getEmail()));
    }

    @Test
    void getContactById() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/abcd-efgh-ijkl-mnop")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.contactId").value(contact.getContactId()))
               .andExpect(jsonPath("$.firstName").value(contact.getFirstName()))
               .andExpect(jsonPath("$.lastName").value(contact.getLastName()))
               .andExpect(jsonPath("$.email").value(contact.getEmail()));
    }

    @Test
    void createContact() throws Exception {
        mockMvc.perform(post("/api/v1/contacts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(contactRequestModel)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.contactId").value(contact.getContactId()))
               .andExpect(jsonPath("$.firstName").value(contact.getFirstName()))
               .andExpect(jsonPath("$.lastName").value(contact.getLastName()))
               .andExpect(jsonPath("$.email").value(contact.getEmail()));
    }

    @Test
    void editContactById() throws Exception {
        mockMvc.perform(put("/api/v1/contacts/abcd-efgh-ijkl-mnop")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(contactRequestModel)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.contactId").value(contact.getContactId()))
               .andExpect(jsonPath("$.firstName").value(contact.getFirstName()))
               .andExpect(jsonPath("$.lastName").value(contact.getLastName()))
               .andExpect(jsonPath("$.email").value(contact.getEmail()));
    }

    @Test
    void deleteContactById() throws Exception {
        mockMvc.perform(delete("/api/v1/contacts/abcd-efgh-ijkl-mnop")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(contactRequestModel)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.operationName").value("DELETE"))
               .andExpect(jsonPath("$.operationResult").value("SUCCESS"));
    }
}