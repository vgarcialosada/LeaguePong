function updateProfile() {
    res = fetch("http://127.0.0.1:8080/" + localStorage.getItem("id") + "/update_usuario", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
	
	id:localStorage.getItem("id"),
        nombre_usuario: nombre.value,
    	password: "placeholder",
        mail: mail.value,
        localidad: localidad.value,
        nivel: nivel.value}),
     
    });	
    
    window.location.href = 'http://127.0.0.1:5500/src/main/resources/static/html/profile.html'
  }
  