package com.gorgaz.gazmyas.gererated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ChatMessageRq
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-09T13:48:45.568470500+03:00[Europe/Moscow]")
public class ChatMessageRq {

  private UUID id;

  private UUID userId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dateTime;

  private String text;

  /**
   * Default constructor
   * @deprecated Use {@link ChatMessageRq#ChatMessageRq(UUID, UUID, LocalDateTime)}
   */
  @Deprecated
  public ChatMessageRq() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatMessageRq(UUID id, UUID userId, LocalDateTime dateTime) {
    this.id = id;
    this.userId = userId;
    this.dateTime = dateTime;
  }

  public ChatMessageRq id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull @Valid 
  @Schema(name = "id", example = "3edb44ee-4d6e-4194-a61d-7f7d6caf48d3", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ChatMessageRq userId(UUID userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @NotNull @Valid 
  @Schema(name = "userId", example = "456744ee-4d6e-4194-a61d-7f7d6caf9876", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userId")
  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public ChatMessageRq dateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  /**
   * Get dateTime
   * @return dateTime
  */
  @NotNull @Valid 
  @Schema(name = "dateTime", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("dateTime")
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public ChatMessageRq text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
  */
  
  @Schema(name = "text", example = "Всем привет!", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatMessageRq chatMessageRq = (ChatMessageRq) o;
    return Objects.equals(this.id, chatMessageRq.id) &&
        Objects.equals(this.userId, chatMessageRq.userId) &&
        Objects.equals(this.dateTime, chatMessageRq.dateTime) &&
        Objects.equals(this.text, chatMessageRq.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, dateTime, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatMessageRq {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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

