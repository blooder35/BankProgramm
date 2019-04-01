document.addEventListener("DOMContentLoaded", function () {
    var form = document.getElementById("newClient");
    var name = document.getElementById("name");
    var surName = document.getElementById("surName");
    var patrName = document.getElementById("patrName");
    var passportNumber = document.getElementById("passportNumber");
    var dateOfBirth = document.getElementById("dateOfBirth");

    document.getElementById("submitRegistrationButton").onclick = function (ev) {
        if (validateInput(name.value, surName.value, patrName.value, passportNumber.value, dateOfBirth.value)) {
            form.submit();
        } else {
            alert("Something wrong with your input");
            ev.preventDefault();
        }
    }
});

function validateInput(name, surName, patrName, passportNumber, dateOfBirth) {
    if (isNaN(name) && isNaN(surName) && isNaN(patrName) && !isNaN(passportNumber) && passportNumber.length === 10 && dateOfBirth.length === 10) {
        return true;
    } else {
        return false;
    }
}