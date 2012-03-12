#Distributed Spell Checker

Based on [Peter Norvig's](http://norvig.com/) essay [How to Write a Spelling Correctory](http://norvig.com/spell-correct.html), this is a distributed spelling corrector. It's programmed in Java, and uses Apache's Hadoop framework. A user inputs a giant word document, and then the program generates an auto-corrected duplicate, automatically identifying and correcting misspelled words. Currently, the repository holds a non-distributed implementation, which I'll be modifying to run in a distributed fashion. For now, the model does quite poorly (it's a naive, non-contextual model):

`Words tested: 265, percent correct: 0.618868, time taken: 420 milliseconds, 0.630952 ms/word`



