# mentoring-interview-questions

## OOP
* What are the main features of OOPs
    * Inheritance
    * Encapsulation
    * Polymorphism
    * Data Abstraction
* How do you express an ‘is a’ relationship and a ‘has a’ relationship or explain inheritance and composition
*  Which one to favor, composition or inheritance

## JAVA

* Abstract class
* Interface
* Difference between Interface and Abstract class
* What is the method signature
* What is method overloading
* What is method overriding
  * What are the rules for access modifiers and exception declarations when overriding a method
* Access modifiers
  * What access modifier do interfaces have
  * What kind of methods can you have in an interface
      * public (no body), static, default
* Final keyword, where can it be applied and what does it mean
* What is the static keyword and where can it be used
  * method, field, nested class, block
*  What is an exception
    * Ecxeption vs Error
* What is a try catch block
    * What is a finally block
    * What is try-with-resources
* String, StringBuffer and StringBuilder
* Difference between Set and List
* What is a Map
* Hierarchy of list
    * List->collection->Iterable
* Benefit of HashMap vs List
* Equals contract
  * reflexive: an object must equal itself
  * symmetric: x.equals(y) must return the same result as y.equals(x)
  * transitive: if x.equals(y) and y.equals(z) then also x.equals(z)
  * consistent: the value of equals() should change only if a property that is contained in equals() changes (no randomness allowed)
* HashCode contract
  * the value of hashCode() may only change if a property that is in equals() changes
  * objects that are equal to each other must return the same hashCode
  * objects that are not equal to each other may or may not return the same hashCode
* Given an array of strings strs, group the anagrams together. You can return the answer in any order. An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.


## UNIX

* Usefule commands in Unix shell
  * mkdir make a directory
  * ls list contents of current directory
  * delete
      * rm file
      * rm directory (only if directory is empty)
      * rm -r directory (delete directory and everything inside it recursively)
* What is piping
    * Piping is a way to redirect the standard output of one command to another command for further processing, and it is represented by | operator.
    * Example: Print line in a file that contains a word
        * `cat file.txt | grep word`
* What chmod, chown and chgrp commands do
    * chmod	It can change the permission set of the file
    * chown	Change the ownership of the file
    * chgrp	Change the group of the file
        * Owner permissions − The owner's permissions determine what actions the owner of the file can perform on the file
        * Group permissions − The group's permissions determine what actions a user, who is a member of the group that a file belongs to, can perform on the file.
        * Other (world) permissions − The permissions for others indicate what action all other users can perform on the file
        * read (r), write (w), execute (x)
    * example of permissions
        * rwx r-- --x : Owner can do anything, group can read only and everyone else can execute
        * number equivalents
            * rwx r-- --x === 741
            * r-x rw- --- === 560
            * rwx rwx rwx === 777 (open to everyone)
            * rwx -w- -wx === 723
* What is && & and ;
    * && execute following command if and only if the previous succeeds
    * & execute command in background thread
    * ; chain command regardless of success
* What is the command to fetch first 10 records in a file
    * sed -n 1,10p interview.md
    * tail

## SQL

* What is a primary key
* What is a foreign key and when is it used
* What are indexes and why are they used
* What are contraints
* What kinds of JOINS exist
* What is normalisation, why use it
* What is the difference between `where` and `having`
* What is `count`, `avg`, etc and how are they used
* What is LIMIT

