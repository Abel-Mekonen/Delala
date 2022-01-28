package com.delala.delala.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class AdminRegistration {
    @NotNull(message="Invalid Username")
    @NotBlank(message="Username can't be blank")
    private String username;
    @NotNull(message="Invalid Password")
    @NotBlank(message="password can't be blank")
    private String password;
    @NotNull(message="Invalid Lastname")
    @NotBlank(message="Last Name can't be blank")
    private String lastName;
    @NotNull(message="Invalid Firstname")
    @NotBlank(message="First Name can't be blank")
    private String firstName;
    @NotNull(message="Invalid email")
    @NotBlank(message="First Name can't be blank")
    @Email(message="NOt a valid email address")
    private String email;
    @NotNull(message="First Name can't be null")
    @NotBlank(message="First Name can't be blank")
    private String role;
    // TODO: anotate it with size.
    private String phoneNumber;

   public User toUser(PasswordEncoder encoder) {
       User user = new User();
       user.setFirstName(this.firstName);
       user.setLastName(this.lastName);
       user.setUsername(this.username);
       user.setEmail(this.email);
       user.setPhoneNumber(this.phoneNumber);
       user.setPassword(encoder.encode(this.password));
       user.setRole("ADMIN");
       return user;
   }
}