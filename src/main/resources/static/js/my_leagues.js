async function mis_ligas(id) {
  res = await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas").then(
    (response) => response.json()
  );
  for (i = 0; i < res.length; i++) {

    document.getElementById("my_leagues").innerHTML += `<div class="card">
                      <h2>${res[i].nombre}</h2>
                      <h4>${res[i].reglas}</h4>
                      <h4>${res[i].ubicacion}</h4>
                      <h4>${res[i].numero_jugadores}</h4>
                    </div> <br><br>`;

    document.getElementsByClassName(
      "card"
    ).innerHTML += `<div>${res[i].nombre}</div>`;
    document.getElementsByClassName(
      "card"
    ).innerHTML += `<div>${res[i].reglas}</div>`;
    document.getElementsByClassName(
      "card"
    ).innerHTML += `<div>${res[i].ubicacion}</div>`;
    document.getElementsByClassName(
      "card"
    ).innerHTML += `<div>${res[i].numero_jugadores}</div>`;
  }
}

mis_ligas(localStorage.getItem("id"));

