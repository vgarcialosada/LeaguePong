async function infoGeneral() {
	id_liga=localStorage.getItem("id_liga")
  res = await fetch(
    "http://127.0.0.1:8080/" + id_liga + "/get-liga"
  ).then((response) => response.json());
    document.getElementById(
      "partidos_liga"
    ).innerHTML = `<div class="card" style="color:white; height:380px;""></br></br>
                    NOMBRE : `+res[0].nombre+ ` </br></br>
                    REGLAS : `+res[0].reglas+ ` </br></br>
                    UBICACION : `+res[0].ubicacion+` </br></br>
                    NUMERO DE JUGADORES  : `+res[0].numero_jugadores+`
					                    </div>`;
  }

infoGeneral()