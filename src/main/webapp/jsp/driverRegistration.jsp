<html>
<body>
    <center>
        <h3>REGISTRATION</h3>
        <hr/>
        <form name="driverRegisterForm" method="POST" action="Controller">
        <input type="hidden" name="command" value="driverRegistration"/>
        <table>
            <tbody>
            <tr>
                <td>Telegram Username:</td>
                <td><input type="text" name="telegram_username"/></td>
            </tr>
            <tr>
                <td>Car Number:</td>
                <td><input type="text" name="car_number"/></td>
            </tr>
            <tr>
                <td>Car Model:</td>
                <td><input type="text" name="car_model"/></td>
            </tr>
            <tr>
                <td>Car Color: </td>
                <td><input type="text" name="car_color"/></td>
            </tr>
            <tr>
                 <td>Car Type: </td>
                 <td><select name="car_type" required="required">
                         <option value="econom">Enconom</option>
                         <option value="standart">Standart</option>
                         <option value="comfort">Comfort</option>
                         <option value="buisness">Buisness</option>
                 </select></td>
            </tr>
            <tr>
                <td>Car Average Speed: </td>
                <td><input type="text" name="average_speed"/></td>
            </tr>
            <tr>
                <td>Car Capacity: </td>
                <td><input type="text" name="car_capacity"/></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="Enter">
        </form>
    </center>
</body>
</html>