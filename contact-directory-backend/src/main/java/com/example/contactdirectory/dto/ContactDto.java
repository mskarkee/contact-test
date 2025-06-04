package com.example.contactdirectory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // To be flexible if JSON has more fields
public class ContactDto {
    private String designation;
    private String name;
    private String callSign;
    private String landline;
    private String contact1;
    private String contact2;
    private String email;
    private String details;
}
