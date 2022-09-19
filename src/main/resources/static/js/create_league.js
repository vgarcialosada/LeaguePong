function create_league() {
    res = fetch("http://127.0.0.1:8080/" + localStorage.getItem("id") + "/crear-liga", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre: nombre_de_liga.value,
        password: password.value,
        reglas: reglas_de_liga.value,
        ubicacion: ubicacion_de_liga.value,
        numero_jugadores: jugadores_de_liga.value,
      }),
     
    });
    
    window.location.href = 'http://127.0.0.1:5500/src/main/resources/static/html/my_leagues.html'
  }
  