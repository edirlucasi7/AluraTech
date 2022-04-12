function disableCategory(id) {
    $.post("/admin/categories/update/"+id, function (){
        $(".infoActive"+id).html("Inativa");
        $(".buttonDisableCategory"+id).css("display","none");
    });
}