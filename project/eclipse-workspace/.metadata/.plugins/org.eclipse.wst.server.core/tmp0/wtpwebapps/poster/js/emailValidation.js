// emailValidation.js

document.addEventListener("DOMContentLoaded", function () {
    const emailInput = document.getElementById("new-email");
    const emailError = document.getElementById("email-error");
    const form = document.querySelector("form");

    form.addEventListener("submit", function (event) {
        const email = emailInput.value;
        if (!validateEmail(email)) {
            emailError.style.display = "block";
            event.preventDefault(); // Prevent form submission
        } else {
            emailError.style.display = "none";
        }
    });

    function validateEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }
});