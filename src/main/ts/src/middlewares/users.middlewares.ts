import { NextFunction, Request, Response } from 'express';
import HTTP_STATUS from '~/constants/httpStatus';
import { USERS_MESSAGE } from '~/constants/messages';
import { isMatchPasswordAndConfirmPassword } from '~/controllers/users.controllers';

import { REGEX_PASSWORD, REGEX_USERNAME } from '~/utils/regex';
export const loginValidator = (req: Request, res: Response, next: NextFunction) => {
  if (!req.body) {
    return res.status(HTTP_STATUS.BAD_REQUEST).send({
      message: USERS_MESSAGE.INVALID_USERNAME_OR_PASSWORD
    });
  }
  next();
};

export const registerValidator = (req: Request, res: Response, next: NextFunction) => {
  if (!req.body) {
    return res.status(HTTP_STATUS.BAD_REQUEST).send({
      message: USERS_MESSAGE.INVALID_USERNAME_OR_PASSWORD
    });
  }
  if (req.body) {
    //validate password and confirmPassword
    const { username, password, confirmPassword } = req.body;
    if (!isMatchPasswordAndConfirmPassword(password, confirmPassword)) {
      return res.status(HTTP_STATUS.BAD_REQUEST).send({
        message: USERS_MESSAGE.PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH
      });
    }

    //validate register account
    if (!REGEX_USERNAME.test(username)) {
      return res.status(HTTP_STATUS.BAD_REQUEST).send({
        message: USERS_MESSAGE.USERNAME_RULE
      });
    }

    if (!REGEX_PASSWORD.test(password)) {
      return res.status(HTTP_STATUS.BAD_REQUEST).send({
        message: USERS_MESSAGE.PASSWORD_RULE
      });
    }
  }

  //if success
  next();
};
