<%-- 
    Document   : LoARe
    Created on : Jun 13, 2023, 12:30:32 PM
    Author     : Admin
--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Web Site With Login & Registration </title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <header>
            <h2 class="logo">Nemo</h2>
            <nav class="navigation">
                <a href="index.jsp">Home</a>
                <a href="viewCart.jsp">View Cart</a>
                <a href="personalPage.jsp">Personal Page</a>
                <a href="#">Contact</a>
                <button class="btnLogin-popup">Login</button>
            </nav>
        </header>
        <div class="wrapper">
            <span class="icon-close">
                <ion-icon name="close"></ion-icon>
            </span>
            <div class="form-box login">
                <h2>Login</h2>
                <form action="mainController" method="post">
                    <font style='color:red;'><%= (request.getAttribute("WARNING") == null) ? "" : (String) request.getAttribute("WARNING")%></font>
                    <div class="input-box">
                        <span class="icon"><ion-icon name="mail"></ion-icon></span>
                        <input type="email" name="txtemail" required>
                        <label>Email</label>
                    </div>
                    <div class="input-box">
                        <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                        <input type="password" name="txtpassword" required>
                        <label>Password</label>
                    </div>
                    <div class="remember-forgot">
                        <label for=""><input type="checkbox" name="saveLogin">Remember me</label>
                        <a href="#">Forgot Password</a>
                    </div>
                    <button type="submit" class="btn" name="action" value="Login">Login</button>
                    <div class="login-register">
                        <p>Don't have an account? <a href="#" class="register-link">Register</a></p>
                    </div>
                </form>
            </div>
            <div class="form-box register">
                <h2>Registration</h2>
                <form action="mainController" method="post">
                    <div class="input-box">
                        <span class="icon"><ion-icon name="mail"></ion-icon></span>
                        <input type="email" name="txtemail" pattern="^(\\w)+@(a-zA-Z]+([.](\\w)+){1,2}$" value="<%=(request.getAttribute("txtemail") == null) ? "" : request.getAttribute("txtemail")%>" required>
                        <label>Email</label>
                    </div>
                    <div class="input-box">
                        <span class="icon"><ion-icon name="person"></ion-icon></span>
                        <input type="text" name="txtfullname" value="<%=(request.getAttribute("txtfullname") == null) ? "" : request.getAttribute("txtfullname")%>" required>
                        <label>Username</label>
                    </div>
                    <div class="input-box">
                        <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                        <input type="password" name="txtpassword" value="<%=(request.getAttribute("txtpassword") == null) ? "" : request.getAttribute("txtpassword")%>" required>
                        <label>Password</label>
                    </div>
                    <div class="input-box">
                        <span class="icon"><ion-icon name="phone-portrait-outline"></ion-icon></span>
                        <input type="tel" name="txtphone"value="<%=(request.getAttribute("txtphone") == null) ? "" : request.getAttribute("txtphone")%>" required>
                        <label>Phone</label>
                        <%=(request.getAttribute("ERROR") == null) ? "" : request.getAttribute("ERROR")%>
                    </div>
                    <div class="remember-forgot">                       
                        <label><input type="checkbox">I agree to the terms & conditions</label>
                    </div>
                    <button type="submit" class="btn" name="action" value="Register">Register</button>
                    <div class="login-register">
                        <p>Already have an account? <a href="#" class="login-link">Login</a></p>
                    </div>
                </form>
            </div>
        </div>
        <script src="js/script.js"></script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>

