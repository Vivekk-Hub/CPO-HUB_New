package com.evgateway.cpohubserver.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Document(collection = "ocpi_cpo_users")
public class User {

  @Id
  private String id;

  @NotNull(message = "Username is required")
  @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
  private String username;

  @NotNull(message = "First Name is required")
  @Size(min = 1, max = 100, message = "First Name must be between 1 and 100 characters")
  private String firstName;

  @NotNull(message = "Last Name is required")
  @Size(min = 1, max = 100, message = "Last Name must be between 1 and 100 characters")
  private String lastName;

  @NotNull(message = "Password is required")
  @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
  // @JsonIgnore // Prevent password from being included in JSON response
  private String password;

  @NotNull(message = "Email is required")
  @Email(message = "Email must be a valid email address")
  private String email;

  @NotNull(message = "Role is required")
  @Pattern(regexp = "CPOADMIN|EMSPADMIN|ADMIN", message = "Role must be one of the following: CPOADMIN, EMSPADMIN, ADMIN")
  private String role;

  @NotNull(message = "Active status is required")
  private Boolean active = true; // Default to true (active)

  private Instant lastUpdated; // Last updated timestamp

 // @NotNull(message = "Party Id is required")
  @Size(min = 2, max = 3, message = "Party Id must be between 2 and 3 characters")
  private String party_id;

  //@NotNull(message = "Country Code is required")
  @Size(min = 2, max = 3, message = "Country Code must be between 2 and 3 characters")
  private String country_code;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  // Getter for firstName
  public String getFirstName() {
    return firstName;
  }

  // Setter for firstName
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  // Getter for lastName
  public String getLastName() {
    return lastName;
  }

  // Setter for lastName
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Instant getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Instant lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getParty_id() {
    return party_id;
  }

  public void setParty_id(String party_id) {
    this.party_id = party_id;
  }

  // Getter and Setter for country_code
  public String getCountry_code() {
    return country_code;
  }

  public void setCountry_code(String country_code) {
    this.country_code = country_code;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", firstName=" + firstName +
        ", lastName=" + lastName +
        ", email=" + email +
        ", role=" + role +
        ", active=" + active +
        ", lastUpdated=" + lastUpdated +
        ", party_id=" + party_id +
        ", country_code=" + country_code +
        '}';
  }
}