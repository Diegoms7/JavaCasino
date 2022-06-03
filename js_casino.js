
var imagenes = ["Images/btc.png", "Images/pera.png", "Images/berenjena.png", "Images/sandia.png", "Images/tomate.png", "Images/banana.png", "Images/caca.png"];

var ruleta;
var user;



document.getElementsByClassName("imagen")
function slot() {


    var spin = new Audio("audio/Spin.WAV");
    var http;
    http = new XMLHttpRequest();

    http.onreadystatechange = function () {

        if (http.readyState == 4 && http.status == 200) {

            ruleta = JSON.parse(http.responseText);

            spin.play();
            document.getElementById("num1").setAttribute("src", imagenes[ruleta.num1 - 1]);
            document.getElementById("num4").setAttribute("src", imagenes[ruleta.num4 - 1]);
            document.getElementById("num7").setAttribute("src", imagenes[ruleta.num7 - 1]);


            setTimeout(function () {
                spin.play();
                document.getElementById("num2").setAttribute("src", imagenes[ruleta.num2 - 1]);
                document.getElementById("num5").setAttribute("src", imagenes[ruleta.num5 - 1]);
                document.getElementById("num8").setAttribute("src", imagenes[ruleta.num8 - 1]);

            }, 600);

            setTimeout(function () {
                spin.play();
                document.getElementById("num3").setAttribute("src", imagenes[ruleta.num3 - 1]);
                document.getElementById("num6").setAttribute("src", imagenes[ruleta.num6 - 1]);
                document.getElementById("num9").setAttribute("src", imagenes[ruleta.num9 - 1]);

            }, 1200);

        }

    }
    http.open("GET", "http://127.0.0.1:8080/Casino/SlotGame", true);
    http.send();



    setTimeout(function () {

        document.getElementById("text").innerHTML = "HAS GANADO: " + ruleta.reward;
        var sonido = new Audio("audio/BigWin.WAV")
        if (ruleta.reward >= document.getElementById("betbox").value * 5 && ruleta.reward != 0) {
            sonido.play();
        }
    }, 1200);
}

function bet() {

    var http;
    http = new XMLHttpRequest();

    http.onreadystatechange = function () {

        if (http.readyState == 4 && http.status == 200) {

            if (http.responseText != "0") {
                document.getElementById("spin").disabled = false;
            }
            else {
                alert("Crédito insuficiente! Ingrese crédito para continuar");
            }

        }



    }


    http.open("POST", "http://127.0.0.1:8080/Casino/SlotGame", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log(user.Credito);
    http.send("bet=" + document.getElementById("betbox").value + "&&id=" + localStorage.getItem("user") + "&&credito=" + user.Credito + "&&dni=" + localStorage.getItem("dni"));

}

function login() {
    var http;
    http = new XMLHttpRequest();


    http.onreadystatechange = function () {

        if (http.readyState == 4 && http.status == 200) {

            if (http.readyState == 4 && http.status == 200) {

                user = JSON.parse(http.responseText);

                localStorage.setItem("user", user.ID);
                localStorage.setItem("dni", user.DNI);


                if (user.Check == true) {
                    alert("Sesión iniciada con éxito")

                }
                else {
                    alert("Usuario y/o contraseña incorrecto")
                }


            }

        }
    }

    http.open("POST", "http://localhost:8080/Casino/LoginAccess", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("password=" + document.getElementById("password").value + "&&username=" + document.getElementById("username").value);

}

function newUser() {

    var http;
    http = new XMLHttpRequest();

    http.onreadystatechange = function () {

        if (http.readyState == 4 && http.status == 200) {

            document.getElementById("Msg").style.visibility = "visible";
        }
    }

    http.open("POST", "http:/localhost:8080/Casino/CreateUser", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("dni=" + document.getElementById("id").value + "&&nombre=" + document.getElementById("name").value
        + "&&surnames=" + document.getElementById("surnames").value + "&&date="
        + document.getElementById("date").value + "&&password=" + document.getElementById("password").value);

}

function ingresar() {

    var http;
    http = new XMLHttpRequest();

    http.onreadystatechange = function () {

        if (http.readyState == 4 && http.status == 200) {

        }
    }

    if (user == null) {
        alert("INICIA SESIÓN ANTES DE INGRESAR!")
    }
    else {

        http.open("POST", "http:/localhost:8080/Casino/InsertCredit", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send("credito=" + document.getElementById("creditbox").value + "&&dni=" + localStorage.getItem("dni"));

    }

}