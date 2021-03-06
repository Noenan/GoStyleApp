# GoStyleApp
 Application affichant des promotions et permettant de scanner un qrcode

**Version 1.0.0**

Notre stack technique pour la réalisation de ce projet se compose du framework android studio avec pour backend
le backend as a service Firebase.

## Pourquoi du développement natif (Android)
Aujourd'hui beaucoup de méthodes existent permettant de faire des applications hybrides.
Mais nous avons préféré partir sur du natif car il permet de pouvoir utiliser avec beaucoup
de flexibilité et de facilité les ressources du téléphone comparé aux plateforme hybrides bien que permettant
de gagner en délai et en coût.
Avec le développement d'applications mobiles natives, l'application est créée et optimisée 
pour une plate-forme spécifique. En conséquence, l'application affiche un niveau de performance 
extrêmement élevé. Les applications natives sont très rapides et réactives car elles sont conçues 
pour cette plate-forme spécifique et sont compilées à l'aide du langage de programmation de base 
des plates-formes et des API. En conséquence, l'application est beaucoup plus efficace. 
L'appareil stocke l'application permettant au logiciel de tirer parti de la vitesse de traitement 
de l'appareil. Lorsque les utilisateurs naviguent dans une application mobile native, 
le contenu et les éléments visuels sont déjà stockés sur leur téléphone, ce qui signifie 
que les temps de chargement sont rapides.

## Pourquoi Firebase 
La création d'une application ou d'un site Web peut sembler à première vue être un processus 
très exhaustif impliquant des investissements importants en temps et en argent. 
Cependant, il existe de nombreux services qui peuvent faciliter le travail de développement 
de diverses manières. Firebase en fait partie. Il offre plusieurs modules assez interessants facilitant
le développement backend. Google le décrit comme un  backend-as-a-service (BaaS). Parmi ces différents services ceux que nous utilisons sont :

 - **Real-time Database** <br/>
La base de données en temps réel est une base de données hébergée dans le cloud. Les données sont stockées au format JSON et synchronisées en continu avec chaque client associé. 
Lorsque vous créez des applications multiplates-formes avec les SDK iOS, Android et JavaScript, la plus grande partie de la demande de vos clients est basée sur une instance de base de données en temps réel et obtient par conséquent des mises à jour avec les données les plus récentes. 
En utilisant cette fonctionnalité de Firebase, il n'est pas nécessaire de créer votre propre base de données ou votre propre API, Firebase gère tous les composants qui accompagnent généralement la création d'un backend pour les applications. Il fournit un langage de règles adaptable basé sur l'expression pour définir comment vos données doivent être organisées et quand les informations peuvent être lues ou composées.
 - **Firebase Analytics** <br/>
Ce module permet de connaître les statistiques sur le nombre d’utilisateurs de votre application, 
du nombre d’heure passé sur l’application, des interfaces sur lesquelles les utilisateurs
passent plus de temps. Des informations assez utiles à des fins de marketing

- **Storage** <br/>
Il est conçu pour les développeurs d'applications qui ont besoin de stocker et de diffuser du contenu
 généré par l'utilisateur, par exemple des photos ou des vidéos. 
 Il permet des transferts et des téléchargements de documents sécurisés pour les applications Firebase,
  quelle que soit la qualité du réseau. V
  ous pouvez l'utiliser pour stocker des images, du son, des vidéos ou tout autre contenu généré 
  par l'utilisateur. Firebase Storage est soutenu par Google Cloud Storage, 
  un service de stockage d'objets performant, basique et rentable.

Vous pouvez voir via ce lien, consultez d'autres modules assez interessants qui rendent ce Baas très puissant [Firebase] (https://firebase.google.com/)
## Prérequis pour exécuter le projet 
 - **JDK**
 - **Android Studio**
 - **minSdkVersion 19** 
 
 ## Comment exécuter le projet
 - importer le projet dans votre android studio
 - appuyer sur le bouton run pour exécuter le projet


## Librairies externes utilisées 
- Pour faire des animations graphiques : [Airbnb Lottie] (https://airbnb.io/lottie/#/)
- Pour afficher des images  : [Picasso] (https://square.github.io/picasso/)



## Contributeurs
- AGUESSY Dodji 
- LECOMPTE Quentin
- NANDKISORI Noelandre
- VINUALEZ Clément