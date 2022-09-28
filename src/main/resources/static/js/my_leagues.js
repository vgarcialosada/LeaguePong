async function mis_ligas(id) {
	res = await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas").then(
		(response) => response.json()
	);
	for (i = 0; i < res.length; i++) {
		let jugadoresFetch = await fetch(
			`http://127.0.0.1:8080/usuarios/${res[i].id_liga}`
		).then((response) => response.json());

		      document.getElementById("my_leagues").innerHTML += `<div onclick="setLigaid(${res[i].id_liga})" class="card" style="text-align:center";">
                    <a style="color:gold;">  <h2 style="font-weight: bold  ;" >${res[i].nombre}</h2></a>
                      <h4> <a style="color:#F0FD71;"> Ubicación : </a> ${res[i].ubicacion} </h4>
                      <h4> <a style="color:#F0FD71;"> Participantes :  </a>   ${jugadoresFetch.length == 0 ? 1 : jugadoresFetch.length}/${res[i].numero_jugadores}</h4>
                      <h4> <a style="color:#F0FD71;"> Reglas :  </a> ${res[i].reglas}</h4>
                    </div> <br><br>`;
}
}
function setLigaid(liga_id) {
	localStorage.setItem("id_liga", liga_id);
	window.location.href = "http://127.0.0.1:5500/src/main/resources/static/html/league_view.html";
}
mis_ligas(localStorage.getItem("id"));
