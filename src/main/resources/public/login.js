const isRequired = (value) => {
  return value == "" ? "That field is required" : "";
};

const min = (num) => (value) => value.length >= num ? "" : `Min is ${num}`;
const max = (num) => (value) => value.length <= num ? "" : `Max is ${num}`;

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
  ];

  const isValidForm = errorMsg.every((item) => !item);

  if (isValidForm) {
    clearMsg();
    alert("Form is valid");
  }
});
