
async function display_all_leagues(){
  let res = await fetch("http://127.0.0.1:8080/" + localStorage.getItem("id") + "/todas-ligas").then(
    (response) => response.json()
  );
  
      //document.getElementById("display_leagues").innerHTML = `<h4> No hay ligas a las que puedas unirte </h4>`
  for (i = 0; i < res.length; i++) {
    let password = res[i].password
    let id_liga = res[i].id_liga
    let jugadoresFetch= await fetch(`http://127.0.0.1:8080/usuarios/${id_liga}`).then(
      (response) => response.json()
    );
    
    document.getElementById("display_leagues").innerHTML += `<div class="card" style="text-align:center";">
                    <a style="color:#F0FD71;">  <h2 style="font-weight: bold  ;" >${res[i].nombre}</h2></a>
                      <h4> <a style="color:#F0FD71;"> Ubicación : </a> ${res[i].ubicacion} </h4>
                      <h4> <a style="color:#F0FD71;"> Participantes :  </a>   ${jugadoresFetch.length == 0 ? 1 : jugadoresFetch.length}/${res[i].numero_jugadores}</h4>
                      <h4> <a style="color:#F0FD71;"> Reglas :  </a> ${res[i].reglas}</h4>
                      <button class="button1" onclick="joinLeague(${id_liga}, '${password}')">Unirse</button>
                    </div> <br><br>`;
  }
}


async function league_search() {
	let search=document.getElementById("searchInput").value
  res = await fetch("http://127.0.0.1:8080/" + search +"/"+ localStorage.getItem("id")+ "/buscar-ligas").then(
    (response) => response.json()
  );
  for (i = 0; i < res.length; i++) {
    let password = res[i].password
    let id_liga = res[i].id_liga
 let jugadoresFetch= await fetch(`http://127.0.0.1:8080/usuarios/${id_liga}`).then(
      (response) => response.json()
    );
    
       document.getElementById("display_leagues").innerHTML="<div></div>";
      document.getElementById("display_leagues").innerHTML += `<div class="card" style="text-align:center";">
                    <a style="color:#F0FD71;">  <h2 style="font-weight: bold  ;" >${res[i].nombre}</h2></a>
                      <h4> <a style="color:#F0FD71;"> Ubicación : </a> ${res[i].ubicacion} </h4>
                      <h4> <a style="color:#F0FD71;"> Participantes :  </a>   ${jugadoresFetch.length == 0 ? 1 : jugadoresFetch.length}/${res[i].numero_jugadores}</h4>
                      <h4> <a style="color:#F0FD71;"> Reglas :  </a> ${res[i].reglas}</h4>
                      <button class="button1" onclick="joinLeague(${id_liga}, '${password}')">Unirse</button>
                    </div> <br><br>`;
  }
}



display_all_leagues();




// esta funcion es para cunado se toque el boton Unirse
// se pida una contraseña y si es correcta entres.
async function joinLeague(id_liga, password) {
  let inputPassword = prompt("Intruduzca la contraseña. Si no la sabe preguntele al administrador de la liga")
  if(inputPassword == password){
    res = fetch(`http://127.0.0.1:8080/add-usuario/${id_liga}/${localStorage.getItem("id")}`, {
      method: "POST"
    });
    window.location.reload()
  }else{
    alert("Contraseña incorrecta");
  }
}

window.onload = function(){
document.getElementById('searchInput').onkeydown = function(e) {
	if (e.keyCode == 13) {
		league_search();
	}
};

}

