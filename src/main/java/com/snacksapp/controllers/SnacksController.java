package com.snacksapp.controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.snacksapp.models.Ingredient;
import com.snacksapp.models.Snack;

@RestController
@RequestMapping(value ="/snacks")
public class SnacksController {
	
	private static Ingredient alface;
	private static Ingredient bacon;
	private static Ingredient hamburguer;
	private static Ingredient ovo;
	private static Ingredient queijo;
	private ArrayList<Snack> snackList;
	private Boolean light;
	
	public SnacksController() {

		snackList = new ArrayList<Snack>();

		//Ingredientes
		alface = new Ingredient(1, "Alface", 0.40, 1);
		bacon = new Ingredient(2, "Bacon", 2.00, 1);
		hamburguer = new Ingredient(3, "Hamburguer de carne", 3.00, 1);
		ovo = new Ingredient(4, "Ovo", 0.80, 1);
		queijo = new Ingredient(5, "Queijo", 1.50, 1);
		
		listSnacks();
	}
	
	public String formatValue(double value) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String valueFormated = nf.format(value);
		return valueFormated;
	}

	public void listSnacks() {
		//X-Bacon - ingredientes: Bacon, hambúrguer de carne e queijo
		List<Ingredient> xBaconTraditional = new ArrayList<>();
		xBaconTraditional.add(bacon);
		xBaconTraditional.add(hamburguer);
		xBaconTraditional.add(queijo);
		
		//X-Burger - ingredientes: Hambúrguer de carne e queijo
		List<Ingredient> xBurguerTraditional = new ArrayList<>();
		xBurguerTraditional.add(hamburguer);
		xBurguerTraditional.add(queijo);
		
		//X-Egg - ingredientes: Ovo, hambúrguer de carne e queijo
		List<Ingredient> xEggTraditional = new ArrayList<>();
		xEggTraditional.add(ovo);
		xEggTraditional.add(hamburguer);
		xEggTraditional.add(queijo);
		
		//X-Egg Bacon - ingredientes: Ovo, bacon, hambúrguer de carne e queijo
		List<Ingredient> xEggBaconTraditional = new ArrayList<>();
		xEggBaconTraditional.add(ovo);
		xEggBaconTraditional.add(bacon);
		xEggBaconTraditional.add(hamburguer);
		xEggBaconTraditional.add(queijo);
		
		Snack xBacon = new Snack(1, "X-Bacon", Double.toString(calcValue(xBaconTraditional)), xBaconTraditional);
		Snack xBurguer = new Snack(2, "X-Burguer", Double.toString(calcValue(xBurguerTraditional)), xBurguerTraditional);
		Snack xEgg = new Snack(3, "X-Egg", Double.toString(calcValue(xEggTraditional)), xEggTraditional);
		Snack xEggBacon = new Snack(4, "X-Egg Bacon", Double.toString(calcValue(xEggBaconTraditional)), xEggBaconTraditional);
		
		snackList.add(xBacon);
		snackList.add(xBurguer);
		snackList.add(xEgg);
		snackList.add(xEggBacon);
	}

	public Double calcValue(List<Ingredient> ingredientsList) {
		Double valueTotal = 0D;
		boolean hasAlface = false;
		boolean hasBacon = false;
		int porcentagem = 10;
		for (Ingredient ingredient : ingredientsList) {
			//Promoção "Muita carne" ou "Muito queijo" - A cada 3 porções o cliente só paga 2. Se o lanche tiver 6 porções, ocliente pagará 4. Assim por diante...
			if ((hamburguer.getIngredientsId() == ingredient.getIngredientsId()
					|| queijo.getIngredientsId() == ingredient.getIngredientsId())
						&& ingredient.getQuantity() >= 3){
				Integer quantity = calcQuantity(ingredient.getQuantity());
				ingredient.setQuantity(quantity);
			}
			valueTotal += ingredient.getValue() * ingredient.getQuantity();
			if(alface.getIngredientsId() == ingredient.getIngredientsId()){
				hasAlface = true;
			}
			if(bacon.getIngredientsId() == ingredient.getIngredientsId()){ 
				hasBacon = true;
			}
		}
		//Promoção light - Se o lanche tem alface e não tem bacon, ganha 10% de desconto.
		if(hasAlface && !hasBacon){
			light = true;
			valueTotal -= (valueTotal * porcentagem)/100;
		} else {
			light = false;
		}
		return valueTotal;
	}

	private int calcQuantity(Integer quantity) {
		return ((quantity / 3) * 2) + (quantity % 3 );
	}
	
	@RequestMapping(value = "/changed", method = RequestMethod.POST)
	ResponseEntity<Snack> snackChanged(@RequestBody List<Ingredient> ingredients) {
		Snack snack = new Snack();
		snack.setSnackId(0);
		snack.setIngredients(ingredients);
		snack.setTotalValue(Double.toString(calcValue(ingredients)));
		if (Boolean.TRUE.equals(light)) {
			snack.setName("Lanche Light");
		}  else {
			snack.setName("Lanche tradicional");
		}
		return new ResponseEntity<Snack>(snack, HttpStatus.OK);
	}
}
