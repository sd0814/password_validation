package practice.controller.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Validation {
    @NotBlank
    private String text;

    private boolean valid;
}
