$(document).ready(function(){

    $('#modalButton').click(modalToggle);

    function modalToggle (){
    $('#addAirportModal').modal('toggle');
    }

    $("#btnsubmit").click(function(){
        var jsonObject = {
            name: $("#newName").val(),
        };
        $.ajax({
            contentType: "application/json",
            url: "api/airport",
            type: "post",
            data: JSON.stringify(jsonObject),
            succes: function(data){
                console.log(data);
            }
        });
        updateTable();
        modalToggle();
    });
});

    var updateTable = function(){
          console.log("ik start update");
          $('#allAirports').DataTable().ajax.reload();
    }