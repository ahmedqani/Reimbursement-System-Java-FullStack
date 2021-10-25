let form = document.getElementById("login").addEventListener('submit', login);
let temp = document.getElementById("placeHolder");
const currentUser = {};
async function login(e){

    e.preventDefault();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let user = {
        username,
        password
    }

    try{
        let req = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        console.log(res)
        currentUser.id = res.user_id;
        currentUser.username = res.username;
        currentUser.password = res.password;
        currentUser.firstName = res.firstName;
        currentUser.lastName = res.lastName;
        currentUser.email = res.email;
        currentUser.userRole = res.userRole;
        console.log(currentUser);
        sessionStorage.setItem("currentUser", JSON.stringify(currentUser));
        if (currentUser.userRole === "MANAGER"){
            location.href = '../admin_updateEmp.html';
        }else {
            location.href = '../emp_account.html';
        }

    } catch(e){
        console.log(e)
        // temp.innerHTML = "UserName or Pass is inCorrect";
        return;
    }

}