$(document).ready(function() {

        $('#allAirports').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: 'http://localhost:8080/api/airport/',
                        dataSrc: ''
                    },
                "columns": [
                    { "data": "name" },
                    { "data": "plane" }
                ]
         } );


    // Functionality for interaction when clicking on rows of the table
        $('#allAirports tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                deselect();
                $(this).addClass('selected');
                var table = $('#allAirports').DataTable();
                var data = table.row(this).data();
                apiGetSingleAirport(data.name);
                $('#allAirportsModal').modal('toggle');
            }
        });

} );

function getData() {
      var api = "http://localhost:8080/apiairport";
        $.get(api, function(data){
            if (data){
                setData(data);
            }
        });
}

function setData(data){
    $("#allAirports").DataTable().clear();
    $("#allAirports").DataTable().rows.add(data);
    $("#allAirports").DataTable().columns.adjust().draw();
}

// Get the data of a guest using an id
function apiGetSingleAirport(id){
    var api = "http://localhost:8080/api/airport/" + id;
    $.get(api, function(data){
        if (data){
            fillUpdateDiv(data);
        }
    });
}

// Fill the form with guestdata when updating the guest
function fillUpdateDiv(airport){

    console.log(airport);
    $("#btndelete").attr('onclick', 'submitDelete(' + airport.name + ');');
    $("#editbutton").attr('onclick', 'submitEdit(' + airport.name + ');');
    document.getElementById("modal-title-all-tables").innerHTML="Edit Airport";
    $("#name").val(airport.name);
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = airport.name;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation:true,
        content:elem,
        html:true,
        container: allAirportsModal
    });
}

// Deselect all items in the table
function deselect(){
    $('#allAirports tr.selected').removeClass('selected');
    // rloman dit moet straks terug. ik denk dat dit het modal form is
    document.getElementById("tableForm").reset();
}

// Submit the edited data in the form to the database
function submitEdit(id){
// shortcut for filling the formData as a JavaScript object with the fields in the form
    console.log("Formdata");
    var formData = $("#tableForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    console.log(formData);
    var name = id;
    for(var key in formData){
        if(formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url:"/api/airport/update/" + name,
        type:"put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error){
            displayError(error);
        }
    });
    deselect();
    $('#allAirportsModal').modal('toggle');
}

// Delete the guest in the database with the corresponding id
function submitDelete(){
    console.log("Deleting");
    var formData = $("#tableForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var tableNumber = deleteID;
    $.ajax({
        url:"/api/airport/delete/" + name,
        type:"delete",
        data: JSON.stringify(formData),
        success: getData,
        contentType: "application/json; charset=utf-8"
    });

    updateTable();

    $('#allAirportsModal').modal('toggle');
    deselect();
}

    var updateTable = function(){
          console.log("ik start update");
          $('#allAirports').DataTable().ajax.reload();
    }
