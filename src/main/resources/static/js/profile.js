
//funcion ver datos perfil
async function mi_perfil(id) {
	res = await fetch("http://127.0.0.1:8080/" +  localStorage.getItem("id") + "/usuario").then(
		(response) => response.json()
	);
	document.getElementById("nombre").innerHTML = res.map((res) => res.nombre_usuario);
	document.getElementById("mail").innerHTML = res.map((res) => res.mail);
	document.getElementById("ubicacion").innerHTML = res.map((res) => res.localidad);
	var level = "";
	let nivel = res.map((res) => res.nivel)[0];
	console.log(nivel);
	switch (nivel) {
		case 1: {level = "Principiante";}
		break;
		case 2: {level = "Amateur";}
		break;
		case 3: {level = "Avanzado";}
		break;
		case 4: {level = "Profesional";}
		break;
		default: {level="pito";}
	}

	document.getElementById("nivel").innerHTML = level;


}	

function editProfile(){
	    window.location.href = 'http://127.0.0.1:5500/src/main/resources/static/html/edit_profile.html'
}	

mi_perfil(localStorage.getItem("id"));
