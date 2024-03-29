import express, { NextFunction, Request, Response } from "express"
import dotenv from 'dotenv';
import usersRouters from '~/routes/users.routers'
dotenv.config({ path: __dirname + "/../.env" });

const app = express();
const port = process.env.PORT || 4000;

//use middleware to parse json
app.use(express.json());

app.get('/', (req, res) => {
  res.send('This is the home page');
});

app.use('/users', usersRouters);

app.use((err: any, req: Request, res: Response, next: NextFunction) => {
  console.log(err.stack);
  res.status(500).send("Something broke!");
});

app.listen(port, () => {
  console.log(`Server is running on port http://localhost:${port}`);
});
