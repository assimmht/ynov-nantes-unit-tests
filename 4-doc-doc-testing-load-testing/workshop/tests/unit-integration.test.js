const ToDo = require("../toDoModel.js").ToDo;
const DB_URI = "mongodb://mongo:27017/toDoApp";
const mongoose = require("mongoose");

describe("Unitaire and Integration Test", () => {
  beforeAll(async () => {
    await mongoose
      .connect(DB_URI, {
        useNewUrlParser: true,
        useUnifiedTopology: true,
      })
      .then(() => console.log("MongoDB Connected"))
      .catch((err) => console.log(err));
  });

  test("Integration Test: Get all Item", () => {
    ToDo.find()
      .then((toDos) => {
        expect(toDos).toEqual(expect.arrayContaining(toDos));
        res.status(201).send(toDos);
      })
      .catch((err) => res.status(400).send(err));
  });

  test("Integration Test: Creat item", () => {
    const toDo = new ToDo({
      text: "Chocolat",
    });

    toDo
      .save(toDo)
      .then((savedToDo) => {
        expect(savedToDo).toEqual(expect.objectContaining(savedToDo));
        res.status(201).send(savedToDo);
      })
      .catch((err) => res.status(400).send(err));
  });

  test("Unit Test: Check if item exist", () => {
    ToDo.find()
      .then((toDos) => {
        expect(toDos).toContain("Couscous");
      })
      .catch((err) => res.status(400).send(err));
  });
});
