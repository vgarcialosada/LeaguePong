
function create_user() {
    res = fetch("http://127.0.0.1:8080/register_user", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre_usuario: nombre_usuario.value, 
        password: password.value,
        mail: mail.value,
        localidad: localidad.value,
        nivel: nivel.value,
      }),
    });
    alert(promiseState(res));
    console.log(promiseState(res));
  }

  function promiseState(p) {
    const t = {};
    return Promise.race([p, t])
      .then(v =>
        (v === t) ? { state: "pending" } : { state: "fulfilled", value: v },
        () => { state: "rejected" }
      );
}

console.log = async function(...args) {
  for (let arg of args) {
    if (arg instanceof Promise) {
      let state = await promiseState(arg);
      window.innerHTML += `Promise { &lt;state&gt;: "${ state.state }"${ state.state === "fulfilled" ? ', &lt;value&gt;: ' + state.value : '' } }<br>`;
    } else if (typeof arg === 'object') {
      window.innerHTML += 'console.log arg:  ' + String(arg) + '<br>';
    }
    // add more else-ifs to handle strings, numbers, booleans, ... etc
  }
}

//comprueba pwd iguales
function passCheck() {
  var password = document.getElementById('password');
  var vpassword = document.getElementById('confirm_password');

  if (password.value != vpassword.value) {
    document.getElementById("submitFormRegister").disabled = true;
    document.getElementById("failed_register_pwd").style.display="inline"
  }
  else {
    document.getElementById("submitFormRegister").disabled = false;
        document.getElementById("failed_register_pwd").style.display="none"

  }
}

