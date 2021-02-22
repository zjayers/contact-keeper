package io.ayers.contactkeeper.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseModel {
    private String contactId;
    private String firstName;
    private String lastName;
    private String email;
}
