# 🚢 Sea Battle Ships Counter  
### 📄 Short description:  
Sea battle is a famous 2 player game where ships are placed on 2d play field and players need to guess opponent ships locations.  
This app implements ships counting logic as well as basic play field grid validation. In our case grid is 2d int array where '1' means ship is present and '0' - ship is absent.
Any vertically or horizontally adjustment '1' count as one ship.
Here are the rules for valid grid in our version of the game:
- grid size must be at least 5x5. No upper limit.
- Only '1' and '0' are allowed.
- Ships are rectangles of any size and can be present in any amount.
- Ships can not touch or overlap.

### 📂 Project structure:
- /service/ShipCounter -contains count() method that takes grid as input and returns number of ships.
- /service/GridValidator -contains validate() method that checks if input grid is valid according to our rules. Throws InvalidGridException if not.

Additionally, count ships and grid validation logic are covered by Unit tests using JUnit5.

Enjoy 😃
