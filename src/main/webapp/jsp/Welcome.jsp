<html>
<body>
    <center>
        <h3>WELCOME</h3>
        <hr/>
        <h1>Welcome, <%=(String) session.getAttribute("login")%>, to the taxi service. Hope you will enjoy your ride.</h1>
        <form name="goToAddRide" method="GET" action="Controller">
            <input type="hidden" name="command" value="goTo"/>
            <% session.setAttribute("where", "addNewRide"); %>
            <input type="submit" value="Add new ride">
        </form>
        <form name="logout" method="GET" action="Controller">
              <input type="hidden" name="command" value="logout"/>
              <input type="submit" value="Log Out">
        </form>
    </center>
</body>
</html>