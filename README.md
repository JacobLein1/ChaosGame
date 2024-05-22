# **IDATT2003 - Programmering 2**

**Prosjekt beskrivelse -**
Denne applikasjonen generer og viser kaos spill ved hjelp av matematiske transformasjoner. Applikasjonen genererer Sierpinski-trekanter, Julia mengder og Barnsley-bregn i et visuelt brukergrensesnitt, programmert i Java.

[Repository] https://gitlab.stud.idi.ntnu.no/programmering-2-gruppe-26/chaos-game-3

Hvordan kjøre programmet - 
For å kjøre programmet, last ned prosjektet fra gitlab og åpne prosjektet i en IDE som støtter Java. Kjør mvn javafx:run i terminalen for å kjøre programmet.
For å kjøre testene til programmet så kan du lokalisere testene i mappen /chaos-game-3/src/test og kjøre testene derfra.

## **Brukermanual -**

Alle sidene inneholder en "Show" knapp som viser bildet som blir generert, og en "Save image" knapp som lagrer bildet som blir generert på din pc.
"Steps"-feltet forventer et heltall og representerer antall steg som blir tatt for å generere bildet. (antall piksler som blir generert)
Om du vil tilbake til hovedsiden trykk "Home". Fyll inn min- og maksverdier for x og y for å se hvordan fraktalet blir generert innenfor disse verdiene.

**Affine2D side -** 
Genererer en forhåndsdefinert Sierpinski trekant.

**Julia side -**
Genererer en forhåndsdefinert Julia mengde. 

**Barnsley side -**
Genererer en forhåndsdefinert Barnsley bregn.

## **Create Fractal sider -**
Sidene gir bruker mulighet til å generere egne fraktaler ved å legge til transformasjoner. Min.punkter bør være mindre enn maks.punkter for å unngå feil.

**Create Affine Transformation side -** 
Genererer en Sierpinski trekant basert på brukerdefinerte verdier.
Legg inn vektorer og matrise og trykk "Save Transformation" for å lagre en transformasjon. Det er mulig å lagre flere.
Trykk på save file for å lagre transformasjonene i en .txt fil på din pc.

**Create Barnsley Transformation side -**
Genererer en barnsley bregn basert på brukerdefinerte verdier.
Legg inn vektorer og matrise og trykk "Save Transformation" for å lagre en transformasjon. Det er mulig å lagre flere.
Trykk på save file for å lagre transformasjonene i en .txt fil på din pc.

**Create Julia Transformation -**
Genererer en Julia mengde basert på brukerdefinerte verdier.
Verdiene til den komplekse konstanten bør være innenfor intervallet [-2, 2] gi spennende fraktaler.
Legg inn reell og imaginær del og trykk "Save Transformation" for å lagre en transformasjon. Det er ikke mulig å lagre flere transformasjoner.
Trykk save as file for å lagre transformasjonen i en .txt fil på din pc.

**Upload File** 
Last opp en .txt fil med transformasjoner for å generere et fraktal. Trykk "Show" for å vise bildet.

 Filen må være på formatet: (Julia kan byttes ut med Affine2D)

Julia               # Type of transform
-1.6, -1            # Lower left
1.6, 1              # Upper right
-.74543, .11301     # Real and imaginary parts of the constant c



