async function mi_perfil(id) {
	res = await fetch("http://127.0.0.1:8080/" + id + "/usuario").then(
		(response) => response.json()
	);
	document.getElementById("nombre").innerHTML = res.map((res) => res.nombre_usuario);
	document.getElementById("mail").innerHTML = res.map((res) => res.mail);
	document.getElementById("ubicacion").innerHTML = res.map((res) => res.localidad);
	var level = "";
	console.log(res.map((res) => res.nivel));
	switch (res.map((res) => res.nivel)) {
		case 1: {level = "Principiante";}
		case 2: {level = "Amateur";}
		case 3: {level = "Avanzado";}
		case 4: {level = "Profesional";}
		default: {level="Principiante";}
	}

	document.getElementById("nivel").innerHTML = level;


}	
mi_perfil(1);

