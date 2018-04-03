$(document).ready(function() {

        $('#allPlanes').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: 'http://localhost:8080/api/plane/',
                        dataSrc: ''
                    },
                "columns": [
                    { "data": "Plane ID" }
                    { "data": "Amount of Fuel" },
                    { "data": "Airport" }
                ]
         } );


    // Functionality for interaction when clicking on rows of the table
        $('#allPlanes tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                deselect();
                $(this).addClass('selected');
                var table = $('#allPlanes').DataTable();
                var data = table.row(this).data();
                apiGetSinglePlane(data.planeId);
                $('#allPlanesModal').modal('toggle');
            }
        });

} );

function getData() {
      var api = "http://localhost:8080/api/plane";
        $.get(api, function(data){
            if (data){
                setData(data);
            }
        });
}

function setData(data){
    $("#allPlanes").DataTable().clear();
    $("#allPlanes").DataTable().rows.add(data);
    $("#allPlanes").DataTable().columns.adjust().draw();
}

// Get the data of a guest using an id
function apiGetSinglePlane(id){
    var api = "http://localhost:8080/api/plane/" + id;
    $.get(api, function(data){
        if (data){
            fillUpdateDiv(data);
        }
    });
}

// Fill the form with guestdata when updating the guest
function fillUpdateDiv(plane){

    console.log(plane);
    $("#btndelete").attr('onclick', 'submitDelete(' + plane.planeId + ');');
    $("#editbutton").attr('onclick', 'submitEdit(' + plane.planeId + ');');
    document.getElementById("modal-title-all-tables").innerHTML="Edit Plane";
    $("#planeId").val(plane.planeId);
    $("#fuel").val(plane.fuel);
    $("#airport").val(plane.airport);
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = plane.planeId;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation:true,
        content:elem,
        html:true,
        container: allPlanesModal
    });
}

// Deselect all items in the table
function deselect(){
    $('#allPlanes tr.selected').removeClass('selected');
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
        url:"/api/plane/transfer/" + name,
        type:"put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error){
            displayError(error);
        }
    });
    deselect();
    $('#allPlanesModal').modal('toggle');
}

// Delete the guest in the database with the corresponding id
function submitDelete(){
    console.log("Deleting");
    var formData = $("#tableForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var tableNumber = deleteID;
    $.ajax({
        url:"/api/plane/delete/" + name,
        type:"delete",
        data: JSON.stringify(formData),
        success: getData,
        contentType: "application/json; charset=utf-8"
    });

    updateTable();

    $('#allPlanesModal').modal('toggle');
    deselect();
}

    var updateTable = function(){
          console.log("ik start update");
          $('#allPlanes').DataTable().ajax.reload();
    }
