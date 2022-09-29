async function nombre(id_liga) {
  nombreLiga = await fetch(
    "http://127.0.0.1:8080/" + id_liga + "/get-liga"
  ).then((response) => response.json());
  document.getElementById("title").innerHTML = nombreLiga[0].nombre;
}
nombre(localStorage.getItem("id_liga"));
async function admin(id, id_liga) {
  admin = await fetch(
    `http://127.0.0.1:8080/admin-liga/${id}/${id_liga}`
  ).then((response) => response.json());
  if(admin[0].admin == true){
    document.getElementById("botones").innerHTML +=`<button class="button1" onclick="administrarLiga()" >Administrar Liga</button>`
  }
}
admin(localStorage.getItem("id"), localStorage.getItem("id_liga"));

async function partidos_liga(id_liga) {
  document.getElementById("partidos_liga").innerHTML = `<div></div>`;
  res = await fetch(
    "http://127.0.0.1:8080/" + id_liga + "/get-partidos-por-jugar"
  ).then((response) => response.json());
  if(res.length == 0){
    document.getElementById(
      "partidos_liga"
    ).innerHTML =`<div class="card">No hay partidos pendientes</div>`
  }
  else{
 
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
                    ${resplayer1[0].nombre_usuario} <img src='../img/r2.png' style='width:50px; height:50px;''"> <img src='../img/vs.png' style='width:55px; height:55px;''">  <img src='../img/r1.png' style='width:50px; height:50px;''">  ${resplayer2[0].nombre_usuario}
                      <div id="whowon${idPartido}"><br>  <button class="btn btn-secondary btn-sm" 
                       onclick="setWinner(${idPartido},'${resplayer1[0].nombre_usuario}','${resplayer2[0].nombre_usuario}'	)
                       ">Ganador</button></h4>
                    </div>`;
  }
}
}

//define id de ganador de partido
function setWinner(id_partido_vista, p1, p2) {
  //winnerToBDD(1,p1);
  partidoWinner = document.getElementById("whowon" + id_partido_vista);
  partidoWinner.innerHTML = `Ganador? <button style="color:white; background-color:black;"
		onclick="winnerToBDD('${id_partido_vista}','${p1}');"> ${p1} </button>
		<button style="color:white; background-color:red;" onclick="winnerToBDD('${id_partido_vista}','${p2}');"> ${p2} </button>`;
}

//inserta ganador de partido en tabla partidos
async function winnerToBDD(id_partido, winner) {
  regGetUser = res = await fetch(
    "http://127.0.0.1:8080/" + winner + "/get-usuario"
  ).then((response) => response.json());

  resSetWinner = await fetch(
    "http://127.0.0.1:8080/" +
      id_partido +
      "/" +
      res[0].id_usuario +
      "/set-winner"
  ).then((response) => response.json());

  partidoWinner = document.getElementById("whowon" + id_partido).innerHTML =
    "GANADOR " + winner;
}

function deleteLeaugeId() {
  localStorage.removeItem("id_liga");
}

async function posiciones(id_liga) {
  document.getElementById("partidos_liga").innerHTML = `<div class="posicionesHead">
                                                            <h3>POSICION</h3>
                                                            <h3>NOMBRE</h3>
                                                            <h3>PUNTOS</h3>
                                                        </div>`;
  posicionesLiga = await fetch("http://127.0.0.1:8080/usuarios/" + id_liga).then(
    (response) => response.json()
  );
  
  for (i = 0; i < posicionesLiga.length; i++) {
    document.getElementById(
      "partidos_liga"
    ).innerHTML += `<div class="cardPosiciones">
    <div class="posicionCard">${i+1}</div>
    <h4>  
      ${posicionesLiga[i].nombre_usuario}
    </h4>
    <div>${posicionesLiga[i].puntos}</div>
  </div>`;
  }
}

async function administrarLiga(){
  document.getElementById(
    "partidos_liga"
  ).innerHTML = `<div></div>`;

  liga = await fetch(
    "http://127.0.0.1:8080/"+localStorage.getItem('id_liga')+"/get-liga"
  ).then((response) => response.json());
  document.getElementById(
    "partidos_liga"
  ).innerHTML = `<div class="card" style="color:white;align-items:center;"></br>
  <div>NOMBRE : <input id="nombreLiga" type="text" value="${liga[0].nombre}"/></div> </br>
  <div><div>REGLAS : </div> <textarea id="reglas" style="width:50%;" >${liga[0].reglas}</textarea></div></br>
  <div>UBICACION : <input type="text" id="ubicacion" value="${liga[0].ubicacion}"/></div> </br>
  <div>NUMERO DE JUGADORES  : <input type="number" id="numero_de_jugadores" value="${liga[0].numero_jugadores}"/></div></br>
  <button class="button1" onclick="guardarCambios()">Guardar Cambios</button>
            </div>`;


}

async function guardarCambios () {
 await fetch("http://127.0.0.1:8080/" + localStorage.getItem("id_liga") + "/modificar-liga", {
              method: "POST",
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
          
          id:localStorage.getItem("id_liga"),
                nombre: document.getElementById("nombreLiga").value,
                password:"",
                reglas: reglas.value,
                ubicacion: ubicacion.value,
                numero_jugadores: numero_de_jugadores.value}),
              }
  )

  window.location.reload()
}