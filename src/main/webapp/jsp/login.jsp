<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
    <center>
        <h3>LOG IN</h3>
        <hr/>
        <form name="logInForm" method="POST" action="Controller">
            <input type="hidden" name="command" value="login"/>
            <table>
                <tbody>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                <tr>
                </tbody>
            </table>
            <input type="submit" value="Enter">
        </form>

        <p>Never used taxi service?</p>
        <form name="goToRegistration" method="GET" action="Controller">
            <input type="hidden" name="command" value="goToRegistration"/>
            <input type="submit" value="Register Now">
        </form>
    </center>
</body>
</html>