function disableCategory(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/atualizaVisibilidadeCategorias?id="+id);
    xhr.addEventListener("load", function (){
        if(xhr.status == 200) {
            let status = ".info-active"+id;
            let active = document.querySelector(status);
            let error = validateActive(active.textContent);
            let messageError = document.querySelector("#message-error");

            if (active.textContent === "ATIVA") {
                active.textContent = "INATIVA";
                messageError.textContent = error;
            } else {
                messageError.textContent = error;
            }
        } else {
            alert("Requisição falhou!");
        }
    });
    xhr.send();
}

function validateActive(message) {
    if(message == "INATIVA") {
        return "A categria já está desativada!";
    } else {
        return  "";
    }
}