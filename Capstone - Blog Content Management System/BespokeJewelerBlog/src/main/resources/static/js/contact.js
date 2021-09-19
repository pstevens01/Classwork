$("#selection :input").change(function() {
    switch (this.id) {
        case 'add':
            $("#actionTitle").text("Add New Superperson");
            break;
        case 'edit':
            $("#actionTitle").text("Edit Superperson");
            break;
        default:
            $("#actionTitle").text("Delete Superperson");
            break;
    }
    
    if (this.id === "delete") {
        $("#delBtn").css("display", "");
        $("#addBtn").css("display", "none");
        $("#editBtn").css("display", "none");
        
        $("#superSel").attr("required", true);        
        $("#superSel").addClass("readonly");        
        $("#superSelGroup").css("display", "");
        $("#superSelDrop").css("display", "");
        
        $("#superNameGroup").css("display", "none");
        $("#superName").attr("required", false);
        
        $("#powerInputGroup").css("display", "none");
        $("#powInput").attr("required", false);
        
        $("#descInputGroup").css("display", "none");
        $("#descInput").attr("required", false);
        
        $("#orgInputGroup").css("display", "none");
        $("#orgSel").attr("required", false);
    } else {
        $("#delBtn").css("display", "none");
        
        $("#powerInputGroup").css("display", "");

        $("#descInputGroup").css("display", "");
        $("#descInput").attr("required", true);

        $("#orgInputGroup").css("display", "");
        
        $("#superNameGroup").css("display", "");
        
        $("#orgSel").attr("required", true);
        if (this.id === "add") {
            $("#addBtn").css("display", "");
            $("#editBtn").css("display", "none");
            
            $("#superName").attr("required", true);
            
            $("#superSel").css("display", "none");      
            $("#superSel").attr("required", false);        
            $("#superSelGroup").css("display", "none");     
            $("#superSelDrop").css("display", "none");
            
            $("#powInput").attr("required", true);
        } else {
            $("#superSel").attr("required", true);        
            $("#superSelGroup").css("display", "");
            $("#superSelDrop").css("display", "");
            
            $("#superName").attr("required", false);
            
            $("#powInput").attr("required", false);
            
            $("#editBtn").css("display", "");
            $("#addBtn").css("display", "none");
        }
    }
    $('#superSel').val(null);
    $('#superName').val(null);
    $('#powInput').val(null);
    $('#descInput').val(null);
    $('#orgSel').val(null);
    
    $("#superSelBtn").html("Superpersons");
    $('#superSelMenu option:selected').removeAttr("selected");
    
    $("#powBtn").html("Powers");
    $('#powInputMenu option:selected').removeAttr("selected");
    
    $("#orgBtn").html("Organizations");
    $('#orgMenu option:selected').removeAttr("selected");
});

function changeSuperNameText (value) {
    document.getElementById("superSelBtn").innerText = value;
    $("#superSel").val(value.trim());
}

function changePowText (value) {
    document.getElementById("powBtn").innerText = value;
    $("#powInput").val(value.trim());
}

function changeOrgText (value) {
    //document.getElementById("orgBtn").innerText = value;
    var original = $("#orgSel").val();
    if (original !== (""))
        var newText = "," + value.trim();
    else
        var newText = value.trim();
    
    if (!original.includes(value.trim()))
        original = original.concat(newText);
    
    $("#orgSel").val(original);
}

function undoButton() {
    var text = $("#orgSel").val();
    if (text === "")
        return;
    
    var index = text.lastIndexOf(",");
    text = text.substring(0, index);
    $("#orgSel").val(text);
}

$(document).ready(function () {
});