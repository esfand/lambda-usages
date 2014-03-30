# Get all countries using Java SE 8 Locale #

By: [
Bruno Borges
](http://blog.brunoborges.com.br/2014/03/get-all-countries-using-java-se-8-locale.html)

I saw this blog post [
Get all the country using Java Locale List
](http://www.javatutorialscorner.com/2014/02/get-all-county-using-java-locale-list.html) 
and then I thought about posting something similar, but using Lambda and the 
Stream API of Java SE 8.

Here's my "fork", including a call to sort the locales based on "display country"
property.

to execute the program simply type:

```bash
mvn clean compile exec:java
```
