<html>
<body>
    <center>
        <h3>REGISTRATION</h3>
        <hr/>
        <form name="registerForm" method="POST" action="Controller">
        <input type="hidden" name="command" value="registration"/>
        <table>
            <tbody>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Telephone Number:</td>
                <td><input type="text" name="telephone number"/></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td>
                    <select name="role" required="required">
                        <option value="passenger">Passenger</option>
                        <option value="driver">Driver</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="Enter">
        </form>
    </center>
</body>
</html>