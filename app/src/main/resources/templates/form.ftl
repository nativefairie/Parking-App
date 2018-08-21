<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form example with Java, Spring Boot, FreeMarker</title>

</head>
<body>>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script language = "javascript">


    function doPoll(){
        $.post('ajax/test.html', function(data) {
            alert(data);  // process results here
            setTimeout(doPoll,5000);
        });
    }


    $( document ).ready(function() {
        $.ajax({url: "http://localhost:8080/parking", success: function(result){
                console.log(result);
                $("#div1").val(result);
            }});
    });
</script>
<div class = "div1"></div>
<h2>Handling Form Submission example with Java, Spring Boot, FreeMarker</h2>
        <#if getHelper?? >
        Your submitted data<br>
        User Email: ${getHelper.userEmail}<br>
        User Password: ${getHelper.userPassword}<br>
        End Hour: ${getHelper.endHour}<br>
        Parking Spot: ${getHelper.parkingSpotId}<br>
        <#else>
        <form action="/form" method="post">
            User Email:<br>
            <input type="text" name="userEmail">
            <br><br>
            User Password:<br>
            <input type="password" name="userPassword">
            <br><br>
            End Hour:<br>
            <input type="time" name="endHour">
            <br><br>
            Parking Spot:<br>
            <input type="text" name="parkingSpotId">
            <br><br>

            <input type="submit" value="Log in">
        </form>
        </#if>

</body>
</html>