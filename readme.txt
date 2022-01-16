GUIHARD Fabien
HOMERY Axel 

                                           Binding of Isaac:
                                           
Tout d'abord, notre jeu reprends les bases du jeu originale avec Isaac en tant qu'hero, pouvant se déplacer dans differentes salles avec au moins 1 magasin, une salle boss et 2 salles monstres dans la carte. Le hero gagne si il a tué le monstre et perd si il n'a plus de vie. 

Nous avons utilisé le polymorphisme dans l'ensemble de notre projet afin de simplifier l'ensemble du code. En effet, l'implementation des monstres suit ce procédé car à partir de la classe mère 'Monstre', nous avons généré 3 classes filles correspondant aux 3 différents types de monstres (Fly, Spider, Gaper).
L'implementation des differentes Rooms suit exctement le même principe, avec 3 classes filles (ShopRoom, MonsterRoom, BossRoom, Spawn) à partir de la classe mère 'Room'.

D'un autre côté, nous avons créer une classe mère 'StaticObject' representant l'ensemble des entités statique, c'est-à-dire les obstacles (Rock et Spikes). Les Spikes inflige des degats lorsque Isaac passe à travers et les Rpck bloque simplement son passage. 

//pickableObject séparement

Dans l'ensemble de notre code, nous avons évité au maximum de hardcoder nos valeurs et de plutôt les définir dans différentes classes du package 'resources' comme GaperInfos, ObjectInfos etc... Cela permet notamment d'avoir une meilleur compréhension globale du code et ainsi permettre un débuggage efficace. 

//TODO: process de reflexion collision(map, entité, tear) 


//TODO: creation dongeon (arbre) 
Nous voulions implémenter un arbre N-aire afin de générer le dongeon //idee de base 



En ce qui concerne les textures, nous avons sélectionné un texture à l'adresse suivante 'https://www.spriters-resource.com/pc_computer/bindingofisaacrebirth/sheet/146176/?source=genre' que nous avons ensuite sélectionner et redimensionner à l'aide d'un outil d'édition et de retouche d'image (GIMP)

Par ailleurs, nous avons ajouté une fonctionnalité son dans notre jeu. En effet, il suffit de supprimer les commentaires dans la boucle principale du jeu se situant dans la classe Main pour bénéficier d'une musique de fond ou/et d'un message de bienvenue en français ou anglais au choix.



