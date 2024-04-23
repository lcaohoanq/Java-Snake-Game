<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <title>Home</title>
    <link href="./resources/favicon.ico" rel="icon" type="image/png" />
    <link rel="stylesheet" href="./util/reset.css" />
    <!--    <link rel="stylesheet" href="./src/main/resources/public/commons.css">-->
    <link rel="stylesheet" href="./util/index.commons.css" />
    <link rel="stylesheet" href="style/theme/style-light.css" id="theme" />
    <link rel="stylesheet" href="./component/toggle/toggle.css" />
    <link rel="stylesheet" href="./responsive/home.responsive.css" />
    <!-- <link
      href="./src/main/resources/public/node_modules/bootstrap/dist/css/bootstrap.min.css"
      rel="stylesheet"
    /> -->
    <script type="module" src="./controller/control.js" defer></script>
  </head>
  <body>
    <div class="App">
      <header class="App-header">
        <h1>Snake Game</h1>
      </header>
      <section id="tmp">
        <section id="App-img">
          <img alt="logo" class="App-logo" src="./resources/logo.png" />
        </section>
        <section id="App-data">
          <div class="card_button">
            <a class="log" href="./login.jsp">Login</a>
          </div>
          <div class="card_button">
            <a class="reg" href="./register.jsp">Register</a>
          </div>
        </section>
      </section>
      <div class="toggle">
        <input
          class="toggle-input"
          type="checkbox"
          onchange="changeTheme()"
          id="toggle"
        />
        <div class="toggle-handle-wrapper">
          <div class="toggle-handle">
            <div class="toggle-handle-knob"></div>
            <div class="toggle-handle-bar-wrapper">
              <div class="toggle-handle-bar"></div>
            </div>
          </div>
        </div>
        <div class="toggle-base">
          <div class="toggle-base-inside"></div>
        </div>
      </div>
      <footer>
        <p class="App-link">Follow my Github for more</p>
        <a
          class="App-link"
          href="https://github.com/lcaohoanq/Java-Snake-Game"
          rel="noopener noreferrer"
          target="_blank"
        >
          Click here !
        </a>
      </footer>
    </div>
  </body>
</html>
