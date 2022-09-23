let number;
async function display_all_leagues(){
  res = await fetch("http://127.0.0.1:8080/" + localStorage.getItem("id") + "/todas-ligas").then(
    (response) => response.json()
  );
  
      document.getElementById("display_leagues").innerHTML = `<h4> Todas las ligas </h4>`

  for (i = 0; i < res.length; i++) {
    let password = res[i].password
    let id_liga = res[i].id_liga

    document.getElementById("display_leagues").innerHTML = `<div class="card">
                      <h2>${res[i].nombre}</h2>
                      <h4>${res[i].reglas}</h4>
                      <h4>${res[i].ubicacion}</h4>
                      <h4>${res[i].numero_jugadores}</h4>
                      <button class="button1" onclick="joinLeague(${id_liga}, '${password}')">Unirse</button>
                    </div> <br><br>`;
  }
}


async function league_search() {
	let search=document.getElementById("searchInput").value
  res = await fetch("http://127.0.0.1:8080/" + search + "/buscar-ligas").then(
    (response) => response.json()
  );
  for (i = 0; i < res.length; i++) {
    let password = res[i].password
    let id_liga = res[i].id_liga

    document.getElementById("display_leagues").innerHTML = `<div class="card">
                      <h2>${res[i].nombre}</h2>
                      <h4>${res[i].reglas}</h4>
                      <h4>${res[i].ubicacion}</h4>
                      <h4>${res[i].numero_jugadores}</h4>
                      <button class="button1" onclick="joinLeague(${id_liga}, ${password})">Unirse</button>
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
  }else{
    alert("Contraseña incorrecta");
  }
}
