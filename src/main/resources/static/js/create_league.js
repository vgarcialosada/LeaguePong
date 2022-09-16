function create_league(id) {
    console.log("hello");
    res = fetch("http://127.0.0.1:8080/register_user", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre: nombre_de_liga.value,
        password: Input2.value,
        reglas: reglas_de_liga.value,
        ubicacion: ubicacion_de_liga.value,
        numero_jugadores: jugadores_de_liga.value,
      }),
    });
    //const content = rawResponse.json();
  
    console.log(res);
  }
  //console.log("hello");