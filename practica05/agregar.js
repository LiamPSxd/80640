function leer(){
	return document.getElementById("to-do").value
}

function agregar(){
	if(!buscar()){
		nodo = document.createElement("li")
		nodo.appendChild(document.createTextNode(leer()))
		document.getElementById("lista").appendChild(nodo)
	}
}

function buscar(){
	ban = false

	var array = [].slice.call(document.getElementsByTagName("li")).forEach(element => {
		if(element.textContent == leer()){
			ban = true
		}
	});

	if(ban){
		alert("El elemento ya está en la lista")
	}else{
		alert("El elemento no está en la lista")
	}

	return ban
}