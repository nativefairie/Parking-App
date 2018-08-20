$(document).ready(function () {

    var mock_svg = $("#mock_svg").contentDocument;
    var spots = {};
    var status= {};
    var emails= {};
    var spotClicked = -1;
    spots = $("svg > g",mock_svg);

    spots.each(function(index,element) {
        //initial le punem pe toate verzi
        status[index] = 0; // toate-s verzi
        updateSpotToServer(index + 1 , 0);

    });

    spots.each(function(index,element) {
      
        element.addEventListener("click", function() {
            showModal(5);
            spotClicked = index + 1;
        });
    });



    $("#make-available").click(function () {
        if(status[spotClicked -1] == 2) {
            console.log("merge");
            clickAvailable();}
            else  {
            console.log("status nu e 2");
        }
    });

    $("#make-unavailable").click(function () {
        if(status[spotClicked] == 0)
            clickUnavailable();
    });

   
    getSpotFromServer();

    function getSpotFromServer() {
   
        fetch('http://localhost:8080/parkingSpots')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                renderSpots(myJson);
                setTimeout(getSpotFromServer,10000);
            });
    }

    function updateSpotToServer(id , newStatus) {
        //stabilim statusul parcarii id-ului primit ca parametru
        if(newStatus == 0) {

            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "green" );
            }
            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "green" );
            }
            if(status[id-1] == 3) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "green" );
            }

            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "green" );
            }
            
            status[id -1] = 0;
        }
        if(newStatus == 1) {

            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "red" );
            }
            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "red" );
            }
            if(status[id-1] == 3) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "red" );
            }
            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "red" );
            }

            status[id -1] = 1;
        }
        if(newStatus == 2) {



            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "gray" );
            }
            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "gray" );
            }
            if(status[id-1] == 3) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "gray" );
            }
            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "gray" );
            }
            status[id -1] = 2;
            console.log(id , newStatus , status[id - 1]);
        }
        if(newStatus == 3) {

            if(status[id-1] == 1) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("red").addClass( "orange" );
            }
            if(status[id-1] == 0) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("green").addClass( "orange" );
            }
            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("gray").addClass( "orange" );
            }
            if(status[id-1] == 2) {
                $(spots[id - 1]).find("[id^='overlay']").children().removeClass("orange").addClass( "orange" );
            }
            status[id -1] = 3;
        }

        //TODO in cazul in care avem variabila locala
    }


    function clickAvailable() {
        updateSpotToServer(spotClicked , 0);

        var obJSON = new Object();

        obJSON.parkingSpotId = spotClicked;
        obJSON.endHour = "12:00:00";
        var finalJSON = JSON.stringify(obJSON);

        console.log(finalJSON);

        sendJSON(finalJSON);

    }
    function clickUnavailable() {
        updateSpotToServer(spotClicked , 2);

        var obJSON = new Object();

        obJSON.parkingSpotId = spotClicked;
        obJSON.endHour = "12:00:00";
        var finalJSON = JSON.stringify(obJSON);

        console.log(finalJSON);

        sendJSON(finalJSON);
    }


    function sendJSON(finalJSON) {
        $.ajax({
            url: "http://localhost:8080/parkingAdmin",
            type: 'POST',
            contentType: 'application/json',
            data: finalJSON,
            dataType:'json'
          });
    }
   
    function showModal (index) {
        if(index == 5) {
            jQuery.noConflict();
            $("#modal5").modal('show');
        }
        
    }

    function renderSpots(myJson) {

        var i;
        var spotCount = Object.keys(myJson).length;

        for (i = 0; i < spotCount; i++) {
           var spotId = myJson[i].parkingSpotId;
           var email = myJson[i].userEmail;
           emails[spotId] = email;
           updateSpotToServer(spotId  , myJson[i].status);

        }
    }

    

});