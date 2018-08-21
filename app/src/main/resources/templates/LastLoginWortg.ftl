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
        <#if user?? >
        Your submitted data<br>
        User Email: ${user.userEmail}<br>
        User Password: ${user.userPassword}<br>

        <#else>
        <form action="/login" method="post">
            User Email:<br>
            <input type="text" name="userEmail">
            <br><br>
            User Password:<br>
            <input type="password" name="userPassword">
            <br><br>


            <input type="submit" value="Log in">
        </form>
        </#if>

</body>
</html>