document.querySelector("#log-google").addEventListener("click", (e) => {
    gapi.auth2.getAuthInstance().signIn();
});

function onSignIn(googleuser) {
    var idToken = googleuser.getAuthResponse().id_token;
    console.log("ID Token: " + idToken);
}

(function init() {
    gapi.load("auth2", function () {
        gapi.auth2
            .init({
                client_id:
                    "734220047585-k1cva91mhgpq98q5tss8dd3c535q0ccc.apps.googleusercontent.com",
                scope: [
                    "https://www.googleapis.com/auth/userinfo.email",
                    "https://www.googleapis.com/auth/userinfo.profile",
                ].join(" "),
                prompt: "consent",
            })
            .then(function () {
                console.log("Google OAuth initialized");
            })
            .catch(function (error) {
                console.error("Error initializing Google OAuth:", error);
            });
    });
})();