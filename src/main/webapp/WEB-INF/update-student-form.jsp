<!DOCTYPE html>
<html>

<head>
    <title>Update Student</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>FooBar University</h2>
    </div>
</div>

<div id="container">
    <h3>Update Student</h3>

    <form action="StudentController" method="GET">

        <input type="hidden" name="execution" value="UPDATE" />

        <input type="hidden" name="studentId" value="${THE_STUDENT.id}" />

        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><input type="text" name="firstName"
                           value="${THE_STUDENT.firstName}" /></td>
            </tr>

            <tr>
                <td><label>Last name:</label></td>
                <td><input type="text" name="lastName"
                           value="${THE_STUDENT.lastName}" /></td>
            </tr>

            <tr>
                <td><label>Guardian name:</label></td>
                <td><input type="text" name="guardianName"
                           value="${THE_STUDENT.guardianName}" /></td>
            </tr>

            <tr>
                <td><label>Address:</label></td>
                <td><input type="text" name="address"
                           value="${THE_STUDENT.address}" /></td>
            </tr>

            <tr>
                <td><label>DOB:</label></td>
                <td><input type="text" name="DOB"
                           value="${THE_STUDENT.DOB}" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="StudentController">Back to List</a>
    </p>
</div>
</body>

</html>











