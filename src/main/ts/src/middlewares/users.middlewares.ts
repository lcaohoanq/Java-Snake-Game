import { NextFunction, Request, Response } from 'express';
import HTTP_STATUS from '~/constants/httpStatus';
import { isMatchPasswordAndConfirmPassword } from '~/controllers/users.controllers';

import User from '~/models/schemas/User.schema';
import { REGEX_PASSWORD, REGEX_USERNAME } from '~/utils/regex';

export const loginValidator = (req: Request, res: Response, _: NextFunction) => {
  if (req.body) {
    const user = new User(req.body);
    res.send({
      username: user.name,
      password: user.pass
    });
  }
};

export const registerValidator = (req: Request, res: Response, _: NextFunction) => {
  if (req.body) {
    //validate password and confirmPassword
    const { username, password, confirmPassword } = req.body;
    if (!isMatchPasswordAndConfirmPassword(password, confirmPassword)) {
      res.status(HTTP_STATUS.BAD_REQUEST).send({
        message: 'Password and Confirm Password are not match. Please try again.'
      });
    }

    //validate register account
    if (!REGEX_USERNAME.test(username)) {
      res.status(HTTP_STATUS.BAD_REQUEST).send({
        message: 'Username must from 1 to 20 character and do not contain special character'
      });
    }

    if (!REGEX_PASSWORD.test(password)) {
      res.status(HTTP_STATUS.BAD_REQUEST).send({
        message:
          'Password must from 8 to 20 character and contain at least 1 lowercase, 1 uppercase, 1 number and 1 special character'
      });
    }

    res.status(HTTP_STATUS.OK).send({
      message: 'Register successfully'
    });
  }
  res.status(HTTP_STATUS.BAD_REQUEST).send({
    message: 'Invalid username or password. Please try again.'
  });
};
