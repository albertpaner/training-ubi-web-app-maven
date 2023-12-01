<!DOCTYPE html>
<html>
<body>

<h2>Registration Form</h2>

<form action="RegistrationServlet" method="post">
    <div class="container">
        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <label for="ruoloId"><b>Ruolo ID</b></label>
        <input type="number" placeholder="Enter Ruolo ID" name="ruoloId" required>

        <label for="nome"><b>Nome</b></label>
        <input type="text" placeholder="Enter Nome" name="nome" required>

        <label for="cognome"><b>Cognome</b></label>
        <input type="text" placeholder="Enter Cognome" name="cognome" required>

        <label for="responsabileId"><b>Responsabile ID</b></label>
        <input type="number" placeholder="Enter Responsabile ID" name="responsabileId" required>

        <label for="societaOp"><b>Societa Op</b></label>
        <input type="text" placeholder="Enter Societa Op" name="societaOp" required>

        <label for="mansione"><b>Mansione</b></label>
        <input type="text" placeholder="Enter Mansione" name="mansione" required>

        <label for="ambito"><b>Ambito</b></label>
        <input type="text" placeholder="Enter Ambito" name="ambito" required>

        <label for="jobFam"><b>Job Fam</b></label>
        <input type="text" placeholder="Enter Job Fam" name="jobFam" required>

        <label for="subFam"><b>Sub Fam</b></label>
        <input type="text" placeholder="Enter Sub Fam" name="subFam" required>

        <label for="stdJob"><b>Std Job</b></label>
        <input type="text" placeholder="Enter Std Job" name="stdJob" required>

        <label for="jobLevel"><b>Job Level</b></label>
        <input type="text" placeholder="Enter Job Level" name="jobLevel" required>

        <button type="submit">Register</button>
    </div>
</form>

</body>
</html>