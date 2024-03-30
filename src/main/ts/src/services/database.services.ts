import  {MongoClient} from 'mongodb'
import dotenv from "dotenv";
dotenv.config({ path: __dirname + "/../../.env" });

const uri = `mongodb+srv://${process.env.MONGO_USER}:${process.env.MONGO_PASSWORD}@snake-game.t2nmru9.mongodb.net/?retryWrites=true&w=majority&appName=Snake-Game`;

// Create a MongoClient with a MongoClientOptions object to set the Stable API version
const client = new MongoClient(uri);

export async function run() {
  try {
    // Connect the client to the server	(optional starting in v4.7)
    await client.connect();
    // Send a ping to confirm a successful connection
    await client.db("admin").command({ ping: 1 });
    console.log("Pinged your deployment. You successfully connected to MongoDB!");
  } catch (err) {
    console.log(err)
  }
}


// const pool = mysql.createPool(poolOptions).promise()
//
// export async function getAccounts() {
//   return pool.query("SELECT * FROM users");
// }
//
// export async function getAccount(username: string){
//   return pool.query("CALL proc_select_username_password (?)", [username])
// }
//
// export async function createAccount(username: string, password: string){
//   return pool.query("CALL proc_insert_user (?, ?)", [username, password])
// }