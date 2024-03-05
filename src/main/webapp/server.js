const express = require("express");
const path = require("path");

const app = express();
const port = 5000;

app.use(express.static(path.join(__dirname, "/public")));

app.get("/", (req, res) => {
  console.log("Hello đây là tầng đầu tiên của API");
  res.sendFile(path.join(__dirname, "index.html"));
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
