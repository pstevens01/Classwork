$("#selection :input").change(function() {
    switch (this.id) {
        case 'add':
            $("#actionTitle").text("Add New Sighting");
            break;
        case 'edit':
            $("#actionTitle").text("Edit Sighting");
            break;
        default:
            $("#actionTitle").text("Delete Sighting");
            break;
    }
    
    if (this.id === "delete") {
        $("#delBtn").css("display", "");
        $("#addBtn").css("display", "none");
        $("#editBtn").css("display", "none");
        $("#locDateInput").css("display", "");
        $("#dateTimeInput").css("display", "none");
        $("#superInput").css("display", "none");
        $("#locationInput").css("display", "none");
        $("#locDate").attr("required", true);
        $("#superName").attr("required", false);
        $("#locName").attr("required", false);
        $("#dateTime").attr("required", false);
    } else {
        $("#delBtn").css("display", "none");
        $("#dateTimeInput").css("display", "");
        $("#superInput").css("display", "");
        if (this.id === "add") {
            $("#addBtn").css("display", "");
            $("#editBtn").css("display", "none");
            $("#locDateInput").css("display", "none");
            $("#locDate").attr("required", false);
            $("#locName").attr("required", true);
            $("#dateTime").attr("required", true);
            $("#superName").attr("required", true);
        } else {
            $("#editBtn").css("display", "");
            $("#addBtn").css("display", "none");
            $("#locDateInput").css("display", "");
            $("#locationInput").css("display", "");
            $("#locDate").attr("required", true);
            $("#locName").attr("required", false);
            $("#dateTime").attr("required", false);
            $("#superName").attr("required", false);
        }
    }
    $('#superName').val(null);
    $('#locName').val(null);
    $('#locDate').val(null);
    $("#locBtn").html("Location");
    $('#locMenu option:selected').removeAttr("selected");
    $("#locDateBtn").html("Sighting");
    $('#locDateMenu option:selected').removeAttr("selected");
    $("#superNameBtn").html("Superpersons");
    $('#superNameMenu option:selected').removeAttr("selected");
});

function changeLocText (value) {
    document.getElementById("locBtn").innerText = value;
    $("#locName").val(value.trim());
}

function changeLocDateText (value) {
    document.getElementById("locDateBtn").innerText = value;
    $("#locDate").val(value.trim());
}

function changeSuperNameText (value) {
    document.getElementById("superNameBtn").innerText = value;
    $("#superName").val(value.trim());
}


$(document).ready(function () {
});