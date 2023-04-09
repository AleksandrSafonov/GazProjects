package com.gorgaz.gazmyas.gererated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ChatUserRq
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-09T13:48:45.568470500+03:00[Europe/Moscow]")
public class ChatUserRq {

  private UUID id;

  private String name;

  private Boolean active;

  /**
   * Default constructor
   * @deprecated Use {@link ChatUserRq#ChatUserRq(UUID, String)}
   */
  @Deprecated
  public ChatUserRq() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatUserRq(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public ChatUserRq id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull @Valid 
  @Schema(name = "id", example = "080944ee-4d6e-4194-a61d-7f7d6caf3215", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ChatUserRq name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Никнейм
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "Дядя Ваня", description = "Никнейм", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ChatUserRq active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  */
  
  @Schema(name = "active", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("active")
  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatUserRq chatUserRq = (ChatUserRq) o;
    return Objects.equals(this.id, chatUserRq.id) &&
        Objects.equals(this.name, chatUserRq.name) &&
        Objects.equals(this.active, chatUserRq.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatUserRq {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

