function updateProfile() {
		//resGetUser = await fetch("http://127.0.0.1:8080/" +  localStorage.getItem("id") + "/usuario").then(
		//(response) => response.json()
	//);
	//
	//if(nombre.value==null){nombre.value=resGetUser.map((resGetUser) => resGetUser.nombre_usuario);}
		//if(mail.value==null){mail.value=resGetUser.map((resGetUser) => resGetUser.mail);}
	//if(localidad.value==null){localidad.valuee=resGetUser.map((resGetUser) => resGetUser.localidad);}
//	if(nivel.value==null){nivel.value=resGetUser.map((resGetUser) => resGetUser.nivel);}
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
  