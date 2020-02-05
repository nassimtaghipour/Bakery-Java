# Bakery-Java
 optimized algorithm for bakery
 
Problem:
A bakery used to base the price of their produce on an individual item cost. So if a customer ordered
10 cross buns then they would be charged 10x the cost of single bun. The bakery has decided to start
selling their produce prepackaged in bunches and charging the customer on a per pack basis. So if the
shop sold vegemite scroll in packs of 3 and 5 and a customer ordered 8 they would get a pack of 3 and
a pack of 5. 

![alt text](https://github.com/nassimtaghipour/Bakery-Java/src/photo.png)

solution:

The problem is rosolved by Dynamic Programming algorithm with time complexity of O(nm) (n refers to packets count and m refers to customer orders count) instead of recursive or other solutions with high complexity and heavy resources.

the solution contains unit tests that the first one is implemented by the dataset that was mentioned  and the second one is implemented with random count of packets and random count of orders that provides a large number of test modes (The prepared solution was tested more than 1000000 times)

Dev environment and libs:

Eclipse 4.12.0, JDK 12.0.2 & Junit 4.8.1

