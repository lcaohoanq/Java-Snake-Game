import { NextFunction, Request, Response } from 'express';

export const loginController = (req: Request, res: Response, _: NextFunction) => {
  // const account = await findAccount(username, password);
  // res.send(account);
};

export const registerController = (req: Request, res: Response, _: NextFunction) => {
  // const account = await createAccount(username, password);
  // res.send(account);
};

export const isMatchPasswordAndConfirmPassword = (password: string, confirmPassword: string) => {
  return password === confirmPassword;
};
