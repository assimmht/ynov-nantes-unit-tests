Feature("Saisir nouveau Items");

Scenario("Test Saisir nouveau item", ({ I }) => {
  I.amOnPage("http://127.0.0.1:5000/");
  I.fillField("input", "Coucou les musulmans!");
  I.click("#create-todo");
});

Feature("Voir titre");

Scenario("test voir titre", ({ I }) => {
  I.amOnPage("http://127.0.0.1:5000/");
  I.see("What do I need to do?");
});
