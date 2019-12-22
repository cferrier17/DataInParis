# Projet mobile 4A

## Corentin FERRIER - CFA - FSI2

### Présentation

Dans le cadre de notre deuxième année de cycle ingénieur à l'ESIEA, il nous a été demandé de réaliser une application sous Android de manière individuelle.

J'ai eu pour concept d'application d'exploiter l'[API Open Data de la mairie de Paris](https://opendata.paris.fr/pages/home/)  et d'afficher les données sur la localisation d'hotspots wifi dans la ville sur une carte.

#### Objectifs initiaux

Mes objectifs étaient d'utiliser ces données avec trois appels différents à l'API :
1. le cas de base : un simple appel où l'utilisateur peut choisir le nombre d'hotspots à chercher
2. l'appel va prendre en compte la position de l'utilisateur et une distance modifiable qui permet de trouver les hotspots dans la zone autour de l'utilisateur
3. le cas le plus complexe : l'utilisateur peut cliquer sur deux points de la carte et l'API ressort les hotspots présents dans le rectangle formés.

#### Application réelle
L'API que j'utilise a montré ses limites à partir du deuxième cas : quand je l'appelais elle me renvoyait toujours un même point en boucle, qui m'a forcé à développer autre chose afin d'avoir plus qu'une fonctionnalité.

J'ai donc choisi de développer un deuxième vue qui affiche le contenu renvoyé par la caméra du téléphone et de prendre des photos.

Ci-dessous des captures d'écran de l'application :

- Premier écran quand l'application est lancée. Il affiche par défaut 10 hotspots.

![screen1](https://zupimages.net/up/19/51/769v.jpg)

- Ci-dessous le même écran, mais une fois que l'utilisateur a choisi le nombre de hotspots à afficher.

![screen2](https://zupimages.net/up/19/51/i2j3.jpg)

- Ici la deuxième activité :
![screen3](https://zupimages.net/up/19/51/4g45.jpg)

### Ce que j'ai appris
1. Kotlin

J'ai developpé cette application en Kotlin, langage que je ne connaissais que de nom avant ce projet. C'est sans aucun doute le point sur lequel j'ai le plus appris et aimé apprendre durant ce développement.
Étant un grand utilisateur de Java j'ai vraiment apprécié l'élégance et la simplicité que Kotlin peut fournir par moment.
Je comprends maintenant l'engouement qu'il y a autour de langage et pourquoi il s'annonce comme le successeur de Java pour le développement Android.

2. Les fragments
Les fragments sont un point vraiment important au sein du développement Android. Ils permettent de rendre l'application facile à maintenir

3. Relative layout
Les relative layout permettent bien plus de choses que les layouts plus basiques tels que les vertical/horizontal, comme afficher des éléments au dessus d'un autre.

4. Les permissions
Les permissions permettent d'utiliser les capteurs hardware du téléphone. Dans mon cas j'ai dû les utiliser pour utiliser les coordonnées GPS (au final je n'en ai pas eu besoin à cause du changement de fonctionnalité) et pour utiliser l'appareil photo.

### Les prérequis & bonus
- [x]   Fragment

- [x]  Appel WebService à une API Rest.

-  [ ] Design

-  [x] Architecture MVC

-  [x] Git

Bonus :
- [x] Configuration du projet moderne grâce à un fichier YAML. Cela évite d'écrire en dur les url, credentials, etc .. et donc les erreurs/oublis lors des modifications.


