package library.api.dto;

import library.domain.log.LogType;

public class LogDto {
  public LogType type;
  public Long entityId;
  public String entityName;

  public LogDto(LogType type, Long entityId, String entityName) {
    super();
    this.type = type;
    this.entityId = entityId;
    this.entityName = entityName;
  }

}
