D. Analyse de qualité avec SonarQube for IDE

L'analyse du package web.controller avec SonarQube for IDE a détecté 49 problèmes répartis sur 9 fichiers.

Anomalie 1 : règle java

SonarQube recommande de supprimer certains enveloppements @ApiResponses lorsqu'ils sont inutiles. Cette règle concerne la manière de documenter les codes de réponse des endpoints REST. La correction consiste à simplifier les annotations concernées tout en conservant la documentation des réponses HTTP nécessaires.

Anomalie 2 : règle java

SonarQube recommande de remplacer les entités JPA utilisées directement dans les contrôleurs par des objets simples ou des DTO. L'utilisation directe des entités couple l'API REST à la structure de la base de données et peut exposer des relations entre entités. Une solution consiste à créer des DTO et à convertir les données entre les entités et les DTO. Cette refactorisation peut être reportée si le TP demande, à cette étape, d'exposer directement les entités.

Bilan

L'analyse a permis d'identifier des améliorations possibles concernant la documentation Swagger et la séparation entre la couche de persistance et l'API REST. Les résultats doivent être réexaminés après les corrections éventuelles.