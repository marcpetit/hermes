# Warning !

Hermes is :
- A toy project created as a working base for software architecture lessons.

Hermes **IS NOT** :
- A working product to be used for actual specification work.
- An example of how software development should be done.

# Hermes

Once the previous statements are clear, here is a presentation of what Hermes would be, if it was an actual project.

## A specification tool

While not complete or really useful in any way, Hermes is a specification tool. It defines the notion of **product** which can be specified. Each product is progressively defined by **milestones** which have their own specification. This is done by creating **requirements** which are associated to one or more milestones. In order to keep things tractable even when a (rather) large number of requirements is defined, those are classified under a tree of **categories**. The categories, while they could probably reused in a real context (at least by using standards such as the ISO-25010), are defined per product.

## A Work in Progress

Many versions of Hermes will become available while the project progresses. Since the goal is not to actually use this project for specification but to work on it for training, it is important to get the right version when working on a specific topic. Please be cautious about it, I will use specific tags for important commits.

# Basic usage

## Eclipse/Maven

The Hermes project is developped under Eclipse but the whole compile/test/package process is managed through Maven. Any Maven-capable IDE/editor can thus be used in order to work on this project.

A specific choice has been made to distinguish the repository and the Eclipse project(s) (currently one, later possibly more). This come with a consequence : when importing the Eclipse projec(s), the file(s) at the root of this repository (including this one) will not be seen from Eclipse. Other choices could be made for other projects but this one let us keep things in a single place when it comes to getting the project.

## Build

In order to build Hermes (including dependencies, except the JRE), run :
```
mvn package
```

## Run

In order to run Hermes from the project's repository, run :
```
mvn javafx:run
```

When running Hermes from a build, on Windows you can use the provided bat file :
```
.\Hermes.bat
```

