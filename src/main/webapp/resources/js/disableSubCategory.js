function disableSubCategory(id) {
    $.post("/admin/subcategories/update/"+id, function (){
        $(".infoActive"+id).html("Inativa");
        removeButtonDisableSubCategory(id)
    }).fail(function (){
        alert("Ocorreu um erro, tente novamente mais tarde!")
    });;
}

function removeButtonDisableSubCategory(id) {
    const btDisableSubCategory = $(".buttonDisableSubCategory"+id).fadeOut(1500);
    setTimeout(function (){
        btDisableSubCategory.remove();
    }, 1500);
}