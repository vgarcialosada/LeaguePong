import axios from "axios";

function getLigas() {
  axios.get("http://localhost:8080/todas-ligas").then((res) => {
    console.log(res);
  });
}
getLigas();
