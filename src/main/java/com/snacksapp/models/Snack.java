package com.snacksapp.models;

import java.util.List;

public class Snack {
	
	private int snackId;
	private String name;
	private String totalValue;
	private List<Ingredient> ingredients;
	
	public Snack(int snackId, String name, String calcValue, List<Ingredient> ingredients) {
		this.snackId = snackId;
		this.name = name;
		this.totalValue = calcValue;
		this.ingredients = ingredients;
	}
	
	public Snack() {

	}

	public long getSnackId() {
		return snackId;
	}
	public void setSnackId(int snackId) {
		this.snackId = snackId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}