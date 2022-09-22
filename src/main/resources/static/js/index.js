
async function entrar() {
	pwd = document.getElementById("password").value
	res = await fetch("http://localhost:8080/" + document.getElementById("nombre_usuario").value + "/" +
		pwd + "/usuario").then(
			(response) => response.json()
		);
		//si no devuelve nada falla login
	if (typeof res !== 'undefined') {
		document.getElementById("failed_login").style.display = "inline";
	}
	localStorage.setItem("id", res[0].id_usuario)
	localStorage.setItem("token", generateToken(30))

	if (localStorage.getItem("id") != null || localStorage.getItem("id") != 0) {
		window.location.href = 'http://127.0.0.1:5500/src/main/resources/static/html/menu.html'
	}
}



function loginCheck() {
	var uname = document.getElementById('nombre_usuario').value;
	var pwd = document.getElementById('password').value;
	console.log(uname)
	console.log(pwd)
	if (uname == "" ||
		pwd == "") {
		document.getElementById("submitFormLogin").disabled = true;
	}
	else {
		document.getElementById("submitFormLogin").disabled = false;
	}
}

function generateToken(n) {
	var chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
	var token = '';
	for (var i = 0; i < n; i++) {
		token += chars[Math.floor(Math.random() * chars.length)];
	}
	return token;
}

loginCheck();