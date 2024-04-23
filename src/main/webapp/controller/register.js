//rules validate
//username         : isRequired
//password         : isRequired, min 8, max 30
//confirmedPassword: isRequired, min 8, max 30, isSame(password)

const isRequired = (value) => {
  return value == "" ? "That field is required" : "";
};

const min = (num) => (value) => value.length >= num ? "" : `Min is ${num}`;
const max = (num) => (value) => value.length <= num ? "" : `Max is ${num}`;

const isSame = (paramValue, fieldName1, fieldName2) => (value) =>
  paramValue == value ? "" : `${fieldName1} not match ${fieldName2}`;

//create error message, show error message on UI
const createMsg = (parentNode, controlNode, msg) => {
  const invalidDiv = document.createElement("div");
  invalidDiv.className = "invalid-feedback";
  invalidDiv.innerHTML = msg;
  parentNode.appendChild(invalidDiv);
  controlNode.forEach((inputNode) => {
    inputNode.classList.add("is-invalid");
  });
};

const isValid = (paraObject) => {
  let { value, funcs, parentNode, controlNode } = paraObject;

  for (const funcCheck of funcs) {
    let msg = funcCheck(value);
    if (msg !== "") {
      createMsg(parentNode, controlNode, msg);
      return msg;
    }
  }

  return "";
};

//clear all error message
const clearMsg = () => {
  document.querySelectorAll(".is-invalid").forEach((inputItem) => {
    inputItem.classList.remove("is-invalid");
  });

  document.querySelectorAll(".invalid-feedback").forEach((divMsg) => {
    divMsg.remove();
  });
};

document.querySelector("form").addEventListener("submit", (event) => {
  event.preventDefault();
  clearMsg();

  let usernameNode = document.querySelector("#username");
  let passwordNode = document.querySelector("#password");
  let confirmPasswordNode = document.querySelector("#confirmPassword");

  const errorMsg = [
    isValid({
      value: usernameNode.value,
      funcs: [isRequired],
      parentNode: usernameNode.parentElement,
      controlNode: [usernameNode],
    }),
    isValid({
      value: passwordNode.value,
      funcs: [isRequired, min(8), max(30)],
      parentNode: passwordNode.parentElement,
      controlNode: [passwordNode],
    }),
    isValid({
      value: confirmPasswordNode.value,
      funcs: [
        isRequired,
        min(8),
        max(30),
        isSame(passwordNode.value, "password", "confirmed-password"),
      ],
      parentNode: confirmPasswordNode.parentElement,
      controlNode: [confirmPasswordNode],
    }),
  ];

  const isValidForm = errorMsg.every((item) => !item);

  if (isValidForm) {
    clearMsg();
    registerNewAccount(usernameNode, passwordNode, confirmPasswordNode);
  }
});

function registerNewAccount(usernameNode, passwordNode, confirmPasswordNode) {
  fetch("http://localhost:4000/users/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      username: usernameNode.value,
      password: passwordNode.value,
      confirmPassword: confirmPasswordNode.value,
    }),
  })
    .then((response) => response.json())
    .then((data) => console.log(data))
    .catch((error) => {
      console.error("Error:", error);
    });
}
