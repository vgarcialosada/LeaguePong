function perfil(){
    window.location.href="http://127.0.0.1:5500/src/main/resources/static/html/profile.html"     
}
function misLigas(){
    window.location.href="http://127.0.0.1:5500/src/main/resources/static/html/my_leagues.html"     
}
function buscarLigas(){
    window.location.href="http://127.0.0.1:5500/src/main/resources/static/html/search_league.html"     
}
function otros(){
    window.location.href="http://127.0.0.1:5500/src/main/resources/static/html/others.html"     
}
function salir(){
    localStorage.removeItem("token")
     localStorage.removeItem("id")
    window.location.href="http://127.0.0.1:5500/src/main/resources/static/html/index.html"     
}
