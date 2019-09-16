# Hanlon-Simulator
Simple 2D game coded with only native Java libraries
[https://www.youtube.com/watch?v=VPmtIVExQHU&feature=youtu.be]
This is a proof of concept game to experiment with the capabilities of Java.
It doesn't really have a story, other than you are trapped inside cyberspace

# Level Loading
The level loading algorithm depends on an image, each pixel of that image represents a 32x32 pixel space
on the actual game window. It goes through each pixel of the small images and depending on the color of
the pixel, it will place the corresponding gameObject.


# Graphics
The main game is made up of basic sprites taken from a central sprite sheet, it uses
the awt libraries.

The base textures use very little computer resources, however the lighting is not very
efficient. The way lighting works in this game is by using a java class called AlphaComposite and
semi-transparent ovals, where the opacity increases as the radius increases. When a level is
loaded there is an lightmap image which is the same as the level images, except each lighting source
is represented by a white pixel. Then a completely opaque black rectangle is rendered. Then a class
called LightHandler is called, that goes through and creates the new lightingObjects where they are needed
after going through the lightmap image in the same way a level image is read. Each lightingObject is an oval
object that uses AlphaComposite to change the opaqueness as you get farther away from the center. The lightmap
is rendered once when a level is loaded and simply placed on top of every frame rendered. AlphaComposite
and the native Java libraries are not efficient enough to do dynamic lighting which is why I only render
the lightmap once. When I tried to implement dynamic lighting with more than a few light sources, the game
would become unplayable. I havent found a fix for this other than rewriting the whole game using an OpenGL
framework.

# Enemy Actions
Two types of enemies are currently in the game.
One type are enemies that chase the player and do damage by physically touching the player, the other enemies
are turrets that will fire when the player passes in front of them.

The chasing enemies have weak pathfinding due to the reliance of the built in pixel grid for their path
finding. It simply calculates where the player is and moves in the x and y direction towards the player, if an
obstacle is encountered then the enemy will move parallel to the object in the direction of the player. This gets
gets tricky to manage when the enemy sprite arrives at a corner so the levels are designed around that. In a 
better pathfinding implementation, I would use a hashmap to represent a 32x32 pixel node on the screen, and then
use a pathfinding algorithim so that the enemy can find the player, and navigate past obstacles.

Turret enemies are very simple, they check whether the player is in front of them and just shoot a projectile.

# GameObjects

Every object in the game is in one central arraylist of GameObjects, every tick and render cycle, the handler goes 
through the arraylist and calls .render() and .tick() on each object. Every object simply extends GameObject,
which is why they can all be held in a single arraylist and have those methods called on them.


