package io.ayers.contactkeeper.models.mappings;

import io.ayers.contactkeeper.models.dto.ContactDto;
import io.ayers.contactkeeper.models.entity.Contact;
import io.ayers.contactkeeper.models.request.ContactRequestModel;
import io.ayers.contactkeeper.models.response.ContactResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper
public interface ContactMapper {

    // Convert Request Model To DTO
    @Mapping(target = "contactId", ignore = true)
    @Mapping(target = "id", ignore = true)
    ContactDto requestToDto(ContactRequestModel requestModel);

    // Convert DTO To Response Model
    ContactResponseModel dtoToResponse(ContactDto dto);

    Collection<ContactResponseModel> dtoToResponse(Collection<ContactDto> dtos);

    // Convert DTO to Domain Entity
    Contact dtoToDomain(ContactDto dto);

    // Convert Domain Entity To DTO
    ContactDto domainToDto(Contact contact);

    Collection<ContactDto> domainToDto(Collection<Contact> contact);

}
