import { NextFunction, Request, Response } from 'express';
import HTTP_STATUS from '~/constants/httpStatus';
import { isMatchPasswordAndConfirmPassword } from '~/controllers/users.controllers';

import { User } from '~/models/schemas/User.schema';
import { REGEX_PASSWORD, REGEX_USERNAME } from '~/utils/regex';
export const loginValidator = (req: Request, res: Response, next: NextFunction) => {
  if (!req.body) {
    return res.status(HTTP_STATUS.BAD_REQUEST).send({
      message: 'Invalid username or password. Please try again.'
    });
  }
  next();
};

export const registerValidator = (req: Request, res: Response, next: NextFunction) => {
  if (!req.body) {
    return res.status(HTTP_STATUS.BAD_REQUEST).send({
      message: 'Invalid username or password. Please try again.'
    });
  }
  if (req.body) {
    //validate password and confirmPassword
    const { username, password, confirmPassword } = req.body;
    if (!isMatchPasswordAndConfirmPassword(password, confirmPassword)) {
      return res.status(HTTP_STATUS.BAD_REQUEST).send({
        message: 'Password and Confirm Password are not match. Please try again.'
      });
    }

    //validate register account
    if (!REGEX_USERNAME.test(username)) {
      return res.status(HTTP_STATUS.BAD_REQUEST).send({
        message: 'Username must from 1 to 20 character and do not contain special character'
      });
    }

    if (!REGEX_PASSWORD.test(password)) {
      return res.status(HTTP_STATUS.BAD_REQUEST).send({
        message:
          'Password must from 8 to 20 character and contain at least 1 lowercase, 1 uppercase, 1 number and 1 special character'
      });
    }
  }

  //if success
  next();
};
