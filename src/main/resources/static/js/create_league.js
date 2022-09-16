function create_league(id) {
    console.log("hello");
    res = fetch("http://127.0.0.1:8080/" + id + "/crear-liga", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre: nombre_de_liga.value,
        password: contraseña_de_liga.value,
        reglas: reglas_de_liga.value,
        ubicacion: ubicacion_de_liga.value,
        numero_jugadores: jugadores_de_liga.value,
      }),
     
    });
    //const content = rawResponse.json();
    
    window.location.href = 'http://127.0.0.1:5500/src/main/resources/static/html/my_leagues.html'
  }
  //console.log("hello");
  