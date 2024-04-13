import { ObjectId } from 'mongodb';

export interface IAccount {
  _id?: ObjectId;
  username: string;
  password: string;
  score?: number;
  created?: Date;
}

export class User {
  private _id?: ObjectId;
  private username: string;
  private password: string;
  private score?: number;
  private created: Date;

  constructor(user: IAccount) {
    const date = new Date();
    this._id = user._id;
    this.username = user.username;
    this.password = user.password;
    this.score = user.score;
    this.created = user.created || date;
  }

  get account() {
    return {
      username: this.username,
      password: this.password
    };
  }

  get id() {
    return this._id;
  }

  get name() {
    return this.username;
  }

  get pass() {
    return this.password;
  }

  get points() {
    if (this.score) {
      return this.score;
    }
    return 0;
  }

  get date() {
    return this.created;
  }

  toJSON() {
    return {
      id: this._id,
      username: this.username,
      score: this.score,
      created: this.created
    };
  }
}
