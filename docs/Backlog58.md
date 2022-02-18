
# Backlog  
## Short Description  
 
**Quizzz!** is a Kahoot-like online multiplayer quiz game, centred around the 180th Anniversary Energy Challenge. Quizzz! aims to educate players about the energy consumption of various activities, such as household tasks and travelling, while providing an entertaining and competitive experience.
  
## Must-Have Features  
### As a user:  
- Must be able to connect to the game host. 
- The game will present a splash screen, with buttons for starting a game in singleplayer or multiplayer.
- I shall be able to start a singleplayer game.
- The game will show me highscores from previous games, in a leaderboard format.
- The game must present three types of text-based questions.
   - Open-ended questions (estimating the answer) that are randomised.
   - Comparing the energy usage of three activities.
   - Comparing the energy usage of one activity to three other activities:
      - (eg. "Instead of ... you could ...")
- Each round of questions will end after a certain time limit (20 sec).
- Each game should consist of 20 rounds of questions.
- The scoring system will award points based on the correctness of the answer(open questions) and the time remaining before the end of the round. The fastest answer will be rewarded with the most points.  
  
### As a developer:  
The back-end of the game shall be implemented using the REST API and Spring Boot, while the front-end must be implemented using OpenJFX. The various activities, as well as the leaderboard data will be stored in a relational database in the back-end. The game will only use one server instance.  
  
## Should-have Features  
### As a user:  
- Pressing the multiplayer button should lead to a prompt for choosing a name. The name will be checked in order to avoid more than two users with the same name.  
- After entering a username, I should be put into a "waiting room"/lobby in order to wait for more players. The waiting room will display the names of all players currently waiting to start a game.  
- After more than two players are present, I should be able to start a game. Upon pressing the start button, a countdown will start, during which more players may join.  
- Upon entering a game, questions will be displayed at the same time to all players. Additionally the countdown timer should be synchronized between all players in a game.  
- Each player will have three joker cards available for use during the course of the game. Using a card will result in it being unavailable for use for the remainder of the match.  
   - The first card will eliminate one wrong answer for the user only.  
   - The second card will award double points for the user only.  
   - The third card will halve the remaining time for all users.  
- Each question will be accompanied by a relevant image.  
- The online game will  display a leaderboard after 10 rounds.  
  
### As a developer  
The images relevant to the questions should be stored in the same relational database as the activities. There should be no limit to how many player can be in a game at once. Additionally there may be multiple games all running at the same time.  
  
## Could-have features  
### As a user:  
- I should be able to react to other users using a set of predefined emojis/text-based replies.  
- Every user should see the reactions at the same time.  
- Spamming the react button will result in a lot of reaction appearing on the screen.  
- The interface could have animations.  
- Random assortment of joker cards (with different abilities) could be available for use.  
- Sounds could play sounds for clicking on choices and for answers being correct/wrong.  
- Leaderboard could display a list of high-scores in singleplayer.  
  
## Won't-have features  
### As a user:  
- I should not be able to log-in using a stored account, or use third-party authentication.  
- I will not be able to create private rooms.  
- I will not be able to chat to other players without using the predefined reactions.  
### As a developer:
The server will not store game information (except that which is contained in the activity database)
