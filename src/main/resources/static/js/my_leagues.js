async function mis_ligas(id) {
  res = await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas").then(
    (response) => response.json()
  );
  res1 = res.map((res) => res.nombre);
  console.log(res1);
  document.getElementById("my_leagues").innerHTML = res1;
}
//console.log("hello");
//document.getElementById("my_leagues").innerHTML = mis_ligas(1);
//const mis_ligas = async (id) =>
//(await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas")).json();

mis_ligas(1);
//.then(
//(data) => (document.getElementById("my_leagues").innerHTML = data.map(nombre))
//);
