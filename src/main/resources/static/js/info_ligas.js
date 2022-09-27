async function partidos_liga(id_liga) {
  res = await fetch(
    "http://127.0.0.1:8080/" + id_liga + "/get-partidos-por-jugar"
  ).then((response) => response.json());

  for (i = 0; i < res.length; i++) {
    resplayer1 = await fetch(
      "http://127.0.0.1:8080/" + res[i].id_jugador_1 + "/usuario"
    ).then((response) => response.json());

    resplayer2 = await fetch(
      "http://127.0.0.1:8080/" + res[i].id_jugador_2 + "/usuario"
    ).then((response) => response.json());
    idPartido = res[i].id_partido;

    //let player1Wins = player1Win(res[0].id_jugador_1, res[0].id_ganador);
    //let winner = player1Wins ? resplayer1[0].nombre_usuario : resplayer2[0].nombre_usuario
    //${res[i].id_partido} .
    document.getElementById(
      "partidos_liga"
    ).innerHTML += `<div class="card" style="color:white;">
                    	<div id="partido${idPartido}">  <h4>  
                    ${resplayer1[0].nombre_usuario} <img src='../img/r2.png' style='width:55px; height:30px;''"> <img src='../img/vs.png' style='width:55px; height:55px;''">  <img src='../img/r1.png' style='width:55px; height:30px;''">  ${resplayer2[0].nombre_usuario}
                      <div id="whowon${idPartido}"><br>  <button class="btn btn-secondary btn-sm" 
                       onclick="setWinner(${idPartido},'${resplayer1[0].nombre_usuario}','${resplayer2[0].nombre_usuario}'	)
                       ">Ganador</button></h4>
                    </div>`;
  }
}
