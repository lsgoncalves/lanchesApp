package com.snacksapp.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.snacksapp.controllers.SnacksController;
import com.snacksapp.models.Ingredient;
import com.snacksapp.models.Snack;

public class TestSnacks {
	
	private SnacksController snacksController = new SnacksController();
	
	@Test
	public void testTraditionalSnacks(){

		//Ingredientes
		Ingredient alface = new Ingredient(1, "Alface", 0.40, 1);
		Ingredient bacon = new Ingredient(2, "Bacon", 2.00, 1);
		Ingredient hamburguer = new Ingredient(3, "Hamburguer de carne", 3.00, 1);
		Ingredient ovo = new Ingredient(4, "Ovo", 0.80, 1);
		Ingredient queijo = new Ingredient(5, "Queijo", 1.50, 1);
		
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
		
		Snack xBacon = new Snack(1, "X-Bacon", Double.toString(snacksController.calcValue(xBaconTraditional)), xBaconTraditional);
		Snack xBurguer = new Snack(2, "X-Burguer", Double.toString(snacksController.calcValue(xBurguerTraditional)), xBurguerTraditional);
		Snack xEgg = new Snack(3, "X-Egg", Double.toString(snacksController.calcValue(xEggTraditional)), xEggTraditional);
		Snack xEggBacon = new Snack(4, "X-Egg Bacon", Double.toString(snacksController.calcValue(xEggBaconTraditional)), xEggBaconTraditional);
		
		assertEquals("6.5", xBacon.getTotalValue());
		assertEquals("4.5", xBurguer.getTotalValue());
		assertEquals("5.3", xEgg.getTotalValue());
		assertEquals("7.3", xEggBacon.getTotalValue());
	}
	
	@Test
	public void testPromotionMeat(){
		
		//Ingredientes
		Ingredient bacon = new Ingredient(2, "Bacon", 2.00, 1);
		Ingredient hamburguer = new Ingredient(3, "Hamburguer de carne", 3.00, 3);
		Ingredient queijo = new Ingredient(5, "Queijo", 1.50, 1);
		
		//X-Bacon - ingredientes: Bacon, hambúrguer de carne e queijo
		List<Ingredient> xBaconTraditional = new ArrayList<>();
		xBaconTraditional.add(bacon);
		xBaconTraditional.add(hamburguer);
		xBaconTraditional.add(queijo);
		
		Snack xBaconMeat = new Snack(1, "X-Bacon Promoção Meat", Double.toString(snacksController.calcValue(xBaconTraditional)), xBaconTraditional);
		
		//X-Bacon com o triplo de carne na promoção "Muita carne"
		assertEquals("9.5", xBaconMeat.getTotalValue());
	}
	
	@Test
	public void testPromotionCheese(){
		//Ingredientes
		Ingredient bacon = new Ingredient(2, "Bacon", 2.00, 1);
		Ingredient hamburguer = new Ingredient(3, "Hamburguer de carne", 3.00, 1);
		Ingredient queijo = new Ingredient(5, "Queijo", 1.50, 6);
		
		//X-Bacon - ingredientes: Bacon, hambúrguer de carne e queijo
		List<Ingredient> xBaconTraditional = new ArrayList<>();
		xBaconTraditional.add(bacon);
		xBaconTraditional.add(hamburguer);
		xBaconTraditional.add(queijo);
		
		Snack xBaconCheese = new Snack(1, "X-Bacon Promoção Cheese", Double.toString(snacksController.calcValue(xBaconTraditional)), xBaconTraditional);
		
		//X-Bacon com o sextuplo de queijo na promoção "Muito queijo"
		assertEquals("11.0", xBaconCheese.getTotalValue());
	}
	
	@Test
	public void testPromotionLight(){
		
		//Ingredientes
		Ingredient alface = new Ingredient(1, "Alface", 0.40, 1);
		Ingredient hamburguer = new Ingredient(3, "Hamburguer de carne", 3.00, 1);

		//X-Burguer Light - ingredientes: Alface, Hambúrguer de carne
		List<Ingredient> xBurguerLight = new ArrayList<>();
		xBurguerLight.add(alface);
		xBurguerLight.add(hamburguer);
		
		Snack xLight = new Snack(1, "X-Light", Double.toString(snacksController.calcValue(xBurguerLight)), xBurguerLight);
		
		//Valor do lanche com desconto de 10% da promoção Light
		assertEquals("3.06", xLight.getTotalValue());
	}
}
