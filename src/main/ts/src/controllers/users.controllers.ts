import bcrypt from 'bcrypt';
import { config } from 'dotenv';
import { NextFunction, Request, Response } from 'express';
import HTTP_STATUS from '~/constants/httpStatus';
import { USERS_MESSAGE } from '~/constants/messages';
import databaseServices from '~/services/database.services';

config();

export const accountController = async (req: Request, res: Response, _: NextFunction) => {
  const account = await databaseServices.getAllAccounts();
  return res.status(HTTP_STATUS.OK).send(account);
};
export const loginController = async (req: Request, res: Response, _: NextFunction) => {
  const account = await databaseServices.login(req.body);

  if (account) {
    return res.json({
      message: USERS_MESSAGE.LOGIN_SUCCESS
    });
  }

  return res.status(HTTP_STATUS.BAD_REQUEST).json({
    message: USERS_MESSAGE.INVALID_USERNAME_OR_PASSWORD
  });
};

export const registerController = async (req: Request, res: Response, _: NextFunction) => {
  try {
    // const account = await databaseServices.register(req.body);

    const { username, password } = req.body;
    const hashedPassword = await bcrypt.hash(password, 10);
    const account = await databaseServices.register({ username, password: hashedPassword });

    return res.json({
      message: USERS_MESSAGE.REGISTER_SUCCESS,
      data: account
    });
  } catch (err: unknown) {
    if (err instanceof Error) {
      console.error(err.message); // Now you can access err.message safely

      if (err.message === USERS_MESSAGE.ACCOUNT_EXISTS) {
        return res.status(HTTP_STATUS.BAD_REQUEST).json({
          message: USERS_MESSAGE.ACCOUNT_EXISTS
        });
      }
    }

    // For any other error, send a 500 response
    return res.status(HTTP_STATUS.INTERNAL_SERVER_ERROR).json({
      message: USERS_MESSAGE.ERROR
    });
  }
};

export const isMatchPasswordAndConfirmPassword = (password: string, confirmPassword: string) => {
  return password === confirmPassword;
};
