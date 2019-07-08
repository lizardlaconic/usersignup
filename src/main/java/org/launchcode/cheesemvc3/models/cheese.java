package org.launchcode.cheesemvc3.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class cheese {

  @Id
  @GeneratedValue
  private int id;

  @NotNull
  @Size(min=3, max=15)
  private String name;

  @NotNull
  @Size(min=1, message="describe the cheese!!!!")
  private String description;

  private CheeseType type = CheeseType.HARD;

  public cheese(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public cheese() {
  }

  public int getId() {
    return id;
  }

  public void setName(String aName) {
    name = aName;
  }

  public String getName() {
    return name;
  }

  public void setDescription(String aDescription) {
    description = aDescription;
  }

  public String getDescription() {
    return description;
  }

  public CheeseType getType() {
    return type;
  }

  public void setType(CheeseType type) {
    this.type = type;
  }
}
