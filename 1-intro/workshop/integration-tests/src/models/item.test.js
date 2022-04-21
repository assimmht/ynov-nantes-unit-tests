const mongoose = require("mongoose");
const ItemService = require("../services/itemService");

describe("Connexion database", () => {
  beforeAll(async () => {
    await mongoose
      .connect("mongodb://mongo:27017/docker-node-mongo", {
        useNewUrlParser: true,
      })
      .then(() => console.log("MongoDB Connected"))
      .catch((err) => console.log(err));
  });

  test("Get all Item", async () => {
    const items = await ItemService.listItems();
    expect(items).toEqual(expect.arrayContaining(items));
  });

  test("Creat item", async () => {
    const newItem = {
      name: "Couscous",
    };
    const item = await ItemService.createItem(newItem);
    expect(item).toEqual(expect.objectContaining(item));
  });
});
