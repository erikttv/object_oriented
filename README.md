# Delegering - The Office-oppgave

Denne oppgaven bruker delegeringsteknikken for å modellere arbeidsfordeling på en “vanlig” arbeidsplass. Denne oppgaven kan muligens oppleves som mindre meningsfull. Dette er kanskje omtrent tilsvarende hvor meningsløst noen typer kontorarbeid kan virke.

Vi skal i dette scenarioet ha en sjef, eller **Manager**, som har én eller flere arbeidere, eller **Clerk**s, altså i en såkalt én-til-mange relasjon. Et **Employee**-grensesnitt definerer en oppførsel som er felles for de ansatte, og implementeres av både **Manager** og **Clerk**.

**Employee**-objekter på denne simulerte arbeidsplassen har to oppgaver:

- utskrift av dokumenter
- utførelse av matematiske beregninger

## Del 1: Employee, Clerk og Printer

**Employee**-grensesnittet har følgende metoder:

- `double doCalculations(BinaryOperator<Double> operation, double value1, double value2)` - regner ut resultatet av å utføre **operation** med argumentene **value1** og **value2**.
- `void printDocument(String document)` - Printer **document**. Hvordan dette gjøres avhenger av den spesifikke implementasjonen.
- `int getTaskCount()` - returnerer hvor mange oppgaver (beregninger og printinger) som har blitt utført av eller på vegne av dette **Employee**-objektet.
- `int getResourceCount()` - antallet employees til rådighet, inkludert **Employee**-objektet metoden blir kalt på. En **Employee** skal altså medregne seg selv i antall ressurser den ansatte har til rådighet. Dette tallet skal inkludere alle **Employee**-objekter nedover i hierarkiet.

Lag dette grensesnittet, og lag så en **Clerk**-klasse som implementerer det. **Clerk** må ha følgende konstruktør:

- `Clerk(Printer printer)`

**Clerk**-klassen må inneholde egen logikk for å løse **doCalculations**, men skal altså delegere **printDocuments** til **Printer**-objektet gitt i konstruktøren.

Definer en **Printer**-klasse med følgende metoder:

- `void printDocument(String document, Employee employee)` - skriver documentet til konsollen og tar vare på dokumentet i **employee** sin historikk.
- `List<String> getPrintHistory(Employee employee)` - returnerer en **List<String>** med alle dokumentene som har blitt printet av **employee** av denne printeren i rekkefølgen de har blitt printet. Om **employee** ikke har printet noen dokumenter ved denne printeren skal en tom liste returneres.

La så **Clerk** delegere **printDocument** til **Printer**. Siden **Clerk** ikke har noen andre ansatte å delegere til, vil **getResourceCount()** alltid være 1.

Testkode for Clerk er her: [delegation/office/ClerkTest.java](../../tests/patterns/delegation/office/ClerkTest.java) Originalkoden for testen(jextest) kan du finne her: [delegation/office/Clerk.jextest](../../tests/patterns/delegation/office/Clerk.jextest)

Testkode for Printer er her: [delegation/office/PrinterTest.java](../../tests/patterns/delegation/office/PrinterTest.java) Originalkoden for testen(jextest) kan du finne her: [delegation/office/Printer.jextest](../../tests/patterns/delegation/office/Printer.jextest)

## Del 2: Manager

Vi definerer så sjefen til de hardt-arbeidende **Clerk**-objektene. **Manager**-klassen har følgende konstruktør:

- `Manager (Collection<Employee> employees)` - utløser et **IllegalArgumentException** dersom employees er tom.

La så **Manager** implementere **Employee**-grensesnittet. Implementer **Manager**s oppgaver ved å delegere alle videre til en av arbeiderne i listen med **Employee**-objekter gitt i konstruktøren. Regelen for hvilken **Employee** som får hvilken oppgave delegert til seg kan du bestemme selv, men prøv å gjøre det slik at arbeidet fordeles jevnt på alle. Mens **Clerk** altså har kun én tilgjengelig ressurs vil **Manager**-objekter vil ha flere.

Testkode for Manager er her: [delegation/office/ManagerTest.java](../../tests/patterns/delegation/office/ManagerTest.java) Originalkoden for testen(jextest) kan du finne her: [delegation/office/Manager.jextest](../../tests/patterns/delegation/office/Manager.jextest)

## Del 3: Main-metode

Lag en main-metode som illustrerer hva som skjer med effektiviteten når vi legger til flere nivåer med mellomledere.

Lag først et **Manager**-objekt som blir tildelt noen **Clerk**-objekter under seg. Presentér deretter effektiviteten av hierarkiet ved å skrive ut `getTaskCount() / getResourceCount()` for **Manager**-objektet. Vis deretter hvordan effektiviteten faller når vi legger til nivåer med mellomledere ved å lage to eller flere nivåer med **Manager**, hvor lederne på bunnen tildeles **Clerk**-objekter, og skriv ut den nye effektiviteten for topplederne.

**Exercise-panelet**

Bruk av Exercise-panelet er obligatorisk for denne øvingen. Du må ha panelet åpent med **Office.ex**-filen (**tests > patterns.delegation.office > Office.ex**) i før du begynner med oppgaven. For mer informasjon/hjelp, se nederst på forrige side, altså hovedsiden for [Øving 8](./README.md).

