# PARTE TEORICA

### Arquitecturas de UI: MVP, MVVM y MVI

#### MVVM

##### ¿En qué consiste esta arquitectura?
Es un patró d’arquitectura de software dedicat a la separació de rols en diferents capes per al correcte funcionament d’una aplicació.
Les sigles MVVM signifiquen Model-View-ViewModel:
•	Model: Es la estructura que segueix la informació per a ser desada. En Kotlin son les anomenades “data class”.
•	View: Es la interfície amb la que interacciona l’usuari. En Android es representa mitjançant les Activitats i Fragments. S’encarrega de vigilar els canvis que es produeixen en el “ViewModel” assignat per actualitzar-se.
•	ViewModel: Aconsegueix la informació del “Model”, si es necessari aplica operacions i mostra l’ informació a les “Views”.


##### ¿Cuáles son sus ventajas?
•	Bona separació de rols.
•	L’informació desada en un “ViewModel” pot persistir durant canvis en la configuració o el cicle de vida de l’aplicació. Ja que romandrà en memòria fins que l’activitat o fragment al que esta vinculat sigui eliminat.
•	Gràcies a manipular totes les dades en un “ViewModel” s’aconsegueix que el “Unit testing” sigui senzill d’implementar ja que no hi ha referències a les “Views”.


##### ¿Qué inconvenientes tiene?
•	Per aplicacions en que la interfície d’usuari es senzilla potser que sigui massa complicat.

#### MVP

##### ¿En qué consiste esta arquitectura?
Es un patró d’arquitectura de software dedicat a la separació de rols en diferents capes per al correcte funcionament i la maximització de la capacitat de testejar unitàriament.
Les sigles MVP signifiquen Model-View-Presenter:
•	Model: Encarregat de la manipulació de les dades. Serà una classe independent del framework Android.
•	View: Es el punt d’entrada principal de l’aplicació. Visualització dels components amb els que interactua l’usuari. Estendrà de “Activity” o “Fragment” del Framework d’Android. Comunica al “Presenter” de les accions del usuari mitjançant interfícies.
•	Presenter: Executa qualsevol operació necessària per processar les dades que provenen del “Model” per a que la “View” ho pugui representar. Serà una classe independent del framework Android. També ordena al “Model” com ha d’actualitzar les dades.


##### ¿Cuáles son sus ventajas?
•	Bona separació de rols.
•	Es poden fer “JUnit test” ja que els “Presenter” i “Model” no estenen cap classe d’Android.


##### ¿Qué inconvenientes tiene?
•	Necessitat d’implementació d’interfícies.

#### MVI

##### ¿En qué consiste esta arquitectura?
Es un patró d’arquitectura de software unidireccional i cíclic.
Les sigles MVI signifiquen Model-View-Intent:
•	Model: Representa un estat inmutable de l’aplicació per assegurar la unidireccionalitat. Quan es fa algun canvi, es crea un altre model amb el seu nou estat. Per a reduir el transit de dades del nou model es fan servir “State Reducers” que unifiquen la informació de l’estat passat i el nou en un “accumulator”.
•	View: Representada per una interfície.
•	Intent: Es una intenció del usuari de fer una acció determinada, serà observada per el “Presenter” i convertida en un nou estat del “Model”. No te res a veure amb “Intent” del framwork Android.


##### ¿Cuáles son sus ventajas?
•	Circulació de dades de forma unidireccional i cíclica.
•	Un únic estat per a tota la vida útil de les “View”.
•	Un comportament estable gràcies a que els “Model” són immutables i seguretat davant del diferents “threads” en aplicacions grans.


##### ¿Qué inconvenientes tiene?
•	La corba d’aprenentatge per implementar aquesta arquitectura es molt pronunciada.

---

### Testing

#### ¿Qué tipo de tests se deberían incluir en cada parte de la pirámide de test? Pon ejemplos de librerías de testing para cada una de las partes. 
La realització de testos per comprovar que l’aplicació funciona correctament es molt important per a que l’usuari tingui una experiència positiva.
Podem dividir els testos en tres categories:

Testos petits 
•	Haurien de ser el 70% del total de testos.
•	Son testos unitaris que es poden realitzar de manera independent d’un emulador o dispositiu físic.
•	Comproven el comportament d’ un component en concret.
•	Són els més ràpids de comprovar.
•	Les eines mes utilitzades son “JUnit” i “Mockito”.

Testos mitjans
•	Haurien de ser el 20% del total de testos.
•	Son testos d’integració per comprovar que el codi interactua correctament amb altres parts del Framework d’Android.
•	Es realitzen a continuació dels testos petits si han sigut correctes.
•	Una eina molt utilitzada es “Roboelectric”.

Testos grans
•	Haurien de ser el 10% del total de testos.
•	Son testos que simulen el comportament de l’usuari i comproven els resultats.
•	Es realitzen a continuació dels testos mitjans si han sigut correctes.
•	Són els més lents i cars de comprovar ja que es necessita un emulador o dispositiu físic.
•	Una eina molt utilitzada es “Espresso”.


#### ¿Por qué los desarrolladores deben centrarse sobre todo en los Unido Tests?
Les raons per a que els desenvolupadors es centrin en els “Unit Test” són:
•	Molt més ràpids d’executar que qualsevol altre tipus.
•	No es necessari cap llibreria de testos específica del sistema Android, només es necessita la llibreria de test de Java anomenada “JUnit”.
•	S’assegura una base de funcionament correcte per a l’aplicació.
Si les diferents parts del codi funcionen correctament per separat, es molt probable que quan s’agrupin també funcionin correctament.


---

### Inyección de dependencias

#### Explica en qué consiste y por qué nos ayuda a mejorar nuestro código.
La injecció de dependències consisteix en proporcionar els objectes que siguin necessària a la classe per al seu funcionament en comptes de que la mateixa classe hagi de crear instancies noves d’aquests objectes.
Ens ajuda a millorar el codi perquè:
•	Millorar la re-usabilitat de la classe a la que fem injecció.
•	Ens permeteix fer “Unit Test” a la classe, ja que podem proporcionar-li objectes “mock” durant el test.


#### Explica cómo se hace para aplicar inyección de dependencias de forma manual a un proyecto (sin utilizar librerías externas).
Primerament hem de crear els objectes necessaris per a continuació crear un objecte de la nova classe que necessita aquests objectes com a dependència.
Exemple de la classe d’una biblioteca:
1.	Creem varis objectes de la classe “Llibre.”
val llibre1 = Llibre(“Java Basics”)
val llibre2 = Llibre(“How to cook something”)
val llibres = {llibre1, llibre2}

2.	Creem un objecte de la classe ”Edifici”.
val edifici  = Edifici(“Carrer Primavera numero 56”)

3.	Finalment creem un objecte de la classe “Bibilioteca” passant com a paràmetres la llista de llibres i l’edifici.
Val biblioteca = Biblioteca(llibres,edifici)
