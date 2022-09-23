async function mis_ligas(id) {
  res = await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas").then(
    (response) => response.json()
  );
  for (i = 0; i < res.length; i++) {

    let jugadoresFetch= await fetch(`http://127.0.0.1:8080/usuarios/${id_liga}`).then(
      (response) => response.json()
    );
    document.getElementById("my_leagues").innerHTML += `<a href="../html/league_view.html"><div class="card" 
    onclick="setLigaid(${res[i].id_liga})">
                <p>Ubicación</p><h4>${res[i].ubicacion}</h4>
                <p>Participantes</p><h4>${jugadoresFetch.length}/${res[i].numero_jugadores}</h4>
                <p>Reglas</p><h4>${res[i].reglas}</h4>
    </div></a> <br><br>`;
  }
}

function setLigaid(liga_id){
	localStorage.setItem("id_liga",liga_id);
}
mis_ligas(localStorage.getItem("id"));

