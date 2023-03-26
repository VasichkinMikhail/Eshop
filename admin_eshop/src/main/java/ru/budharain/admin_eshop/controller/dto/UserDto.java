package ru.budharain.admin_eshop.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String mail;
    private String password;
    @NotBlank
    private String repeatPassword;
    private BigDecimal balance;
    private Set<RoleDto> roles;

    public UserDto(Long id, String userName, String mail, String password, BigDecimal balance) {
        this.id = id;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.balance = balance;
    }

    public UserDto(Long id, String userName, String mail, String password, BigDecimal balance, Set<RoleDto> roles) {
        this.id = id;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.balance = balance;
        this.roles = roles;
    }
}
