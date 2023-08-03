<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023/08/02
  Time: 8:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>JSP Templates</title>
        <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
    </head>
<h1 style="text-align: center">User List</h1>
    <form id="searchForm" action="/ListUsers" method="GET">
        Filter: <input type="text" name="keyword" id="keyword" size="50" th:value="${keyword}" required placeholder="Nhập từ khóa tìm kiếm" />
        &nbsp;
        <input type="submit" value="Search" />
        &nbsp;
        <input type="button" value="Clear" id="btnClear" onclick="clearSearch()" />
    </form>
    <div style="display: flex; align-items: center; justify-content: center;">
        <table border="1" >
            <thead>
            <tr>
                <th>User ID</th>
                <th>User Email</th>
                <th>User Phone</th>
                <th>User Address</th>
                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td id="ID">${user.id}</td>
                    <td id="email">${user.email}</td>
                    <td id="phone">${user.phone}</td>
                    <td id="address">${user.address}</td>
                    <td>
                        <button onclick="deleteUser(this)"
                                style="background-color: crimson"
                                th:user-id="${user.id}"
                        >Delete Here
                        </button>
                    </td>
                    <td>
                        <button onclick="updateUser(this)" style="background-color: cornflowerblue"
                                th:user-id="${user.id}"
                                th:user-email="${user.email}"
                                th:user-phone="${user.phone}"
                                th:user-address="${user.address}"
                        >
                            Update Here
                        </button>
                    </td>
                </tr>
            </c:forEach>

            <!-- Add more rows as needed -->
            </tbody>
        </table>
    </div>

    <h1 id="mess" style="text-align: center"></h1>
    <div id="AddDiv"  style="display: none; align-items: center; justify-content: center;" >

        <table border="1">
            <thead>
            <tr>
                <th>User ID</th>
                <th>User Email</th>
                <th>User Phone</th>
                <th>User Address</th>
                <th>Save</th>
                <th>Cancel</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td ><input type="text" id="IdUpdate" name="id" required></td>
                    <td ><input type="text" id="EmailUpdate" required></td>
                    <td ><input type="text" id="PhoneUpdate" required></td>
                    <td ><input type="text" id="AddressUpdate" required></td>
                    <td>
                        <button onclick="addFunction(this)"
                                style="background-color: crimson"
                                th:user-id="${user.id}"
                        >Save
                        </button>
                    </td>
                    <td>
                        <button onclick="cancelFuntion()"
                                style="background-color: rgba(20,213,220,0.53)"
                        >Huy
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div id="container" style="display: flex; align-items: center; justify-content: center;">
        <button  id="myButton" onclick="toggleFunction()" style="background: yellow;display: flex;align-items: center;justify-content: center"> Post User</button>
    </div>

</body>
<script>
    function deleteUser(button){
        var id = button.getAttribute("th:user-id")
        if(confirm("Xoá nó thiệt luôn hả ông nội")){
            fetch(`user/`+id,{
                method:'DELETE',
                headers:{
                    'Content-Type':'application/json'
                }
            }).then(response =>response.json()).then(response=>{
                alert(response.message)
                if(response.status==="200"){
                    location.reload()
                }
            })
        }
    }

    function toggleFunction() {
        document.getElementById("mess").innerHTML="Thêm User"
        var div = document.getElementById('AddDiv');
        if (div.style.display === 'none') {
            div.style.display = 'flex';
        } else {
            div.style.display = 'none';
        }
        var myButton = document.getElementById('myButton')
        if(div.style.display==='block'){
            myButton.style.display = 'none'
        }else{
            myButton.style.display = 'block'
        }
    }
    function addFunction(){
        var mess = document.getElementById("mess").innerText
        var id = document.getElementById("IdUpdate")
        var email = document.getElementById("EmailUpdate")
        var phone = document.getElementById("PhoneUpdate")
        var address = document.getElementById("AddressUpdate")
        var data
        if(email.value===''){
            window.alert("Vui lòng nhập email")
            email.focus()
        } else if(phone.value===''){
            window.alert("Vui lòng nhập Sdt")
            phone.focus()
        }else if(address.value===''){
            window.alert("Vui lòng nhập Dia chi")
            address.focus()
        }else{
            var method ='POST'
            if(mess ==="Thêm User"){
                method='POST'
                data={

                    email:email.value.toString(),
                    phone:phone.value.toString(),
                    address:address.value.toString()
                }
            }else if(mess==='Chỉnh sửa User'){
                method='PUT'
                data ={
                    id:parseInt(id.value),
                    email:email.value.toString(),
                    phone:phone.value.toString(),
                    address:address.value.toString()
                }
            }
            fetch("user",{
                method:method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify(data)
            }).then(response=>response.json()).then(response=>{
                alert(response.message)
                location.reload()
            })
        }

    }
    function cancelFuntion(){
        toggleFunction()
    }
    function updateUser(button){

        var div = document.getElementById('AddDiv');
        if(div.style.display==='none'){
            toggleFunction()

        }
        document.getElementById("mess").innerHTML="Chỉnh sửa User"

        var userRow = button.parentNode.parentNode;
        var userId = userRow.querySelector("#ID").innerText;
        var userEmail = userRow.querySelector("#email").innerText;
        var userPhone = userRow.querySelector("#phone").innerText;
        var userAddress = userRow.querySelector("#address").innerText;
        // Hiển thị thông tin user
        document.getElementById("IdUpdate").value = userId;
        document.getElementById("EmailUpdate").value = userEmail;
        document.getElementById("PhoneUpdate").value = userPhone;
        document.getElementById("AddressUpdate").value = userAddress;
    }
    function clearSearch() {
        window.location = "[[@{/}]]";
    }
</script>
</html>
