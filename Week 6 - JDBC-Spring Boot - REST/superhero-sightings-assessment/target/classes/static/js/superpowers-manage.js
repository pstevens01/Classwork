$("#selection :input").change(function() {
    switch (this.id) {
        case 'add':
            $("#actionTitle").text("Add New Superpower");
            break;
        case 'edit':
            $("#actionTitle").text("Edit Superpower");
            break;
        default:
            $("#actionTitle").text("Delete Superpower");
            break;
    }
    
    if (this.id === "delete") {
        $("#delBtn").css("display", "");
        $("#addBtn").css("display", "none");
        $("#editBtn").css("display", "none");
        
        $("#powSel").attr("required", true);        
        $("#powSel").addClass("readonly");        
        $("#powSelGroup").css("display", "");        
        $("#powNameGroup").css("display", "none");
        $("#powName").attr("required", false);
    } else {
        $("#delBtn").css("display", "none");
        
        $("#powerInputGroup").css("display", "");
        
        $("#powNameGroup").css("display", "");
        $("#powName").attr("required", true);
        if (this.id === "add") {
            $("#addBtn").css("display", "");
            $("#editBtn").css("display", "none");
                        
            $("#powSel").attr("required", false);        
            $("#powSelGroup").css("display", "none");     
        } else {
            $("#powSel").attr("required", true);        
            $("#powSelGroup").css("display", "");
            
                        
            $("#editBtn").css("display", "");
            $("#addBtn").css("display", "none");
        }
    }
    $('#powSel').val(null);
    $('#powName').val(null);
    
    $("#powSelBtn").html("Superpowers");
    $('#powSelMenu option:selected').removeAttr("selected");
});

function changePowNameText (value) {
    document.getElementById("powSelBtn").innerText = value;
    $("#powSel").val(value.trim());
}

$(document).ready(function () {
});