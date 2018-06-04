$(document).ready(function() {
	var obj = [];

	//Inicializa a aplicação com um lanche default selecionado
	$(function() {
		if ($("#rdXbacon:checked").length) {
			$("#checkBacon").trigger('click');
			$("#checkHamburguer").trigger('click');
			$("#checkQueijo").trigger('click');
		}
	});

	//Seleciona os ingredientes default de acordo com o lanche selecionado
	$("input[name=radio]").change(function() {
		if ($("#rdXbacon:checked").length) {
			clearItens();
			$("#checkBacon").trigger('click');
			$("#checkHamburguer").trigger('click');
			$("#checkQueijo").trigger('click');
		} else if ($("#rdXburguer:checked").length) {
			clearItens();
			$("#checkHamburguer").trigger('click');
			$("#checkQueijo").trigger('click');
		} else if ($("#rdXegg:checked").length) {
			clearItens();
			$("#checkOvo").trigger('click');
			$("#checkHamburguer").trigger('click');
			$("#checkQueijo").trigger('click');
		} else if ($("#rdXeggBacon:checked").length) {
			clearItens();
			$("#checkOvo").trigger('click');
			$("#checkBacon").trigger('click');
			$("#checkHamburguer").trigger('click');
			$("#checkQueijo").trigger('click');
		}
	});

	//Adiciona ou remove a quantidade de ingredientes
	var countAlface = 1;
	$("#countAlface").click(function() {
		if ($("#checkAlface").is(":checked")) {
			$("#countAlface").val(countAlface + 1);
			countAlface++;
		}
	});
	$("#checkAlface").change(function() {
		if (!$("#checkAlface").is(":checked")) {
			$("#countAlface").val(1);
			countAlface = 1;
		}
	});

	var countBacon = 1;
	$("#countBacon").click(function() {
		if ($("#checkBacon").is(":checked")) {
			$("#countBacon").val(countBacon + 1);
			countBacon++;
		}
	});
	$("#checkBacon").change(function() {
		if (!$("#checkBacon").is(":checked")) {
			$("#countBacon").val(1);
			countBacon = 1;
		}
	});

	var countHamburguer = 1;
	$("#countHamburguer").click(function() {
		if ($("#checkHamburguer").is(":checked")) {
			$("#countHamburguer").val(countHamburguer + 1);
			countHamburguer++;
		}
	});
	$("#checkHamburguer").change(function() {
		if (!$("#checkHamburguer").is(":checked")) {
			$("#countHamburguer").val(1);
			countHamburguer = 1;
		}
	});

	var countOvo = 1;
	$("#countOvo").click(function() {
		if ($("#checkOvo").is(":checked")) {
			$("#countOvo").val(countOvo + 1);
			countOvo++;
		}
	});
	$("#checkOvo").change(function() {
		if (!$("#checkOvo").is(":checked")) {
			$("#countOvo").val(1);
			countOvo = 1;
		}
	});
	
	var countQueijo = 1;
	$("#countQueijo").click(function() {
		if ($("#checkQueijo").is(":checked")) {
			$("#countQueijo").val(countQueijo + 1);
			countQueijo++;
		}
	});
	$("#checkQueijo").change(function() {
		if (!$("#checkQueijo").is(":checked")) {
			$("#countQueijo").val(1);
			countQueijo = 1;
		}
	});

	//Adiciona ou remove o valor de cada ingrediente no lanche
	$("#btnCalc").click(function() {
		var valueAlface;
		var alface = {};
		if ($("#checkAlface:checked").length) {
			alface["ingredientId"] = parseInt($("#checkAlface").val());
			alface["name"] = "Alface";
			alface["value"] = "0.40";
			alface["quantity"] = countAlface;
			obj.push(alface);
			valueAlface = alface.value;
		} else {
			$("#valuePrice").val($("#valuePrice").val() - valueAlface);
			removeObj("Alface", obj);
		}

		//Bacon
		var valueBacon;
		if ($("#checkBacon:checked").length) {
			var bacon = {};
			bacon["ingredientId"] = parseInt($("#checkBacon").val());
			bacon["name"] = "Bacon";
			bacon["value"] = "2.00";
			bacon["quantity"] = countBacon;
			obj.push(bacon);
			valueBacon = bacon.value;
		} else {
			$("#valuePrice").val($("#valuePrice").val() - valueBacon);
			removeObj("Bacon", obj);
		}

		//Hamburguer
		var valueHamburguer;
		if ($("#checkHamburguer:checked").length) {
			var hamburguer = {};
			hamburguer["ingredientId"] = parseInt($("#checkHamburguer").val());
			hamburguer["name"] = "Hamburguer de carne";
			hamburguer["value"] = "3.00";
			hamburguer["quantity"] = countHamburguer;
			obj.push(hamburguer);
			valueHamburguer = hamburguer.value;
		} else {
			$("#valuePrice").val($("#valuePrice").val() - valueHamburguer);
			removeObj("Hamburguer", obj);
		}

		//Ovo
		var valueOvo;
		if ($("#checkOvo:checked").length) {
			var ovo = {};
			ovo["ingredientId"] = parseInt($("#checkOvo").val());
			ovo["name"] = "Ovo";
			ovo["value"] = "0.80";
			ovo["quantity"] = countOvo;
			obj.push(ovo);
			valueOvo = ovo.value;
		} else {
			$("#valuePrice").val($("#valuePrice").val() - valueOvo);
			removeObj("Ovo", obj);
		}

		//Queijo
		var valueQueijo;
		if ($("#checkQueijo:checked").length) {
			var queijo = {};
			queijo["ingredientId"] = parseInt($("#checkQueijo").val());
			queijo["name"] = "Queijo";
			queijo["value"] = "1.50";
			queijo["quantity"] = countQueijo;
			obj.push(queijo);
			valueQueijo = queijo.value;
		} else {
			$("#valuePrice").val($("#valuePrice").val() - valueQueijo);
			removeObj("Queijo", obj);
		}

		calcSnack(obj);
		obj = [];
	});

	//Faz a chamada para a aplicação calcular o valor total do lanche
	calcSnack = function(obj) {
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/snacks/changed",
			//async: false,
			data : JSON.stringify(obj),
			contentType : "application/json",
			success : function(response) {
				$("#snack").val(response.name);
				/*$("#valuePrice").val(response.totalValue);*/
				$("#valuePrice").val((response.totalValue * 1).toLocaleString('pt-br', {style: 'currency', currency: 'BRL'}));
			},
			error : function(error) {
				alert("Error");
			}
		});
	}

	//Remove o ingrediente indesejado
	removeObj = function(ingredient, obj) {
		obj.map(function(value, index) {
			if (value.name === ingredient) {
				obj = obj.splice(index, 1);
			}
		});
	}

	//Limpa itens
	clearItens = function() {
		checkAlface.checked = false;
		checkBacon.checked = false;
		checkHamburguer.checked = false;
		checkOvo.checked = false;
		checkQueijo.checked = false;
		snack.value = "";
		valuePrice.value = "";
		countAlface = 1;
		countBacon = 1;
		countHamburguer = 1;
		countOvo = 1;
		countQueijo = 1;
		$("#countAlface").val(1);
		$("#countBacon").val(1);
		$("#countHamburguer").val(1);
		$("#countOvo").val(1);
		$("#countQueijo").val(1);
		obj = [];
	}
});