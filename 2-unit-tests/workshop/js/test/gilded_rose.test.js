const { Shop, Item } = require("../src/gilded_rose");

const items_data = [
  new Item("+5 Dexterity Vest", 10, 20),
  new Item("Aged Brie", 2, 0),
  new Item("Sulfuras, Hand of Ragnaros", 0, 40),
  new Item("Sulfuras, Hand of Ragnaros", 4, 30),
  new Item("Elixir of the Mongoose", 5, 7),
  new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
  new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
  new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
  new Item("Conjured Mana Cake", 3, 6),
];

describe("La qualité (quality) d'un produit ne peut jamais être négative.", function () {
  it("quality always > 0.", function () {
    const gildedRose = new Shop(items_data);
    const items = gildedRose.updateQuality();
    for (let i = 0; i < items_data.length; i++) {
      expect(items[i].quality).toBeGreaterThan(0);
    }
  });
});

describe("Une fois que la date de péremption (sellIn) est passée, la qualité (quality) se dégrade deux fois plus rapidement.", function () {
  it("la qualité (quality) se dégrade deux fois plus rapidement. (-2)", function () {
    const gildedRose = new Shop(items_data);
    const items = gildedRose.updateQuality();
    for (let i = 0; i < items_data.length; i++) {
      if (items[i].sellIn < 0) {
        if (items[i].name != "Sulfuras, Hand of Ragnaros") {
          expect(items[i].quality).toEqual(items_data[i].quality - 2);
        }
      }
    }
  });
});

describe("'Aged Brie' augmente sa qualité (quality) plus le temps passe.", function () {
  it("sellIN -- quality ++ if 'Aged Brie'", function () {
    const gildedRose = new Shop(items_data);
    const items = gildedRose.updateQuality();
    for (let i = 0; i < items_data.length; i++) {
      if (items[i].name == "Aged Brie") {
        expect(items[i].sellIn).toBeLessThan(items_data[i].sellIn);
        expect(items[i].quality).toBeGreaterThan(items_data[i].quality);
      }
    }
  });
});

describe("La qualité d'un produit n'est jamais de plus de 50.", function () {
  it("quality always <= 50", function () {
    const gildedRose = new Shop(items_data);
    const items = gildedRose.updateQuality();
    for (let i = 0; i < items_data.length; i++) {
      expect(items[i].quality).toBeLessThanOrEqual(50);
    }
  });
});

describe("'Sulfuras', étant un objet légendaire, n'a pas de date de péremption et ne perd jamais en qualité (quality)", function () {
  it("quality always >= quality before update", function () {
    const gildedRose = new Shop(items_data);
    const items = gildedRose.updateQuality();
    for (let i = 0; i < items_data.length; i++) {
      if (items[i].name == "Sulfuras, Hand of Ragnaros") {
        expect(items[i].sellIn).toBeGreaterThanOrEqual(0);
        expect(items[i].quality).toBeGreaterThanOrEqual(items_data[i].quality);
      }
    }
  });
});

describe("'Backstage passes', comme le 'Aged Brie', augmente sa qualité (quality) plus le temps passe (sellIn)", function () {
  it("if 10 sellIn -> quality + 2 | if 5 sellIn -> quality + 3 | if 0 sellIn -> quality + 0", function () {
    const gildedRose = new Shop(items_data);
    const items = gildedRose.updateQuality();
    for (let i = 0; i < items_data.length; i++) {
      if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
        if (items[i].sellIn <= 0) {
          expect(items[i].quality).toEqual(0);
        }
        if (items[i].sellIn <= 5) {
          if (items_data[i].quality + 3 < 50) {
            expect(items[i].quality).toEqual(items_data[i].quality + 2);
          } else {
            expect(items[i].quality).toEqual(50);
          }
        }
        if (items[i].sellIn <= 10) {
          if (items_data[i].quality + 2 < 50) {
            expect(items[i].quality).toEqual(items_data[i].quality + 2);
          } else {
            expect(items[i].quality).toEqual(50);
          }
        }
      }
    }
  });
});
