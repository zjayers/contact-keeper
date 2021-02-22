package io.ayers.contactkeeper.models.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorMessageResponseModel {
    private Date timestamp;
    private String message;
}
