<%@ page import ="kpi.iasa.mmsa.taxiservice.entities.User"%>
<html>
<body>
    <center>
    <h3>Track the status of your ride</h3>
    <hr/>
    <form name="changeRideStatusForm" method="POST" action="Controller">
         <input type="hidden" name="command" value="changeRideStatus"/>
         <h1>You have accepted the ride with <%=((User)session.getAttribute("passenger")).getName()%></h1>
         <h3>Now you can control the status of your ride</h3>
         <select name="driverStatusChange" required="required">
            <option value="driver has arrived">Arrived</option>
            <option value="downtime">Downtime</option>
            <option value="on the way">On the way</option>
            <option value="completed">Completed</option>
         </select>
         <input type="submit" value="Enter">
    </form>
    <form name="goBackDriver" method="GET" action="Controller">
                <input type="hidden" name="command" value= "logout"/>
                <input type="submit" value= "Go back">
    </form>
    </center>
</body>
</html>