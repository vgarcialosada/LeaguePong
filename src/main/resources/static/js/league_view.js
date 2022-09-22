async function partidos_liga(id_liga) {
	res = await fetch("http://127.0.0.1:8080/" + id_liga + "/get-partidos").then(
		(response) => response.json()
	);
	resplayer1=await fetch("http://127.0.0.1:8080/" + res.id_jugador_1 + "/usuario").then(
		(response) => response.json()
	);
	resplayer2=await fetch("http://127.0.0.1:8080/" + res.id_jugador_2 + "/usuario").then(
		(response) => response.json()
	);
	
	for (i = 0; i < res.length; i++) {
		document.getElementById("partidos_liga").innerHTML += `<div class="card" style="color:black;">
                      <h4>${res[i].id_partido} + ${resplayer1.nombre_usuario} + ${resplayer2.nombre_usuario} + ${res[i].id_ganador}</h4>
                    </div>`;
	}
}


partidos_liga(8);
//partidos_liga(localStorage.getItem("id_liga"));

