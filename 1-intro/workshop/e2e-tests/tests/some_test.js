Feature("Ynov Nantes");

Scenario("Test Ynov Nantes Land Page", ({ I }) => {
  I.amOnPage("https://www.ynov-nantes.com/");
  I.click(".icon-search");
  I.fillField("s", "Info");
});
