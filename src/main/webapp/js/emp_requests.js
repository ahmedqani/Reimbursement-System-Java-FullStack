let currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
let currentUserRequests = {};
let managedUser = {};
let getEmpReq = document.getElementById("showAllCurrentUserReq").addEventListener("click", getEmpRequests);


if (currentUser == null) {
    alert("Please Login First");
    location.href = "../signin.html";
}

let nav = document.getElementsByClassName("navbar-nav");


function logout() {
    sessionStorage.clear();
    location.href = "../signin.html";
}

async function findAccount() {

    let user = {
        username: currentUser.username
    }

    try {
        let req = await fetch('http://localhost:8080/api/finduser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        managedUser = res;
        sessionStorage.setItem("managedUser", JSON.stringify(managedUser));
        managedUser = JSON.parse(sessionStorage.getItem("managedUser"));
        console.log(res)
    } catch (e) {
        alert('Could not Find User');
        return;
    }

}

async function getEmpRequests(e) {

    e.preventDefault();
    await findAccount();

    let user = {
        author_id: managedUser.id
    }
    console.log(user)
    try {
        let req = await fetch('http://localhost:8080/api/getReimbursementByUserid', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        console.log(res)
        let requests = res;
        sessionStorage.setItem("currentUserRequests", JSON.stringify(requests));
        currentUserRequests = JSON.parse(sessionStorage.getItem("currentUserRequests"));
        console.log(currentUserRequests);
        let td = document.getElementById("reqEmpTableTD");
        console.log("loggin CurrentuserRequ", currentUserRequests);
        console.log("loggin CurrentuserRequts first row", currentUserRequests[0]);
        for (let row in currentUserRequests) {
            console.log(currentUserRequests[row]);
                td.innerHTML += `<tr><td>${currentUserRequests[row].id}</td>
                     <td>${currentUserRequests[row].amount}</td>
                     <td>${currentUserRequests[row].submitted}</td>
                     <td>${currentUserRequests[row].resolved}</td>
                     <td>${currentUserRequests[row].description}</td>
                     <td>${currentUserRequests[row].resolver_id}</td>
                     <td>${currentUserRequests[row].reimbursementType}</td>
                     <td>${currentUserRequests[row].reimbursementStatus}</td>
                     </tr>`
        }
    } catch (e) {
        console.log(e)
        // temp.innerHTML = "UserName or Pass is inCorrect";
        return;
    }

}


