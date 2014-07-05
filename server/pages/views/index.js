var PATH = "../services/";

$(function() {
    fillVersions(); 

    var $form = $('#form');
    $form.submit(function(event) {
        event.preventDefault();
        create_sandbox();
    });
});

function create_sandbox() {
    var $form = $('#form');
    data = $form.serialize();
    
    $.ajax({
        url: PATH + "do_create.php",
        data : data,
        async : false,
        success : function(filename) {
            console.log(filename);
            open_modal();
            call_file(filename);
        },
        error : function() {
            open_modal();
            console.log("Sandbox creation failed ! ");
            console.log("Something goes wrong.");
        }
    });
}

function open_modal() {
    $script = $('#script').dialog({
        title : "Creating Sandbox...",
        minWidth : 800,
        minHeight : 600,
        maxHeight: 600,
        modal : true,
        draggable: false,
        resizable: false,
        buttons: [
                    {
                      text: "OK",
                      click: function() {
                        $( this ).dialog( "close" );
                      }
                    }
                  ]
    })
}

function call_file(filename) {
    $.ajax({
        url: PATH + "read.php",
        data : {fichier : filename},
        type : "GET",
        success : function(data) {
            console.log(data);
            if(data!=="false") {
                display_script(data);
                setTimeout(function() {call_file(filename)}, 1000);
            } else {
                display_sandboxCreated();
            }
        }
    });
}

function display_sandboxCreated() {
    $('<span style="color:green;font-weight:bold;">Sandbox created</span>').appendTo('#script');
}

function display_script(data) {
    var array = $.parseJSON(data);
    
    var $script = $('#script');
    $script.empty();
    
    array.forEach(function(line) {
        $script.append(line + "<br />");
    });
}

function fillVersions() {
    $.ajax({
        url: PATH + "listversions.php",
        success : function(data) {
            var $version = $('#version');
            var versions = $.parseJSON(data);
            versions.forEach(function(entry) {
                $version.append('<option value='+entry+'>'+entry+'</option>');
            });
        }
    });
}
