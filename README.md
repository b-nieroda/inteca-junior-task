# inteca-junior-task
Bartłomiej Nieroda - zadanie rekrutacyjne na stanowisko Junior Java Developer

Opis:
Projekt zawiera trzy moduły aplikacji (credit, product, customer).
Mikroserwisy komunikują się ze sobą przez REST.
Aby wygenerować unikalny numer kredytu aplikacja Credit odpytuje bazę o wolne id.
Model bazy danych został zaimplementowany zgodnie z treścią zadania.
Użyta baza: H2. Przykładowe zapytania do zaimportowania w programie Postman - pliki: CreateCredit.postman_collection.json oraz GetCredits.postman_collection.json.

Czego nie udało się zrobić:
- wyciągnąć wspólne modele do osobnego modułu;
- dodać obsługę błędów;
- dodać testy;
- rozmieścić komponenty jako kontenery Docker;

Uwagi:
Według mnie model bazy danych można byłoby zmienić na przedstawiony w pliku model_bazy.png. Tabela Credit zawiera id klienta, który złożył kredyt oraz id produktu. W ten sposób nie będzie zduplikowanych klientów oraz produktów.
