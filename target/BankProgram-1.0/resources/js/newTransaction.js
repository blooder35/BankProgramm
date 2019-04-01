document.addEventListener("DOMContentLoaded", function () {
    var form = document.getElementById("newTransaction");
    var sender = document.getElementById("sender");
    var recipient = document.getElementById("recipient");
    var amount = document.getElementById("amount");
    if (recipient.value === "0") {
        recipient.value = "";
        if (sender.value === "0") sender.value = "";
    }
    amount.value = "";

    document.getElementById("submitTransactionButton").onclick = function (ev) {
        if (validateInput(sender, recipient, amount)) {
            form.submit();
        } else {
            alert("Something wrong with your input");
            ev.preventDefault();
        }
    }
});

function validateInput(sender, recipient, amount) {
    if (!isNaN(sender.value) && !isNaN(recipient.value) && !isNaN(amount.value) && sender.value !== "" && recipient.value !== "" && amount.value !== "") {
        return true;
    } else {
        return false;
    }
}