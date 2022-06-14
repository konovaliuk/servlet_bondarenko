<html>
<style type="text/css">
    div{
        width: 30%
        -webkit-box-sizing: border-box;
        margin-bottom: 5px
    }
</style>
<body>
    <center>
    <h3>Track the status of your ride</h3>
    <hr/>
    <form name="statusPage" method="GET" action="Controller">
        <input type="hidden" name="command" value= "statusCheck"/>
        <h1><%=request.getAttribute("status_message")%></h1>
        <input type="submit" value= "Update status">
        <p><i>Thank you for using our taxi-service)</i></p>
    </form>
    <form name="goBack" method="GET" action="Controller">
            <input type="hidden" name="command" value= "goTo"/>
            <%session.setAttribute("where", "goBack");%>
            <input type="submit" value= "Go back">
    </form>
    </center>
</body>
</html>