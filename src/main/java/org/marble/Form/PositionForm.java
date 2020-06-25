package org.marble.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PositionForm {
    @NotBlank
    private String name;


}
