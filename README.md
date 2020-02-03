# Packing Challenge 
Implementation for 0-1 Knapsack problem using Dynamic Programming.

## Algorithms
I've used dynamic programming implementation of 0-1 Knapsack problem.

### Implementation
Let's suppose the most valuable load weighs `W`. If we remove the `ith` item from this load, then the  remaining load 
should be the most valuable load weighing at most `W - w[i]` which does not include the removed item. Therefore, 
the solution for the 0-1 Knapsack problem contain an optimal sub-structure required by dynamic programming.

In order to formalize our solution, let's suppose we're going to decide to whether add the item `i` to a set of already 
optimized items. Here the term `memntoTable[i][j]` represents the maximum cost when choosing the first `i` items in a way that 
the total weight is less then `j`.

If the weight of `ith`-item is greater than the limit `j`, then we can't add this item to the group. So:
```
If w[i] > j then mementoTable[i][j] = mementoTable[i - 1][j]
```
Otherwise, we should compare the cost of adding and not adding this item and choose the one with greater
cost:
```
mementoTable[i][j] = max(mementoTable[i - 1][j], mementoTable[i - 1][j - weight[i]] + cost[i])
```
#### Multiple Solutions
If two options are having the same cost, then we should choose the one with lower weight. In our bottom-up approach, 
if each `mementoTable[i][j]` guarantees that they always choose the lighter packages in case of identical costs, 
then we can be sure that the the `mementoTable[n][w]` (actual solution) also guarantees the same thing.

### Time & Space Complexities
Since Dynamic Programming algorithms are exploiting memory-time trade-offs, we have to analyze this
algorithm from both memory and time perspectives.

The most memory consuming part of this algorithm is the memento table. Since this is a two dimensional
array, our memory complexity is:
```
O nW)
```
The most time-consuming part of this algorithm is related to the nested for loops. So by the same logic,
our time complexity is also `O(nW)`.

## Design Patterns & Best Practices
I've tried to incorporate design patterns and other best practices wherever they created a noticeable value. In the following
sections, I'm going talk about the motivation and mechanics of them.

### SOLID Principles
#### Single Responsibility
All abstractions I've used to trying to achieve one common goal and nothing more. For example:
 - The `PackParserStrategy` class is responsible for reading and parsing the file.
 - The `ItemPresenterStrategy` class is responsible for present solved items.
 - The `KnapsackSolverStrategy` class is responsible for solving algorithm.
 - The `PackerTemplate` Template that  abstracting the packer implementations.
 
#### Open-Closed
The whole library is open for extension without big change. For example, in order to add a new parser for example 
[Apache daffodil](https://daffodil.apache.org/) only need implement parser strategy and used in the `KnapsackTemplate` .
### Interface Segregation
To keep solution decoupled and thus easier to refactor, change, and redeploy, three strategy introduced that
splits interfaces that are very large into smaller and more specific ones so that clients will only 
have to know about the methods that are of interest to them and change only required parts.

### Strategy Interfaces
I've used strategy interfaces a few places. For example, we may need different implementations of Knapsack. So, we have
a `KnapsackSolverStrategy` interface with different implementations (Currently there is one implementation for 0-1 but we could
simply implement a Recursive fractional Knapsack, too.).

Moreover, in order to support different presentation logic, there is `ItemPresenterStrategy` logic with two different 
implementations.

### Template Pattern
class is a template that implemented challenge requirements such as parsing data, validation and apply algorithms.
We need implementation of these three concerns that have any strategy for each of them.
In the constructor, we can implement a new strategy and use and default constructor used pre implemented
 strategies based on requirements.          

### Clean Code
The code is extremely clean: 
 - Documented everything as thorough as possible.
 - Incorporated a lot of well-named private methods to help you better understand the code. 

### TDD Mindset
Even though it's a bit hard to tell if a project was developed using TDD or not, I've used this approach while developing.

## Tests
In order to run tests and generate JaCoCo coverage results, just issue the following command:
```bash
> ./mvnw clean verify
```
Then open the `target/site/jacoco/index.html` to see the coverage report. 

## Built With
* [JUnit 5](https://junit.org/junit5/) - The testing framework on JVM.
* [JoCoCo](https://github.com/jacoco/jacoco) - As our *Code Coverage* tool.
* [Mockito](https://site.mockito.org/)
* [Maven](https://maven.apache.org) - As our build tool.

and last but certainly not least:
* Java & JVM


## Versioning

We use [Maven Release Plugin ](http://maven.apache.org/maven-release/maven-release-plugin/index.html) for versioning.
