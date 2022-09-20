function updatePwd() {	
    res = fetch("http://127.0.0.1:8080/" + localStorage.getItem("id") + "/"+document.getElementById("password").value+"/update_password", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
	
	id:localStorage.getItem("id"),
    	password: password.value}),
  
    });	
           
    document.getElementById("succesfulUpdate").style.display="inline"
  }
  