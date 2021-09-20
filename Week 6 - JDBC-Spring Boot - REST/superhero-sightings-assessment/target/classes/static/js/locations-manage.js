$("#selection :input").change(function() {
    switch (this.id) {
        case 'add':
            $("#actionTitle").text("Add New Location");
            break;
        case 'edit':
            $("#actionTitle").text("Edit Location");
            break;
        default:
            $("#actionTitle").text("Delete Location");
            break;
    }
    
    if (this.id === "delete") {
        $("#delBtn").css("display", "");
        $("#addBtn").css("display", "none");
        $("#editBtn").css("display", "none");
        
        $("#locSel").attr("required", true);        
        $("#locSel").addClass("readonly");        
        $("#locSelGroup").css("display", "");
        $("#locSelDrop").css("display", "");
        
        $("#locNameGroup").css("display", "none");
        $("#locName").attr("required", false);
        
        $("#descInputGroup").css("display", "none");
        $("#descInput").attr("required", false);
        
        $("#addrInputGroup").css("display", "none");
        $("#addrInput").attr("required", false);
                
        $("#cordInputGroup").css("display", "none");
        $("#cordInput").attr("required", false);
    } else {
        $("#delBtn").css("display", "none");
        
        $("#addrInputGroup").css("display", "");

        $("#locNameGroup").css("display", "");

        $("#descInputGroup").css("display", "");
        $("#cordInputGroup").css("display", "");
        
        if (this.id === "add") {
            $("#addBtn").css("display", "");
            $("#editBtn").css("display", "none");
            
            $("#locName").attr("required", true);
            
            $("#locSel").css("display", "none");      
            $("#locSel").attr("required", false);        
            $("#locSelGroup").css("display", "none");     
            $("#locSelDrop").css("display", "none");
            
            $("#addrInput").attr("required", true);
            $("#descInput").attr("required", true);
            $("#cordInput").attr("required", true);
        } else {
            $("#locSel").attr("required", true);        
            $("#locSelGroup").css("display", "");
            $("#locSelDrop").css("display", "");
            
            $("#locName").attr("required", false);
                        
            $("#addrInput").attr("required", false);
            $("#descInput").attr("required", false);
            $("#cordInput").attr("required", false);
            
            $("#editBtn").css("display", "");
            $("#addBtn").css("display", "none");
        }
    }
    $('#locSel').val(null);
    $('#locName').val(null);
    $('#addrInput').val(null);
    $('#descInput').val(null);
    $('#cordInput').val(null);
    
    $("#locSelBtn").html("Locations");
    $('#locSelMenu option:selected').removeAttr("selected");
});

function changeLocNameText (value) {
    document.getElementById("locSelBtn").innerText = value;
    $("#locSel").val(value.trim());
}

$(document).ready(function () {
});