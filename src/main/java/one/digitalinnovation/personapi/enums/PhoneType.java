package one.digitalinnovation.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    HOME("Nome"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;
}
