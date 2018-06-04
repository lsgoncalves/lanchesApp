package com.snacksapp.models;

public class Ingredient{
	
	private int ingredientId;
	private String name;
	private Double value;
	private Integer quantity;
	
	public Ingredient(int ingredientId, String name, Double value, Integer quantity) {
		this.ingredientId = ingredientId;
		this.name = name;
		this.value = value;
		this.quantity = quantity;
	}
	
	public Ingredient() {

	}
	
	public long getIngredientsId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
