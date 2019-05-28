# Celestial-Movement-Simulator
My "Introduction to Computer Science Ⅱ Lab"(計算機實習) Java final project.
In this project, any of "University" means "Universe", it's my stupid spelling error.
"Ball" means "Astronomical".

How to use?

1.
Upper right corner's button can stop or play the animation.

On its left side, has a text show how many balls you created,
more specifically, how many balls in Universe's ArrayList.
and I limit it below 170, because if the ball number higher than this amount, 
then the program may slow down, and shut down.

Under the play/stop button, has a reset button, 
clicking it can make all sliders become an initial state.
And making all balls disappear.

2.
The lower right corner has a slider and four buttons,
these buttons can move the user's view,
and the slider can change how far can you move.

On its upper position, has two sliders, 
left side one can zoom in the whole window, on the other word, anything's distance shortens.
its also can zoom out the whole window, on the other word, whole thing's distance lengthens.
right side one can make only balls bigger or smaller.

3.
Lower left corner has three radio button, click on any one can change to their unique mode.
According to which button(or mode), you choose, will showing the corresponding toolbox.

4.
a.Create Mode:
This mode toolbox will show you three slider, Mass slider can change your ball's Mass,
V: X slider can change the x component velocity of your ball,
V: Y slider can change the y component velocity of your ball,

after changing your attributes, 
click on Mouse primary button, will create a ball having above attributes.

But if you click on Mouse secondary button, different from origin attributes,
Your ball's mass would be multiplied thousand.

b.Time Stop Mode:
This mode toolbox has one slider, this slider can change the Time Stop Radius.
Click on Mouse primary button,
any one's distance with cursor position less than Time Stop Radius,
will stop any movement.

Click on Mouse secondary button, Time Stop Radius will change to five times.

c. G/R force mode
This mode toolbox has one slider, this slider can change Force.
Click on Mouse primary button,
Cursor position will generate a gravitational force, its scalar is according your Force.
Click on Mouse secondary button,
Cursor position will generate a Repulsive force, its scalar is according your Force.

5.BlackHole
When a ball's Mass more than 10000000, it will become blackhole, it is unmovable, and its radius multiplied ten. 
