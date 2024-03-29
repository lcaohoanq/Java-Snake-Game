import mysql, { PoolOptions } from 'mysql2'

import dotenv from "dotenv"
dotenv.config({ path: __dirname + "/../../.env" });

const poolOptions: PoolOptions = {
  host: process.env.MYSQL_HOST,
  port: process.env.MYSQL_PORT ? parseInt(process.env.MYSQL_PORT) : 4545,
  user: process.env.MYSQL_USER,
  password: process.env.MYSQL_PASSWORD,
  database: process.env.MYSQL_DATABASE
}

const pool = mysql.createPool(poolOptions).promise()

export async function getAccounts() {
  return pool.query("SELECT * FROM users");
}

export async function getAccount(username: string){
  return pool.query("CALL proc_select_username_password (?)", [username])
}

export async function createAccount(username: string, password: string){
  return pool.query("CALL proc_insert_user (?, ?)", [username, password])
}