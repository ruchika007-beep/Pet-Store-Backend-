package com.petStore.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="petstore")
public class Pet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
  
  private String name;
  
  private String Type;
  
  private int age;
  
  private String imageName;

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public String getType() {
	return Type;
  }

  public void setType(String type) {
	Type = type;
  }

  public int getAge() {
	return age;
  }

  public void setAge(int age) {
	this.age = age;
  }

  public String getImageName() {
	return imageName;
  }

  public void setImageName(String imageName) {
	this.imageName = imageName;
  }
  
  
}
