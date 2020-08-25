The game is played on a square board and the player has to click on the cells which do not have a mine. And obviously he doesn't know where the mines are. If a cell where a mine is present is clicked then he loses, else he is still in the game until all safe cells are revealed.

There should be three levels for playing this game:
1. Beginner – 9 * 9 Board and 10 Mines
2. Intermediate – 16 * 16 Board and 40 Mines
3. Advanced – 24 * 24 Board and 99 Mines

At the beginning of the game, the user should be able to select the level of difficulty of the game. Once the level is chosen, the player should be able to enter a position on the board. Make sure that the first move of the player is always safe and not a mine!

If the user steps on a mine,  he loses the game and a proper message should be displayed. Else if he steps on a safe cell and there is at least a single adjacent mine to this cell, then that count is displayed on the current cell. Else if there are no adjacent mines to this cell, recursively step on all the safe adjacent cells (hence reducing the time of the game-play). If the user steps on a cell having no adjacent mines (in any of the surrounding eight cells) then all the adjacent cells are automatically cleared.

The user keeps on playing until he steps on a cell having a mine (in this case the user loses) or if he had clicked/stepped on all the safe cells (in this case the user wins).
