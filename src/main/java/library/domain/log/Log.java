package library.domain.log;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import library.api.dto.LogDto;

@Entity
public class Log {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private LocalDateTime created = LocalDateTime.now();

  @NotNull
  @Enumerated(EnumType.STRING)
  private LogType type;

  private Long customerId;

  @NotNull
  private Long entityId;
  @NotNull
  private String entityName;

  public Log(
      @NotNull LogType type,
      Long customerId,
      @NotBlank Long entityId) {
    super();
    this.type = type;
    this.customerId = customerId;
    this.entityId = entityId;
    this.entityName = this.type.getEntityName();
  }

  public LogDto viewModel() {
    return new LogDto(this.type, this.entityId, this.entityName);
  }


}
