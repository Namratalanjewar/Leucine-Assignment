const loginForm = document.getElementById('login-form');
const errorMessage = document.getElementById('error-message');

loginForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password, role })
    })
    .then(response => response.json())
    .then((data) => {
        if (data.error) {
            errorMessage.textContent = data.error;
        } else {
            window.location.href = `/dashboard/${role.toLowerCase()}`;
        }
    })
    .catch((error) => {
        errorMessage.textContent = 'Error logging in. Please try again.';
    });
});