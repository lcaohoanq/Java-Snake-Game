import express from 'express';
import { loginController, registerController } from '~/controllers/users.controllers';
import { loginValidator, registerValidator } from '~/middlewares/users.middlewares';

const usersRoutes = express.Router();

usersRoutes.post('/login', loginValidator, loginController);

usersRoutes.post('/register', registerValidator, registerController);

export default usersRoutes;
