# Noise Seeker

Noise Seeker is an algorithm which aims at exploring potential order in noise and chaos. More likely than not this project
is utterly useless, but the underlying questions raised are nevertheless interesting:

- What are the properties of humanly recognizable 'noise'?
- What are the properties of humanly recognizable patterns?
- What makes an image more recognizable as 'anti-noise', than others?

Given our field of vision there exists an upper boundary for how many 'pixels' we can perceive. If, for the sake of simplicity,
we assume that all humans perceive the same amount of 'pixels' and 'ray colors', there must exist a finite number of how many
images we can sense with our eyes. Let's assume this number is denoted by X. What percentage of X is recognizable as something of
our own world? E.g. lines, circles, or even pictures of humans or houses? Furthermore, can we develop an algorithm to a) detect
images that are recognizable by humans as something not made by a computer, and b) does it exist a pattern of distribution between pictures
that are humanly recognizable? Although no parallels can be drawn, a similar question exists for the distribution of prime numbers.

Early Results
--------------

To start off with some sort of benchmark. Consider this randomly generated image:

![Random noise](https://github.com/chridahl/noiseseeker/blob/master/imgs/test-999999.png?raw=true "Random noise")

I'm sure most people will get fairly little enjoyment out of watching that picture. The next image is also generated,
but has been through a small fitness function:

![Shapeish 1](https://github.com/chridahl/noiseseeker/blob/master/imgs/test-66781182.png?raw=true "Shapeish 1")

The image holds little resemblance to anything in the real world, however we can say that it 'feels less noisy' to look at.
It also looks less 'random'. Now for the final generated image:

![Shapeish 2](https://github.com/chridahl/noiseseeker/blob/master/imgs/test-89462.png?raw=true "Shapeish 2")

It certainly looks more recognizable to something unknown than compared to the other two images.