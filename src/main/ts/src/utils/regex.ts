export const REGEX_USERNAME = /^[a-zA-Z0-9]{1,20}$/;
export const REGEX_PASSWORD = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}$/;
export const REGEX_HASHED_PASSWORD = /\\$31\\$(\\d\\d?)\\$(.{43})/;
