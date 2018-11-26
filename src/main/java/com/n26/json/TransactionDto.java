package com.n26.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {

  @NotNull
  private BigDecimal amount;
  @NotNull
  private LocalDateTime timestamp;

  @JsonCreator
  public TransactionDto(String string) {
  }

  @JsonCreator
  public TransactionDto(
      @JsonProperty(required = true, value = "amount") BigDecimal amount,
      @JsonProperty(required = true, value = "timestamp") LocalDateTime timestamp) {
    this.amount = amount;
    this.timestamp = timestamp;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
