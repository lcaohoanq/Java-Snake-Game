import express from "express";
import {createAccount, getAccount, getAccounts} from '~/services/database.services';

const usersRoutes = express.Router();

usersRoutes.get("/",async(req,res) => {
  const accountList = await getAccounts();
  res.send(accountList[0]);
})

usersRoutes.get("/:username", async(req,res) => {
  const account = await getAccount(req.params.username);
  res.send(account[0]);
})

usersRoutes.post("/register", async(req,res) => {
  const {username, password} = req.body;
  const result = await createAccount(username, password);
  res.status(201).send(result[0]);
})

export default usersRoutes;