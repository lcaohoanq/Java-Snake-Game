### Tiến hành cài đặt nodejs + ts + eslint + prettier

bật terminal

```bash
npm init -y --tạo package.json
npm i typescript -D --vì nó chỉ dùng để làm , chứ sản phẩm vẫn là js
npm i @types/node -D -thêm kiểu ts cho thằng nodejs hiểu
npm install eslint prettier eslint-config-prettier eslint-plugin-prettier @typescript-eslint/eslint-plugin @typescript-eslint/parser ts-node tsc-alias tsconfig-paths rimraf nodemon -D

```

**eslint**: Linter (bộ kiểm tra lỗi) chính

**prettier**: Code formatter chính

**eslint-config-prettier**: Cấu hình ESLint để không bị xung đột với Prettier

**eslint-plugin-prettier**: Dùng thêm một số rule prettier cho eslint

**@typescript-eslint/eslint-plugin**: ESLint plugin cung cấp các rule cho Typescript

**@typescript-eslint/parser**: Parser cho phép ESLint kiểm tra lỗi Typescript

**ts-node**: Dùng để chạy TypeScript code trực tiếp mà không cần build

**tsc-alias**: Xử lý alias khi build

**tsconfig-paths**: Khi setting alias import trong dự án dùng ts-node thì chúng ta cần dùng tsconfig-paths để nó hiểu được paths và baseUrl trong file tsconfig.json

**rimraf**: Dùng để xóa folder dist khi trước khi build

**nodemon**: Dùng để tự động restart server khi có sự thay đổi trong code

### Cấu hình ts bằng file tsconfig.json

Tạo cùng cấp với package.json

```bash
touch tsconfig.json
```

Nội dung:

```json
{
  "compilerOptions": {
    "module": "CommonJS", // Quy định output module được sử dụng
    "moduleResolution": "node", //
    "target": "ES2020", // Target ouput cho code
    "outDir": "dist", // Đường dẫn output cho thư mục build
    "esModuleInterop": true /* Emit additional JavaScript to ease support for importing CommonJS modules. This enables 'allowSyntheticDefaultImports' for type compatibility. */,
    "strict": true /* Enable all strict type-checking options. */,
    "skipLibCheck": true /* Skip type checking all .d.ts files. */,
    "baseUrl": ".", // Đường dẫn base cho các import
    "paths": {
      "~/*": ["src/*"] // Đường dẫn tương đối cho các import (alias)
    }
  },
  "ts-node": {
    "require": ["tsconfig-paths/register"]
  },
  "files": ["src/type.d.ts"], // Các file dùng để defined global type cho dự án
  "include": ["src/**/*"] // Đường dẫn include cho các file cần build
}
```

### Cấu hình eslint bằng file .eslintrc

- Cài extensions eslint, tạo file `.eslintrc`

```bash
touch .eslintrc
```

Nội dung:

```js
{
  "root": true,
  "parser": "@typescript-eslint/parser",
  "plugins": ["@typescript-eslint", "prettier"],
  "extends": ["eslint:recommended", "plugin:@typescript-eslint/recommended", "eslint-config-prettier", "prettier"],
  "rules": {
    "@typescript-eslint/no-explicit-any": "off",
    "@typescript-eslint/no-unused-vars": "off",
    "prettier/prettier": [
      "warn",
      {
        "arrowParens": "always",
        "semi": false,
        "trailingComma": "none",
        "tabWidth": 2,
        "endOfLine": "auto",
        "useTabs": false,
        "singleQuote": true,
        "printWidth": 120,
        "jsxSingleQuote": true
      }
    ]
  }
}

```

- Cài thêm .eslintignore để loại bỏ những file mà mình không muốn nó format code của mình

```bash
touch .eslintignore
```

Nội dung:

```js
node_modules/
dist/
```

### Cấu hình cho prettier tự canh chỉnh lề cho đẹp

- Cài extensions prettier
tạo file `.prettierrc` để cấu hình

```bash
touch .prettierrc
```

Nội dung .prettierrc

```js
{
  "arrowParens": "always",
  "semi": false,
  "trailingComma": "none",
  "tabWidth": 2,
  "endOfLine": "auto",
  "useTabs": false,
  "singleQuote": true,
  "printWidth": 120,
  "jsxSingleQuote": true
}

```

- Cài thêm file `.prettierignore` để nó k canh lề cho những cái mình k thích

```bash
touch .prettierignore
```

Nội dung:

```js
node_modules/
dist/
```

### Editor để chuẩn hóa khi code

cài extensions EditorConfig for VS Code
tạo file .editorconfig

```bash
touch .editorconfig
```

nội dung .editorconfig

```js
indent_size = 2;
indent_style = space;
```

### .gitignore

để tránh push những thứ k cần thiết lên git
tạo file .gitignore

```bash
touch .gitignore
```

mọi người vào trang này [link](https://www.toptal.com/developers/gitignore)
tìm nodejs

và chép nội dung đó vào file

### cấu hình nodemon.json

tạo file nodemon.json

```bash
touch nodemon.json
```

nội dung

```json
{
  "watch": ["src"],
  "ext": ".ts,.js", //trecking các file có ts và js
  "ignore": [], //liệt kê file nào mà bạn k thích theo dõi vào
  "exec": "npx ts-node ./src/index.ts" //chạy file index
}
```

### cấu hình package.json

vào file package.json
thay script thành

```json
  "scripts": {
    "dev": "npx nodemon", //dùng để code
    "build": "rimraf ./dist && tsc && tsc-alias",//code xong build ra sản phẩm
    "start": "node dist/index.js", //run code vừa build, phải build trước
    "lint": "eslint .", //kiểm tra lỗi
    "lint:fix": "eslint . --fix",//fix lỗi
    "prettier": "prettier --check .",
    "prettier:fix": "prettier --write ."
  }

```

### tạo type.d.ts

**tạo thư mục src**

```bash
mkdir src
```

tạo thư mục src và tạo file type.d.ts trong src

```bash
cd src
touch type.d.ts
```


# II . Kiểm tra project vừa setup và cài đặt môi trường server routing với expressjs

```bash
npm run dev
```

kiểm tra xem code trong **index.ts** có hoạt động không

```ts
const sum = (a: number, b: number) => {return a+b};
console.log(sum(1,2))
```
cd /src
```bash
ts-node index.ts
```

sau đó ta sẽ tiến hành **xóa hết code** trong **index.ts**, và code những cái liên quan đến expressjs

cài đặt expressjs phiên bản cho ts

```bash
npm i express
npm i @types/express -D //dành cho ts
```

# III. Kết nối với database
## MySQL
- Sử dụng `mysql2` và `dotenv`
```bash 
npm install --save mysql2
npm install --save-dev @types/node

npm install dotenv --save
```
## MongoDB
```
npm install mongodb
```