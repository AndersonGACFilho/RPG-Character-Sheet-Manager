# RPG Character Sheet Manager
This is a simple character sheet manager for tabletop RPGs.
It allow the master manage the character sheets of the players in a simple way.
Some of the features are:
### Session
- **Create a Session:** The Master can create a new game session to organize and manage the gameplay.
- **Manage Players:** The Master can add or remove players from the session to control who is participating.
- **Manage Character Sheets:** The Master can see and manage the character sheets of both players and enemies, ensuring all relevant information is accessible and up-to-date.
- **Roll History:** The Master can view the history of dice rolls to track the outcomes of various actions.
- **Take Notes:** Both the Master and players can take notes during the session for reference.

### Character Sheets
#### Players
- **Template Sheet:** The Master sets a template character sheet that players will use as a basis for their characters.
- **Create Character Sheet:** Players can create their character sheets based on the template provided by the Master.
- **View Character Sheets:** The Master can view the character sheets of all players to monitor their progress and make necessary adjustments.
- **Manage Character Sheets:** The Master can add or remove items, spells, and skills to the players' character sheets to enhance gameplay.
- **Multi-Class:** Players can have more than one class on their character sheet, allowing for more complex and versatile characters.

#### Enemies
- **Create Character Sheet:** The Master can create character sheets for enemies to represent adversaries in the game.
- **View Character Sheets:** The Master can view the character sheets of all enemies to manage combat and interactions.
- **Manage Character Sheets:** The Master can add or remove items, spells, and skills to the enemies' character sheets to adjust difficulty and strategy.

### Non-Player Characters (NPCs)
- **Add NPC:** The Master can add NPCs to the session to enhance storytelling and interactions.
- **Remove NPC:** The Master can remove NPCs from the session as needed.
- **Player Interaction with NPCs:** Players can see the NPCs they are interacting with, providing a more immersive experience.

### Dice Rolls
- **Roll Dice:** Players can roll dice to determine the outcomes of their actions.
- **View Rolls:** The Master can see the results of dice rolls to keep track of game mechanics and fairness.

### Combat Management
- **Damage:** The Master can apply damage to players or enemies, managing health and combat outcomes.
- **Healing:** The Master can heal players or enemies, providing recovery options during gameplay.

### Template Management
- **Personalize Attributes:** The Master can personalize the attributes of the template sheet to fit the specific needs of the game or campaign.

### Creation and Customization by the Master
- **Create Classes, Abilities, Spells, Enemies, Races:** The Master has the ability to create all elements of the game, including classes, abilities, spells, enemies, and races.
- **Apply Effects:** The Master can apply effects to both players and enemies to influence gameplay.
- **Personalized Lore and Game Rules:** The Master can create and share personalized lore and game rules for players to enhance the storytelling and immersion.

This structure ensures that the Master has full control over the game elements and can create a rich, customized gaming experience for the players.
## How to run
To run the project you need to have installed:
- JDK 21
- Maven 3.8.4
- PostgreSQL 14
- React 17.0.2

1. First you need to edit the file `application.properties` in the folder `src/main/resources` and set the following properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

2. After that you need to create a database in PostgreSQL with the name you set in the `application.properties` file.

Then you need to run the following command in the root folder of the project:
```shell
mvn clean install
```

3. After that you need to run the following command in the root folder of the project:
```shell
mvn spring-boot:run
```

4. Then you need to go to the folder `src/main/frontend` and run the following command:
```shell
npm install
```

5. After that you need to run the following command in the folder `src/main/frontend`:
```shell
npm start
```

6. Then you can access the application in the following URL: `http://localhost:3000`
