import express from 'express';
import dotenv from 'dotenv';
dotenv.config({ path: __dirname + "/../.env" });

const app = express();
const port = process.env.PORT || 4000;

app.get('/', (req, res) => {
  res.send('This is the home page');
});

app.listen(port, () => {
  console.log(`Server is running on port http://localhost:${port}`);
});
