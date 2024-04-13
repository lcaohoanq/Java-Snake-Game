import dotenv from 'dotenv';
import { Db, MongoClient } from 'mongodb';
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
      const account = await this.db.collection('accounts').insertOne({ username, password });
      return account;
    } catch (error) {
      console.log(error);
    }
  }

  async getAccount(username: string) {
    try {
      const account = await this.db.collection('accounts').findOne({ username });
      return account;
    } catch (error) {
      console.log(error);
    }
  }
}

const databaseServices = new DatabaseServices();
export default databaseServices;
