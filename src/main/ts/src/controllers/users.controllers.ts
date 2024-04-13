import { NextFunction, Request, Response } from 'express';
import HTTP_STATUS from '~/constants/httpStatus';
import databaseServices from '~/services/database.services';

export const accountController = async (req: Request, res: Response, _: NextFunction) => {
  const account = await databaseServices.getAllAccounts();
  return res.status(HTTP_STATUS.OK).send(account);
};
export const loginController = async (req: Request, res: Response, _: NextFunction) => {
  const account = await databaseServices.login(req.body);

  if (account) {
    return res.json({
      message: 'Login successfully'
    });
  }

  return res.status(HTTP_STATUS.BAD_REQUEST).json({
    message: 'Invalid username or password. Please try again.'
  });
};

export const registerController = async (req: Request, res: Response, _: NextFunction) => {
  try {
    const account = await databaseServices.register(req.body);

    return res.json({
      message: 'Register successfully',
      data: account
    });
  } catch (err: unknown) {
    if (err instanceof Error) {
      console.error(err.message); // Now you can access err.message safely

      if (err.message === 'Account already exists') {
        return res.status(HTTP_STATUS.BAD_REQUEST).json({
          message: 'Account already exists'
        });
      }
    }

    // For any other error, send a 500 response
    return res.status(HTTP_STATUS.INTERNAL_SERVER_ERROR).json({
      message: 'An error occurred'
    });
  }
};

export const isMatchPasswordAndConfirmPassword = (password: string, confirmPassword: string) => {
  return password === confirmPassword;
};
