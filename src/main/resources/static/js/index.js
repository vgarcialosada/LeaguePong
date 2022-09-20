
function entrar(){
  fetch("http://localhost:8080/"+document.getElementById("nombre_usuario").value+"/"+
  document.getElementById("password").value+"/usuario", {
    headers: {
      "Content-Type": "application/json",
    }}
    ).then((res)=>
    console.log(res.json()))
   
}