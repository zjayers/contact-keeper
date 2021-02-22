package io.ayers.contactkeeper.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactRequestModel {
    private String firstName;
    private String lastName;
    private String email;
}
