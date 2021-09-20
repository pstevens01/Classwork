$("#selection :input").change(function() {
    switch (this.id) {
        case 'add':
            $("#actionTitle").text("Add New Organization");
            break;
        case 'edit':
            $("#actionTitle").text("Edit Organization");
            break;
        default:
            $("#actionTitle").text("Delete Organization");
            break;
    }
    
    if (this.id === "delete") {
        $("#delBtn").css("display", "");
        $("#addBtn").css("display", "none");
        $("#editBtn").css("display", "none");
        $("#orgInput").css("display", "");
        $("#orgNameInput").css("display", "none");
        $("#orgAddrInput").css("display", "none");
        $("#orgDescInput").css("display", "none");
        $("#locationInput").css("display", "none");
        $("#orgSel").attr("required", true);
        $("#orgName").attr("required", false);
        $('#orgDesc').attr("required", false);
        $('#orgAddr').attr("required", false);
        $('#locName').attr("required", false);
    } else {
        $("#delBtn").css("display", "none");
        $("#dateTimeInput").css("display", "");
        $("#superInput").css("display", "");
        $("#orgNameInput").css("display", "");
        $("#orgDescInput").css("display", "");
        $("#orgAddrInput").css("display", "");
        $("#locationInput").css("display", "");
        if (this.id === "add") {
            $("#addBtn").css("display", "");
            $("#editBtn").css("display", "none");
            $("#orgInput").css("display", "none");
            $("#orgSel").attr("required", false);
            $("#orgName").attr("required", true);
            $('#orgDesc').attr("required", true);
            $('#orgAddr').attr("required", true);
            $('#locName').attr("required", true);
        } else {
            $("#editBtn").css("display", "");
            $("#addBtn").css("display", "none");
            $("#orgInput").css("display", "");
            $("#orgSel").attr("required", true);
            $("#orgName").attr("required", false);
            $('#orgDesc').attr("required", false);
            $('#orgAddr').attr("required", false);
            $('#locName').attr("required", false);
        }
    }
    $('#orgSel').val(null);
    $('#orgName').val(null);
    $('#orgDesc').val(null);
    $('#orgAddr').val(null);
    $('#locName').val(null);
    $("#orgBtn").html("Organization");
    $("#locBtn").html("Locations");
    $('#orgMenu option:selected').removeAttr("selected");
});

function changeOrgText (value) {
    document.getElementById("orgBtn").innerText = value;
    $("#orgSel").val(value.trim());
}

function changeLocText (value) {
    document.getElementById("locBtn").innerText = value;
    $("#locName").val(value.trim());
}

$(document).ready(function () {
});