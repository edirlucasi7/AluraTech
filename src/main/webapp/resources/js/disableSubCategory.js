function disableSubCategory(id) {
    $.post("/admin/subcategories/update/"+id, function (){
        $(".infoActive"+id).html("Inativa");
        $(".buttonDisableSubCategory"+id).css("display","none");
    });
}