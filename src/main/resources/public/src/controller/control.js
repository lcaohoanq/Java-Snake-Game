import { darkThemePath, lightThemePath } from "../constant/file.path.js";

document.querySelector(".log").addEventListener("click", (e) => {
  window.location.href = "/login.html";
});

document.querySelector(".reg").addEventListener("click", (e) => {
  window.location.href = "/register.html";
});

function changeTheme() {
  const toggle = document.getElementById("toggle");
  let newTheme;
  if (toggle.checked) {
    newTheme = lightThemePath;
    console.log(lightThemePath);
  } else {
    newTheme = darkThemePath;
  }
  document.getElementById("theme").setAttribute("href", newTheme);
}

// Set the initial theme based on the current time
function setInitialTheme() {
  var currentTime = new Date().getHours();
  var initialTheme;

  // Check if it's daytime (6:00 to 18:00)
  if (currentTime >= 6 && currentTime < 18) {
    initialTheme = lightThemePath;
  } else {
    initialTheme = darkThemePath;
  }

  document.getElementById("theme").setAttribute("href", initialTheme);
}

// Call setInitialTheme function when the page loads
window.onload = setInitialTheme;

// Add event listener to the checkbox after DOM content is loaded
document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("toggle").addEventListener("change", function () {
    changeTheme();
  });
});
