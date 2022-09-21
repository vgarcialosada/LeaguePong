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
           
    document.getElementById("succesfulUpdate").style.display="inline"
  }
  
  async function current_data(id) {
	res = await fetch("http://127.0.0.1:8080/" +  localStorage.getItem("id") + "/usuario").then(
		(response) => response.json()
	);
	document.getElementById("nombre").value = res.map((res) => res.nombre_usuario);
	document.getElementById("mail").value = res.map((res) => res.mail);
	document.getElementById("localidad").value = res.map((res) => res.localidad);
document.getElementById("nivel").value = res.map((res) => res.nivel);	

}	



current_data(localStorage.getItem("id"));
