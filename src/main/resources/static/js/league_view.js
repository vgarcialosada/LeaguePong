
async function partidos_liga(id_liga) {
	res = await fetch("http://127.0.0.1:8080/" + id_liga + "/get-partidos").then(
		(response) => response.json()
	);

	resplayer1 = await fetch("http://127.0.0.1:8080/" + res[0].id_jugador_1 + "/usuario").then(
		(response) => response.json()
	);

	resplayer2 = await fetch("http://127.0.0.1:8080/" + res[0].id_jugador_2 + "/usuario").then(
		(response) => response.json()
	);

	for (i = 0; i < res.length; i++) {
		//let player1Wins = player1Win(res[0].id_jugador_1, res[0].id_ganador);
		//let winner = player1Wins ? resplayer1[0].nombre_usuario : resplayer2[0].nombre_usuario
		//${res[i].id_partido} .
		document.getElementById("partidos_liga").innerHTML += `<div class="card" style="color:white;">
                    	<div id="partido${i}">  <h4>  
                    ${resplayer1[0].nombre_usuario} <img src='../img/r2.png' style='width:55px; height:30px;''"> <img src='../img/vs.jpg' style='width:55px; height:55px;''">  <img src='../img/r1.png' style='width:55px; height:30px;''">  ${resplayer2[0].nombre_usuario}
                      <div id="whowon${i}">  <button class="btn btn-secondary btn-sm" 
                       onclick="setWinner(${i},'${resplayer1[0].nombre_usuario}','${resplayer2[0].nombre_usuario}'	)
                       ">Ganador</button></h4>
                    </div>`;
	}
}

function setWinner(íd_partido_vista, p1, p2) {
	partidoWinner = document.getElementById("whowon" + íd_partido_vista);
	partidoWinner.innerHTML = "Ganador? <button style='color:white; background-color:black;'>" + p1 + "</button><button style='color:white; background-color:red;'>" + p2 + "</button>"
}


function player1Win(player1, winner) { return player1 == winner ? true : false; }
partidos_liga(8);
//partidos_liga(localStorage.getItem("id_liga"));

