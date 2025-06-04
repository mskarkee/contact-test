package com.example.contactdirectory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherOfficesWrapperDto {
    private List<CategoryDataDto> otherOffices;
}
