function disableCategory(id) {
    $.post("/admin/categories/update/"+id, function (){
        $(".infoActive"+id).html("Inativa");
        removeButtonDisableCategory(id)
    }).fail(function (){
        alert("Ocorreu um erro, tente novamente mais tarde!")
    });
}

function removeButtonDisableCategory(id) {
    const btDisableCategory = $(".buttonDisableCategory"+id).fadeOut(1500);
    setTimeout(function (){
        btDisableCategory.remove();
    }, 1500);
}