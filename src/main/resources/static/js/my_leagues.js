async function mis_ligas(id) {
  res = await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas").then(
    (response) => response.json()
  );
  console.log(res);
  // res1 = res.map((x) => );
  console.log(res);
  for (i = 0; i < res.length; i++) {
    // var new_row = document.getElementById("my_leagues");
    // new_row.className = "card";
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
//console.log("hello");
//document.getElementById("my_leagues").innerHTML = mis_ligas(1);
//const mis_ligas = async (id) =>
//(await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas")).json();

mis_ligas(1);
//.then(
//(data) => (document.getElementById("my_leagues").innerHTML = data.map(nombre))
//);
