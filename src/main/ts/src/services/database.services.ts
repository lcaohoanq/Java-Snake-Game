import dotenv from 'dotenv';
import { Db, MongoClient } from 'mongodb';
import { IAccount,User } from '~/models/schemas/User.schema'
dotenv.config({ path: __dirname + '/../../.env' });

const uri = `mongodb+srv://${process.env.MONGO_USER}:${process.env.MONGO_PASSWORD}@snake-game.t2nmru9.mongodb.net/?retryWrites=true&w=majority&appName=Snake-Game`;

class DatabaseServices {
  private client: MongoClient;
  private db: Db;
  constructor() {
    this.client = new MongoClient(uri);
    this.db = this.client.db(process.env.DB_NAME);
  }

  async connect() {
    try {
      await this.db.command({ ping: 1 });
      console.log('Pinged your deployment. You successfully connected to MongoDB!');
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  async createAccount(username: string, password: string) {
    try {
      const account = await this.db.collection('users').insertOne({ username, password });
      return account;
    } catch (error) {
      console.log(error);
    }
  }

  async getAccount(username: string) {
    try {
      const accountDocument = await this.db.collection('users').findOne({ username }) as IAccount;
      if(accountDocument){
        const account = new User(accountDocument);
        return account;
      }
      return null;
    } catch (error) {
      console.log(error);
    }
  }

  async register(account: IAccount) {
    try {
      const { username, password } = account;

      // Check if the account already exists
      const existingAccount = await this.db.collection('users').findOne({ username });
      if (existingAccount) {
        throw new Error('Account already exists');
      }

      // If the account does not exist, insert it into the database
      const insertResult = await this.db.collection('users').insertOne({ username, password });

      // Retrieve the inserted document using the insertedId
      const accountDocument = await this.db.collection('users').findOne({ _id: insertResult.insertedId });

      if (accountDocument) {
        return new User(accountDocument as IAccount);
      }
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  async getAllAccounts(){
    try {
      return this.db.collection('users').find().toArray();
    } catch (error) {
      console.log(error);
    }
  }
}

const databaseServices = new DatabaseServices();
export default databaseServices;
