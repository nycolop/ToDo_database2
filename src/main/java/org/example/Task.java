package org.example;

public class Task {

  private int id;
  private String name;
  private String description;
  private String priority;
  private String status;
  private String creationDate;
  private String startDate;
  private String estimatedEndDate;

  public Task(int id, String name, String description, String priority, String status,
      String creationDate, String startDate, String estimatedEndDate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.priority = priority;
    this.status = status;
    this.creationDate = creationDate;
    this.startDate = startDate;
    this.estimatedEndDate = estimatedEndDate;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getPriority() {
    return priority;
  }

  public String getStatus() {
    return status;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEstimatedEndDate() {
    return estimatedEndDate;
  }
}
