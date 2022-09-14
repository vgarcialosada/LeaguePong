

var users = JSON.parse(localStorage.getItem('	')) || [];
var userData = [{Username:document.getElementById("UserName").value},
{Email:document.getElementById("EmailAddress").value},
{Password:document.getElementById("PassWord").value},
{Address:document.getElementById("Address1").value},
{Phone:document.getElementById("PhoneNumber").value}];

users.push(userData);
localStorage.setItem('Users', JSON.stringify(users));