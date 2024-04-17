document.querySelector(".log").addEventListener("click", (e) => {
    window.location.href = "/login.html";
});

document.querySelector(".reg").addEventListener("click", (e) => {
    window.location.href = "/register.html";
});


function changeTheme() {
    var currentTheme = document.getElementById('theme').getAttribute('href');
    var newTheme;

    // Toggle between light and dark themes
    if (currentTheme === './src/main/resources/public/style-light.css') {
        newTheme = './src/main/resources/public/style-dark.css';
    } else {
        newTheme = './src/main/resources/public/style-light.css';
    }

    document.getElementById('theme').setAttribute('href', newTheme);
}

// Set the initial theme based on the current time
function setInitialTheme() {
    var currentTime = new Date().getHours();
    var initialTheme;

    // Check if it's daytime (6:00 to 18:00)
    if (currentTime >= 6 && currentTime < 18) {
        initialTheme = './src/main/resources/public/style-light.css';
    } else {
        initialTheme = './src/main/resources/public/style-dark.css';
    }

    document.getElementById('theme').setAttribute('href', initialTheme);
}

// Call setInitialTheme function when the page loads
window.onload = setInitialTheme;