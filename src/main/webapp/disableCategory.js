function disableCategory(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/atualizaVisibilidadeCategoria?id="+id);

    let status = document.querySelector(".info-active"+id);

    if(status.textContent === "ATIVA") {
        xhr.addEventListener("load", function () {
            if (xhr.status == 200) {
                status.textContent = "INATIVA";
            } else {
                alert("Ocorreu um erro, tente novamente mais tarde!");
            }
        });
        xhr.send();
    }
}