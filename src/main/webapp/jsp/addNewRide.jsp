<html>
<body>
<center>
        <h3>Add new ride to a basket</h3>
        <hr/>
        <form name="addRideForm" method="POST" action="Controller">
            <input type="hidden" name="command" value="addRide"/>
            <table>
                <tbody>
                    <tr>
                        <td>My location:</td>
                        <td><input type="text" name = "my location"></td>
                    </tr>
                    <tr>
                        <td>Destination:</td>
                        <td><input type="text" name = "destination"></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Enter">
        </form>
    </center>
</body>
</html>