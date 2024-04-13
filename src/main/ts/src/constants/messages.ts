export const USERS_MESSAGE = {
  LOGIN_SUCCESS: 'Login successfully',
  INVALID_USERNAME_OR_PASSWORD: 'Invalid username or password',
  REGISTER_SUCCESS: 'Register successfully',
  ACCOUNT_EXISTS: 'Account already exists',
  PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH: 'Password and Confirm Password are not match',
  USERNAME_RULE: 'Username must from 1 to 20 character and do not contain special character',
  PASSWORD_RULE:
    'Password must from 8 to 20 character and contain at least 1 lowercase, 1 uppercase, 1 number and 1 special character',
  ERROR: 'An error occurred'
} as const;
