let currentUser  = JSON.parse(sessionStorage.getItem("currentUser"));
document.getElementById("showEmpInfo").onclick = function () {
    showAccountInfo()
}

if (currentUser == null){
    alert("Please Login First");
    location.href = "../login.html";
}
console.log(currentUser);

let nav  = document.getElementsByClassName("navbar-nav");


document.addEventListener("DOMContentLoaded", function() {
    nav.innerHTML += `<li  class="nav-item"><a class="nav-link" href="../emp_account.html">${currentUser.username}</a></li>`;

});

function logout(){
    sessionStorage.clear();
    location.href = "../signin.html";
}

let formUpdateAccount = document.getElementById("updateAccount").addEventListener('submit', update);

async function update(e){

    e.preventDefault();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;

    let user = {
        username,
        password,
        firstname,
        lastname,
        email
    }

    try{
        let req = await fetch('http://localhost:8080/api/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        currentUser.id = res.id;
        currentUser.username = res.username;
        currentUser.password = res.password;
        currentUser.firstName = res.firstName;
        currentUser.lastName = res.lastName;
        currentUser.email = res.email;
        currentUser.userRole = res.userRole;
        console.log(currentUser);
        sessionStorage.setItem("currentUser", JSON.stringify(currentUser));
        currentUser  = JSON.parse(sessionStorage.getItem("currentUser"));
        console.log(res)
        alert( `An Employee Account with username ${username} was Updated
		 `);
        showAccountInfo();
    } catch(e){
        alert('Username or password was incorrect!');
        return;
    }

}
function showAccountInfo() {
    console.log("Called!!!")
    document.getElementById("username").value = currentUser.username;
    document.getElementById("password").value = currentUser.password;
    document.getElementById("firstname").value = currentUser.firstName;
    document.getElementById("lastname").value = currentUser.lastName;
   document.getElementById("email").value = currentUser.email;
}

