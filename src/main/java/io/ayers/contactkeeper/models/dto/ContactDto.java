package io.ayers.contactkeeper.models.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ContactDto
        implements Serializable {
    private static final long serialVersionUID = 313772353040300979L;

    private long id;
    private String contactId;
    private String firstName;
    private String lastName;
    private String email;
}
