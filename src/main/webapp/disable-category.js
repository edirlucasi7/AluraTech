function disableCategory(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/atualizaVisibilidadeCategorias?id="+id);
    xhr.addEventListener("load", function (){
        if(xhr.status == 200) {
            let status = ".info-active"+id;
            let active = document.querySelector(status);

            if (active.textContent === "ATIVA") {
                active.textContent = "INATIVA";
            }
        } else {
            alert("Requisição falhou!");
        }
    });
    xhr.send();
}