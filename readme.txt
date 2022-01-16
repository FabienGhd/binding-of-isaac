GUIHARD Fabien
HOMERY Axel 

                                           Binding of Isaac:
                                           
Tout d'abord, notre jeu reprends les bases du jeu originale avec Isaac en tant qu'hero, pouvant se déplacer dans differentes salles avec au moins 1 magasin, une salle boss et 2 salles monstres dans la carte. Le hero gagne si il a tué le monstre et perd si il n'a plus de vie. Le hero peut tirer des larmes dans les 4 directions à l'aide des fleches directionnelle.

Nous avons utilisé Git et Github nous permettant de partager notre code facilement et d’avoir un suivi clair de l’évolution du projet. Cela nous a aussi permis de bien répartir les tâches afin de gagner du temps.
Le nombre de classes a implémentées était important donc nous avons réalisé un UML résumant les attributs, méthodes et la hiérarchie.

Nous avons naturellement utilisé le polymorphisme dans l'ensemble de notre projet afin de simplifier l'ensemble du code. En effet, l'implementation des monstres suit ce procédé car à partir de la classe mère 'Monstre', nous avons généré 3 classes filles correspondant aux 3 différents types de monstres (Fly, Spider, Gaper).
L'implementation des differentes Rooms suit exctement le même principe, avec 3 classes filles (ShopRoom, MonsterRoom, BossRoom, Spawn) à partir de la classe mère 'Room'.

D'un autre côté, nous avons créer une classe mère 'StaticObject' représentant l'ensemble des entités statiques, c'est-à-dire les obstacles (Rock et Spikes). Les Spikes infligent des degats lorsque Isaac passe à travers et les Rock bloque simplement son passage. 
Nous avons bien différencier les objets statiques des objets pickables à l'aide de classes distinctes. 

Le nombre, le type et le spawn des monstres est généré aléatoirement dans les salles de monstres.




Dans l'ensemble de notre code, nous avons évité au maximum de hardcoder nos valeurs et de plutôt les définir dans différentes classes du package 'resources' comme GaperInfos, ObjectInfos etc... Cela permet notamment d'avoir une meilleur compréhension globale du code et ainsi permettre un débuggage efficace. 

//TODO: process de reflexion collision(map, entité, tear) 


//TODO: creation dongeon (arbre) 
Pour la génération procédurale des rooms, notre idée de base était d'implémenter un arbre N-aire afin de générer le dongeon. 
Suite à de nombreux soucis de programmation sur ce sujet, nous avons au final décider d'implementer une map statique avec 2 salles de monstres, 1 magasin et la salle de boss. Cette implementation statique à été realisé à l'aide d'un arbre comme étuidé en cours de PO. 

Nous avons notamment utilisé les differentes collections Java (surtout ArrayList) pour représenter beaucoup de fonctinnalité comme les portes, les monstres, les objets etc... 

En ce qui concerne les textures, nous avons sélectionné un texture à l'adresse suivante 'https://www.spriters-resource.com/pc_computer/bindingofisaacrebirth/sheet/146176/?source=genre' que nous avons ensuite sélectionner et redimensionner à l'aide d'un outil d'édition et de retouche d'image (GIMP)

Par ailleurs, nous avons ajouté une fonctionnalité son dans notre jeu. En effet, il suffit de supprimer les commentaires dans la boucle principale du jeu se situant dans la classe Main pour bénéficier d'une musique de fond ou/et d'un message de bienvenue en français ou anglais au choix.



